package Lab_7;

import java.util.List;
import java.util.ArrayList;

public class Instructor extends User {

    private List<String> createdCourses;

    public Instructor(String userId,String username,  String email, String passwordHash) {
        super(userId, username , email,passwordHash,"INSTRUCTOR");
        this.createdCourses = new ArrayList<>();
    }

    public List<String> getCreatedCourses() { return createdCourses; }
    
    public void addCreatedCourse(String courseId) {
        if (!createdCourses.contains(courseId)) {
            createdCourses.add(courseId);
        }
    }
    
    public void removeCreatedCourse(String courseId) {
        createdCourses.remove(courseId);
    }
   
    public String getDetailSummary() {
        return "Instructor: " + getEmail() + " (Courses Created: " + createdCourses.size() + ")";
    }
}