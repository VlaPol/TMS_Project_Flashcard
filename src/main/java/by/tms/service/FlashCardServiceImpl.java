package by.tms.service;

import by.tms.models.FullTopic;
import by.tms.models.Quiz;
import by.tms.models.Topic;

import java.util.List;
import java.util.Optional;

public class FlashCardServiceImpl implements FlashCardService {
    @Override
    public Optional<String> getTopicTitleById(Long topicId) {
        return Optional.empty();
    }

    @Override
    public List<Topic> getTopics() {
        return null;
    }

    @Override
    public List<FullTopic> getTopicsWithCounts() {
        return null;
    }

    @Override
    public List<Quiz> getAllQuiz(Long topicId) {
        return null;
    }

    @Override
    public Optional<Quiz> getCardByIdAndOffset(Long topicId, int offset) {
        return Optional.empty();
    }

    @Override
    public void addNewTopic(String topic) {

    }

    @Override
    public void addNewQuiz(Long topicId, String question, String answer, boolean isRemembered) {

    }

    @Override
    public void removeTopic(Long topicId) {

    }

    @Override
    public void removeQuiz(Long quizId) {

    }

    @Override
    public void updateQuizIsRememberedToTrue(Long quizId) {

    }
}
