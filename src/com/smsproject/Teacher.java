package com.smsproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Teacher {

    public void addTeacher(String teacherName) throws SQLException {
        String sql = "INSERT INTO teachers (teacher_name) VALUES (?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, teacherName);
            stmt.executeUpdate();
        }
    }

    public void listTeachers() throws SQLException {
        String sql = "SELECT * FROM teachers";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("Teacher ID: " + rs.getInt("teacher_id"));
                System.out.println("Teacher Name: " + rs.getString("teacher_name"));
                System.out.println("---------------------------");
            }
        }
    }
}
