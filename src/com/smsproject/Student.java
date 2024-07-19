package com.smsproject;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Student {
    public void addStudent(String name, String dob, String email) throws SQLException {
        String sql = "INSERT INTO students (student_name, date_of_birth, email) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setDate(2, java.sql.Date.valueOf(dob));
            stmt.setString(3, email);
            stmt.executeUpdate();
        }
    }

    public void listStudents() throws SQLException {
        String sql = "SELECT * FROM students";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("Student ID: " + rs.getInt("student_id"));
                System.out.println("Student Name: " + rs.getString("student_name"));
                System.out.println("Date of Birth: " + rs.getDate("date_of_birth"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("---------------------------");
            }
        }
    }
}
