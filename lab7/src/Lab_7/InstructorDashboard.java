package Lab_7;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class InstructorDashboard extends JFrame {
    private final Instructor instructor;
    private DefaultTableModel coursesTableModel;
    private DefaultListModel<String> lessonsListModel;
    private JTable coursesTable;
    private JList<String> lessonsList;
    
    private List<Course> courses;
    private List<Student> enrolledStudents;

    public InstructorDashboard(Instructor instructor) {
        this.instructor = instructor;
        this.courses = new ArrayList<>();
        this.enrolledStudents = new ArrayList<>();
        
        setTitle("Instructor Dashboard - " + instructor.getEmail());
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        initializeUI();
        loadSampleData();
    }

    private void initializeUI() {
        JTabbedPane tabbedPane = new JTabbedPane();
        
       
        tabbedPane.addTab("My Courses", createCoursesPanel());
        tabbedPane.addTab("Lessons", createLessonsPanel());
        tabbedPane.addTab("Enrolled Students", createStudentsPanel());
        
        add(tabbedPane);
    }

    private JPanel createCoursesPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        String[] columns = {"Course ID", "Course Name", "Description", "Students"};
        coursesTableModel = new DefaultTableModel(columns, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        
        coursesTable = new JTable(coursesTableModel);
        JScrollPane scrollPane = new JScrollPane(coursesTable);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(createButton("Add Course", e -> addCourse()));
        buttonPanel.add(createButton("Edit Course", e -> editCourse()));
        buttonPanel.add(createButton("Delete Course", e -> deleteCourse()));
        
        panel.add(buttonPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }

    private JPanel createLessonsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        
        lessonsListModel = new DefaultListModel<>();
        lessonsList = new JList<>(lessonsListModel);
        JScrollPane scrollPane = new JScrollPane(lessonsList);
        
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(createButton("Add Lesson", e -> addLesson()));
        buttonPanel.add(createButton("Edit Lesson", e -> editLesson()));
        buttonPanel.add(createButton("Delete Lesson", e -> deleteLesson()));
        
        panel.add(buttonPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }

    private JPanel createStudentsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        String[] columns = {"Student ID", "Name", "Email", "Courses Enrolled"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        
        JTable table = new JTable(model);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        
        return panel;
    }

    private JButton createButton(String text, java.awt.event.ActionListener listener) {
        JButton button = new JButton(text);
        button.addActionListener(listener);
        return button;
    }

    private void loadSampleData() {
        // Database integration - Uncomment when ready
        /*
        try {
            JsonDatabaseManager dbManager = new JsonDatabaseManager("courses.json");
            List<Course> dbCourses = dbManager.getInstructorCourses(instructor.getId());
            courses.clear();
            courses.addAll(dbCourses);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error loading courses: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        */
        
        // Sample data (remove when database is implemented)
        for (String courseId : instructor.getCreatedCourses()) {
            courses.add(new Course(courseId, "Course " + courseId, "Description for " + courseId));
        }
        updateCoursesTable();
        updateLessonsList();
    }

    private void updateCoursesTable() {
        coursesTableModel.setRowCount(0);
        for (Course course : courses) {
            coursesTableModel.addRow(new Object[]{
                course.getId(),
                course.getName(),
                course.getDescription(),
                course.getEnrolledStudents().size()
            });
        }
    }

    private void updateLessonsList() {
        lessonsListModel.clear();
        if (!courses.isEmpty()) {
            lessonsListModel.addElement("Introduction to " + courses.get(0).getName());
            lessonsListModel.addElement("Advanced Topics in " + courses.get(0).getName());
        }
    }

    private void addCourse() {
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextArea descArea = new JTextArea(3, 20);
        
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Course ID:"));
        panel.add(idField);
        panel.add(new JLabel("Course Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Description:"));
        panel.add(new JScrollPane(descArea));
        
        if (JOptionPane.showConfirmDialog(this, panel, "Add New Course",
                JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            
            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            String desc = descArea.getText().trim();
            
            if (!id.isEmpty() && !name.isEmpty()) {
                Course newCourse = new Course(id, name, desc);
                saveCourse(newCourse);
                instructor.addCreatedCourse(id);
                updateCoursesTable();
                JOptionPane.showMessageDialog(this, "Course added successfully!");
            }
        }
    }

    private void saveCourse(Course course) {
        // Database integration - Uncomment when ready
        /*
        try {
            JsonDatabaseManager dbManager = new JsonDatabaseManager("courses.json");
            dbManager.saveCourse(course);
            
            // Update the course in the local list
            boolean exists = false;
            for (int i = 0; i < courses.size(); i++) {
                if (courses.get(i).getId().equals(course.getId())) {
                    courses.set(i, course);
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                courses.add(course);
            }
            
            updateCoursesTable();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error saving course: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        */
        
        // Temporary implementation (remove when database is ready)
        boolean exists = false;
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId().equals(course.getId())) {
                courses.set(i, course);
                exists = true;
                break;
            }
        }
        if (!exists) {
            courses.add(course);
        }
        updateCoursesTable();
    }

    private void editCourse() {
        int selectedRow = coursesTable.getSelectedRow();
        if (selectedRow >= 0) {
            String courseId = (String) coursesTableModel.getValueAt(selectedRow, 0);
            Course course = findCourseById(courseId);
            
            if (course != null) {
                JTextField nameField = new JTextField(course.getName());
                JTextArea descArea = new JTextArea(course.getDescription(), 3, 20);
                
                JPanel panel = new JPanel(new GridLayout(0, 1));
                panel.add(new JLabel("Course Name:"));
                panel.add(nameField);
                panel.add(new JLabel("Description:"));
                panel.add(new JScrollPane(descArea));
                
                if (JOptionPane.showConfirmDialog(this, panel, "Edit Course",
                        JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                    
                    String newName = nameField.getText().trim();
                    String newDesc = descArea.getText().trim();
                    
                    if (!newName.isEmpty()) {
                        course.setName(newName);
                        course.setDescription(newDesc);
                        saveCourse(course);
                        updateCoursesTable();
                        JOptionPane.showMessageDialog(this, "Course updated successfully!");
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a course to edit.");
        }
    }

    private void deleteCourse() {
        int selectedRow = coursesTable.getSelectedRow();
        if (selectedRow >= 0) {
            String courseId = (String) coursesTableModel.getValueAt(selectedRow, 0);
            
            if (JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete this course?",
                    "Confirm Delete", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                
                // Database integration - Uncomment when ready
                /*
                try {
                    JsonDatabaseManager dbManager = new JsonDatabaseManager("courses.json");
                    if (dbManager.deleteCourse(courseId)) {
                        courses.removeIf(c -> c.getId().equals(courseId));
                        updateCoursesTable();
                        JOptionPane.showMessageDialog(this, "Course deleted successfully!");
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this,
                        "Error deleting course: " + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                }
                */
                
                // Temporary implementation (remove when database is ready)
                courses.removeIf(c -> c.getId().equals(courseId));
                updateCoursesTable();
                JOptionPane.showMessageDialog(this, "Course deleted successfully!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a course to delete.");
        }
    }

    private void addLesson() {
        String title = JOptionPane.showInputDialog(this, "Enter lesson title:");
        if (title != null && !title.trim().isEmpty()) {
            lessonsListModel.addElement(title);
            JOptionPane.showMessageDialog(this, "Lesson added successfully!");
        }
    }

    private void editLesson() {
        int selectedIndex = lessonsList.getSelectedIndex();
        if (selectedIndex >= 0) {
            String currentTitle = lessonsListModel.getElementAt(selectedIndex);
            String newTitle = JOptionPane.showInputDialog(this, "Edit lesson title:", currentTitle);
                
            if (newTitle != null && !newTitle.trim().isEmpty()) {
                lessonsListModel.set(selectedIndex, newTitle);
                JOptionPane.showMessageDialog(this, "Lesson updated successfully!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a lesson to edit.");
        }
    }

    private void deleteLesson() {
        int selectedIndex = lessonsList.getSelectedIndex();
        if (selectedIndex >= 0) {
            if (JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete this lesson?",
                    "Confirm Delete", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                
                lessonsListModel.remove(selectedIndex);
                JOptionPane.showMessageDialog(this, "Lesson deleted successfully!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a lesson to delete.");
        }
    }

    private Course findCourseById(String courseId) {
        return courses.stream()
            .filter(c -> c.getId().equals(courseId))
            .findFirst()
            .orElse(null);
    }

    
    private static class Course {
        private final String id;
        private String name;
        private String description;
        private final List<Student> enrolledStudents = new ArrayList<>();

        public Course(String id, String name, String description) {
            this.id = id;
            this.name = name;
            this.description = description;
        }

        
        public String getId() { return id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public List<Student> getEnrolledStudents() { return enrolledStudents; }
    }

    private static class Student {
        private final String id;
        private final String name;
        private final String email;

        public Student(String id, String name, String email) {
            this.id = id;
            this.name = name;
            this.email = email;
        }

        public String getId() { return id; }
        public String getName() { return name; }
        public String getEmail() { return email; }
    }
}
