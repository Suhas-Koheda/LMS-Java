package service;

import exceptions.BookNotAvailableException;
import exceptions.BookNotFoundException;
import exceptions.PersonNotFoundException;
import exceptions.TransactionNotFoundException;
import model.Book;
import model.Person;
import model.Transaction;
import repository.BookRepo;
import repository.PersonRepo;
import repository.TransactionRepo;

public class TransactionIMPL implements TransactionService{
    TransactionRepo transactionRepo;
    public TransactionIMPL(TransactionRepo transactionRepository) {
        this.transactionRepo= transactionRepository;
    }

    @Override
    public Transaction newTransaction(Transaction transaction) throws BookNotAvailableException, BookNotFoundException, PersonNotFoundException {
        Book book = new Book();
        book.setID(transaction.getBookID());
        BookRepo br=new BookRepo();
        Person person = new Person();
        person.setMemID(transaction.getMemID());
        PersonRepo pr=new PersonRepo();
        if(br.checkAvailability(book).getStatus().equals("In Library") && pr.checkAvailability(person).getRole().equals("Student")){
            transactionRepo.writeTransaction(transaction);
            return transaction;
        }
        throw new BookNotAvailableException("Book is not available");
    }

    @Override
    public Transaction deleteTransaction(Transaction transaction) throws BookNotFoundException, TransactionNotFoundException {

        transactionRepo.deleteTransaction(transaction);
        return transaction;
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) throws BookNotFoundException, TransactionNotFoundException {
        return null;
    }

    @Override
    public Transaction getTransactionById(String transactionID) throws BookNotFoundException, TransactionNotFoundException {
        return null;
    }


}