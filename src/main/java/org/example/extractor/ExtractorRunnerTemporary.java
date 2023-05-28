package org.example.extractor;

import org.example.model.Person;
import org.example.model.Task;
import org.example.repository.PersonRepository;

public class ExtractorRunnerTemporary {

    public static void main(String[] args) {
        FileChecker fc = new FileChecker();
        PersonRepository pr = fc.getPersonRepositoryFromString("src/main/resources/2012/");
        for (Person p : pr.getPeople()) {
            System.out.println(p.getName());
            for (Task t : p.getTasks()) {
                System.out.println(t);
            }
        }
    }

}
