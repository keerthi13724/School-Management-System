package com.smsproject.DAO;

import com.smsproject.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private Connection connection;

    public StudentDAO(Connection connection) {
        this.connection = connection;
    }

    public void addStudent(Student student) throws SQLException {
        String query = "INSERT INTO students (student_name, date_of_birth, email) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, student.getStudentName());
            stmt.setDate(2, student.getDateOfBirth());
            stmt.setString(3, student.getEmail());
            stmt.executeUpdate();
        }
    }

    public List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Student student = new Student();
                student.setStudentId(rs.getInt("student_id"));
                student.setStudentName(rs.getString("student_name"));
                student.setDateOfBirth(rs.getDate("date_of_birth"));
                student.setEmail(rs.getString("email"));
                students.add(student);
            }
        }
        return students;
    }
}
