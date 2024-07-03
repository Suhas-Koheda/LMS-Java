package repository;

import Credentials.Creds;
import com.mongodb.client.*;
import exceptions.DataBaseConnError;
import exceptions.PersonExistsException;
import exceptions.PersonNotFoundException;
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
                Document doc = new Document("MemID", person.getMemID())
                        .append("Name", person.getName())
                        .append("Address", person.getAddress())
                        .append("Email", person.getEmail())
                        .append("PhoneNo", person.getPhnNo())
                        .append("Role", person.getRole());
                collection.insertOne(doc);
            }
            if (person.getRole().equals("Teacher")) {
                MongoCollection<Document> collection = database.getCollection("Teachers");
                Document doc = new Document("MemID", person.getMemID())
                        .append("Name", person.getName())
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
        MongoCollection<Document> collection = database.getCollection(person.getRole()+"s");
        Document filter = new Document("MemID", person.getMemID());
        Document existingDoc = collection.find(filter).first();
        return existingDoc == null ? Optional.empty() : Optional.of(person);
    }
    public Person ViewDetails(Person person) throws PersonNotFoundException {
        MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
        MongoCollection<Document> collection = database.getCollection(person.getRole()+"s");
        Document filter = new Document("MemID", person.getMemID());
        Document documents = collection.find(filter).first();

        if (documents != null) {
            documents.remove("_id");
            person.setName(documents.getString("Name"));
            person.setAddress(documents.getString("Address"));
            person.setEmail(documents.getString("Email"));
            person.setPhnNo(documents.getString("PhoneNo"));
            person.setRole(documents.getString("Role"));
        } else {
            throw new PersonNotFoundException(" ");
        }
        return person;
    }

}
