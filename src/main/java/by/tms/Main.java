package by.tms;

import by.tms.repository.RepositoryImpl;
import by.tms.utils.DBConnection;

import javax.sql.DataSource;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        //System.out.println("Hello world");
        DataSource connection = DBConnection.getConnection();
        RepositoryImpl ri = new RepositoryImpl(connection);
        Optional<String> topicTitleById = ri.selectTopicTitleById(5L);
        topicTitleById.ifPresent(System.out::println);

    }
}