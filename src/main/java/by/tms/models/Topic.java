package by.tms.models;

public class Topic {

    private Long topicId;
    private String topicTitle;

    public Topic(Long topicId, String topicTitle){
        this.topicTitle = topicTitle;
        this.topicId = topicId;
    }
    public Topic(){}

    @Override
    public String toString() {
        return
                "[" + topicId + "] " + topicTitle;
    }
}
