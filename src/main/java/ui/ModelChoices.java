package ui;

import java.util.Scanner;

public class ModelChoices {
    public void Display() {
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Main Menu:");
            System.out.println("1. Manage People");
            System.out.println("2. Manage Books");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    PersonMenu personMenu = new PersonMenu();
                    personMenu.displayMenu();
                    break;
                case 2:
                    BookMenu bookMenu = new BookMenu();
                    bookMenu.displayMenu();
                    break;
                case 3:
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
