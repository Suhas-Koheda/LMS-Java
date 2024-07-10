package repository;

import Credentials.Creds;
import com.mongodb.client.*;
import exceptions.PersonNotFoundException;
import model.Person;
import org.bson.Document;

import java.util.Optional;

public class PersonRepo {
    private static final String CONNECTION_STRING = "mongodb+srv://" + Creds.getUSERNAME() + ":" + Creds.getPASSWORD() + "@test.zt5blxl.mongodb.net/?retryWrites=true&w=majority&appName=test";
    private static final String DATABASE_NAME = "PeopleData";
    private static final MongoClient mongoClient = MongoClients.create(CONNECTION_STRING);
    private static final MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);

    public Person writePerson(Person person) {
        MongoCollection<Document> collection = database.getCollection(person.getRole() + "s");
        Document doc = new Document("MemID", person.getMemID())
                .append("Name", person.getName())
                .append("Address", person.getAddress())
                .append("Email", person.getEmail())
                .append("PhoneNo", person.getPhnNo())
                .append("Role", person.getRole());
        collection.insertOne(doc);
        return person;
    }

    public Optional<Person> checkPerson(Person person) {
        MongoCollection<Document> collection = database.getCollection(person.getRole() + "s");
        Document filter = new Document("MemID", person.getMemID());
        Document existingDoc = collection.find(filter).first();
        return existingDoc == null ? Optional.empty() : Optional.of(person);
    }

    public Person viewDetails(Person person) throws PersonNotFoundException {
        MongoCollection<Document> collection = database.getCollection(person.getRole() + "s");
        Document filter = new Document("MemID", person.getMemID());
        Document document = collection.find(filter).first();
        if (document != null) {
            return updatePersonFromBSON(person, document);
        } else {
            throw new PersonNotFoundException("Sorry, the document requested does not exist");
        }
    }

    private Person updatePersonFromBSON(Person person, Document document) {
        person.setName(document.getString("Name"));
        person.setAddress(document.getString("Address"));
        person.setEmail(document.getString("Email"));
        person.setPhnNo(document.getString("PhoneNo"));
        person.setRole(document.getString("Role"));
        return person;
    }

    public Person updateDetails(Person person, String property, String newValue) throws PersonNotFoundException {
        MongoCollection<Document> collection = database.getCollection(person.getRole() + "s");
        Document filter = new Document("MemID", person.getMemID());
        Document existingDoc = collection.find(filter).first();
        if (existingDoc == null) {
            throw new PersonNotFoundException("Person with MemID " + person.getMemID() + " not found.");
        }
        Document update = new Document("$set", new Document(property, newValue));
        collection.updateOne(filter, update);
        return updatePersonFromBSON(person, collection.find(filter).first());
    }

    public void deletePerson(Person person) throws PersonNotFoundException {
        MongoCollection<Document> collection = database.getCollection(person.getRole() + "s");
        Document filter = new Document("MemID", person.getMemID());
        Document existingDoc = collection.find(filter).first();
        if (existingDoc == null) {
            throw new PersonNotFoundException("Person with MemID " + person.getMemID() + " not found.");
        }
        collection.deleteOne(filter);
    }

    public void viewAllPersons(String role) {
        MongoCollection<Document> collection = database.getCollection(role + "s");
        FindIterable<Document> documents = collection.find();
        for (Document doc : documents) {
            System.out.println(doc.toJson());
        }
    }
}
