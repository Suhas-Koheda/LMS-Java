package ui;

import controller.ClassController;
import controller.PersonController;
import model.Class;
import model.Person;
import util.Factory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClassMenu {

    public ClassMenu() {
    }

    public void displayMenu() {
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Class Menu:");
            System.out.println("1. Add New Class");
            System.out.println("2. View Class");
            System.out.println("3. Update Class");
            System.out.println("4. Delete Class");
            System.out.println("5. View All Classes");
            System.out.println("6. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addNewClass();
                    break;
                case 2:
                    viewClass();
                    break;
                case 3:
                    updateClass();
                    break;
                case 4:
                    deleteClass();
                    break;
                case 5:
                    viewAllClasses();
                    break;
                case 6:
                    return; // Return to the main menu
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private void addNewClass() {
        Scanner sc = new Scanner(System.in);
        Class c = new Class();

        System.out.print("Enter Course Name: ");
        c.setCourseName(sc.nextLine());

        System.out.print("Enter Slot: ");
        c.setSlot(sc.nextLine());

        System.out.print("Enter Slot Size: ");
        c.setSlotSize(sc.nextInt());
        sc.nextLine();

        System.out.println("Add Students to Class\n 1. Add Students by ID by manually entering \t Enter 1 \n 2. Add Students by ID by adding CSV \t Enter 2 ");
        int choice=sc.nextInt();
        switch(choice) {
            case (1): {
                System.out.print("Enter Students ID (comma separated): ");
                String[] studentIds = sc.nextLine().split(",");
                List<Person> students = new ArrayList<>();
                ClassController classController = Factory.getClassController();
                PersonController personController = Factory.getPersonController();
                for (int i = 0; i < studentIds.length; i++) {
                    studentIds[i] = studentIds[i].trim();
                    Person p = new Person();
                    p.setMemID(studentIds[i]);
                    p.setRole("Student");
                    try {
                        p = personController.view(p);
                        students.add(p);
                    } catch (Exception e) {
                        System.out.println("Error adding student: " + e.getMessage());
                    }
                }
                c.setStudents(students);
                break;
            }
            case(2):{
                sc.nextLine();
                System.out.print("Enter CSV file path: ");
                String path = sc.nextLine();
                ClassController classController = Factory.getClassController();
                PersonController personController = Factory.getPersonController();
                List<Person> students = new ArrayList<>();
                students = classController.addStudentsByCSV(c, path);
                try {
                    for (Person p : students) {
                        p = personController.view(p);
                    }
                    c.setStudents(students);
                } catch (Exception e) {
                    System.out.println("Error adding students: " + e.getMessage());
                }
                break;
            }
        }
        try {
            ClassController classController = Factory.getClassController();
            classController.save(c);
            System.out.println("Class added successfully.");
        } catch (Exception e) {
            System.out.println("Error adding class: " + e.getMessage());
        }
    }

    private void viewClass() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Course Name: ");
        String courseName = sc.nextLine();

        ClassController classController = Factory.getClassController();

        try {
            Class c = classController.view(courseName);
            System.out.println("Class Details: " + c.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateClass() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Course Name: ");
        String courseName = sc.nextLine();

        System.out.print("Enter Property to Update (CourseName, Slot, SlotSize): ");
        String property = sc.nextLine();

        System.out.print("Enter New Value: ");
        String newValue = sc.nextLine();

        ClassController classController = Factory.getClassController();

        try {
            Class c = classController.update(courseName, property, newValue);
            System.out.println("Class updated successfully. New Details: " + c);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteClass() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Course Name: ");
        String courseName = sc.nextLine();

        ClassController classController = Factory.getClassController();

        try {
            classController.delete(courseName);
            System.out.println("Class deleted successfully.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void viewAllClasses() {
        ClassController classController = Factory.getClassController();

        try {
            classController.viewAll();
        } catch (Exception e) {
            System.out.println("Error viewing classes: " + e.getMessage());
        }
    }
}
