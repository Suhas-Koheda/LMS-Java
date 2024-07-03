package controller;

import exceptions.MemberIDChangeException;
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
    public Person update(Person p,String Property,String newValue) throws PersonNotFoundException, MemberIDChangeException, UserRoleNotFoundException {
        return this.ps.update(p,Property,newValue);
    }
    public void delete(Person p) throws PersonNotFoundException {
        this.ps.delete(p);
    }
    public void viewALl(String Role) throws UserRoleNotFoundException {
        this.ps.viewAll(Role);
    }
}
