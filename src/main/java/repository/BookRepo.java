package repository;

import Credentials.Creds;
import com.mongodb.client.*;
import exceptions.BookNotFoundException;
import model.Book;
import org.bson.Document;

import java.util.Optional;

public class BookRepo {
    private static final String CONNECTION_STRING = "mongodb+srv://" + Creds.getUSERNAME() + ":" + Creds.getPASSWORD() + "@test.zt5blxl.mongodb.net/?retryWrites=true&w=majority&appName=test&connectTimeoutMS=30000000&socketTimeoutMS=30000000";
    private static final String DATABASE_NAME = "LibraryData";
    private static final MongoClient mongoClient = MongoClients.create(CONNECTION_STRING);
    static final MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);

    public Book writeBook(Book book) {
        try (MongoClient mongoClient = MongoClients.create(CONNECTION_STRING)) {
            MongoCollection<Document> collection = database.getCollection("Books");
            Document doc = new Document("BookID", book.getBookID())
                    .append("Title", book.getTitle())
                    .append("ISBN", book.getISBN())
                    .append("Author", book.getAuthor())
                    .append("Category", book.getCategory())
                    .append("Status", book.getStatus())
                    .append("PubDate", book.getPubDate());
            collection.insertOne(doc);
            return book;
        } catch (Exception e) {
            throw e;
        }
    }

    public Optional<Book> checkBook(Book book) {
        MongoCollection<Document> collection = database.getCollection("Books");
        Document filter = new Document("BookID", book.getBookID());
        Document existingDoc = collection.find(filter).first();
        return existingDoc == null ? Optional.empty() : Optional.of(book);
    }

    public Book viewDetails(Book book) throws BookNotFoundException {
        MongoCollection<Document> collection = database.getCollection("Books");
        Document filter = new Document("BookID", book.getBookID());
        Document documents = collection.find(filter).first();

        if (documents != null) {
            documents.remove("_id");
            return updateBookFromBSON(book, documents);
        } else {
            throw new BookNotFoundException("Book with ID " + book.getBookID() + " not found.");
        }
    }

    public Book updateDetails(Book book, String property, String newValue) throws BookNotFoundException {
        MongoCollection<Document> collection = database.getCollection("Books");
        Document filter = new Document("BookID", book.getBookID());
        Document documents = collection.find(filter).first();

        if (documents != null) {
            documents.remove("_id");
            Document update = new Document("$set", new Document(property, newValue));
            collection.updateOne(filter, update);
            Document updatedDocument = collection.find(filter).first();
            assert updatedDocument != null;
            return updateBookFromBSON(book, updatedDocument);
        } else {
            throw new BookNotFoundException("Book with ID " + book.getBookID() + " not found.");
        }
    }

    public Book updateBookFromBSON(Book book, Document documents) {
        book.setTitle(documents.getString("Title"));
        book.setISBN(documents.getString("ISBN"));
        book.setAuthor(documents.getString("Author"));
        book.setCategory(documents.getString("Category"));
        book.setStatus(documents.getString("Status"));
        book.setPubDate(documents.getString("PubDate"));
        return book;
    }

    public void deleteBook(Book book) throws BookNotFoundException {
        MongoCollection<Document> collection = database.getCollection("Books");
        Document filter = new Document("BookID", book.getBookID());
        Document existingDoc = collection.find(filter).first();
        if (existingDoc == null) {
            throw new BookNotFoundException("Book with ID " + book.getBookID() + " not found.");
        }
        collection.deleteOne(filter);
    }

    public void viewAllBooks() {
        MongoCollection<Document> collection = database.getCollection("Books");
        FindIterable<Document> documents = collection.find();
        MongoCursor<Document> cursor = documents.iterator();
        try {
            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson() + "\n");
            }
        } finally {
            cursor.close();
        }
    }

    public Book checkAvailability(Book book) throws BookNotFoundException {
        MongoCollection<Document> collection = database.getCollection("Books");
        Document filter = new Document("BookID", book.getBookID());
        Document existingDoc = collection.find(filter).first();
        if (existingDoc != null) {
            updateDetails(book, "Status", "In Library");
            return book;
        } else {
            return null;
        }
    }
}
