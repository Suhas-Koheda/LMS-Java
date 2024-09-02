//package util;
//
//import java.awt.*;
//import java.awt.event.*;
//import exceptions.MemberIDChangeException;
//import exceptions.PersonExistsException;
//import exceptions.PersonNotFoundException;
//import exceptions.UserRoleNotFoundException;
//import model.Person;
//import util.Factory;
//import util.ReadCSV;
//import controller.PersonController;
//
//public class PersonMenu extends Frame {
//
//    // Components
//    private TextField memIDField, nameField, addressField, emailField, roleField, phoneField, csvPathField;
//    private TextArea outputArea;
//    private PersonController personController;
//
//    public PersonMenu() {
//        personController = Factory.getPersonController();
//
//        // Setting up the frame
//        setTitle("Person Menu");
//        setSize(500, 400);
//        setLayout(new BorderLayout());
//        addWindowListener(new WindowAdapter() {
//            public void windowClosing(WindowEvent we) {
//                System.exit(0);
//            }
//        });
//
//        // Create UI components
//        initializeUI();
//    }
//
//    private void initializeUI() {
//        // Top Panel for Menu
//        Panel menuPanel = new Panel();
//        menuPanel.setLayout(new GridLayout(2, 3));
//        Button newRegButton = new Button("New Registration");
//        Button viewRegButton = new Button("View Registration");
//        Button updateRegButton = new Button("Update Registration");
//        Button deleteRegButton = new Button("Delete Registration");
//        Button viewAllButton = new Button("View All People by Role");
//        Button exitButton = new Button("Exit");
//
//        menuPanel.add(newRegButton);
//        menuPanel.add(viewRegButton);
//        menuPanel.add(updateRegButton);
//        menuPanel.add(deleteRegButton);
//        menuPanel.add(viewAllButton);
//        menuPanel.add(exitButton);
//
//        // Center Panel for Inputs
//        Panel inputPanel = new Panel();
//        inputPanel.setLayout(new GridLayout(6, 2));
//        inputPanel.add(new Label("MemID:"));
//        memIDField = new TextField();
//        inputPanel.add(memIDField);
//
//        inputPanel.add(new Label("Name:"));
//        nameField = new TextField();
//        inputPanel.add(nameField);
//
//        inputPanel.add(new Label("Address:"));
//        addressField = new TextField();
//        inputPanel.add(addressField);
//
//        inputPanel.add(new Label("Email:"));
//        emailField = new TextField();
//        inputPanel.add(emailField);
//
//        inputPanel.add(new Label("Role:"));
//        roleField = new TextField();
//        inputPanel.add(roleField);
//
//        inputPanel.add(new Label("Phone Number:"));
//        phoneField = new TextField();
//        inputPanel.add(phoneField);
//
//        // Bottom Panel for Output
//        Panel outputPanel = new Panel();
//        outputPanel.setLayout(new BorderLayout());
//        outputArea = new TextArea();
//        outputPanel.add(outputArea, BorderLayout.CENTER);
//
//        // Add panels to the frame
//        add(menuPanel, BorderLayout.NORTH);
//        add(inputPanel, BorderLayout.CENTER);
//        add(outputPanel, BorderLayout.SOUTH);
//
//        // Adding Action Listeners
//        newRegButton.addActionListener(e -> newRegistration());
//        viewRegButton.addActionListener(e -> viewRegistration());
//        updateRegButton.addActionListener(e -> updateRegistration());
//        deleteRegButton.addActionListener(e -> deleteRegistration());
//        viewAllButton.addActionListener(e -> viewAll());
//        exitButton.addActionListener(e -> System.exit(0));
//    }
//
//    // Method implementations for each button
//
//    private void newRegistration() {
//        String memID = memIDField.getText();
//        String name = nameField.getText();
//        String address = addressField.getText();
//        String email = emailField.getText();
//        String role = roleField.getText();
//        String phone = phoneField.getText();
//
//        Person p = new Person(memID, name, address, email, role, phone);
//        try {
//            Person saved = personController.save(p);
//            if (saved != null) {
//                outputArea.setText("Person added successfully");
//            } else {
//                outputArea.setText("Person not added");
//            }
//        } catch (PersonExistsException e) {
//            outputArea.setText("Person already exists");
//        }
//    }
//
//    private void viewRegistration() {
//        String memID = memIDField.getText();
//        String role = roleField.getText();
//
//        Person p = new Person();
//        p.setMemID(memID);
//        p.setRole(role);
//
//        try {
//            Person returnedPerson = personController.view(p);
//            if (returnedPerson != null) {
//                outputArea.setText(returnedPerson.toString());
//            }
//        } catch (PersonNotFoundException pe) {
//            outputArea.setText(pe.getMessage());
//        }
//    }
//
//    private void updateRegistration() {
//        String memID = memIDField.getText();
//        String role = roleField.getText();
//        String key = nameField.getText(); // For simplicity, using the name field as the key
//        String newValue = addressField.getText(); // Using address field as new value
//
//        Person p = new Person();
//        p.setMemID(memID);
//        p.setRole(role);
//
//        try {
//            Person updatedPerson = personController.update(p, key, newValue);
//            if (updatedPerson != null) {
//                outputArea.setText("Person updated successfully\n" + updatedPerson.toString());
//            }
//        } catch (Exception e) {
//            outputArea.setText(e.getMessage());
//        }
//    }
//
//    private void deleteRegistration() {
//        String memID = memIDField.getText();
//        String role = roleField.getText();
//
//        Person p = new Person();
//        p.setMemID(memID);
//        p.setRole(role);
//
//        try {
//            personController.delete(p);
//            outputArea.setText("Person deleted successfully");
//        } catch (PersonNotFoundException pe) {
//            outputArea.setText(pe.getMessage());
//        }
//    }
//
//    private void viewAll() {
//        String role = roleField.getText();
//
//        try {
//            personController.viewAll(role);
//            outputArea.setText("All people with role: " + role);
//            // Here, you might need to implement a method to actually display all people with the given role
//        } catch (UserRoleNotFoundException ur) {
//            outputArea.setText(ur.getMessage());
//        }
//    }
//
//    public static void main(String[] args) {
//        PersonMenu app = new PersonMenu();
//        app.setVisible(true);
//    }
//}