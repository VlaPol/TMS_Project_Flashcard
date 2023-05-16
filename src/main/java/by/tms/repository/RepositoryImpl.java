package by.tms.repository;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;

public class RepositoryImpl {

    private final DataSource dataSource;

    public RepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Optional<String> selectTopicTitleById(Long topicId) {

        String sql = " SELECT topic_title FROM topic WHERE topic_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1,topicId);

            ResultSet rs = statement.executeQuery();

            String result = null;
            if (rs.next()) {
                return Optional.of(rs.getString("TOPIC_TITLE"));
            }
            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
