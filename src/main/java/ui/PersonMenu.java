package ui;

import controller.PersonController;
import exceptions.MemberIDChangeException;
import exceptions.PersonExistsException;
import exceptions.PersonNotFoundException;
import exceptions.UserRoleNotFoundException;
import model.Person;
import util.Factory;
import util.ReadCSV;

import java.util.Scanner;

public class PersonMenu {

    public PersonMenu() {
    }
    public void displayMenu() {
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Menu:");
            System.out.println("1. New Registration");
            System.out.println("2. View Registration");
            System.out.println("3. Update Registration");
            System.out.println("4. Delete Registration");
            System.out.println("5. View All People according to Role");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    NewRegistration();
                    break;
                case 2:
                    ViewRegistration();
                    break;
                case 3:
                    updateRegistration();
                    break;
                case 4:
                    DeleteRegistration();
                    break;
                case 5:
                    viewAll();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
    public void NewRegistration(){
        System.out.println("Add Person to database\n 1. Add Person by manually entering his/her details \t Enter 1 \n 2. Add Person by adding details in form of CSV \t Enter 2 ");
        Scanner sc=new Scanner(System.in);
        int choice=sc.nextInt();
        PersonController pc = Factory.getPersonController();
        switch(choice) {
            case (1):{
                Person p = new Person();
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
                break;
            }
            case (2):{
                sc.nextLine();
                Person p = new Person();
                System.out.print("Enter the path of the CSV file: ");
                String path = sc.nextLine();
                try {
                    ReadCSV util = new ReadCSV();
                    p = util.readMemberfromCSV(path);
                } catch (Exception e) {
                    System.out.println("Error reading from CSV: " + e.getMessage());
                }
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
                break;
        }
    }
}
    public void ViewRegistration(){
        Person p = new Person();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter MemID: ");
        p.setMemID(sc.nextLine());
        System.out.print("Enter Role (e.g., Student): ");
        p.setRole(sc.nextLine());

        PersonController pc = Factory.getPersonController();
        try {
            Person returnedPerson=pc.view(p);
            if(returnedPerson != null){
               System.out.println(returnedPerson.toString());
            }
        }
        catch (PersonNotFoundException pe){
            System.out.println(pe.getMessage());
        }
    }

    public void updateRegistration(){
        Person p = new Person();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter MemID: \n");
        p.setMemID(sc.nextLine());
        System.out.print("Enter Role: ");
        p.setRole(sc.nextLine());
        System.out.println("Enter the value to be updated");
        String key=sc.nextLine();
        System.out.println("Enter the new value");
        String newValue=sc.nextLine();

        PersonController pc = Factory.getPersonController();
        try{
            Person updatedPerson = pc.update(p,key, newValue);
            if (updatedPerson != null) {
                System.out.println("Person updated successfully");
                System.out.println(updatedPerson.toString());
            }
        } catch (Exception pe) {
            System.out.println(pe.getMessage());
        }
    }
    public void DeleteRegistration(){
        Person p = new Person();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter MemID: ");
        p.setMemID(sc.nextLine());
        System.out.print("Enter Role: ");
        p.setRole(sc.nextLine());

        PersonController pc = Factory.getPersonController();
        try{
            pc.delete(p);
            System.out.println("Person deleted successfully");
        } catch (PersonNotFoundException pe) {
            System.out.println(pe.getMessage());
        }
    }
    public void viewAll(){
        String Role;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Role: ");
        Role=sc.nextLine();
        PersonController pc = Factory.getPersonController();
        try{
            pc.viewAll(Role);
        }
        catch(UserRoleNotFoundException ur){
            System.out.println(ur.getMessage());
        }
    }

}
