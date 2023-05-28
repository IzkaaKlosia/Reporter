package org.example.repository;

import lombok.Builder;
import lombok.Data;
import org.example.model.Person;
import org.example.model.Project;
import org.example.model.Task;

import java.util.List;
import java.util.stream.Collectors;

import static org.example.reports.ReportGeneratorUtils.getTasksFromPerson;

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

    // Returns Project objects from PersonRepository without repetitions
    public List<Project> getProjects(){
        return getTasksFromPerson(getPeople()).stream()
                .map(Task::getProject)
                .collect(Collectors.toSet()).stream().
                toList();
    }
}



