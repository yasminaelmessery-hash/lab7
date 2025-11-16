package Lab_7;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Student extends User {

    private List<String> enrolledCourses;
    private Map<String, Map<String, Boolean>> progress; 

    public Student(String userId, String username , String email, String passwordHash ,  List<String> enrolledCourses,
                           Map<String, Map<String, Boolean>> progress) 
    {
        super(userId, username ,email,passwordHash,"STUDENT"); 
        this.enrolledCourses = new ArrayList<>();
        this.progress = new HashMap<>();
    }

    public List<String> getEnrolledCourses() { return enrolledCourses; } 
    public Map<String, Map<String, Boolean>> getProgress() { return progress; }
    
    public void enrollCourse(String courseId) {
        if (!enrolledCourses.contains(courseId)) {
            enrolledCourses.add(courseId);
            progress.put(courseId, new HashMap<>()); 
        }
    }

    public void markLessonCompleted(String courseId, String lessonId) {
        if (progress.containsKey(courseId)) {
            progress.get(courseId).put(lessonId, true);
        } else {
            throw new IllegalArgumentException("Cannot mark lesson. Student is not enrolled in course: " + courseId);
        }
    }
    public String getDetailSummary() {
        return "Student: " + getEmail() + " (Enrolled in: " + enrolledCourses.size() + " courses)";
    }
}