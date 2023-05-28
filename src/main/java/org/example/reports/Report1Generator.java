package org.example.reports;

import org.example.model.Person;
import org.example.model.Project;
import org.example.model.Task;
import org.example.repository.PersonRepository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.example.reports.ReportGeneratorUtils.*;

public class Report1Generator {
    public static Map<String, Double> generateReport1(PersonRepository personRepository) {
        List<Person> personList = personRepository.getPeople();
        List<Project> projectList = personRepository.getProjects();
        Map<String, Double> report1 = new HashMap<>();

        for (Project project : projectList){
            List<Task> taskPerPerson = getTasksFromPerson(personList);
            List<Task> filteredTasks = getTasksFromCertainProject(taskPerPerson, project.getName());
            Double timeForProject = getTimeAmountFromTasks(filteredTasks);

            if(report1.containsKey(project.getName())) {
                var currentValue = report1.get(project.getName());
                report1.computeIfPresent(project.getName(), (k, v) -> currentValue + v);
            } else {
                report1.put(project.getName(), timeForProject);
            }
        }
        return report1;
    }
}
