package service;

import model.Transaction;
import repository.TransactionRepo;

public class TransactionIMPL implements TransactionService{
    TransactionRepo transactionRepo;
    public TransactionIMPL(TransactionRepo transactionRepository) {
        this.transactionRepo= transactionRepository;
    }

    @Override
    public Transaction newTransaction(Transaction transaction) {

    }
}
