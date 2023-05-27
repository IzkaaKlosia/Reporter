package org.example;
import org.apache.commons.cli.*;
import org.example.model.Person;
import org.example.model.Project;
import org.example.model.Task;
import org.example.reports.Report_1;

import java.util.ArrayList;
import java.util.List;

import static org.example.FileExtractor.listFilesFromAFolder;

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

        var l1 = List.of(p1, p2);

        Report_1 report1 = new Report_1();
        Report_1.countingHoursPerProject(l1);

    }
}