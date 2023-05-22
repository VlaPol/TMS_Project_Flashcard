package by.tms.service;

import by.tms.models.FullTopic;
import by.tms.models.Quiz;
import by.tms.models.Topic;

import java.util.List;
import java.util.Optional;

public interface FlashCardService {

    Optional<String> getTopicTitleById(Long topicId);

    List<Topic> getTopics();

    List<FullTopic> getTopicsWithCounts();

    List<Quiz> getAllQuiz(Long topicId);

    Optional<Quiz> getCardByIdAndOffset(Long topicId, int offset);

    void addNewTopic(String topic);

    void addNewQuiz(Long topicId, String question, String answer, boolean isRemembered);

    void removeTopic(Long topicId);

    void removeQuiz(Long quizId);

    void updateQuizIsRememberedToTrue(Long quizId);
}
