package by.tms.models;

public class FullTopic {

    private Long topicId;
    private String topicTitle;
    private int total;
    private int learned;

    public FullTopic(Long topicId, String topicTitle, int learned, int total) {
        this.topicTitle = topicTitle;
        this.topicId = topicId;
        this.total = total;
        this.learned = learned;
    }

    public FullTopic() {
    }

    @Override
    public String toString() {
        return
                "[" + topicId + "] " + topicTitle + " " +
                        "всего вопросов - " + total +
                        "  выучено - " + learned;
    }
}
