package org.example.printer;

import java.io.IOException;
import java.util.HashMap;

public class CodeRunnerTester {
    public static void main(String[] args) throws IOException {
        //PrinterToExcel pr = new PrinterToExcel();
        HashMap hm = new HashMap<String, Double>();
        hm.put("Project1", 2);
        hm.put("Project2", 3);

        for (Object key:hm.keySet()){
            System.out.println(key);
            System.out.println(hm.get(key));
        }

    }
}
