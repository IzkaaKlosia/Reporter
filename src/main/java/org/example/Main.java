package org.example;
import org.apache.commons.cli.*;
import org.example.model.Person;
import org.example.model.Project;
import org.example.model.Task;
import org.example.reports.Report1Generator;

import java.util.List;
import java.util.Map;

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
            //Template for CLI input
         //   Report1Generator.generateReport1(personsList, List.of(pr1, pr2));
//            listFilesFromAFolder("/home/students/j/o/jonowak/reporter/reporter-dane/2012");
        }

        if(cmd.hasOption("Report_2")) {
            System.out.println("Report_2");
        }

        if(cmd.hasOption("Report_3")) {
            System.out.println("Report_3");
        }


        //Test data
        Project pr1 = Project.builder()
                .name("Projekt1")
                .build();

        Project pr2 = Project.builder()
                .name("Projekt2")
                .build();

        Task t1 = Task.builder()
                .name("bleble")
                .timeAmount(5.0)
                .project(pr1)
                .build();

        Task t2 = Task.builder()
                .name("XD")
                .timeAmount(2.0)
                .project(pr2)
                .build();

        Person p1 = Person.builder()
                .name("Kamil Z")
                .tasks(List.of(t1, t2))
                .build();

        Person p2 = Person.builder()
                .name("Zbigniew S")
                .tasks(List.of(t1))
                .build();

        List<Person> personsList = List.of(p1, p2);

        Map<String, Double> report_1 = Report1Generator.generateReport1(personsList, List.of(pr1, pr2));
        System.out.println(report_1);
    }

}