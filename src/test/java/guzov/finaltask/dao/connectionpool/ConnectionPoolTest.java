package guzov.finaltask.dao.connectionpool;

import by.guzov.finaltask.dao.connectionpool.ConnectionPool;
import by.guzov.finaltask.dao.connectionpool.ConnectionPoolImpl;
import by.guzov.finaltask.dao.ConnectionPoolException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@RunWith(JUnit4.class)
public class ConnectionPoolTest {
    private Properties props;
    private static final int N_THREADS = 30;

    @Before
    public void init() throws Exception {
        props = new Properties();
        props.load(getClass().getResourceAsStream("/db.properties"));
    }

    @Test
    public void getConnectionTest() throws Exception {
        ConnectionPool connectionPool = Mockito.spy(ConnectionPoolImpl.getInstance());
        ExecutorService executorService = Executors.newFixedThreadPool(N_THREADS);
        Set<Integer> hashCodes = Collections.synchronizedSet(new HashSet<>());
        IntStream.range(0, N_THREADS).forEach(i -> executorService.submit(() -> {
            try (Connection connection = connectionPool.retrieveConnection()) {
                Thread.sleep(1_00L);
                Assert.assertTrue(connection instanceof Proxy);
                int hashCode = connection.hashCode();
                hashCodes.add(hashCode);
            } catch (SQLException | IllegalStateException | ConnectionPoolException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }));
        executorService.awaitTermination(5L, TimeUnit.SECONDS);
        Assert.assertEquals(Integer.parseInt(props.getProperty("poolCapacity")), hashCodes.size());
        Mockito.verify((connectionPool),
                Mockito.times(N_THREADS)).putBackConnection(Mockito.any());
    }
}
