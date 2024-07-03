import model.Person;
import util.ReadData;

public class Main {
    public static void main(String[] args){
        Person p =new Person();
        p.setMemID("AAA");
        p.setName("AAA");
        p.setAddress("aaa");
        p.setEmail("aaa@aaa.com");
        p.setRole("aaa");
        p.setPhnNo("7396824087");
        ReadData rd= new ReadData();
        rd.writePerson(p);
        p.setMemID("BBB");
        p.setName("BB");
        p.setAddress("bbb");
        p.setEmail("aaa@aaa.com");
        p.setRole("aaa");
        p.setPhnNo("7396824087");
        rd.writePerson(p);
    }
}
