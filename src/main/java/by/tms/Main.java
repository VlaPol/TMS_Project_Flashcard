package by.tms;

import by.tms.repository.CardRepository;
import by.tms.repository.CardRepositoryImpl;
import by.tms.service.CardService;
import by.tms.service.CardServiceImpl;
import by.tms.utils.DBConnection;
import com.zaxxer.hikari.HikariDataSource;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try (HikariDataSource hds = new HikariDataSource(DBConnection.getHikariConfig())) {
            CardRepository ri = new CardRepositoryImpl(hds);
            CardService ser = new CardServiceImpl(ri);

            System.out.println("Тема по id:");
            System.out.println(ser.getTopicTitleById(1L));
            System.out.println("--------------------------------");
            System.out.println("Список тем");
            ser.getTopics().stream().forEach(System.out::println);
            System.out.println("--------------------------------");
            System.out.println("Список тем с количеством выученных и общим количеством");
            ser.getTopicsWithCounts().stream().forEach(System.out::println);
            System.out.println("--------------------------------");
            System.out.println("id темы");
            Scanner scanner = new Scanner(System.in);
            Long topicId = Long.parseLong(scanner.nextLine());
            System.out.println("Список вопросов по теме");
            ser.getAllQuiz(topicId).stream().forEach(System.out::println);
            System.out.println("--------------------------------");
            System.out.println("Проверка знаний. Введите id темы");
            topicId = Long.parseLong(scanner.nextLine());
            System.out.println("Введите смещение");
            int offset = Integer.parseInt(scanner.nextLine());
            System.out.println("Список вопросов по теме");
            System.out.println(ser.getCardByIdAndOffset(topicId, offset));
            System.out.println("--------------------------------");
            System.out.println("Введите новую тему");
            String topic = scanner.nextLine();
            ser.addNewTopic(topic);
            ser.getTopics().stream().forEach(System.out::println);
            System.out.println("--------------------------------");
            System.out.println("Введите id темы");
            topicId = Long.parseLong(scanner.nextLine());
            System.out.println("Введите вопрос");
            String question = scanner.nextLine();
            System.out.println("Введите ответ");
            String answer = scanner.nextLine();
            boolean isRemembered = false;
            ser.addNewQuiz(topicId, question, answer, isRemembered);
            ser.getAllQuiz(4L).stream().forEach(System.out::println);
            System.out.println("--------------------------------");
            System.out.println("Введите id темы для удаления");
            topicId = Long.parseLong(scanner.nextLine());
            ser.removeTopic(topicId);
            ser.getTopics().stream().forEach(System.out::println);
            System.out.println("--------------------------------");
            System.out.println("Введите id задания для удаления");
            Long quizId = Long.parseLong(scanner.nextLine());
            ser.removeQuiz(quizId);
            ser.getTopics().stream().forEach(System.out::println);
            System.out.println("--------------------------------");
            System.out.println("Введите id задания которое знаем");
            quizId = Long.parseLong(scanner.nextLine());
            ser.updateQuizToTrue(quizId);
            ser.getAllQuiz(3L).stream().forEach(System.out::println);
            System.out.println("--------------------------------");
        }


    }
}