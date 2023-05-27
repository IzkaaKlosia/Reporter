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
import org.example.repository.PersonRepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class FileExtractor {

    public FileExtractor() {
    }

    public static void main(String[] args) throws IOException {
        //reworkFilesFromFolder("/home/students/j/o/jonowak/reporter/reporter-dane/2012");
//        System.out.println(arrayCreator("/home/students/j/o/jonowak/reporter/reporter-dane/2012/01/Kowalski_Jan.xls"));
//        System.out.println(getPersonFromFile("/home/students/j/o/jonowak/reporter/reporter-dane/2012/01/Kowalski_Jan.xls"));
        PersonRepository pr = getPeopleFromFolder("/home/students/j/o/jonowak/reporter/reporter-dane/2012");
        System.out.println(pr);
    }

    public static PersonRepository getPeopleFromFolder(String folderName) {
        PersonRepository repository = PersonRepository.builder().people(new ArrayList<>()).build();
        try {
            File folder = new File(folderName);
            List<File> listOfFiles = List.of(folder.listFiles());
            for (File f : listOfFiles) {
                if (f.isDirectory()) {
                    List<File> listOfFilesInFolder = List.of(f.listFiles());
                    for (File f2 : listOfFilesInFolder) {
                        if (f2.getName().endsWith("xls") || f2.getName().endsWith("xlsx")) {
                            Person person = getPersonFromFile(f2.getPath());
                            repository.addPerson(person);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Folder does not exist");
        }
        return repository;
    }

    public static Person getPersonFromFile(String path) {
        Person person = Person.builder().tasks(new ArrayList<>()).build();
        int a = path.lastIndexOf('/') + 1;
        int len = path.length();
        String nameOfEmployee = path.substring(a, len - 4);
        person.setName(nameOfEmployee);
        try (InputStream inp = new FileInputStream(path)) {
            HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));
            ExcelExtractor extractor = new ExcelExtractor(wb);
            extractor.setFormulasNotResults(true);
            extractor.setIncludeSheetNames(false);
            for (Sheet sheet : wb) {
                Project project = Project.builder().build();
                project.setName(sheet.getSheetName());
                for (Row row : sheet) {
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
            wb.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return person;
    }


    public static void extractor3(String path) {
        try (InputStream inp = new FileInputStream(path)) {
            HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));
            ExcelExtractor extractor = new ExcelExtractor(wb);
            extractor.setFormulasNotResults(true);
            extractor.setIncludeSheetNames(false);
            for (Sheet sheet : wb) {
                System.out.println(sheet.getSheetName());
                for (Row row : sheet) {
                    for (Cell cell : row) {
                        System.out.println(cell);
                    }
                }
            }
            wb.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List arrayCreator(String path) {
        int a = path.lastIndexOf('/') + 1;
        int len = path.length();
        System.out.println(path.lastIndexOf('/'));
        System.out.println(path.substring(a, len - 4));
        String nameOfEmployee = path.substring(a, len - 4);
        List listOfArrays = new ArrayList();
        try (InputStream inp = new FileInputStream(path)) {
            HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));
            ExcelExtractor extractor = new ExcelExtractor(wb);
            extractor.setFormulasNotResults(true);
            extractor.setIncludeSheetNames(false);
            for (Sheet sheet : wb) {
                System.out.println(sheet.getSheetName());
                for (Row row : sheet) {
                    List listOfCells = new ArrayList<>();
                    listOfCells.add(nameOfEmployee);
                    listOfCells.add(sheet.getSheetName());
                    for (Cell cell : row) {
                        System.out.println(cell);
                        listOfCells.add(cell);
                    }
                    listOfArrays.add(listOfCells);
                }
            }
            wb.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return listOfArrays;
    }
}



