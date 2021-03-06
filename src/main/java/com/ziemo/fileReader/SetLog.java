package com.ziemo.fileReader;

import org.apache.log4j.Appender;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.jdbc.JDBCAppender;

public class SetLog {

    private static SetLog log = null;
    private static final String DB_URL = "jdbc:sqlite:/home/ziemo/shit.db";

    private SetLog() {
        Appender appender3 = new JDBCAppender();
        ((JDBCAppender) appender3).setBufferSize(1);
        ((JDBCAppender) appender3).setUser("ziemo");
        ((JDBCAppender) appender3).setPassword("123");
        ((JDBCAppender) appender3).setDriver("org.sqlite.JDBC");
        ((JDBCAppender) appender3).setURL(DB_URL);
        ((JDBCAppender) appender3).setSql("insert into LOG(p,c,m,date) values('%p', '%c', '%m', '%d');");
        BasicConfigurator.configure(appender3);
    }

    public static SetLog getLog() {
        if (log == null) {
            synchronized (SetLog.class) {
                if (log == null) {
                    log = new SetLog();
                }
            }
        }
        return log;
    }
    
    public static void local() {
        System.out.println("method for start2");
    }

    public static void remote() {
        System.out.println("method for middle2");
    }

    public static void main(String[] args) {
        System.out.println("ending2");
    }

}
