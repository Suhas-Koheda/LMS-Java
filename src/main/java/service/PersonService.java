package service;

import exceptions.DataBaseConnError;
import exceptions.PersonNotFoundException;
import exceptions.PersonExistsException;
import model.Person;

public interface PersonService {
    public Person save(Person person) throws PersonExistsException;
    public Person view(Person person) throws PersonNotFoundException;
}
