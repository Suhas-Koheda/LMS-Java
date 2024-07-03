package service;

import exceptions.DataBaseConnError;
import exceptions.PersonExistsException;
import model.Person;

public interface PersonService {
    public Person save(Person person) throws PersonExistsException;
}
