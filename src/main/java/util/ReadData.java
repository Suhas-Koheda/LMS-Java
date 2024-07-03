package util;

import model.Person;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class ReadData{
    public Person writePerson(Person person) {
        String connectionString = "mongodb+srv://spamsharmask:skqwerty@test.zt5blxl.mongodb.net/?retryWrites=true&w=majority&appName=test";

        // Create a MongoClient object
       try( MongoClient mongoClient = MongoClients.create(connectionString)) {

           // Connect to the database
           MongoDatabase database = mongoClient.getDatabase("PeopleData");

           // Get the collection
           MongoCollection<Document> collection = database.getCollection("Students");
           //         Insert a document
           Document doc = new Document("name",person.getName())
                   .append("ID", person.getMemID())
                   .append("Address",person.getAddress())
                   .append("Email",person.getEmail())
                   .append("PhoneNo",person.getPhnNo())
                   .append("Role",person.getRole());
           collection.insertOne(doc);
           return person;
       }
       catch (Exception e){
        System.out.println(e);
        return null;
       }
    }
}