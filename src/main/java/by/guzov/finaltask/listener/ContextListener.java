package by.guzov.finaltask.listener;

import by.guzov.finaltask.dao.connectionpool.ConnectionPool;
import by.guzov.finaltask.dao.connectionpool.ConnectionPoolImpl;
import by.guzov.finaltask.dao.ConnectionPoolException;
import by.guzov.finaltask.util.MailSenderService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class ContextListener implements ServletContextListener {
    private ConnectionPool pool;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        pool = ConnectionPoolImpl.getInstance();
        MailSenderService.getInstance();
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