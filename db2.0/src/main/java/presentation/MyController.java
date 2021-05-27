package presentation;

import model.Person;
import model.interfaces.IPerson;

public class MyController {
    private static Person person;

    public static void setPerson(Person selectedPerson) {
        person = (Person) selectedPerson;
    }

    public static IPerson getPerson(){
        return person;
    }
}
