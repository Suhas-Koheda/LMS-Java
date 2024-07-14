package util;

import model.Book;
import model.Person;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadCSV {
    public List<Person> readFromCSV(String path) {
        List<Person> personList = new ArrayList<>();
        String line;


        path = path.replaceAll("^\"|\"$", "");

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while ((line = br.readLine()) != null) {
                Person person = new Person();
                person.setMemID(line.trim());  // Use trim() to remove any leading/trailing whitespace
                person.setRole("Student");
                personList.add(person);
            }
        } catch (IOException e) {
            System.out.println("File not found or error accessing the file at path: " + path);
            e.printStackTrace();
            System.exit(0);
        }

        return personList;
    }

    public Person readMemberfromCSV(String path){
        Person person = new Person();
        String line;

        path = path.replaceAll("^\"|\"$", "");

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                person.setMemID(values[0].trim());
                person.setName(values[1].trim());
                person.setAddress(values[2].trim());
                person.setEmail(values[3].trim());
                person.setPhnNo(values[4].trim());
                person.setRole(values[5].trim());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return person;
    }
    public Book readBookfromCSV(String path){
        Book book = new Book();
        String line;

        path = path.replaceAll("^\"|\"$", "");

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                book.setID(values[0].trim());
                book.setTitle(values[1].trim());
                book.setISBN(values[2].trim());
                book.setAuthor(values[3].trim());
                book.setCategory(values[4].trim());
                book.setStatus(values[5].trim());
                book.setPubDate(values[6].trim());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return book;
    }
}
