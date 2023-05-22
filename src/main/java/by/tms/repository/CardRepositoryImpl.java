package by.tms.repository;

import by.tms.models.FullTopic;
import by.tms.models.Quiz;
import by.tms.models.Topic;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CardRepositoryImpl implements CardRepository {

    private final DataSource dataSource;

    public CardRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Получение тем по id
     *
     * @return Optional<String>
     */
    @Override
    public Optional<String> getTopicTitleById(Long topicId) {

        String sql = " SELECT topic_title FROM topic WHERE topic_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, topicId);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return Optional.of(rs.getString("TOPIC_TITLE"));
            }
            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    /**
     * Получение всего списка тем
     *
     * @return List<Topic>
     */
    @Override
    public List<Topic> getTopics() {

        String sql = """
                    SELECT t.topic_id    AS id,
                           t.topic_title AS title
                    FROM topic as t;
                """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet rs = statement.executeQuery();

            List<Topic> topicsList = new ArrayList<>();
            while (rs.next()) {
                topicsList.add(new Topic(rs.getLong("id"), rs.getString("title")));
            }
            return topicsList;

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public List<FullTopic> getTopicsWithCounts() {
        String sql = """
                    SELECT t.topic_id                                           AS id,
                           t.topic_title                                        AS title,
                           count(q.topic_id) FILTER ( WHERE q.is_remembered )   AS learned_count,
                           count(q.topic_id)                                    AS total_count
                    FROM topic t
                                LEFT JOIN quiz as q ON t.topic_id = q.topic_id
                    GROUP BY t.topic_id;
                """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet rs = statement.executeQuery();

            List<FullTopic> topicsList = new ArrayList<>();
            while (rs.next()) {
                topicsList.add(new FullTopic(rs.getLong("id"),
                        rs.getString("title"),
                        rs.getInt("learned_count"),
                        rs.getInt("total_count")));
            }
            return topicsList;

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public List<Quiz> getAllQuiz(Long topicId) {
        String sql = """
                    SELECT q.quiz_id       AS id,
                           q.question      AS question,
                           q.answer        AS answer,
                           q.is_remembered AS remembered
                    FROM quiz q
                    WHERE q.topic_id = ?
                """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, topicId);

            ResultSet rs = statement.executeQuery();

            List<Quiz> topicsList = new ArrayList<>();
            while (rs.next()) {
                topicsList.add(new Quiz(rs.getLong("id"),
                        rs.getString("question"),
                        rs.getString("answer"),
                        rs.getBoolean("remembered")));
            }
            return topicsList;

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public Optional<Quiz> getCardByIdAndOffset(Long topicId, int offset) {
        String sql = """
                    SELECT q.quiz_id        AS id,
                           q.question       AS question,
                           q.answer         AS answer,
                           q.is_remembered  AS remembered
                    FROM quiz q
                    WHERE q.quiz_id = ?
                      AND NOT q.is_remembered
                    ORDER BY q.quiz_id
                    LIMIT 1 OFFSET ?
                """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, topicId);
            statement.setLong(2, offset);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return Optional.of(new Quiz(rs.getLong("id"),
                        rs.getString("question"),
                        rs.getString("answer"),
                        rs.getBoolean("remembered")));
            } else {
                return Optional.empty();
            }

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void addNewTopic(String topic) {
        String sql = """
                INSERT INTO topic (topic_title)
                VALUES (?)
                """;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, topic);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void addNewQuiz(Long topicId, String question, String answer, boolean isRemembered) {
        String sql = """
                INSERT INTO quiz (topic_id, question, answer, is_remembered)
                VALUES (?, ?, ?, ?)
                """;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, topicId);
            statement.setString(2, question);
            statement.setString(3, answer);
            statement.setBoolean(4, isRemembered);

            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void removeTopic(Long topicId) {
        String sql = """
                DELETE
                FROM topic t
                WHERE t.topic_id = ?;
                """;
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setLong(1, topicId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeQuiz(Long quizId) {
        String sql = """
                DELETE
                FROM quiz q
                WHERE q.quiz_id = ?;
                """;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, quizId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateQuizIsRememberedToTrue(Long quizId) {
        String sql = """
                UPDATE quiz q
                SET is_remembered = TRUE
                WHERE q.quiz_id = ?;
                """;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, quizId);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
