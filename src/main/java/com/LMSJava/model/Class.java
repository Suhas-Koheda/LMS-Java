package com.LMSJava.model;

public class Class {
    private String CourseName;
    private String Slot;
    private int SlotSize;
    //list of students to be created

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
}
