package by.tms.service;

import by.tms.models.FullTopic;
import by.tms.models.Quiz;
import by.tms.models.Topic;
import by.tms.repository.CardRepository;

import java.util.ArrayList;
import java.util.List;

public class CardServiceImpl implements CardService {

    CardRepository repo;

    public CardServiceImpl(CardRepository repo) {
        this.repo = repo;
    }

    @Override
    public String getTopicTitleById(Long topicId) throws RuntimeException {

        if (repo.getTopicTitleById(topicId).isPresent()) {
            return repo.getTopicTitleById(topicId).get();
        } else {
            throw new RuntimeException();
        }

    }

    @Override
    public List<Topic> getTopics() {
        List<Topic> topicsList = repo.getTopics();
        if (!topicsList.isEmpty()) {
            return topicsList;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<FullTopic> getTopicsWithCounts() {
        List<FullTopic> topicsList = repo.getTopicsWithCounts();
        if (!topicsList.isEmpty()) {
            return topicsList;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Quiz> getAllQuiz(Long topicId) {
        List<Quiz> quizList = repo.getAllQuiz(topicId);
        if (!quizList.isEmpty()) {
            return quizList;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public Quiz getCardByIdAndOffset(Long topicId, int offset) {
        if (repo.getCardByIdAndOffset(topicId, offset).isPresent()) {
            return repo.getCardByIdAndOffset(topicId, offset).get();
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public void addNewTopic(String topic) {
        if (!topic.isEmpty()) {
            repo.addNewTopic(topic);
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public void addNewQuiz(Long topicId, String question, String answer, boolean isRemembered) {
        if (!question.isEmpty() && !answer.isEmpty()) {
            repo.addNewQuiz(topicId, question, answer, isRemembered);
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public void removeTopic(Long topicId) {
        repo.removeTopic(topicId);
    }

    @Override
    public void removeQuiz(Long quizId) {
        repo.removeQuiz(quizId);
    }

    @Override
    public void updateQuizToTrue(Long quizId) {
        repo.updateQuizIsRememberedToTrue(quizId);
    }
}
