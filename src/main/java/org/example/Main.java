package org.example;
import org.apache.commons.cli.*;
import org.example.model.Person;
import org.example.model.Project;
import org.example.model.Task;
import org.example.reports.Report_1;

import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws ParseException {
        System.out.println("Hello world!");

        Options options = new Options();
        CommandLineParser parser = new DefaultParser();
        options.addOption("Report_1", false, "display current time");
        options.addOption("Report_2", false, "display current time");
        options.addOption("Report_3", false, "display current time");

        CommandLine cmd = parser.parse(options, args);
        if(cmd.hasOption("Report_1")) {
            System.out.println("Report_1");
//            listFilesFromAFolder("/home/students/j/o/jonowak/reporter/reporter-dane/2012");
        }

        if(cmd.hasOption("Report_2")) {
            System.out.println("Report_2");
        }

        if(cmd.hasOption("Report_3")) {
            System.out.println("Report_3");
        }



        var pr1 = Project.builder()
                .name("Projekt1")
                .build();

        var pr2 = Project.builder()
                .name("Projekt2")
                .build();

        var t1 = Task.builder()
                .name("bleble")
                .timeAmount(5.0)
                .projects(List.of(pr1))
                .build();

        var t2 = Task.builder()
                .name("XD")
                .timeAmount(2.0)
                .projects(List.of(pr1, pr2))
                .build();

        var p1 = Person.builder()
                .name("Kamil Z")
                .tasks(List.of(t1, t2))
                .build();

        var p2 = Person.builder()
                .name("Zbigniew S")
                .tasks(List.of(t1))
                .build();

        List<Person> personsList = List.of(p1, p2);

        Report_1 report1 = new Report_1();
        Report_1.countingHoursPerProject(personsList);

        //System.out.println(personsList.get(0));
     //   System.out.println(personsList.get(0).getTasks());
        List<Task> taskPerPerson = getTasksFromPerson(personsList);
        taskPerPerson.forEach(System.out::println);
        System.out.println(taskPerPerson.getClass());
       List<Task> filteredTasks = getTasksWithCertainProject(taskPerPerson, "Projekt1");
        for (Task filteredTask : filteredTasks) {
            System.out.println("przefiltrowany taks " + filteredTask);
        }

   /*     personsList.stream()
                .map(person -> person.getTasks())
                .filter(tasksList -> tasksList
                        .stream()
                        .filter(task -> task.getProjects()
                                .forEach(project -> project.getName().equals("Projekt1"));

                .getTasks().stream().forEach(task -> System.out.println(task.getTimeAmount()));*/
    }

    static List<Task> getTasksFromPerson(List<Person> personsList){
        return personsList.stream()
                .flatMap(p -> p.getTasks().stream())
                .toList();
    }

    static List<Task> getTasksWithCertainProject(List<Task> personsList, String projectName){
        return (List<Task>)personsList.stream()
                .flatMap(task -> task.getProjects().stream()
                        .filter(project -> project.getName().equals(projectName)));
    }
}