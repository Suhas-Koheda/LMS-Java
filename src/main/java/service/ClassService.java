package service;

import model.Class;
import exceptions.ClassNotFoundException;

public interface ClassService {
    Class save(Class c);
    Class view(String courseName) throws ClassNotFoundException;
    Class update(String courseName, String property, Object newValue) throws ClassNotFoundException;
    void delete(String courseName) throws ClassNotFoundException;
    void viewAll();
}
