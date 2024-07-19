package com.smsproject.DAO;

import com.smsproject.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAO {
    private Connection connection;

    public TeacherDAO(Connection connection) {
        this.connection = connection;
    }

    public void addTeacher(Teacher teacher) throws SQLException {
        String query = "INSERT INTO teachers (teacher_name, subject, email) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, teacher.getTeacherName());
            stmt.setString(2, teacher.getSubject());
            stmt.setString(3, teacher.getEmail());
            stmt.executeUpdate();
        }
    }

    public List<Teacher> getAllTeachers() throws SQLException {
        List<Teacher> teachers = new ArrayList<>();
        String query = "SELECT * FROM teachers";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Teacher teacher = new Teacher();
                teacher.setTeacherId(rs.getInt("teacher_id"));
                teacher.setTeacherName(rs.getString("teacher_name"));
                teacher.setSubject(rs.getString("subject"));
                teacher.setEmail(rs.getString("email"));
                teachers.add(teacher);
            }
        }
        return teachers;
    }
}
