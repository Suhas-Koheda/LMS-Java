package repository;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import exceptions.DataBaseConnError;
import exceptions.PersonExistsException;
import model.Person;
import org.bson.Document;

public class PersonRepo {

    private static final String CONNECTION_STRING = "mongodb+srv://spamsharmask:skqwerty@test.zt5blxl.mongodb.net/?retryWrites=true&w=majority&appName=test";
    private static final String DATABASE_NAME = "PeopleData";

    public Person writePerson(Person person) throws PersonExistsException, DataBaseConnError {
        try (MongoClient mongoClient = MongoClients.create(CONNECTION_STRING)) {
            MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);

            if (person.getRole().equals("Student")) {
                MongoCollection<Document> collection = database.getCollection("Students");

                // Check if the student with the same ID already exists
                Document filter = new Document("ID", person.getMemID());
                if (collection.find(filter).first() != null) {
                    throw new PersonExistsException("Student with ID " + person.getMemID() + " already exists");
                }

                // Insert the new student
                Document doc = new Document("name", person.getName())
                        .append("ID", person.getMemID())
                        .append("Address", person.getAddress())
                        .append("Email", person.getEmail())
                        .append("PhoneNo", person.getPhnNo())
                        .append("Role", person.getRole());
                collection.insertOne(doc);
            }
            if (person.getRole().equals("Teacher")) {
                MongoCollection<Document> collection = database.getCollection("Teachers");

                // Check if the teacher with the same ID already exists
                Document filter = new Document("ID", person.getMemID());
                if (collection.find(filter).first() != null) {
                    throw new PersonExistsException("Teacher with ID " + person.getMemID() + " already exists");
                }

                // Insert the new teacher
                Document doc = new Document("name", person.getName())
                        .append("ID", person.getMemID())
                        .append("Address", person.getAddress())
                        .append("Email", person.getEmail())
                        .append("PhoneNo", person.getPhnNo())
                        .append("Role", person.getRole());
                collection.insertOne(doc);
            }
            return person;
        } catch (Exception e) {
            throw new DataBaseConnError("Internal database connection error occurred: " + e.getMessage());
        }
    }
}
