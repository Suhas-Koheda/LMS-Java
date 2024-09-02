package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Person {
    private String MemID;
    private String Name;
    private String Address;
    private String Email;
    private String PhnNo;
    private String Role;

    public Person(){}
    @Override
    public String toString() {
        return "Person{" +
                "MemID='" + MemID + '\'' +
                ", Name='" + Name + '\'' +
                ", Address='" + Address + '\'' +
                ", Email='" + Email + '\'' +
                ", PhnNo='" + PhnNo + '\'' +
                ", Role='" + Role + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(MemID, person.MemID) && Objects.equals(Name, person.Name) && Objects.equals(Address, person.Address) && Objects.equals(Email, person.Email) && Objects.equals(PhnNo, person.PhnNo) && Objects.equals(Role, person.Role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(MemID, Name, Address, Email, PhnNo, Role);
    }

    public Person(String memID){
    }

    public String getMemID() {
        return MemID;
    }

    public void setMemID(String memID) {
        MemID = memID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }



    public String getPhnNo() {
        return PhnNo;
    }

    public void setPhnNo(String phnNo) {
        PhnNo = phnNo;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

}
