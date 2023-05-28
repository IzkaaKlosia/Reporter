package org.example.reports;

import org.example.model.Person;
import org.example.model.Task;

import java.util.List;

public class ReportGeneratorUtils {
    public static List<Task> getTasksFromPerson(List<Person> personsList){
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
}
