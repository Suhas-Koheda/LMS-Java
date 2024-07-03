package controller;

import exceptions.PersonExistsException;
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
}
