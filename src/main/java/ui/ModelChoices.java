package ui;

import java.util.Scanner;

public class ModelChoices {
    private PersonMenu personMenu;
    private BookMenu bookMenu;
    private ClassMenu classMenu;

    public ModelChoices() {
        this.personMenu = new PersonMenu();
        this.bookMenu = new BookMenu();
        this.classMenu = new ClassMenu();
    }

    public void displayModelMenu() {
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Model Menu:");
            System.out.println("1. Manage Persons");
            System.out.println("2. Manage Books");
            System.out.println("3. Manage Classes");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    personMenu.displayMenu(); // Navigate to Person menu
                    break;
                case 2:
                    bookMenu.displayMenu();     // Navigate to Book menu
                    break;
                case 3:
                    classMenu.displayMenu();   // Navigate to Class menu
                    break;
                case 4:
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}
