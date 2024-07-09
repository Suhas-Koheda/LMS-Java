package service;

import model.Class;
import repository.ClassRepo;
import exceptions.ClassNotFoundException;

public class ClassIMPL implements ClassService {
    private ClassRepo classRepo;

    public ClassIMPL(ClassRepo classRepo) {
        this.classRepo = classRepo;
    }

    @Override
    public Class save(Class c) {
        return classRepo.saveClass(c);
    }

    @Override
    public Class view(String courseName) throws ClassNotFoundException {
        return classRepo.findClassByCourseName(courseName)
                .orElseThrow(() -> new ClassNotFoundException("Class with course name " + courseName + " not found."));
    }

    @Override
    public Class update(String courseName, String property, Object newValue) throws ClassNotFoundException {
        try {
            return classRepo.updateClass(courseName, property, newValue);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String courseName) throws ClassNotFoundException {
        try {
            classRepo.deleteClass(courseName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void viewAll() {
        try {
            classRepo.viewAllClasses();
        } catch (Exception e) {
            // Handle or log the exception as needed
            System.err.println("An error occurred while viewing all classes: " + e.getMessage());
        }
    }
}
