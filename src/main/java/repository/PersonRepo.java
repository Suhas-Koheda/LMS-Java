package repository;

import Credentials.Creds;
import com.mongodb.client.*;
import exceptions.*;
import model.Person;
import org.bson.Document;

import javax.management.relation.RoleNotFoundException;
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
        MongoCollection<Document> collection = database.getCollection(person.getRole()+"s");
        Document filter = new Document("MemID", person.getMemID());
        Document documents = collection.find(filter).first();

        if (documents != null) {
            documents.remove("_id");
            return UpdatePersonfromBSON(person,documents);
        } else {
            throw new PersonNotFoundException(" ");
        }
    }

    public Person UpdateDetails(Person person,String Property,String newValue) throws PersonNotFoundException, MemberIDChangeException, UserRoleNotFoundException {
        MongoCollection<Document> collection = database.getCollection(person.getRole()+"s");
        Document filter = new Document("MemID", person.getMemID());
        Document documents = collection.find(filter).first();
        if((Property.equals("MemID"))) {
            throw new MemberIDChangeException("Cannot change the Member id itself ");
        }
        if (!person.getRole().equals("Student") && !person.getRole().equals("Teacher")) {
            throw new UserRoleNotFoundException("The entered Role doesn't exist");
        }
        else{
            if (documents != null) {
                documents.remove("_id");
                Document update = new Document("$set", new Document(Property, newValue));
                collection.updateOne(filter, update);
                Document updateddocument = collection.find(filter).first();
                assert updateddocument != null;
                return UpdatePersonfromBSON(person, updateddocument);
            }
            throw new PersonNotFoundException(" ");
        }
    }

    public Person UpdatePersonfromBSON(Person person,Document documents) {
        person.setName(documents.getString("Name"));
        person.setAddress(documents.getString("Address"));
        person.setEmail(documents.getString("Email"));
        person.setPhnNo(documents.getString("PhoneNo"));
        person.setRole(documents.getString("Role"));
        return person;
    }
}
