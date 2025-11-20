package Lab_7;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private final JsonDatabaseManager dbManager;

    public LoginFrame(JsonDatabaseManager dbManager) {
        this.dbManager = dbManager;

        setTitle("LMS Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        setLayout(new GridLayout(4, 2, 10, 10)); 

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField();
        
        JButton loginButton = new JButton("Login");
        JButton signupButton = new JButton("Sign Up");

        add(emailLabel);
        add(emailField);
        add(passLabel);
        add(passField);
        add(new JLabel()); 
        add(loginButton);
        add(signupButton);
        add(new JLabel()); 

        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            String rawPassword = new String(passField.getPassword());

            User user = dbManager.findUserByEmail(email);

            if (user != null && PasswordHasher.verifyPassword(rawPassword, user.getHashedPassword())) {
                JOptionPane.showMessageDialog(this, "Login Successful!");
                openDashboard(user);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid email or password.", "Login Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        signupButton.addActionListener(e -> {
            new SignupFrame(dbManager).setVisible(true);
            this.dispose(); 
        });
    }

    private void openDashboard(User user) {
        this.dispose(); 
        if (user instanceof Instructor) {
            new InstructorDashboardFrame(dbManager, (Instructor) user).setVisible(true);
        } else if (user instanceof Student) {
            new StudentDashboardFrame(dbManager, (Student) user).setVisible(true);
        }
    }
}
