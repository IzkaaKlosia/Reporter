package org.example;
import org.apache.commons.cli.*;
import org.example.extractor.FileChecker;
import org.example.model.Person;
import org.example.model.Project;
import org.example.model.Task;
import org.example.printer.PrinterOnConsole;
import org.example.reports.Report1Generator;
import org.example.reports.Report2Generator;
import org.example.reports.Report3Generator;
import org.example.repository.PersonRepository;

import java.util.List;

public class Main {
    public static void main(String[] args) throws ParseException {
        Options options = new Options();
        CommandLineParser parser = new DefaultParser();

        options.addOption("Report_1", false, "display current time");
        options.addOption("Report_2", false, "display current time");
        options.addOption("Report_3", false, "display current time");
        options.addOption("path", true, "display current time");

        CommandLine line = parser.parse(options, args);
        String pathFromUser = line.getOptionValue("path");

        // Person Repository from file
        FileChecker fc = new FileChecker();
        PersonRepository personRepositoryFromFile = fc.getPersonRepositoryFromString(pathFromUser == null ? "src/main/resources/2012" : pathFromUser);


        Report1Generator.generateReport1(personRepositoryFromFile);


        // Command Line Runner Script
        CommandLine cmd = parser.parse(options, args);
        if(cmd.hasOption("Report_1")) {
            System.out.println("Report_1");
            new PrinterOnConsole().printReport_1(Report1Generator.generateReport1(personRepositoryFromFile));
        }

        if(cmd.hasOption("Report_2")) {
            System.out.println("Report_2");
            new PrinterOnConsole().printReport_2(Report2Generator.generateReport2(personRepositoryFromFile));

        }

        if(cmd.hasOption("Report_3")) {
            System.out.println("Report_3");
            new PrinterOnConsole().printReport_3(Report3Generator.generateReport3(personRepositoryFromFile));
        }
    }

}