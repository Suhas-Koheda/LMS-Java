package service;

import exceptions.*;
import model.Person;

public interface PersonService {
    public Person save(Person person) throws PersonExistsException;
    public Person view(Person person) throws PersonNotFoundException;
    public Person update(Person person,String Property,String newValue) throws PersonNotFoundException, MemberIDChangeException,UserRoleNotFoundException;
    public void delete(Person person) throws PersonNotFoundException;
    public void viewAll(String Role) throws UserRoleNotFoundException;
}
