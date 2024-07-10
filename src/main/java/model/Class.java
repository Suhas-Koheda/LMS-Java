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

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        StringBuilder studentsString = new StringBuilder();

        for (int i = 0; i < students.size(); i++) {
            studentsString.append(students.get(i).toString()).append("\n");
            if (i < students.size() - 1) {
                studentsString.append(", "); // Add a comma between students
            }
        }
        return "Class{" +
                "CourseName='" + CourseName + "\n" +
                ", Slot='" + Slot + "\n" +
                ", SlotSize=" + SlotSize +"\n"+
                ", Students=[" + studentsString.toString() + "]"+
                '}';
    }
}
