package controller;

import exceptions.BookNotAvailableException;
import exceptions.BookNotFoundException;
import exceptions.PersonNotFoundException;
import exceptions.TransactionNotFoundException;
import model.Transaction;
import service.TransactionService;

public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService){
        this.transactionService=transactionService;
    }

    public Transaction newTransaction(Transaction transaction) throws BookNotAvailableException, BookNotFoundException, PersonNotFoundException {
        return transactionService.newTransaction(transaction);
    }

    public void deleteTransaction(Transaction transaction) throws BookNotFoundException, PersonNotFoundException, TransactionNotFoundException {
        transactionService.deleteTransaction(transaction);
    }

    public Transaction updateTransaction(Transaction transaction) throws BookNotFoundException, PersonNotFoundException, TransactionNotFoundException {
        return transactionService.updateTransaction(transaction);
    }

    public void getTransactionById(String transactionID) throws BookNotFoundException, TransactionNotFoundException {
        transactionService.getTransactionById(transactionID);
    }

    public void viewAllTransactions() {
        transactionService.viewAllTransactions();
    }
}
