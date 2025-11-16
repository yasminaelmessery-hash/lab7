package Lab_7;

import java.util.List;
import java.util.ArrayList;

public class Lesson {
    private final String lessonId;
    private String title;
    private String content;
    private List<String> resources;

    public Lesson(String lessonId, String title, String content) {
        this.lessonId = lessonId;
        this.title = title;
        this.content = content;
        this.resources = new ArrayList<>();
    }
    public String getLessonId() { return lessonId; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public List<String> getResources() { return resources; }
    public void setTitle(String title) { this.title = title; }
    public void setContent(String content) { this.content = content; }
    public void addResource(String resourceUrl) {
        this.resources.add(resourceUrl);
    }
    public void removeResource(String resourceUrl) {
        this.resources.remove(resourceUrl);
    }
}