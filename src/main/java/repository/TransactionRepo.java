package repository;

import Credentials.Creds;
import com.mongodb.client.*;
import exceptions.TransactionNotFoundException;
import model.Transaction;
import org.bson.Document;

import java.util.Optional;

public class TransactionRepo {
    private static final String CONNECTION_STRING = "mongodb+srv://" + Creds.getUSERNAME() + ":" + Creds.getPASSWORD() + "@test.zt5blxl.mongodb.net/?retryWrites=true&w=majority&appName=test&connectTimeoutMS=30000000&socketTimeoutMS=30000000";
    private static final String DATABASE_NAME = "LibraryData";
    private static final MongoClient mongoClient = MongoClients.create(CONNECTION_STRING);
    static final MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);

    public Transaction writeTransaction(Transaction transaction) {
        try (MongoClient mongoClient = MongoClients.create(CONNECTION_STRING)) {
            MongoCollection<Document> collection = database.getCollection("Transactions");
            Document doc = new Document("TransactionID", transaction.getTranID())
                    .append("BookID", transaction.getBookID())
                    .append("MemberID", transaction.getMemID())
                    .append("IssueDate", transaction.getIssueDate())
                    .append("ReturnDate", transaction.getReturnDate());
            collection.insertOne(doc);
            return transaction;
        } catch (Exception e) {
            throw e;
        }
    }

    public Optional<Transaction> checkTransaction(Transaction transaction) {
        MongoCollection<Document> collection = database.getCollection("Transactions");
        Document filter = new Document("TransactionID", transaction.getTranID());
        Document existingDoc = collection.find(filter).first();
        return existingDoc == null ? Optional.empty() : Optional.of(transaction);
    }

    public Transaction viewTransactionDetails(Transaction transaction) throws TransactionNotFoundException {
        MongoCollection<Document> collection = database.getCollection("Transactions");
        Document filter = new Document("TransactionID", transaction.getTranID());
        Document documents = collection.find(filter).first();

        if (documents != null) {
            documents.remove("_id");
            return updateTransactionFromBSON(transaction, documents);
        } else {
            throw new TransactionNotFoundException("Transaction with ID " + transaction.getTranID() + " not found.");
        }
    }

    public Transaction updateTransactionDetails(Transaction transaction, String property, String newValue) throws TransactionNotFoundException {
        MongoCollection<Document> collection = database.getCollection("Transactions");
        Document filter = new Document("TransactionID", transaction.getTranID());
        Document documents = collection.find(filter).first();

        if (documents != null) {
            documents.remove("_id");
            Document update = new Document("$set", new Document(property, newValue));
            collection.updateOne(filter, update);
            Document updatedDocument = collection.find(filter).first();
            assert updatedDocument != null;
            return updateTransactionFromBSON(transaction, updatedDocument);
        } else {
            throw new TransactionNotFoundException("Transaction with ID " + transaction.getTranID() + " not found.");
        }
    }

    public Transaction updateTransactionFromBSON(Transaction transaction, Document documents) {
        transaction.setBookID(documents.getString("BookID"));
        transaction.setMemID(documents.getString("MemberID"));
        transaction.setIssueDate(documents.getString("IssueDate"));
        transaction.setReturnDate(documents.getString("ReturnDate"));
        return transaction;
    }

    public void deleteTransaction(Transaction transaction) throws TransactionNotFoundException {
        MongoCollection<Document> collection = database.getCollection("Transactions");
        Document filter = new Document("TransactionID", transaction.getTranID());
        Document existingDoc = collection.find(filter).first();
        if (existingDoc == null) {
            throw new TransactionNotFoundException("Transaction with ID " + transaction.getTranID() + " not found.");
        }
        collection.deleteOne(filter);
    }

    public void viewAllTransactions() {
        MongoCollection<Document> collection = database.getCollection("Transactions");
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

    public Transaction checkAvailability(Transaction transaction) throws TransactionNotFoundException {
        MongoCollection<Document> collection = database.getCollection("Transactions");
        Document filter = new Document("TransactionID", transaction.getTranID());
        Document existingDoc = collection.find(filter).first();
        if (existingDoc != null) {
            updateTransactionDetails(transaction, "Status", "Completed");
            return transaction;
        } else {
            return null;
        }
    }
}
