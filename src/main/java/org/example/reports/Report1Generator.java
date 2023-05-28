package org.example.reports;

import org.example.model.Person;
import org.example.model.Project;
import org.example.model.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Report1Generator {

    static List<Task> getTasksFromPerson(List<Person> personsList){
        return personsList.stream()
                .flatMap(p -> p.getTasks().stream())
                .toList();
    }

    static List<Task> getTasksFromCertainProject(List<Task> taskList, String projectName){
        return taskList.stream()
                .filter(task -> task.getProject().getName().equals(projectName))
                .toList();
    }

    static Double getTimeAmountFromTasks(List<Task> tasks){
        return tasks.stream()
                .map(Task::getTimeAmount)
                .reduce(Double::sum)
                .orElseThrow();
    }

    public static Map<String, Double> generateReport1(List<Person> personsList, List<Project> projectList){
        Map<String, Double> report1 = new HashMap<>();
        for (Project project : projectList){
            List<Task> taskPerPerson = getTasksFromPerson(personsList);
            List<Task> filteredTasks = getTasksFromCertainProject(taskPerPerson, project.getName());
            Double timeForProject = getTimeAmountFromTasks(filteredTasks);
            report1.put(project.getName(), timeForProject);
        }
        return report1;
    }
}
