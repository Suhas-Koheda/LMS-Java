package util;

import controller.BookController;
import controller.PersonController;
import model.Person;
import repository.BookRepo;
import repository.PersonRepo;
import service.BookIMPL;
import service.BookService;
import service.PersonIMPL;
import service.PersonService;

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
}
