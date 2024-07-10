package util;

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
                // Directly use the line as the ID
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
}
