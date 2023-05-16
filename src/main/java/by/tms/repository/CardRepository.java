package by.tms.repository;

import by.tms.models.FullTopic;
import by.tms.models.Quiz;
import by.tms.models.Topic;

import java.util.List;

public interface CardRepository {

    List<Topic> getTopics();

    List<FullTopic> getTopicsWithCounts();

    List<Quiz> getAllQuiz(Long topicId);

    List<Quiz> checkKnowledge(Long topicId, int offset);

    void addNewTopic(String topic);

    void addNewQuiz(Long topic_id, String question, String answer, boolean isRemembered);

    void removeTopic(Long topicId);

    void removeQuiz(Long quizId);

    void updateQuizIsRemembered(Long quizId);
}
