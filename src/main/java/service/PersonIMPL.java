package service;

import exceptions.DataBaseConnError;
import exceptions.PersonExistsException;
import model.Person;
import repository.PersonRepo;

public class PersonIMPL implements PersonService{
    private PersonRepo pr;
    public PersonIMPL(PersonRepo pr){
        this.pr = pr;
    }
    @Override
    public Person save(Person person) throws PersonExistsException {
        if(pr.CheckPerson(person).isPresent()){
            throw new PersonExistsException("Person already exists");
        }
        return this.pr.writePerson(person);
    }
}
