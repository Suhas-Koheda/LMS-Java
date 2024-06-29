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

    Book(){
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
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

    public voBookID setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getAuthor() {
        return Author;
    }

    public voBookID setAuthor(String author) {
        Author = author;
    }

    public String getCategory() {
        return Category;
    }

    public voBookID setCategory(String category) {
        Category = category;
    }

    public String getStatus() {
        return Status;
    }

    public voBookID setStatus(String status) {
        Status = status;
    }

    public String getPubDate() {
        return PubDate;
    }

    public voBookID setPubDate(String pubDate) {
        PubDate = pubDate;
    }

    @OverrBookIDe
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(BookID, book.BookID) && Objects.equals(Title, book.Title) && Objects.equals(ISBN, book.ISBN) && Objects.equals(Author, book.Author) && Objects.equals(Category, book.Category) && Objects.equals(Status, book.Status) && Objects.equals(PubDate, book.PubDate);
    }

    @OverrBookIDe
    public int hashCode() {
        return Objects.hash(BookID, Title, ISBN, Author, Category, Status, PubDate);
    }

    @OverrBookIDe
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