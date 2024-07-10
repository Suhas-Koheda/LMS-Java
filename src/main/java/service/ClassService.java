package service;

import model.Class;
import exceptions.ClassNotFoundException;
import model.Person;

import java.util.List;

public interface ClassService {
    Class save(Class c);
    Class view(String courseName) throws ClassNotFoundException;
    Class update(String courseName, String property, Object newValue) throws ClassNotFoundException;
    void delete(String courseName) throws ClassNotFoundException;
    void viewAll();
    List<Person> addStudentsByCSV(Class c, String path);
}
