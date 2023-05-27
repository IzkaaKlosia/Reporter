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
import java.util.ArrayList;

public class DataExtractor {

    public DataExtractor(){}

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
                    if (row.getRowNum() != 0){
                        Task task = Task.builder().projects(new ArrayList<>()).build();
                        for (Cell cell : row) {
                            if (cell.getColumnIndex() == 0) {
                                task.setDate(cell.toString());
                            }
                            if (cell.getColumnIndex() == 1) {
                                task.setName(cell.toString());
                            }
                            if (cell.getColumnIndex() == 2) {
                                task.setTimeAmount(cell.toString());
                            }
                        }
                        task.addProject(project);
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

    public static String reworkTheName(String path){
        int a = path.lastIndexOf('\\') + 1;
        int len = path.length();
        String nameOfEmployee = path.substring(a, len - 4);
        return nameOfEmployee;
    }
}
