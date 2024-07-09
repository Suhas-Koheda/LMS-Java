package ui;

import model.Class;
import service.ClassService;
import service.ClassIMPL;
import repository.ClassRepo;
import exceptions.ClassNotFoundException;

import java.util.Scanner;

public class ClassMenu {
    private ClassService classService;

    public ClassMenu() {
        this.classService = new ClassIMPL(new ClassRepo()); // Adjust if using a Factory or Dependency Injection
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

        try {
            classService.save(c);
            System.out.println("Class added successfully.");
        } catch (Exception e) {
            System.out.println("Error adding class: " + e.getMessage());
        }
    }

    private void viewClass() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Course Name: ");
        String courseName = sc.nextLine();

        try {
            Class c = classService.view(courseName);
            System.out.println("Class Details: " + c);
        } catch (ClassNotFoundException e) {
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

        try {
            Class c = classService.update(courseName, property, newValue);
            System.out.println("Class updated successfully. New Details: " + c);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteClass() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Course Name: ");
        String courseName = sc.nextLine();

        try {
            classService.delete(courseName);
            System.out.println("Class deleted successfully.");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void viewAllClasses() {
        try {
            classService.viewAll();
        } catch (Exception e) {
            System.out.println("Error viewing classes: " + e.getMessage());
        }
    }
}
