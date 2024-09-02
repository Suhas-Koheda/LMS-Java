package ui;

import controller.TransactionController;
import exceptions.BookNotAvailableException;
import exceptions.BookNotFoundException;
import exceptions.PersonNotFoundException;
import exceptions.TransactionNotFoundException;
import model.Transaction;
import util.Factory;

import java.time.LocalDateTime;
import java.util.Scanner;

public class TransactionMenu {

    private final TransactionController transactionController;
    private static final Scanner scanner = new Scanner(System.in);

    public TransactionMenu() {
        this.transactionController = Factory.getTransactionController();
    }

    public void displayMenu() {
        while (true) {
            System.out.println("Transaction Menu:");
            System.out.println("1. Create Transaction");
            System.out.println("2. Delete Transaction");
            System.out.println("3. Update Transaction");
            System.out.println("4. Get Transaction by ID");
            System.out.println("5. View All Transactions");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    createTransaction();
                    break;
                case 2:
                    deleteTransaction();
                    break;
                case 3:
                    updateTransaction();
                    break;
                case 4:
                    getTransactionById();
                    break;
                case 5:
                    viewAllTransactions();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void createTransaction() {
        System.out.print("Enter Book ID: ");
        String bookID = scanner.nextLine();
        System.out.print("Enter Member ID: ");
        String memID = scanner.nextLine();

        Transaction transaction = new Transaction();
        transaction.setBookID(bookID);
        transaction.setMemID(memID);
        transaction.setIssueDate(java.time.LocalDate.now().toString());
        transaction.setReturnDate(java.time.LocalDate.now().plusDays(7).toString());

        try {
            Transaction newTransaction = transactionController.newTransaction(transaction);
            System.out.println("Transaction created successfully: " + newTransaction);
        } catch (BookNotAvailableException | BookNotFoundException | PersonNotFoundException e) {
            System.err.println("Error creating transaction: " + e.getMessage());
        }
    }

    private void deleteTransaction() {
        System.out.print("Enter Transaction ID to delete: ");
        String tranID = scanner.nextLine();

        Transaction transaction = new Transaction();
        transaction.setTranID(tranID);

        try {
            transactionController.deleteTransaction(transaction);
            System.out.println("Transaction deleted successfully.");
        } catch (TransactionNotFoundException | BookNotFoundException | PersonNotFoundException e) {
            System.err.println("Error deleting transaction: " + e.getMessage());
        }
    }

    private void updateTransaction() {
        System.out.print("Enter Transaction ID to update: ");
        String tranID = scanner.nextLine();

        Transaction transaction = new Transaction();
        transaction.setTranID(tranID);

        try {
            Transaction updatedTransaction = transactionController.updateTransaction(transaction);
            System.out.println("Transaction updated successfully: " + updatedTransaction);
        } catch (TransactionNotFoundException | BookNotFoundException | PersonNotFoundException e) {
            System.err.println("Error updating transaction: " + e.getMessage());
        }
    }

    private void getTransactionById() {
        System.out.print("Enter Transaction ID to get details: ");
        String tranID = scanner.nextLine();

        try {
            transactionController.getTransactionById(tranID);
        } catch (TransactionNotFoundException | BookNotFoundException e) {
            System.err.println("Error retrieving transaction: " + e.getMessage());
        }
    }

    private void viewAllTransactions() {
        transactionController.viewAllTransactions();
    }
}
