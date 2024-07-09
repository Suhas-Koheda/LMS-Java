package repository;

import Credentials.Creds;
import com.mongodb.client.*;
import model.Class;
import model.Person;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClassRepo {
    private static final String CONNECTION_STRING = "mongodb+srv://"+Creds.getUSERNAME() +":"+ Creds.getPASSWORD() +"@test.zt5blxl.mongodb.net/?retryWrites=true&w=majority&appName=test&connectTimeoutMS=30000000&socketTimeoutMS=30000000";
    private static final String DATABASE_NAME = "SchoolData";
    private static final MongoClient mongoClient = MongoClients.create(CONNECTION_STRING);
    private static final MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);

    public Class saveClass(Class c) {
        MongoCollection<Document> collection = database.getCollection("Classes");

        // Create a Document from the Class object
        Document doc = new Document("CourseName", c.getCourseName())
                .append("Slot", c.getSlot())
                .append("SlotSize", c.getSlotSize())
                .append("Students", toDocumentList(c.getStudents()));  // Convert students (Person objects) to Document list

        collection.insertOne(doc);
        return c;
    }

    public Optional<Class> findClassByCourseName(String courseName) {
        MongoCollection<Document> collection = database.getCollection("Classes");
        Document filter = new Document("CourseName", courseName);
        Document document = collection.find(filter).first();

        if (document != null) {
            return Optional.of(fromDocument(document));
        } else {
            return Optional.empty();
        }
    }

    public Class updateClass(String courseName, String property, Object newValue) throws Exception {
        MongoCollection<Document> collection = database.getCollection("Classes");
        Document filter = new Document("CourseName", courseName);

        if (property.equals("Students")) {
            throw new Exception("Cannot update students list using this method.");
        }

        Document update = new Document("$set", new Document(property, newValue));
        collection.updateOne(filter, update);

        // Retrieve the updated document
        Document updatedDocument = collection.find(filter).first();
        if (updatedDocument != null) {
            return fromDocument(updatedDocument);
        } else {
            throw new Exception("Class not found for update.");
        }
    }

    public void deleteClass(String courseName) throws Exception {
        MongoCollection<Document> collection = database.getCollection("Classes");
        Document filter = new Document("CourseName", courseName);
        Document existingDoc = collection.find(filter).first();
        if (existingDoc == null) {
            throw new Exception("Class with CourseName " + courseName + " not found.");
        }
        collection.deleteOne(filter);
    }

    public void viewAllClasses() {
        MongoCollection<Document> collection = database.getCollection("Classes");
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

    private Class fromDocument(Document doc) {
        Class c = new Class();
        c.setCourseName(doc.getString("CourseName"));
        c.setSlot(doc.getString("Slot"));
        c.setSlotSize(doc.getInteger("SlotSize"));

        List<Document> studentDocs = (List<Document>) doc.get("Students");
        if (studentDocs != null) {
            List<Person> students = fromDocumentList(studentDocs);
            c.setStudents(students);
        } else {
            c.setStudents(new ArrayList<>());
        }
        return c;
    }

    private List<Person> fromDocumentList(List<Document> docs) {
        List<Person> students = new ArrayList<>();
        for (Document doc : docs) {
            Person p = new Person();
            p.setMemID(doc.getString("MemID"));
            p.setName(doc.getString("Name"));
            p.setAddress(doc.getString("Address"));
            p.setEmail(doc.getString("Email"));
            p.setPhnNo(doc.getString("PhoneNo"));
            p.setRole(doc.getString("Role"));
            students.add(p);
        }
        return students;
    }

    private List<Document> toDocumentList(List<Person> students) {
        List<Document> docs = new ArrayList<>();
        for (Person p : students) {
            Document doc = new Document("MemID", p.getMemID())
                    .append("Name", p.getName())
                    .append("Address", p.getAddress())
                    .append("Email", p.getEmail())
                    .append("PhoneNo", p.getPhnNo())
                    .append("Role", p.getRole());
            docs.add(doc);
        }
        return docs;
    }
}
