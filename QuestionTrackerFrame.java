package com.ClimateActions;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class QuestionTrackerFrame extends JFrame {
    private JLabel questionCountLabel;
    private QuestionTracker questionTracker;
    private DatabaseManager dbManager;

    public QuestionTrackerFrame(int userId) throws SQLException {
        dbManager = new DatabaseManager();
        questionTracker = new QuestionTracker(userId);

        setTitle("Question Tracker");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        questionCountLabel = new JLabel("Total Questions: " + questionTracker.getTotalQuestions(), SwingConstants.CENTER);
        add(questionCountLabel, BorderLayout.CENTER);

        JButton askQuestionButton = new JButton("Ask Question");
        askQuestionButton.addActionListener(e -> askQuestion());
        add(askQuestionButton, BorderLayout.SOUTH);
    }

    private void askQuestion() {
        try {
            questionTracker.addQuestion();
            int totalQuestions = questionTracker.getTotalQuestions();
            questionCountLabel.setText("Total Questions: " + totalQuestions);

            if (totalQuestions % 15 == 0) {
                JOptionPane.showMessageDialog(this, "Reminder: You've reached 15 questions! This cycle wastes 500ml of resources.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error asking question.");
        }
    }
}

