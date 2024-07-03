import exceptions.DataBaseConnError;
import exceptions.PersonExistsException;
import model.Person;
import repository.PersonRepo;

public class Main {
    public static void main(String[] args){
        Person p =new Person();
        p.setMemID("CCC");
        p.setName("AAA");
        p.setAddress("aaa");
        p.setEmail("aaa@aaa.com");
        p.setRole("Student");
        p.setPhnNo("7396824087");
        PersonRepo pr=new PersonRepo();
        try{
            pr.writePerson(p);
            System.out.println("Person Details Entered Successfully");
        }
        catch(DataBaseConnError | PersonExistsException e){
            System.out.println(e);
        }
    }
}
