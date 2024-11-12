package com.ClimateActions;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private DatabaseManager dbManager;

    public LoginFrame() {
        dbManager = new DatabaseManager();
        setTitle("Login");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2));

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");

        loginButton.addActionListener(e -> {
            try {
                login();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        registerButton.addActionListener(e -> openRegistration());

        add(loginButton);
        add(registerButton);
    }

    private void login() throws SQLException {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        int userId = dbManager.loginUser(username, password);
        if (userId != -1) {
            JOptionPane.showMessageDialog(this, "Login successful!");
            QuestionTrackerFrame trackerFrame = new QuestionTrackerFrame(userId);
            trackerFrame.setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.");
        }
    }

    private void openRegistration() {
        RegistrationFrame regFrame = new RegistrationFrame();
        regFrame.setVisible(true);
        this.dispose();
    }
}
