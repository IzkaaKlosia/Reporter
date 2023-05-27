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
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileChecker {

    private DataExtractor dataExtractor;

    public FileChecker() {
        this.dataExtractor = new DataExtractor();
    }

//    public static void main(String[] args) throws IOException {
//        PersonRepository pr = getRepositoryFromString("src/main/resources/2012");
//        System.out.println(pr);
//    }

    public PersonRepository getRepositoryFromString(String firstName){
        PersonRepository baseRepository = PersonRepository.builder().people(new ArrayList<>()).build();
        if (!Files.exists(Path.of(firstName))){
            System.out.println("This folder does not exist: " +firstName);
            return baseRepository;
        }
        else {
            PersonRepository updatedRepository = getDataFromFolder(firstName,baseRepository);
            return updatedRepository;
         }

    }

    public PersonRepository getDataFromFolder(String firstName, PersonRepository personRepository) {
        try {
            File firstFile = new File(firstName);
            if ((firstFile.getName().endsWith("xls") || firstFile.getName().endsWith("xlsx")) ){
                Person person = this.dataExtractor.getPersonFromFile(firstFile.getPath());
                personRepository.addPerson(person);
            }
            if (firstFile.isDirectory()){
                List<File> listOfFiles = List.of(firstFile.listFiles());
                for (File file: listOfFiles){
                    getDataFromFolder(file.getPath(), personRepository);
                }
            }
        } catch (Exception e) {
            System.out.println("Folder does not exist");
        }
        return personRepository;
    }


}



