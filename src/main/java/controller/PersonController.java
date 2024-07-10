package controller;

import exceptions.PersonExistsException;
import exceptions.PersonNotFoundException;
import exceptions.UserRoleNotFoundException;
import model.Person;
import service.PersonService;

public class PersonController {
    private PersonService ps;

    public PersonController(PersonService ps) {
        this.ps = ps;
    }

    public Person save(Person p) throws PersonExistsException {
        return this.ps.save(p);
    }

    public Person view(Person p) throws PersonNotFoundException {
        return this.ps.view(p);
    }

    public Person update(Person p, String property, String newValue) throws Exception {
        return this.ps.update(p, property, newValue);
    }

    public void delete(Person p) throws PersonNotFoundException {
        this.ps.delete(p);
    }

    public void viewAll(String role) throws UserRoleNotFoundException {
        this.ps.viewAll(role);
    }
}
