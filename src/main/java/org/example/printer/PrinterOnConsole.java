package org.example.printer;

import org.example.model.Person;
import org.example.reports.Report1Generator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrinterOnConsole {
    public void printReport_1(Map report1){
        var a = "Project Name " + '\t' + "Time";
        System.out.println(a);

        System.out.println("---------------------");

        for (Object key:report1.keySet()){
            System.out.print(key + " " + '\t'+'\t');
            System.out.println(report1.get(key));
        }
    }
}
