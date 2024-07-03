import exceptions.DataBaseConnError;
import exceptions.PersonExistsException;
import model.Person;
import repository.PersonRepo;
import service.PersonIMPL;

public class Main {
    public static void main(String[] args) throws PersonExistsException {
        Person p =new Person();
        p.setMemID("AAA");
        p.setName("AAA");
        p.setAddress("aaa");
        p.setEmail("aaa@aaa.com");
        p.setRole("Student");
        p.setPhnNo("7396824087");
        PersonRepo pr=new PersonRepo();
        PersonIMPL ps = new PersonIMPL(pr);
        try{ps.save(p);}
        catch (PersonExistsException e){
            System.out.println(e);
        }
    }
}
