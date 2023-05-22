package by.tms.utils;

import com.zaxxer.hikari.HikariConfig;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;

public class DBConnection {

    private DBConnection(){}
    public static HikariConfig getHikariConfig(){
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(System.getenv("PG_CONNECTION"));
        hikariConfig.setUsername(System.getenv("PG_USER"));
        hikariConfig.setPassword(System.getenv("PG_PASSWORD"));
        return hikariConfig;
    }


}
