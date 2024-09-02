package util;

import controller.BookController;
import controller.ClassController;
import controller.PersonController;
import model.Person;
import repository.BookRepo;
import repository.ClassRepo;
import repository.PersonRepo;
import repository.TransactionRepo;
import service.*;

public class Factory {
    public static PersonRepo getPersonRepository(){
        return new PersonRepo();
    }
    public static PersonService getPersonService(){
        return new PersonIMPL(getPersonRepository());
    }
    public static PersonController getPersonController(){
        return new PersonController(getPersonService());
    }
    public static BookRepo getBookRepository() {
        return new BookRepo();
    }

    public static BookService getBookService() {
        return new BookIMPL(getBookRepository());
    }

    public static BookController getBookController() {
        return new BookController(getBookService());
    }
    public static ClassRepo getClassRepository() {
        return new ClassRepo();
    }

    public static ClassService getClassService() {
        return new ClassIMPL(getClassRepository());
    }

    public static ClassController getClassController() {
        return new ClassController(getClassService());
    }

    public static TransactionRepo getTransactionRepository() {
        return new TransactionRepo();
    }
    public static TransactionService getTransactionService() {
        return new TransactionIMPL(getTransactionRepository());
    }

}
