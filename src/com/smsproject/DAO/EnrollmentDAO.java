package com.smsproject.DAO;

import com.smsproject.Enrollment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class EnrollmentDAO {
    private Connection connection;

    public EnrollmentDAO(Connection connection) {
        this.connection = connection;
    }

    public void addEnrollment(Enrollment enrollment) throws SQLException {
        String query = "INSERT INTO class_students (class_id, student_id, enrollment_date) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, enrollment.getClassId());
            stmt.setInt(2, enrollment.getStudentId());
            stmt.setDate(3, enrollment.getEnrollmentDate());
            stmt.executeUpdate();
        }
    }

    public List<Enrollment> getAllEnrollments() throws SQLException {
        List<Enrollment> enrollments = new ArrayList<>();
        String query = "SELECT * FROM class_students";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Enrollment enrollment = new Enrollment();
                enrollment.setClassId(rs.getInt("class_id"));
                enrollment.setStudentId(rs.getInt("student_id"));
                enrollment.setEnrollmentDate(rs.getDate("enrollment_date"));
                enrollments.add(enrollment);
            }
        }
        return enrollments;
    }

    public boolean isStudentAlreadyEnrolled(int studentId, int classId) throws SQLException {
        String query = "SELECT COUNT(*) AS count FROM class_students WHERE student_id = ? AND class_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, studentId);
            stmt.setInt(2, classId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("count") > 0;
                }
            }
        }
        return false;
    }
}
