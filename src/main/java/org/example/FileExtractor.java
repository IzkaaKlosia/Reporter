package org.example;

import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FileExtractor {

    static URL excelsPath = FileExtractor.class
            .getClassLoader().getResource("2012");

    public FileExtractor(){
    }

    public static void main(String[] args) throws IOException {
        listFilesFromAFolder(excelsPath.getPath());
        System.out.println(arrayCreator("/home/students/j/o/jonowak/reporter/reporter-dane/2012/01/Kowalski_Jan.xls"));

    }

    public static void listFilesFromAFolder(String folderName) {
        try {
            File folder = new File(folderName);
            List<File> listOfFiles = List.of(folder.listFiles());
            for (File f : listOfFiles) {
                if (f.isDirectory()){
                    List<File> listOfFiles2 = List.of(f.listFiles());
                    for (File f2:listOfFiles2){
                        if (f2.getName().endsWith("xls")){
                            System.out.println(f2.getPath());
                            System.out.println(f2.getName());
                            extractor3(f2.getPath());
                        }
                       }
                }
            }
        } catch (Exception e) {
            System.out.println("Folder does not exist");
        }
    }

//    public static void extractor(String path){
//        try (InputStream inp = new FileInputStream(path)) {
//            HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));
//            ExcelExtractor extractor = new ExcelExtractor(wb);
//            extractor.setFormulasNotResults(true);
//            extractor.setIncludeSheetNames(false);
//            String text = extractor.getText();
//            System.out.println(text);
//            wb.close();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public static void extractor3(String path){
        try (InputStream inp = new FileInputStream(path)) {
            HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));
            ExcelExtractor extractor = new ExcelExtractor(wb);
            extractor.setFormulasNotResults(true);
            extractor.setIncludeSheetNames(false);
            for (Sheet sheet : wb ) {
                System.out.println(sheet.getSheetName());
                for (Row row : sheet) {
                    for (Cell cell : row) {
                        System.out.println(cell);
                    }
                }
            }
//            String text = extractor.getText();
//            System.out.println(text);
            wb.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List arrayCreator(String path){
        List listOfArrays = new ArrayList();
        try (InputStream inp = new FileInputStream(path)) {
            HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));
            ExcelExtractor extractor = new ExcelExtractor(wb);
            extractor.setFormulasNotResults(true);
            extractor.setIncludeSheetNames(false);
            for (Sheet sheet : wb ) {
                System.out.println(sheet.getSheetName());
                for (Row row : sheet) {
                    List listOfCells = new ArrayList<>();
                    listOfCells.add(sheet.getSheetName());
                    for (Cell cell : row) {
                        System.out.println(cell);

                        listOfCells.add(cell);
                    }
                    listOfArrays.add(listOfCells);

                }

            }

//            String text = extractor.getText();
//            System.out.println(text);
            wb.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return listOfArrays;
    }

//    public static void extractor2(String path){
//        try (InputStream inp = new FileInputStream(path)) {
//            HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));
//            ExcelExtractor extractor = new ExcelExtractor(wb);
//            extractor.setFormulasNotResults(true);
//            extractor.setIncludeSheetNames(false);
//            Sheet sheet = wb.getSheetAt(0);
//            Map<Integer, String> data = new HashMap<>();
//            int i = 0;
//            for (Row row : sheet) {
//                for (Cell cell : row) {
//                    switch (cell.getCellType()) {
//
//                        case NUMERIC: break;
//                        case BOOLEAN: break;
//                        case FORMULA: break;
//                        case STRING:
//                            data.put(i,cell.getRichStringCellValue().getString());
//                    }
//                }
//                i++;
//            }
//            System.out.println(data);
//            wb.close();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }



//    public static Map readToMap(String path) throws IOException {
//            FileInputStream file = new FileInputStream(new File(path));
//            Workbook workbook = new XSSFWorkbook(file);
//            Sheet sheet = workbook.getSheetAt(0);
//
//            Map<Integer, String> data = new HashMap<>();
//            int i = 0;
//            for (Row row : sheet) {
//                for (Cell cell : row) {
//                    switch (cell.getCellType()) {
//
//                        case NUMERIC:  break;
//                        case BOOLEAN: break;
//                        case FORMULA: break;
//                        case STRING:
//                            data.put(i,cell.getRichStringCellValue().getString());
//                    }
//                }
//                i++;
//            }
//            return data;
//        }

    }

