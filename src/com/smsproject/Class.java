package com.smsproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Class {
    public void addClass(String className, int teacherId, String scheduleTime, String roomNumber) throws SQLException {
        String sql = "INSERT INTO classes (class_name, teacher_id, schedule_time, room_number) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, className);
            stmt.setInt(2, teacherId);
            stmt.setTime(3, java.sql.Time.valueOf(scheduleTime));
            stmt.setString(4, roomNumber);
            stmt.executeUpdate();
        }
    }

    public void listClasses() throws SQLException {
        String sql = "SELECT * FROM classes";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("Class ID: " + rs.getInt("class_id"));
                System.out.println("Class Name: " + rs.getString("class_name"));
                System.out.println("Teacher ID: " + rs.getInt("teacher_id"));
                System.out.println("Schedule Time: " + rs.getTime("schedule_time"));
                System.out.println("Room Number: " + rs.getString("room_number"));
                System.out.println("---------------------------");
            }
        }
    }
}
