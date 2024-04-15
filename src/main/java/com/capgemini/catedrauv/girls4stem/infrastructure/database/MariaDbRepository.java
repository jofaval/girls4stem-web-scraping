package com.capgemini.catedrauv.girls4stem.infrastructure.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MariaDbRepository {
    private Connection connection;

    public MariaDbRepository() {
        connect();
    }

    private String getUrl() {
        return "jdbc:mariadb://" + System.getProperty("DB_URL") + ":" + System.getProperty("DB_PORT") + "/"
                + System.getProperty("DATABASE");
    }

    private Connection connect() {
        try {
            Class.forName(System.getProperty("JDBC_DRIVER"));
            connection = DriverManager.getConnection(getUrl(), System.getProperty("USER"), System.getProperty("PASS"));
        } catch (Exception e) {
            System.err.println("No se ha podido conectar a la base de datos");
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        return connection;
    }

    public int insert(String table, String values) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeUpdate("INSERT INTO " + table + " VALUES (" + values + ")");
    }

    public int insertMany(String table, String[] rows) throws SQLException {
        int total = 0;

        for (String values : rows) {
            total += insert(table, values);
        }

        return total;
    }
}
