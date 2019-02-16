package guzov.finaltask.dao.impl;

import by.guzov.finaltask.dao.connectionpool.ConnectionPool;
import by.guzov.finaltask.dao.connectionpool.ConnectionPoolImpl;
import by.guzov.finaltask.dao.exception.ConnectionPoolException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Collectors;

public class BdInit {
    private static volatile boolean created = false;

    public static void bdInit() {
        try {
            System.out.println(Thread.currentThread().getName());
            if (!created) {
                System.out.println("i did it");
                created = true;
                ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();
                Connection connection = connectionPool.retrieveConnection();
                String create = Files
                        .readAllLines(Paths.get("src/main/resources/create_script.sql"), StandardCharsets.UTF_8)
                        .stream().collect(Collectors.joining());

                Statement statement = connection.createStatement();
                statement.execute(create);
                statement.close();
            }
        } catch (IOException | SQLException | ConnectionPoolException e) {
            throw new RuntimeException(e);
        }
    }
}
