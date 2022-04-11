package dataFromJSON.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtils {

    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "root";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/practical?serverTimezone=UTC";

    public static Connection databaseConnection = createDatabaseConnection();

    private static Connection createDatabaseConnection() {

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return connection;

    }
}