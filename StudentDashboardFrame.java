package Lab_7;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StudentDashboardFrame extends JFrame {
    private final JsonDatabaseManager dbManager;
    private final Student currentUser;
    private final DefaultListModel<String> availableModel;
    private final DefaultListModel<String> enrolledModel;

    public StudentDashboardFrame(JsonDatabaseManager dbManager, Student user) {
        this.dbManager = dbManager;
        this.currentUser = user;
        this.availableModel = new DefaultListModel<>();
        this.enrolledModel = new DefaultListModel<>();

        setTitle("Student Dashboard - " + user.getEmail());
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(new JLabel("Welcome, Student " + user.getEmail()), BorderLayout.CENTER);
        JButton logoutBtn = new JButton("Logout");
        topPanel.add(logoutBtn, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        JList<String> availableList = new JList<>(availableModel);
        JList<String> enrolledList = new JList<>(enrolledModel);
        
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, 
                new JScrollPane(createTitledPanel("Available Courses", availableList)), 
                new JScrollPane(createTitledPanel("My Enrolled Courses", enrolledList)));
        splitPane.setDividerLocation(350);
        add(splitPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        JButton enrollBtn = new JButton("Enroll in Selected");
        JButton refreshBtn = new JButton("Refresh");
        bottomPanel.add(enrollBtn);
        bottomPanel.add(refreshBtn);
        add(bottomPanel, BorderLayout.SOUTH);

        refreshData();

        logoutBtn.addActionListener(e -> {
            new LoginFrame(dbManager).setVisible(true);
            this.dispose();
        });

        refreshBtn.addActionListener(e -> refreshData());

        enrollBtn.addActionListener(e -> {
            String selected = availableList.getSelectedValue();
            if (selected == null) return;

            String courseId = selected.substring(selected.lastIndexOf("ID: ") + 4, selected.length() - 1);
            
            currentUser.enrollCourse(courseId);
            dbManager.updateUser(currentUser); 
            
            List<Course> courses = dbManager.loadCourses();
            for(Course c : courses) {
                if(c.getCourseId().equals(courseId)) {
                    c.enrollStudent(currentUser.getId());
                    dbManager.updateCourse(c);
                    break;
                }
            }
            
            JOptionPane.showMessageDialog(this, "Enrolled Successfully!");
            refreshData();
        });
    }

    private JPanel createTitledPanel(String title, JComponent component) {
        JPanel p = new JPanel(new BorderLayout());
        p.setBorder(BorderFactory.createTitledBorder(title));
        p.add(component);
        return p;
    }

    private void refreshData() {
        availableModel.clear();
        enrolledModel.clear();
        List<Course> allCourses = dbManager.loadCourses();
        List<String> myCourses = currentUser.getEnrolledCourses();

        for (Course c : allCourses) {
            String display = c.getTitle() + " (ID: " + c.getCourseId() + ")";
            if (myCourses.contains(c.getCourseId())) {
                enrolledModel.addElement(display);
            } else {
                availableModel.addElement(display);
            }
        }
    }
}