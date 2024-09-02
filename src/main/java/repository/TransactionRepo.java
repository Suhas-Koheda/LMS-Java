package repository;

import Credentials.Creds;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import model.Transaction;
import org.bson.Document;

public class TransactionRepo {
    private static final String CONNECTION_STRING = "mongodb+srv://" + Creds.getUSERNAME() + ":" + Creds.getPASSWORD() + "@test.zt5blxl.mongodb.net/?retryWrites=true&w=majority&appName=test&connectTimeoutMS=30000000&socketTimeoutMS=30000000";
    private static final String DATABASE_NAME = "LibraryData";
    private static final MongoClient mongoClient = MongoClients.create(CONNECTION_STRING);
    static final MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
    public void addTransaction(Transaction transaction){
//        transaction.setIssueDate(String.valueOf(java.time.LocalDate.now()));
//        transaction.setReturnDate(String.valueOf(java.time.LocalDate.now().plusDays(7)));
        try (MongoClient mongoClient = MongoClients.create(CONNECTION_STRING)) {
            MongoCollection<Document> collection = database.getCollection("Transactions");
            Document doc= new Document("TransactionID", transaction.getTranID())
                    .append("BookID", transaction.getBookID())
                    .append("MemberID", transaction.getMemID())
                    .append("IssueDate", transaction.getIssueDate())
                    .append("ReturnDate", transaction.getReturnDate());
        } catch (Exception e) {
            throw e;
        }
    }
}
