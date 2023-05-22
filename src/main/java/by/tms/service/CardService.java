package by.tms.service;

import by.tms.models.FullTopic;
import by.tms.models.Quiz;
import by.tms.models.Topic;

import java.util.List;

public interface CardService {

    String getTopicTitleById(Long topicId);

    List<Topic> getTopics();

    List<FullTopic> getTopicsWithCounts();

    List<Quiz> getAllQuiz(Long topicId);

    Quiz getCardByIdAndOffset(Long topicId, int offset);

    void addNewTopic(String topic);

    void addNewQuiz(Long topicId, String question, String answer, boolean isRemembered);

    void removeTopic(Long topicId);

    void removeQuiz(Long quizId);

    void updateQuizToTrue(Long quizId);
}
