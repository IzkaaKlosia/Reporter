package org.example.repository;

import lombok.Builder;
import lombok.Data;
import org.example.model.Person;

import java.util.List;
@Data
@Builder
public class PersonRepository {
    private List<Person> people;

    public void addPerson(Person person){
        people.add(person);
    }

    public Person getPersonByName(String name) {
        for (Person person : people) {
            if (person.getName().equals(name)) {
                return person;
            }
        }
        return null;
        }
}



