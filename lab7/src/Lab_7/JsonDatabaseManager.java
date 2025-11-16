package Lab_7;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonDatabaseManager {

    public static final String USERS_FILENAME = "users.json";
    public static final String COURSES_FILENAME = "courses.json";

    private final Gson gson;

    public JsonDatabaseManager() {
       
        gson = new GsonBuilder().setPrettyPrinting().create();
        initializeDatabaseFiles();
    }

    public List<User> loadUsers() {
        Path path = Paths.get(USERS_FILENAME);
        if (!Files.exists(path))
            return new ArrayList<>();

        try {
            String json = Files.readString(path);

            List<TempUser> tempUsers = gson.fromJson(
                    json, new TypeToken<List<TempUser>>() {}.getType()
            );

            List<User> finalUsers = new ArrayList<>();

            for (int j = 0; j < tempUsers.size(); j++) {
                TempUser t = tempUsers.get(j);

                if ("student".equalsIgnoreCase(t.role)) {
                    Student s = new Student(
                            t.userId,
                            t.username,
                            t.email,
                            t.passwordHash,
                            t.enrolledCourses,
                            t.progress
                    );
                    finalUsers.add(s);
                }

                else if ("instructor".equalsIgnoreCase(t.role)) {
                    Instructor i = new Instructor(
                            t.userId,
                            t.username,
                            t.email,
                            t.passwordHash
                    );
                    finalUsers.add(i);
                }
            }
            return finalUsers;

        } catch (Exception e) {
            System.err.println("Error loading users: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void saveUsers(List<User> users) {
        try (FileWriter writer = new FileWriter(USERS_FILENAME)) {
            gson.toJson(users, writer);
        } catch (IOException e) {
            System.err.println("Error saving users: " + e.getMessage());
        }
    }

public void addUser(User user) {
    List <User> users = loadUsers();
    
    for (int i = 0; i < users.size(); i++) {
        User u = users.get(i);
       
        if (u.getId() == user.getId()) { 
            System.out.println("ERROR: Duplicate userId detected: " + user.getId());
            return;
        }
    }
   
    users.add(user);
    saveUsers(users); 
}
    
public void updateUser(User updated) {
    List<User> list = loadUsers();

    for (int i = 0; i < list.size(); i++) {
        if (list.get(i).getId() == updated.getId()) { 
            list.set(i, updated);
            saveUsers(list);
            return;
        }
    }
   
    System.out.println("ERROR: User with ID " + updated.getId() + " not found for update."); 
}

    public User findUserByEmail(String email) {
        List<User> users = loadUsers();
      
        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);
            if (u.getEmail().equalsIgnoreCase(email)) {
                return u;
            }
        }
        return null;
    }


    public List<Course> loadCourses() {
        Path path = Paths.get(COURSES_FILENAME);
        if (!Files.exists(path)) return new ArrayList<>();

        try {
            String json = Files.readString(path);
            return gson.fromJson(
                    json, new TypeToken<List<Course>>() {}.getType()
            );
        } catch (Exception e) {
            System.err.println("Error loading courses: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void saveCourses(List<Course> courses) {
        try (FileWriter writer = new FileWriter(COURSES_FILENAME)) {
            gson.toJson(courses, writer);
        } catch (IOException e) {
            System.err.println("Error saving courses: " + e.getMessage());
        }
    }

    public String findCourseById(String id) {
        List<Course> courses = loadCourses();

        for (int i = 0; i < courses.size(); i++) {
            Course c = courses.get(i);
            if (c.getCourseId().equals(id)) 
                return c.getTitle();
        }
        return null;
    }

    public boolean updateCourse(Course updated)
    {
        List<Course> list = loadCourses();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getCourseId().equals(updated.getCourseId())) { 
                list.set(i, updated);
                saveCourses(list);
                return true;
            }
        }
        System.out.println("Error");
        return false;
    }
    
    private void initializeDatabaseFiles() {
        createIfMissing(USERS_FILENAME);
        createIfMissing(COURSES_FILENAME);
    }

    private void createIfMissing(String filename) {
        Path path = Paths.get(filename);
        if (!Files.exists(path)) {
            try {
           
                Files.writeString(path, "[]");
            } catch (IOException ignored) {
              
                System.err.println("WARNING: Could not create " + filename);
            }
        }
    }

private static class TempUser {
    String userId;
    String role;
    String username;
    String email;
    String passwordHash;

    List<String> enrolledCourses; 
    Map<String, Map<String, Boolean>> progress; 

    List<String> createdCourses;
}

}