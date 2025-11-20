package Lab_7;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class SignupFrame extends JFrame {
    private final JsonDatabaseManager dbManager;

    public SignupFrame(JsonDatabaseManager dbManager) {
        this.dbManager = dbManager;

        setTitle("Sign Up");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2, 10, 10));

        JTextField userField = new JTextField();
        JTextField emailField = new JTextField();
        JPasswordField passField = new JPasswordField();
        
        String[] roles = {"Student", "Instructor"};
        JComboBox<String> roleBox = new JComboBox<>(roles);

        JButton registerButton = new JButton("Register");
        JButton backButton = new JButton("Back to Login");

        add(new JLabel("Username:")); add(userField);
        add(new JLabel("Email:")); add(emailField);
        add(new JLabel("Password:")); add(passField);
        add(new JLabel("Role:")); add(roleBox);
        add(registerButton); add(backButton);

        registerButton.addActionListener(e -> {
            String username = userField.getText();
            String email = emailField.getText();
            String password = new String(passField.getPassword());
            String role = (String) roleBox.getSelectedItem();

            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required.");
                return;
            }

            if (dbManager.findUserByEmail(email) != null) {
                JOptionPane.showMessageDialog(this, "Email already exists.");
                return;
            }

            String userId = UUID.randomUUID().toString(); 
            String passHash = PasswordHasher.hashPassword(password);

            User newUser;
            if ("Instructor".equals(role)) {
                newUser = new Instructor(userId, username, email, passHash);
            } else {
                newUser = new Student(userId, username, email, passHash, new ArrayList<>(), new HashMap<>());
            }

            dbManager.addUser(newUser);
            JOptionPane.showMessageDialog(this, "Registration Successful! Please Login.");
            new LoginFrame(dbManager).setVisible(true);
            this.dispose();
        });

        backButton.addActionListener(e -> {
            new LoginFrame(dbManager).setVisible(true);
            this.dispose();
        });
    }
}
