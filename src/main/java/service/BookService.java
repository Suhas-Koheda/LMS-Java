    package service;

    import exceptions.*;
    import model.Book;

    public interface BookService {
        public Book save(Book book) throws BookExistsException;
        public Book view(Book book) throws BookNotFoundException;
        public Book update(Book book,String Property,String newValue) throws BookNotFoundException, MemberIDChangeException,UserRoleNotFoundException;
        public void delete(Book book) throws BookNotFoundException;
        public void viewAll() ;
        ;
    }
