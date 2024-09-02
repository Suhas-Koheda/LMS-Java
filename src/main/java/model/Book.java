package model;

import java.util.Objects;

public class Book {
    private String BookID;
    private String Title;
    private String ISBN;
    private String Author;
    private String Category;
    private String Status;
    private String PubDate;

    public Book(String bookID){
    }

    public Book() {}
    public String getBookID() {
        return BookID;
    }

    public void setID(String BookID) {
        this.BookID = BookID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getPubDate() {
        return PubDate;
    }

    public void setPubDate(String pubDate) {
        PubDate = pubDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(BookID, book.BookID) && Objects.equals(Title, book.Title) && Objects.equals(ISBN, book.ISBN) && Objects.equals(Author, book.Author) && Objects.equals(Category, book.Category) && Objects.equals(Status, book.Status) && Objects.equals(PubDate, book.PubDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(BookID, Title, ISBN, Author, Category, Status, PubDate);
    }

    @Override
    public String toString() {
        return "Book{" +
                "BookID='" + BookID + '\'' +
                ", Title='" + Title + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", Author='" + Author + '\'' +
                ", Category='" + Category + '\'' +
                ", Status='" + Status + '\'' +
                ", PubDate='" + PubDate + '\'' +
                '}';
    }
}