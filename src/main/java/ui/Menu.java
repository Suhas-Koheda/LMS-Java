package ui;

import controller.PersonController;
import exceptions.PersonExistsException;
import model.Person;
import repository.PersonRepo;
import service.PersonIMPL;
import util.Factory;

import java.util.Scanner;

public class Menu {
    public Menu() {
    }
    public void NewRegistration(){
        Person p = new Person();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter MemID: ");
        p.setMemID(sc.nextLine());

        System.out.print("Enter Name: ");
        p.setName(sc.nextLine());

        System.out.print("Enter Address: ");
        p.setAddress(sc.nextLine());

        System.out.print("Enter Email: ");
        p.setEmail(sc.nextLine());

        System.out.print("Enter Role (e.g., Student): ");
        p.setRole(sc.nextLine());

        System.out.print("Enter Phone Number: ");
        p.setPhnNo(sc.nextLine());

        sc.close();
        PersonController pc = Factory.getPersonController();
        try {
            Person saved = pc.save(p);
            if (saved != null) {
                System.out.println("Person added successfully");
            } else {
                System.out.println("Person not added");
            }
        } catch (PersonExistsException e) {
            System.out.println("Person already exists");
        }
    }
}
