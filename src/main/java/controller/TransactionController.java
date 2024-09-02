package controller;

import exceptions.BookNotAvailableException;
import exceptions.BookNotFoundException;
import exceptions.PersonNotFoundException;
import exceptions.TransactionNotFoundException;
import model.Transaction;
import service.TransactionService;

public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    public void createTransaction(Transaction transaction) {
        try {
            Transaction newTransaction = transactionService.newTransaction(transaction);
            System.out.println("Transaction created successfully: " + newTransaction);
        } catch (BookNotAvailableException | BookNotFoundException | PersonNotFoundException e) {
            System.err.println("Error creating transaction: " + e.getMessage());
        }
    }

    public void deleteTransaction(Transaction transaction) {
        try {
            Transaction deletedTransaction = transactionService.deleteTransaction(transaction);
            System.out.println("Transaction deleted successfully: " + deletedTransaction);
        } catch (TransactionNotFoundException | BookNotFoundException e) {
            System.err.println("Error deleting transaction: " + e.getMessage());
        }
    }

    public void updateTransaction(Transaction transaction) {
        try {
            Transaction updatedTransaction = transactionService.updateTransaction(transaction);
            System.out.println("Transaction updated successfully: " + updatedTransaction);
        } catch (TransactionNotFoundException | BookNotFoundException e) {
            System.err.println("Error updating transaction: " + e.getMessage());
        }
    }

    public void getTransactionById(String transactionID) {
        try {
            Transaction transaction = transactionService.getTransactionById(transactionID);
            System.out.println("Transaction details: " + transaction);
        } catch (TransactionNotFoundException | BookNotFoundException e) {
            System.err.println("Error retrieving transaction: " + e.getMessage());
        }
    }
}
