package com.ziemo.fileReader;

import org.sqlite.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataConnection {

    private static Connection conn = null;
    private static String DB_URL = "jdbc:sqlite:/home/ziemo/codecool/1_java/Test/FilePartReader/src/main/resources/database.db";

    private DataConnection() {
    }

    public static Connection getConn() {
        if (conn == null) {
            synchronized (DataConnection.class) {
                if (conn == null) {
                    createConnection();
                }
            }
        }
        return conn;
    }

    private static void createConnection() {
        try {
            DriverManager.registerDriver(new JDBC());
            conn = DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            System.err.println("cannot connect with database");
        }

    }
}
