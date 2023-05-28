package org.example.extractor;

import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.example.model.Person;
import org.example.model.Project;
import org.example.model.Task;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;

public class DataExtractor {
    public DataExtractor() {
    }

    public Person getPersonFromFile(String path) {
        Person person = Person.builder().tasks(new ArrayList<>()).build();
        String nameToSet = reworkTheName(path);
        person.setName(nameToSet);
        try (InputStream inp = new FileInputStream(path)) {
            HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));
            ExcelExtractor extractor = new ExcelExtractor(wb);
            extractor.setFormulasNotResults(true);
            extractor.setIncludeSheetNames(false);

            for (Sheet sheet : wb) {
                Project project = Project.builder().build();
                project.setName(sheet.getSheetName());
                for (Row row : sheet) {
                    if (row.getRowNum() != 0 && checkForEmptyRows(row) == false) {
                        Task task = Task.builder().build();
                        for (Cell cell : row) {
                            if (cell.getColumnIndex() == 0) {
                                task.setDate(extractLocalDate(cell, path));
                            }
                            if (cell.getColumnIndex() == 1) {
                                task.setName(cell.toString());
                            }
                            if (cell.getColumnIndex() == 2) {
                                task.setTimeAmount(extractHours(cell, path));
                            }
                        }
                        task.setProject(project);
                        person.addTask(task);
                    }
                }
            }
            wb.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return person;
    }

    public static String reworkTheName(String path) {
        int a = path.lastIndexOf("/") + 1;
        int len = path.length();
        String nameOfEmployee = path.substring(a, len - 4);
        return nameOfEmployee.replaceAll("_", " ");
    }

    public static LocalDate extractLocalDate(Cell c, String path) {
        Sheet sheet = c.getSheet();
        String sheetName = sheet.getSheetName();
        int rowIndex = c.getRowIndex() + 1;
        try {
            LocalDate extracted = c.getLocalDateTimeCellValue().toLocalDate();
            return extracted;
        } catch (Exception e) {
            System.out.println(path + " Wrong format - date - in sheet " + sheetName + " Row " + rowIndex);
            return LocalDate.of(1, 1, 1);
        }
    }

    public static double extractHours(Cell c, String path) {
        try {
            double hours = Double.parseDouble(c.toString());
            return hours;
        } catch (NumberFormatException e) {
            Sheet sheet = c.getSheet();
            String sheetName = sheet.getSheetName();
            int rowIndex = c.getRowIndex() + 1;
            System.out.println(path + " Wrong format  - hours - in sheet " + sheetName + " Row " + rowIndex);
            double hours = 0.0;
            return hours;
        }

    }

    public static boolean checkForEmptyRows(Row r) {
        for (Cell cell : r) {
            if (cell.getColumnIndex() == 1 & cell.toString() != null) {
                return false;
            }
        }
        return true;
    }
}


