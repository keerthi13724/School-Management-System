package com.smsproject;

import java.sql.SQLException;
import java.util.Scanner;

public class SchoolManagement {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Student student = new Student();
    private static final Teacher teacher = new Teacher();
    private static final Class schoolClass = new Class();
    private static final Enrollment enrollment = new Enrollment();

    public static void main(String[] args) {
        while (true) {
            System.out.println("School Management System");
            System.out.println("1. Add Student");
            System.out.println("2. List Students");
            System.out.println("3. Add Teacher");
            System.out.println("4. List Teachers");
            System.out.println("5. Add Class");
            System.out.println("6. List Classes");
            System.out.println("7. Enroll Student");
            System.out.println("8. List Enrollments");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); 

            try {
                switch (option) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        listStudents();
                        break;
                    case 3:
                        addTeacher();
                        break;
                    case 4:
                        listTeachers();
                        break;
                    case 5:
                        addClass();
                        break;
                    case 6:
                        listClasses();
                        break;
                    case 7:
                        enrollStudent();
                        break;
                    case 8:
                        listEnrollments();
                        break;
                    case 9:
                        System.out.println("Thank you for using the School Management System!");
                        System.exit(0);
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void addStudent() throws SQLException {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter date of birth (yyyy-mm-dd): ");
        String dob = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        student.addStudent(name, dob, email);
        System.out.println("Student added successfully.");
    }

    private static void listStudents() throws SQLException {
        student.listStudents();
    }

    private static void addTeacher() throws SQLException {
        System.out.print("Enter teacher name: ");
        String name = scanner.nextLine();
        System.out.print("Enter subject: ");
        String subject = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        teacher.addTeacher(name, subject, email);
        System.out.println("Teacher added successfully.");
    }

    private static void listTeachers() throws SQLException {
        teacher.listTeachers();
    }

    private static void addClass() throws SQLException {
        System.out.print("Enter class name: ");
        String className = scanner.nextLine();
        System.out.print("Enter teacher ID: ");
        int teacherId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter schedule time (HH:MM:SS): ");
        String scheduleTime = scanner.nextLine();
        System.out.print("Enter room number: ");
        String roomNumber = scanner.nextLine();
        schoolClass.addClass(className, teacherId, scheduleTime, roomNumber);
        System.out.println("Class added successfully.");
    }

    private static void listClasses() throws SQLException {
        schoolClass.listClasses();
    }

    private static void enrollStudent() throws SQLException {
        System.out.print("Enter class ID: ");
        int classId = scanner.nextInt();
        System.out.print("Enter student ID: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter enrollment date (yyyy-mm-dd): ");
        String enrollmentDate = scanner.nextLine();
        enrollment.enrollStudent(classId, studentId, enrollmentDate);
        System.out.println("Student enrolled successfully.");
    }

    private static void listEnrollments() throws SQLException {
        enrollment.listEnrollments();
    }
}
