package org.example.reports;

import org.example.model.Person;
import org.example.model.Task;
import org.example.repository.PersonRepository;

import java.util.*;
import java.util.stream.Collectors;

import static org.example.reports.ReportGeneratorUtils.getTimeAmountFromTasks;

public class Report3Generator {
    public static Map<String, Double> generateReport3(PersonRepository personRepository){
        var allTasks = ReportGeneratorUtils.getTasksFromPerson(personRepository.getPeople());

        Map <String, Double> report3 = new HashMap<>();

        for(Task task : allTasks){
            if(report3.containsKey(task.getName())) {
                var currentValue = report3.get(task.getName());
                report3.computeIfPresent(task.getName(), (k, v) -> currentValue + v);
            } else {
                report3.put(task.getName(), task.getTimeAmount());
            }
        }
        return report3;
    }
}
