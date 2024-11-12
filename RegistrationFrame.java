package com.ClimateActions;

import javax.swing.*;
import java.awt.*;

public class RegistrationFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField emailField;
    private DatabaseManager dbManager;

    public RegistrationFrame() {
        dbManager = new DatabaseManager();
        setTitle("Register");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2));

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        add(new JLabel("Email:"));
        emailField = new JTextField();
        add(emailField);

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(e -> register());

        add(new JLabel());  // empty placeholder
        add(registerButton);
    }

    private void register() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String email = emailField.getText();

        if (dbManager.registerUser(username, password, email)) {
            JOptionPane.showMessageDialog(this, "Registration successful!");
            new LoginFrame().setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Registration failed.");
        }
    }
}

