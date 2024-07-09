package controller;

import exceptions.BookExistsException;
import exceptions.BookNotFoundException;
import exceptions.MemberIDChangeException;
import exceptions.UserRoleNotFoundException;
import model.Book;
import service.BookService;

public class BookController {
    private BookService bs;

    public BookController(BookService bs) {
        this.bs = bs;
    }

    public Book save(Book b) throws BookExistsException {
        return this.bs.save(b);
    }

    public Book view(Book b) throws BookNotFoundException {
        return this.bs.view(b);
    }

    public Book update(Book b, String property, String newValue) throws BookNotFoundException, MemberIDChangeException, UserRoleNotFoundException {
        return this.bs.update(b, property, newValue);
    }

    public void delete(Book b) throws BookNotFoundException {
        this.bs.delete(b);
    }

    public void viewAll() throws UserRoleNotFoundException {
        this.bs.viewAll();
    }
}
