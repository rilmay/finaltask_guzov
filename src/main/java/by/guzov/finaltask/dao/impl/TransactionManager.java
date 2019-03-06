package by.guzov.finaltask.dao.impl;

import by.guzov.finaltask.dao.AbstractJdbcDao;
import by.guzov.finaltask.dao.ConnectionPoolException;
import by.guzov.finaltask.dao.DaoException;
import by.guzov.finaltask.dao.GenericDao;
import by.guzov.finaltask.dao.connectionpool.ConnectionPool;
import by.guzov.finaltask.dao.connectionpool.ConnectionPoolFactory;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Implementation of transaction with DAO
 */
public final class TransactionManager {
    private Connection proxyConnection;

    public void begin(GenericDao dao, GenericDao... daos) throws DaoException {

        ConnectionPool connectionPool = ConnectionPoolFactory.getInstance().getConnectionPool();

        try {
            proxyConnection = connectionPool.retrieveConnection();
            proxyConnection.setAutoCommit(false);
            setConnectionWithReflection(dao, proxyConnection);
            for (GenericDao d : daos) {
                setConnectionWithReflection(d, proxyConnection);
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Failed to get a connection from CP.", e);
        }
    }

    public void end() throws SQLException {
        proxyConnection.setAutoCommit(true);
        proxyConnection.close();
    }

    public void commit() throws SQLException {
        proxyConnection.commit();
    }

    public void rollback() throws SQLException {
        proxyConnection.rollback();
    }


    static void setConnectionWithReflection(Object dao, Connection connection) throws DaoException {
        if (!(dao instanceof AbstractJdbcDao)) {
            throw new DaoException("DAO implementation does not extend AbstractJdbcDao.");
        }

        try {

            Field connectionField = AbstractJdbcDao.class.getDeclaredField("connection");
            if (!connectionField.isAccessible()) {
                connectionField.setAccessible(true);
            }

            connectionField.set(dao, connection);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new DaoException("Failed to set connection for transactional DAO. ", e);
        }
    }

}
