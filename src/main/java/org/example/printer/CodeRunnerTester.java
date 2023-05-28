package org.example.printer;

import java.io.IOException;
import java.util.HashMap;

public class CodeRunnerTester {
    public static void main(String[] args) throws IOException {
        PrinterToExcel pr = new PrinterToExcel();

        HashMap hm = new HashMap<String, Double>();
        hm.put("Project1", 3);
        hm.put("Project2", 4);
        hm.put("Project3",3);
        pr.createAnExcelFromHashMap(hm);
    }
}
