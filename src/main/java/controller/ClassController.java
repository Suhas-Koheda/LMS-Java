package controller;

import model.Class;
import model.Person;
import service.ClassService;
import exceptions.ClassNotFoundException;

import java.util.List;

public class ClassController {
    private ClassService classService;

    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    public Class save(Class c) {
        return this.classService.save(c);
    }

    public Class view(String courseName) throws ClassNotFoundException {
        return this.classService.view(courseName);
    }

    public Class update(String courseName, String property, Object newValue) throws ClassNotFoundException {
        return this.classService.update(courseName, property, newValue);
    }

    public void delete(String courseName) throws ClassNotFoundException {
        this.classService.delete(courseName);
    }

    public void viewAll() {
        this.classService.viewAll();
    }

    public List<Person> addStudentsByCSV(Class c, String path) {
        return this.classService.addStudentsByCSV(c, path);
    }
}
