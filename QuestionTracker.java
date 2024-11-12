package com.ClimateActions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionTracker {
    private int userId;
    private Connection conn;

    public QuestionTracker(int userId) {
        this.userId = userId;
        this.conn = DatabaseManager.getConnection();
    }

    // Method to get total questions
    public int getTotalQuestions() throws SQLException {
        if (conn == null) {
            throw new SQLException("Database connection is null");
        }

        String query = "SELECT COUNT(*) FROM user_questions WHERE user_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching total questions");
            e.printStackTrace();
        }
        return 0;
    }

    // Method to insert a question
    public void addQuestion(String question) throws SQLException {
        if (conn == null) {
            throw new SQLException("Database connection is null");
        }

        String insertQuery = "INSERT INTO user_questions (user_id, question_text) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
            stmt.setInt(1, userId);
            stmt.setString(2, question);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Question added successfully");
            } else {
                System.out.println("Failed to add question");
            }
        } catch (SQLException e) {
            System.err.println("Error inserting question");
            e.printStackTrace();
        }
    }

    public void addQuestion() {
    }
}
