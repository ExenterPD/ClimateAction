package com.ClimateActions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class QuestionInput extends JFrame {
    private JTextField questionField;
    private JButton submitButton;
    private JLabel questionCountLabel;
    private QuestionTracker questionTracker;

    public QuestionInput(int userId) {
        questionTracker = new QuestionTracker(userId);

        // Setup Frame
        setTitle("Question Input");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1));

        // UI Components
        questionField = new JTextField();
        submitButton = new JButton("Submit Question");
        questionCountLabel = new JLabel("Questions asked: 0");

        // Add components to the frame
        add(new JLabel("Enter your question:"));
        add(questionField);
        add(submitButton);
        add(questionCountLabel);

        // Load current question count
        try {
            updateQuestionCount();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Submit Button Action
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String questionText = questionField.getText().trim();
                if (questionText.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a question");
                    return;
                }

                try {
                    questionTracker.addQuestion(questionText);
                    updateQuestionCount();
                    questionField.setText("");

                    // Notify if 15 questions asked
                    if (questionTracker.getTotalQuestions() % 15 == 0) {
                        JOptionPane.showMessageDialog(null, "You've reached 15 questions!");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error submitting question");
                    ex.printStackTrace();
                }
            }
        });
    }

    private void updateQuestionCount() throws SQLException {
        int totalQuestions = questionTracker.getTotalQuestions();
        questionCountLabel.setText("Questions asked: " + totalQuestions);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new QuestionInput(1).setVisible(true);
        });
    }
}
