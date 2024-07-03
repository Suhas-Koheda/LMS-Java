package repository;

import Credentials.Creds;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import exceptions.DataBaseConnError;
import exceptions.PersonExistsException;
import model.Person;
import org.bson.Document;

import java.util.Optional;

public class PersonRepo {
    private static final String CONNECTION_STRING = "mongodb+srv://" +Creds.getUSERNAME()+ ":" +Creds.getPASSWORD()+ "@test.zt5blxl.mongodb.net/?retryWrites=true&w=majority&appName=test&connectTimeoutMS=30000000&socketTimeoutMS=30000000";
    private static final String DATABASE_NAME = "PeopleData";
    private static final MongoClient mongoClient = MongoClients.create(CONNECTION_STRING);
    private static final MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);

    public Person writePerson(Person person) {
        try (MongoClient mongoClient = MongoClients.create(CONNECTION_STRING)) {
            if (person.getRole().equals("Student")) {
                MongoCollection<Document> collection = database.getCollection("Students");
                // Insert the new student
                Document doc = new Document("ID", person.getMemID())
                        .append("name", person.getName())
                        .append("Address", person.getAddress())
                        .append("Email", person.getEmail())
                        .append("PhoneNo", person.getPhnNo())
                        .append("Role", person.getRole());
                collection.insertOne(doc);
            }
            if (person.getRole().equals("Teacher")) {
                MongoCollection<Document> collection = database.getCollection("Teachers");
                Document doc = new Document("ID", person.getMemID())
                        .append("name", person.getName())
                        .append("Address", person.getAddress())
                        .append("Email", person.getEmail())
                        .append("PhoneNo", person.getPhnNo())
                        .append("Role", person.getRole());
                collection.insertOne(doc);
            }
            return person;
        }
        catch(Exception e){
            throw e;
        }
    }
    public Optional<Person> CheckPerson(Person person) {
        MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
        MongoCollection<Document> collection = database.getCollection(person.getRole()+"s");
        Document filter = new Document("ID", person.getMemID());
        Document existingDoc = collection.find(filter).first();
        return existingDoc == null ? Optional.empty() : Optional.of(person);
    }
}
