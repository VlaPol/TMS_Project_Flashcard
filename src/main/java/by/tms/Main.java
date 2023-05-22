package by.tms;

import by.tms.repository.CardRepositoryImpl;
import by.tms.utils.DBConnection;

import javax.sql.DataSource;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        DataSource dataSource = DBConnection.getConnection();
        CardRepositoryImpl ri = new CardRepositoryImpl(dataSource);

        System.out.println("Список тем");
        ri.getTopics().stream().forEach(System.out::println);
        System.out.println("--------------------------------");
        System.out.println("Список тем с количеством выученных и общим количеством");
        ri.getTopicsWithCounts().stream().forEach(System.out::println);
        System.out.println("--------------------------------");
        System.out.println("id темы");
        Scanner scanner = new Scanner(System.in);
        Long topicId = Long.parseLong(scanner.nextLine());
        System.out.println("Список вопросов по теме");
        ri.getAllQuiz(topicId).stream().forEach(System.out::println);
        System.out.println("--------------------------------");
        System.out.println("Проверка знаний. Введите id темы");
        topicId = Long.parseLong(scanner.nextLine());
        System.out.println("Введите смещение");
        int offset = Integer.parseInt(scanner.nextLine());
        System.out.println("Список вопросов по теме");
        ri.getCardByIdAndOffset(topicId, offset).stream().forEach(System.out::println);
        System.out.println("--------------------------------");
        System.out.println("Введите новую тему");
        String topic = scanner.nextLine();
        ri.addNewTopic(topic);
        ri.getTopics().stream().forEach(System.out::println);
        System.out.println("--------------------------------");
        System.out.println("Введите id темы");
        topicId = Long.parseLong(scanner.nextLine());
        System.out.println("Введите вопрос");
        String question = scanner.nextLine();
        System.out.println("Введите ответ");
        String answer = scanner.nextLine();
        boolean isRemembered = false;
        ri.addNewQuiz(topicId, question, answer, isRemembered);
        ri.getAllQuiz(4L).stream().forEach(System.out::println);
        System.out.println("--------------------------------");
        System.out.println("Введите id темы для удаления");
        topicId = Long.parseLong(scanner.nextLine());
        ri.removeTopic(topicId);
        ri.getTopics().stream().forEach(System.out::println);
        System.out.println("--------------------------------");
        System.out.println("Введите id задания для удаления");
        Long quizId = Long.parseLong(scanner.nextLine());
        ri.removeQuiz(quizId);
        ri.getTopics().stream().forEach(System.out::println);
        System.out.println("--------------------------------");
        System.out.println("Введите id задания которое знаем");
        quizId = Long.parseLong(scanner.nextLine());
        ri.updateQuizIsRememberedToTrue(quizId);
        ri.getAllQuiz(3L).stream().forEach(System.out::println);
        System.out.println("--------------------------------");


    }
}