package by.guzov.finaltask.dao.connectionpool;

import by.guzov.finaltask.dao.ConnectionPoolException;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Implementation of Connection Pool
 */
public class ConnectionPoolImpl implements ConnectionPool {
    private static ConnectionPool instance;
    private static Lock lock = new ReentrantLock();

    private static final String DRIVER_CLASS = "driver";
    private static final String DB_URL = "url";
    private static final String POOL_CAPACITY = "poolCapacity";

    private Properties dbProps;
    private String jdbcUrl;
    private Semaphore semaphore;
    private final Deque<Connection> connectionDeque = new ConcurrentLinkedDeque<>();
    private final List<Connection> allConnections = new ArrayList<>();

    private ConnectionPoolImpl() {
        dbProps = loadProperties("db.properties");
        this.jdbcUrl = dbProps.getProperty(DB_URL);
        initDriver(dbProps.getProperty(DRIVER_CLASS));
        semaphore = new Semaphore(Integer.parseInt(dbProps.getProperty(POOL_CAPACITY)));
    }

    private Properties loadProperties(String name) {
        try {
            Properties properties = new Properties();
            properties.load(getClass().getResourceAsStream("/" + name));
            return properties;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void initDriver(String driverClass) {
        try {
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Driver cannot be found", e);
        }
    }

    public static synchronized ConnectionPool getInstance() {
        lock.lock();
        try {
            if (instance == null) {
                instance = new ConnectionPoolImpl();
            }

        } finally {
            lock.unlock();
        }

        return instance;
    }

    @Override
    public Connection retrieveConnection() throws ConnectionPoolException {
        try {
            semaphore.acquire();
            if (connectionDeque.size() == 0) {
                return createConnection();
            } else {
                Connection saved = connectionDeque.pop();
                return saved.isClosed() ? createConnection() : saved;
            }
        } catch (InterruptedException | SQLException e) {
            throw new ConnectionPoolException(e);
        }
    }

    @Override
    public void putBackConnection(Connection connection) {
        connectionDeque.push(connection);
        semaphore.release();
    }

    @Override
    public void destroyPool() throws ConnectionPoolException {
        try {
            for (Connection connection : allConnections) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new ConnectionPoolException(e);
        }
    }

    private Connection createConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(jdbcUrl, dbProps);
        allConnections.add(connection);
        InvocationHandler connectionHandler = (Object proxy, Method method, Object[] args) -> {
            if (method.getName().equals("close")) {
                putBackConnection((Connection) proxy);
                return null;
            }
            return method.invoke(connection, args);
        };

        return (Connection) Proxy.newProxyInstance(connection.getClass().getClassLoader(),
                connection.getClass().getInterfaces(), connectionHandler);
    }
}
