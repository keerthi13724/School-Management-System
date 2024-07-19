package com.smsproject.DAO;

import com.smsproject.Class;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClassDAO {
    private Connection connection;

    public ClassDAO(Connection connection) {
        this.connection = connection;
    }

    public void addClass(Class classroom) throws SQLException {
        String query = "INSERT INTO classes (class_name, teacher_id, schedule_time, room_number) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, classroom.getClassName());
            stmt.setInt(2, classroom.getTeacherId());
            stmt.setTime(3, classroom.getScheduleTime());
            stmt.setString(4, classroom.getRoomNumber());
            stmt.executeUpdate();
        }
    }

    public List<Class> getAllClasses() throws SQLException {
        List<Class> classes = new ArrayList<>();
        String query = "SELECT * FROM classes";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Class classroom = new Class();
                classroom.setClassId(rs.getInt("class_id"));
                classroom.setClassName(rs.getString("class_name"));
                classroom.setTeacherId(rs.getInt("teacher_id"));
                classroom.setScheduleTime(rs.getTime("schedule_time"));
                classroom.setRoomNumber(rs.getString("room_number"));
                classes.add(classroom);
            }
        }
        return classes;
    }
}
