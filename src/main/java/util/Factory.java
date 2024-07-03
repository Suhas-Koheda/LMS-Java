package util;

import controller.PersonController;
import model.Person;
import repository.PersonRepo;
import service.PersonIMPL;
import service.PersonService;

public class Factory {
    public static PersonRepo getPersonRepository(){
        return new PersonRepo();
    }
    public static PersonService getPersonService(){
        return new PersonIMPL(getPersonRepository());
    }
    public static PersonController getPersonController(){
        return new PersonController(getPersonService());
    }
}
