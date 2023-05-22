package by.tms.utils;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;

public class DBConnection {

    private DBConnection(){};

    public static DataSource getConnection() {
        PGSimpleDataSource connection = new PGSimpleDataSource();
        connection.setUrl(System.getenv("PG_CONNECTION"));
        connection.setUser(System.getenv("PG_USER"));
        connection.setPassword(System.getenv("PG_PASSWORD"));
        return connection;
    }

}
