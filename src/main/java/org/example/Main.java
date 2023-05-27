package org.example;
import org.apache.commons.cli.*;

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
        }

        if(cmd.hasOption("Report_2")) {
            System.out.println("Report_2");
        }

        if(cmd.hasOption("Report_3")) {
            System.out.println("Report_3");
        }

    }
}