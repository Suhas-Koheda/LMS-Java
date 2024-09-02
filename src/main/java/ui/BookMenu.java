package ui;

import controller.BookController;
import exceptions.*;
import model.Book;
import util.Factory;
import util.ReadCSV;

import java.util.Scanner;

public class BookMenu {

    public BookMenu() {
    }

    public void displayMenu() {
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Menu:");
            System.out.println("1. New Book");
            System.out.println("2. View Book");
            System.out.println("3. Update Book");
            System.out.println("4. Delete Book");
            System.out.println("5. View All Books");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    newBook();
                    break;
                case 2:
                    viewBook();
                    break;
                case 3:
                    updateBook();
                    break;
                case 4:
                    deleteBook();
                    break;
                case 5:
                    viewAllBooks();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    public void newBook() {
        System.out.println("Enter Book Details");
        System.out.println("Add Book to database\n 1. Add Book by manually entering its details \t Enter 1 \n 2. Add Book by adding details in form of CSV \t Enter 2 ");
        Scanner sc=new Scanner(System.in);
        int choice=sc.nextInt();
        BookController bc = Factory.getBookController();
        switch(choice) {
            case (1):{
                Book b = new Book();
                System.out.print("Enter BookID: ");
                b.setID(sc.nextLine());

                System.out.print("Enter Title: ");
                b.setTitle(sc.nextLine());

                System.out.print("Enter ISBN: ");
                b.setISBN(sc.nextLine());

                System.out.print("Enter Author: ");
                b.setAuthor(sc.nextLine());

                System.out.print("Enter Category: ");
                b.setCategory(sc.nextLine());

                System.out.print("Enter Status: ");
                b.setStatus(sc.nextLine());

                System.out.print("Enter Publication Date: ");
                b.setPubDate(sc.nextLine());

                try {
                    Book saved = bc.save(b);
                    if (saved != null) {
                        System.out.println("Book added successfully");
                    } else {
                        System.out.println("Book not added");
                    }
                } catch (BookExistsException e) {
                    System.out.println("Book already exists");
                }
                break;
            }
            case(2):{
                sc.nextLine();
                Book b = new Book();
                System.out.print("Enter the path of the CSV file: ");
                String path = sc.nextLine();
                try {
                    ReadCSV util = new ReadCSV();
                    b = util.readBookfromCSV(path);
                } catch (Exception e) {
                    System.out.println("Error reading from CSV: " + e.getMessage());
                }
                try {
                    Book saved = bc.save(b);
                    if (saved != null) {
                        System.out.println("Book added successfully");
                    } else {
                        System.out.println("Book not added");
                    }
                } catch (BookExistsException e) {
                    System.out.println("Book already exists");
                }
                break;
            }
        }
    }

    public void viewBook() {
        Book b = new Book();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter BookID: ");
        b.setID(sc.nextLine());

        BookController bc = Factory.getBookController();
        try {
            Book returnedBook = bc.view(b);
            if (returnedBook != null) {
                System.out.println(returnedBook.toString());
            }
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateBook() {
        Book b = new Book();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter BookID: ");
        b.setID(sc.nextLine());

        System.out.println("Enter the property to be updated");
        String key = sc.nextLine();
        System.out.println("Enter the new value");
        String newValue = sc.nextLine();

        BookController bc = Factory.getBookController();
        try {
            Book updatedBook = bc.update(b, key, newValue);
            if (updatedBook != null) {
                System.out.println("Book updated successfully");
                System.out.println(updatedBook.toString());
            }
        } catch (BookNotFoundException | MemberIDChangeException | UserRoleNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteBook() {
        Book b = new Book();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter BookID: ");
        b.setID(sc.nextLine());

        BookController bc = Factory.getBookController();
        try {
            bc.delete(b);
            System.out.println("Book deleted successfully");
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void viewAllBooks() {
        BookController bc = Factory.getBookController();
        try {
            bc.viewAll();
        } catch (UserRoleNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
