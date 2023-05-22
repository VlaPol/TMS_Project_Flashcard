package by.tms.utils;

import com.zaxxer.hikari.HikariConfig;

public class DBConnection {

    private static final HikariConfig CONFIG = new HikariConfig();

    private DBConnection(){}
    public static HikariConfig getHikariConfig(){
        CONFIG.setJdbcUrl(System.getenv("PG_CONNECTION"));
        CONFIG.setUsername(System.getenv("PG_USER"));
        CONFIG.setPassword(System.getenv("PG_PASSWORD"));
        return CONFIG;
    }


}
