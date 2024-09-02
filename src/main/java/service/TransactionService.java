package service;

import exceptions.BookNotAvailableException;
import exceptions.BookNotFoundException;
import exceptions.PersonNotFoundException;
import exceptions.TransactionNotFoundException;
import model.Transaction;

public interface TransactionService {
    Transaction newTransaction(Transaction transaction) throws BookNotAvailableException, BookNotFoundException, PersonNotFoundException;
    void deleteTransaction(Transaction transaction) throws BookNotFoundException, TransactionNotFoundException;
    Transaction updateTransaction(Transaction transaction) throws BookNotFoundException, TransactionNotFoundException;
    void getTransactionById(String transactionID) throws BookNotFoundException, TransactionNotFoundException;
    void viewAllTransactions();
}
