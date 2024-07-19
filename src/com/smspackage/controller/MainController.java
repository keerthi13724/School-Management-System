package com.smspackage.controller;

import com.smsproject.Student;
import com.smsproject.Teacher;
import com.smsproject.Class;
import com.smsproject.Enrollment;
import com.smsproject.DAO.StudentDAO;
import com.smsproject.DAO.TeacherDAO;
import com.smsproject.DAO.ClassDAO;
import com.smsproject.DAO.EnrollmentDAO;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Time;
import java.util.List;
import java.util.Scanner;

public class MainController {
    private StudentDAO studentDAO;
    private TeacherDAO teacherDAO;
    private ClassDAO classDAO;
    private EnrollmentDAO enrollmentDAO;

    public MainController(StudentDAO studentDAO, TeacherDAO teacherDAO, ClassDAO classDAO, EnrollmentDAO enrollmentDAO) {
        this.studentDAO = studentDAO;
        this.teacherDAO = teacherDAO;
        this.classDAO = classDAO;
        this.enrollmentDAO = enrollmentDAO;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Add Student");
            System.out.println("2. Add Teacher");
            System.out.println("3. Add Class");
            System.out.println("4. Enroll Student in Class");
            System.out.println("5. List All Students");
            System.out.println("6. List All Teachers");
            System.out.println("7. List All Classes");
            System.out.println("8. Get Number of Students");
            System.out.println("9. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1:
                        addStudent(scanner);
                        break;
                    case 2:
                        addTeacher(scanner);
                        break;
                    case 3:
                        addClass(scanner);
                        break;
                    case 4:
                        enrollStudentInClass(scanner);
                        break;
                    case 5:
                        listAllStudents();
                        break;
                    case 6:
                        listAllTeachers();
                        break;
                    case 7:
                        listAllClasses();
                        break;
                    case 8:
                        getNumberOfStudents();
                        break;
                    case 9:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice, please try again.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void addStudent(Scanner scanner) throws SQLException {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter date of birth (yyyy-mm-dd): ");
        Date dob = Date.valueOf(scanner.nextLine());
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        Student student = new Student();
        student.setStudentName(name);
        student.setDateOfBirth(dob);
        student.setEmail(email);

        studentDAO.addStudent(student);
        System.out.println("Student added successfully.");
    }

    private void addTeacher(Scanner scanner) throws SQLException {
        System.out.print("Enter teacher name: ");
        String name = scanner.nextLine();
        System.out.print("Enter subject: ");
        String subject = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        Teacher teacher = new Teacher();
        teacher.setTeacherName(name);
        teacher.setSubject(subject);
        teacher.setEmail(email);

        teacherDAO.addTeacher(teacher);
        System.out.println("Teacher added successfully.");
    }

    private void addClass(Scanner scanner) throws SQLException {
        System.out.print("Enter class name: ");
        String name = scanner.nextLine();
        System.out.print("Enter teacher ID: ");
        int teacherId = scanner.nextInt();
        System.out.print("Enter schedule time (HH:mm:ss): ");
        Time time = Time.valueOf(scanner.next());
        System.out.print("Enter room number: ");
        String roomNumber = scanner.next();

        Class classroom = new Class();
        classroom.setClassName(name);
        classroom.setTeacherId(teacherId);
        classroom.setScheduleTime(time);
        classroom.setRoomNumber(roomNumber);

        classDAO.addClass(classroom);
        System.out.println("Class added successfully.");
    }

    private void enrollStudentInClass(Scanner scanner) throws SQLException {
        System.out.print("Enter class ID: ");
        int classId = scanner.nextInt();
        System.out.print("Enter student ID: ");
        int studentId = scanner.nextInt();
        System.out.print("Enter enrollment date (yyyy-mm-dd): ");
        Date enrollmentDate = Date.valueOf(scanner.next());

        Enrollment enrollment = new Enrollment();
        enrollment.setClassId(classId);
        enrollment.setStudentId(studentId);
        enrollment.setEnrollmentDate(enrollmentDate);

        if (enrollmentDAO.isStudentAlreadyEnrolled(studentId, classId)) {
            System.out.println("Student is already enrolled in this class.");
        } else {
            enrollmentDAO.addEnrollment(enrollment);
            System.out.println("Student enrolled in class successfully.");
        }
    }

    private void listAllStudents() throws SQLException {
        List<Student> students = studentDAO.getAllStudents();
        for (Student student : students) {
            System.out.println(student.getStudentId() + " - " + student.getStudentName());
        }
    }

    private void listAllTeachers() throws SQLException {
        List<Teacher> teachers = teacherDAO.getAllTeachers();
        for (Teacher teacher : teachers) {
            System.out.println(teacher.getTeacherId() + " - " + teacher.getTeacherName());
        }
    }

    private void listAllClasses() throws SQLException {
        List<Class> classes = classDAO.getAllClasses();
        for (Class classroom : classes) {
            System.out.println(classroom.getClassId() + " - " + classroom.getClassName());
        }
    }

    private void getNumberOfStudents() throws SQLException {
        List<Student> numberOfStudents = studentDAO.getAllStudents();
        System.out.println("Total number of students: " + numberOfStudents);
    }

    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/SMSproject", "root", "root");

            StudentDAO studentDAO = new StudentDAO(connection);
            TeacherDAO teacherDAO = new TeacherDAO(connection);
            ClassDAO classDAO = new ClassDAO(connection);
            EnrollmentDAO enrollmentDAO = new EnrollmentDAO(connection);

            MainController controller = new MainController(studentDAO, teacherDAO, classDAO, enrollmentDAO);
            controller.start();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
