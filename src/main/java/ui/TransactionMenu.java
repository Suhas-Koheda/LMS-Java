package ui;

import controller.TransactionController;
import exceptions.BookNotAvailableException;
import exceptions.BookNotFoundException;
import exceptions.PersonNotFoundException;
import model.Transaction;
import util.Factory;

public class TransactionMenu {
    public static void main(String[] args) throws BookNotAvailableException, BookNotFoundException, PersonNotFoundException {
        TransactionController transactionController = Factory.getTransactionController();
        Transaction t=new Transaction();
        t.setIssueDate(String.valueOf(java.time.LocalDate.now()));
        t.setReturnDate(String.valueOf(java.time.LocalDate.now().plusDays(7)));
        t.setMemID("23BAI1148");
        t.setBookID("23BAI1148");
        t.setTranID("001");

    }
}
