package by.tms.models;

public class Quiz {

    private Long quizId;
    private String question;
    private String answer;
    private boolean isRemembered;

    public Quiz(Long quizId, String question, String answer, boolean isRemembered) {
        this.quizId = quizId;
        this.question = question;
        this.answer = answer;
        this.isRemembered = isRemembered;
    }

    public Quiz() {
    }

    @Override
    public String toString() {
        return "quizId=" + quizId +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", isRemembered=" + isRemembered;
    }
}
