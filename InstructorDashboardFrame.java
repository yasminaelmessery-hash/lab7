package Lab_7;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.UUID;

public class InstructorDashboardFrame extends JFrame {
    private final JsonDatabaseManager dbManager;
    private final Instructor currentUser;
    private final DefaultListModel<String> courseListModel;

    public InstructorDashboardFrame(JsonDatabaseManager dbManager, Instructor user) {
        this.dbManager = dbManager;
        this.currentUser = user;
        this.courseListModel = new DefaultListModel<>();

        setTitle("Instructor Dashboard - " + user.getEmail());
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(new JLabel("Welcome, Instructor " + user.getEmail()), BorderLayout.CENTER);
        JButton logoutBtn = new JButton("Logout");
        topPanel.add(logoutBtn, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        JList<String> courseList = new JList<>(courseListModel);
        refreshCourseList();
        add(new JScrollPane(courseList), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        JButton createCourseBtn = new JButton("Create New Course");
        bottomPanel.add(createCourseBtn);
        add(bottomPanel, BorderLayout.SOUTH);

        logoutBtn.addActionListener(e -> {
            new LoginFrame(dbManager).setVisible(true);
            this.dispose();
        });

        createCourseBtn.addActionListener(e -> showCreateCourseDialog());
    }

    private void refreshCourseList() {
        courseListModel.clear();
        List<Course> allCourses = dbManager.loadCourses();
        for (Course c : allCourses) {
            if (c.getInstructorId().equals(currentUser.getId())) {
                courseListModel.addElement(c.getTitle() + " (ID: " + c.getCourseId() + ")");
            }
        }
    }

    private void showCreateCourseDialog() {
        JTextField titleField = new JTextField();
        JTextField descField = new JTextField();
        
        Object[] message = {
            "Course Title:", titleField,
            "Description:", descField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Create Course", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String title = titleField.getText();
            String desc = descField.getText();
            
            if (!title.isEmpty()) {
                String newId = UUID.randomUUID().toString();
                
                Course newCourse = new Course(newId, currentUser.getId(), title, desc);
                List<Course> courses = dbManager.loadCourses();
                courses.add(newCourse);
                dbManager.saveCourses(courses);
                currentUser.addCreatedCourse(newId);
                dbManager.updateUser(currentUser); 
                refreshCourseList();
            }
        }
    }
}
