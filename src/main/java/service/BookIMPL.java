package service;

import exceptions.*;
import model.Book;
import repository.BookRepo;

public class BookIMPL implements BookService {
    private BookRepo br;

    public BookIMPL(BookRepo br) {
        this.br = br;
    }

    @Override
    public Book save(Book book) throws BookExistsException {
        if (br.checkBook(book).isPresent()) {
            throw new BookExistsException("Book already exists");
        }
        return this.br.writeBook(book);
    }

    @Override
    public Book view(Book book) throws BookNotFoundException {
        if (br.checkBook(book).isEmpty()) {
            throw new BookNotFoundException("Sorry, the book requested does not exist");
        } else {
            return br.viewDetails(book);
        }
    }

    @Override
    public Book update(Book book, String property, String newValue) throws BookNotFoundException, MemberIDChangeException, UserRoleNotFoundException {
        if (property.equals("BookID")) {
            throw new MemberIDChangeException("Cannot change the Book ID itself!");
        }
        if (br.checkBook(book).isEmpty()) {
            throw new BookNotFoundException("Sorry, the book requested does not exist");
        } else {
            return br.updateDetails(book, property, newValue);
        }
    }

    @Override
    public void delete(Book book) throws BookNotFoundException {
        if (br.checkBook(book).isEmpty()) {
            throw new BookNotFoundException("Sorry, the book requested does not exist");
        }
        br.deleteBook(book);
    }

    @Override
    public void viewAll() {
        br.viewAllBooks();
    }

}
