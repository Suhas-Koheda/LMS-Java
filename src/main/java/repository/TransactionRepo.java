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
            transaction.setTranID(String.valueOf(getLastTransactionID()+1));
            transaction.setStatus("Issued");
            Document doc = new Document("TransactionID", String.valueOf(getLastTransactionID()+1))
                    .append("BookID", transaction.getBookID())
                    .append("MemberID", transaction.getMemID())
                    .append("IssueDate", transaction.getIssueDate())
                    .append("ReturnDate", transaction.getReturnDate())
                    .append("Status", "Issued");
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
        transaction.setStatus(documents.getString("Status"));
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
            updateTransactionDetails(transaction, "Status", "Returned");
            return transaction;
        } else {
            return null;
        }
    }

    public void viewDetailsByID(Transaction transaction) throws TransactionNotFoundException {
        MongoCollection<Document> collection = database.getCollection("Transactions");
        Document filter = new Document("TransactionID", transaction.getTranID());
        Document existingDoc = collection.find(filter).first();
        if (existingDoc != null) {
            existingDoc.remove("_id");
            System.out.println(existingDoc.toJson());
        } else {
            throw new TransactionNotFoundException("Transaction with ID " + transaction.getTranID() + " not found.");
        }
    }

    public int getLastTransactionID() {
        MongoCollection<Document> collection = database.getCollection("Transactions");

        Document lastDocument = collection.find()
                .sort(new Document("TransactionID", -1)) // Sort by TransactionID in descending order
                .first(); // Retrieve the first result of the sorted documents

        if (lastDocument != null) {
            return Integer.parseInt(lastDocument.getString("TransactionID")); // Extract and return the TransactionID
        } else {
            return 1; // Or handle this case as appropriate (e.g., no documents found)
        }
    }
}
