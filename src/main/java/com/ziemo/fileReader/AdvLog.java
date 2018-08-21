package com.ziemo.fileReader;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Data
@Slf4j
public class AdvLog {

//    protected Logger log = getLogger(getClass());
    String name;
    int age;
    SetLog logConnection;

    public AdvLog(String name, int age) throws IOException {
        this.name = name;
        this.age = age;
        logConnection.getLog();
        log.error("Object has been set correctly");
    }

    public int strangeSum() {
        int sum = name.length() + age;
        log.debug("sum was set correctly");
        return sum;
    }

//    private void setLog() throws IOException {
//        Layout layout1 = new PatternLayout("[%p] %c %m date:%d%n");
//        Appender appender1 = new ConsoleAppender(layout1);
//        BasicConfigurator.configure(appender1);
//
//        Appender appender2 = new RollingFileAppender(layout1, "/home/ziemo/gowno.log");
//        BasicConfigurator.configure(appender2);

//        Appender appender3 = new JDBCAppender();
//        ((JDBCAppender) appender3).setBufferSize(1);
//        ((JDBCAppender) appender3).setUser("ziemo");
//        ((JDBCAppender) appender3).setPassword("123");
//        ((JDBCAppender) appender3).setDriver("org.sqlite.JDBC");
//        ((JDBCAppender) appender3).setURL("jdbc:sqlite:/home/ziemo/shit.db");
//        ((JDBCAppender) appender3).setSql("insert into LOG(p,c,m,date) values('%p', '%c', '%m', '%d');");
//        BasicConfigurator.configure(appender3);
//    }

    public static void main(String[] args) {
        AdvLog advLog = null;
        try {
            advLog = new AdvLog("ziemo", 32);
        } catch (IOException e) {
            e.printStackTrace();
        }
        advLog.strangeSum();
    }

}
