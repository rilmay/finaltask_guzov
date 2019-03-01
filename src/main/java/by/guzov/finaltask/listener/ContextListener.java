package by.guzov.finaltask.listener;

import by.guzov.finaltask.dao.connectionpool.ConnectionPool;
import by.guzov.finaltask.dao.connectionpool.ConnectionPoolImpl;
import by.guzov.finaltask.dao.exception.ConnectionPoolException;
import by.guzov.finaltask.util.MailBot;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class ContextListener implements ServletContextListener {
    private ConnectionPool pool;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        pool = ConnectionPoolImpl.getInstance();
        MailBot.getInstance();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            pool.destroyPool();
        } catch (ConnectionPoolException e) {
            throw new RuntimeException(e);
        }
    }
}