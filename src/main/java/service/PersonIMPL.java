package service;

import exceptions.PersonExistsException;
import exceptions.PersonNotFoundException;
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

    @Override
    public Person view(Person person) throws PersonNotFoundException {
        if(pr.CheckPerson(person).isEmpty()){
            throw new PersonNotFoundException("Sorry the document requested doesnot exist");
        }else {
            return pr.ViewDetails(person);
        }
    }
}
