package service;

import exceptions.MemberIDChangeException;
import exceptions.PersonExistsException;
import exceptions.PersonNotFoundException;
import exceptions.UserRoleNotFoundException;
import model.Person;
import repository.PersonRepo;

public class PersonIMPL implements PersonService{
    private PersonRepo pr;
    public PersonIMPL(PersonRepo pr){
        this.pr = pr;
    }
    @Override
    public Person save(Person person) throws PersonExistsException {
        if(pr.checkPerson(person).isPresent()){
            throw new PersonExistsException("Person already exists");
        }
        return this.pr.writePerson(person);
    }

    @Override
    public Person view(Person person) throws PersonNotFoundException {
        if(pr.checkPerson(person).isEmpty()){
            throw new PersonNotFoundException("Sorry the document requested doesnot exist");
        }else {
            return pr.viewDetails(person);
        }
    }

    @Override
    public Person update(Person person,String Property,String newValue) throws PersonNotFoundException, MemberIDChangeException, UserRoleNotFoundException {
        if(Property.equals("MemID")){
            throw new MemberIDChangeException("Cannot change the Member id itself! ");
        }
        if(!(person.getRole().equals("Student"))||!(person.getRole().equals("Teacher"))){
            throw new UserRoleNotFoundException("The entered Role doesnt exist");
        }
        if(pr.checkPerson(person).isEmpty()){
            throw new PersonNotFoundException("Sorry the document requested doesnot exist");
        }
        else {
            return pr.updateDetails(person,Property,newValue);
        }
    }

    @Override
    public void delete(Person person) throws PersonNotFoundException {
        if(pr.checkPerson(person).isEmpty()){
            throw new PersonNotFoundException("Sorry the document requested doesnot exist");
        }
        pr.deletePerson(person);
    }

    @Override
    public void viewAll(String Role) throws UserRoleNotFoundException {
        if (!Role.equals("Student") && !Role.equals("Teacher")) {
            throw new UserRoleNotFoundException("The entered Role doesn't exist");
        }
        else{
            pr.viewAllPersons(Role);
        }
    }
}
