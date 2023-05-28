package org.example.printer;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class PrinterToExcel {
    public PrinterToExcel() {
    }

    public void createAnExcelFromHashMap(HashMap data) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Report");
        File currDir = new File(".");
        String fileLocation = "src\\main\\reports\\report1.xlsx";
        //to correct


        sheet.setColumnWidth(0, 6000);
        sheet.setColumnWidth(1, 4000);

        Row header = sheet.createRow(0);

        Cell headerCell = header.createCell(0);
        headerCell.setCellValue("Project");

        headerCell = header.createCell(1);
        headerCell.setCellValue("Hours Total");

        int counter = 1;

        for (Object key:data.keySet()){
            Row rowI = sheet.createRow(counter);
            Cell cellI = rowI.createCell(0);
            Cell cellII = rowI.createCell(1);
            cellI.setCellValue(key.toString());
            cellII.setCellValue(data.get(key).toString());
            counter++;
        }

        FileOutputStream outputStream = new FileOutputStream(fileLocation);
        workbook.write(outputStream);
        workbook.close();
    }
}
