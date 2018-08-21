package com.ziemo.fileReader;

import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.*;
import org.apache.log4j.jdbc.JDBCAppender;
import org.slf4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import static org.slf4j.LoggerFactory.getLogger;

@Slf4j
public class SimpleLogger {

    private Connection conn = DataConnection.getConn();
    private PreparedStatement preparedStatement;
//    private final Logger log = getLogger(SimpleLogger.class);; //  --> nie static - nie potrzebne dzieki lombok

    private int div() {
        try {
            return Math.floorDiv(1, 0);
        } catch (ArithmeticException e) {
            log.error("co to jest {} end", e.getMessage());    //--> logger no needed
        }
        return 0;
    }

    private void workingFoo(String s) {
        if (s.length() > 1) {
            log.info("correct string length end");
        }
    }

    private void createLogBase() {
        String command = "create table if not exists LOG(ID INTEGER PRIMARY KEY AUTOINCREMENT, TYPE TXT, MESSAGE TXT);";
        try {
            Statement statement = conn.createStatement();
            statement.execute(command);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * disable, replaced by log in-built method
     * @param log
     */
    private void addLog(String log) {
        String[] list = log.split(" ");
        String command = "insert into LOG(type, message) values(?,?);";
        try {
            preparedStatement = conn.prepareStatement(command);
            preparedStatement.setString(1, list[0]);
            preparedStatement.setString(2, list[1]);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) throws IOException {
        Layout lay1 = new PatternLayout("[%p] %c - %m - Data wpisu: %d %n");
        Appender app1 = new ConsoleAppender(lay1);
        BasicConfigurator.configure(app1);

        Layout lay2 = new PatternLayout("[%-5p] %c %m Data: %d%n");
        Appender app2 = new RollingFileAppender(lay2, "/home/ziemo/codecool/1_java/Test/FilePartReader/src/main/resources/logger2.log");
        BasicConfigurator.configure(app2);

        Appender app3 = new JDBCAppender();
        ((JDBCAppender) app3).setBufferSize(1);
        ((JDBCAppender) app3).setUser("ziemo");
        ((JDBCAppender) app3).setPassword("ziemo");
        ((JDBCAppender) app3).setDriver("org.sqlite.JDBC");
        ((JDBCAppender) app3).setURL("jdbc:sqlite:/home/ziemo/codecool/1_java/Test/FilePartReader/src/main/resources/database.db");
        ((JDBCAppender) app3).setSql("insert into LOG(type, message) values('%p', '%m');");
        BasicConfigurator.configure(app3);

        SimpleLogger s = new SimpleLogger();
        s.createLogBase();
        s.div();
        s.workingFoo("ziemo");

    }
}
