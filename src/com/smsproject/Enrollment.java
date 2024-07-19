package com.smsproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Enrollment {
    public void enrollStudent(int classId, int studentId, String enrollmentDate) throws SQLException {
        String sql = "INSERT INTO class_students (class_id, student_id, enrollment_date) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, classId);
            stmt.setInt(2, studentId);
            stmt.setDate(3, java.sql.Date.valueOf(enrollmentDate));
            stmt.executeUpdate();
        }
    }

    public void listEnrollments() throws SQLException {
        String sql = "SELECT * FROM class_students";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("Class ID: " + rs.getInt("class_id"));
                System.out.println("Student ID: " + rs.getInt("student_id"));
                System.out.println("Enrollment Date: " + rs.getDate("enrollment_date"));
                System.out.println("---------------------------");
            }
        }
    }
}
