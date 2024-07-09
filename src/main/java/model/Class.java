package model;

import java.util.ArrayList;
import java.util.List;

public class Class {
    private String CourseName;
    private String Slot;
    private int SlotSize;
    private List<Person> students;  // List to store students (Person objects)

    public Class() {
        this.students = new ArrayList<>();
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    public String getSlot() {
        return Slot;
    }

    public void setSlot(String slot) {
        Slot = slot;
    }

    public int getSlotSize() {
        return SlotSize;
    }

    public void setSlotSize(int slotSize) {
        SlotSize = slotSize;
    }

    public List<Person> getStudents() {
        return students;
    }

    public void setStudents(List<Person> students) {
        this.students = students;
    }

    // Method to add a student
    public void addStudent(Person student) {
        this.students.add(student);
    }

    // Method to remove a student
    public void removeStudent(Person student) {
        this.students.remove(student);
    }
}
