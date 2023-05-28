package org.example.extractor;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.example.model.Person;
import org.example.model.Task;
import org.example.repository.PersonRepository;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileChecker {

    private DataExtractor dataExtractor;

    public FileChecker() {
        this.dataExtractor = new DataExtractor();
    }

    public PersonRepository getPersonRepositoryFromString(String firstName) {
        PersonRepository baseRepository = PersonRepository.builder().people(new ArrayList<>()).build();
        if (!Files.exists(Path.of(firstName))) {
            System.out.println("This folder does not exist: " + firstName);
            return baseRepository;
        } else {
            PersonRepository updatedRepository = getDataFromFolder(firstName, baseRepository);
            return updatedRepository;
        }

    }

    public PersonRepository getDataFromFolder(String firstName, PersonRepository personRepository) {
        try {
            File firstFile = new File(firstName);
            if ((firstFile.getName().endsWith("xls") || firstFile.getName().endsWith("xlsx"))) {
                Person person = this.dataExtractor.getPersonFromFile(firstFile.getPath());
                String extractedName = person.getName();
                if (!doesItContainName(personRepository, extractedName)) {
                    personRepository.addPerson(person);
                } else {
                    Person oldPerson = personRepository.getPersonByName(extractedName);
                    List<Task> newTasks = person.getTasks();
                    List<Task> oldTasks = oldPerson.getTasks();
                    for (Task t : newTasks) {
                        oldTasks.add(t);
                    }
                }

            }
            if (firstFile.isDirectory()) {
                List<File> listOfFiles = List.of(firstFile.listFiles());
                for (File file : listOfFiles) {
                    getDataFromFolder(file.getPath(), personRepository);
                }
            }
        } catch (Exception e) {
            System.out.println(ExceptionUtils.getStackTrace(e));
        }
        return personRepository;
    }

    public static boolean doesItContainName(PersonRepository pr, String s) {
        for (Person p : pr.getPeople()) {
            if (p.getName().equals(s)) {
                return true;
            }
        }
        return false;
    }
}



