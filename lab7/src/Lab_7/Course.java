package Lab_7;

import java.util.List;
import java.util.ArrayList;

public class Course {
    private final String courseId;
    private final String instructorId;
    private String title;
    private String description;
    private List<Lesson> lessons;
    private List<String> enrolledStudentIds; 

    public Course(String courseId, String instructorId, String title, String description) {
        this.courseId = courseId;
        this.instructorId = instructorId;
        this.title = title;
        this.description = description;
        this.lessons = new ArrayList<>();
        this.enrolledStudentIds = new ArrayList<>();
    }
    public String getCourseId() { return courseId; }
    public String getInstructorId() { return instructorId; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public List<Lesson> getLessons() { return lessons; }
    public List<String> getEnrolledStudentIds() { return enrolledStudentIds; }
    
    
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    
    public void addLesson(Lesson lesson) {
        this.lessons.add(lesson);
    }
    public void removeLesson(String lessonId) {
        this.lessons.removeIf(l -> l.getLessonId().equals(lessonId));
    }
    public void enrollStudent(String studentId) {
        if (!enrolledStudentIds.contains(studentId)) {
            enrolledStudentIds.add(studentId);
        }
    }
    public void unenrollStudent(String studentId) {
        enrolledStudentIds.remove(studentId);
    }
}