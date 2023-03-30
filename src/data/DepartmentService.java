/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import validInformation.CheckValid;

public class DepartmentService implements FunctionInterface {

    private static final Map< String, Department> mapDepartment = new HashMap<>();
    private final Scanner sc = new Scanner(System.in);
    CheckValid checkValid = new CheckValid();
    String filenameDepart = "department.dat";

    public boolean checkID(String id) {
        return mapDepartment.containsKey(id);
    }
    public boolean isMapDepartNull(){
        return mapDepartment.isEmpty();
    }

    public void showContinueCreating() {
        boolean checkTrueFlase = true;
        do {
            System.out.println("Do you want to continuous create new department or go back to the main menu:");
            System.out.print("Choose [Y/N]: ");
            String continuousOrGoBack = sc.nextLine().toUpperCase();
            switch (continuousOrGoBack) {
                case "Y":
                    createDepartment();
                    break;
                case "N":
                    System.out.println("THIS IS YOUR MENU");
                    checkTrueFlase = false;
                    break;
            }
        } while (checkTrueFlase);
    }

    public void createDepartment() {
        String departmentID;

        System.out.print("Input Department ID: ");
        departmentID = checkValid.input();

        if (mapDepartment.containsKey(departmentID)) {
            System.out.println("Your input is already exist! ");
            return;
        }

        System.out.print("Input Name of Department: ");
        String departmentName = checkValid.input();

        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        String createDate = formatter.format(date);
        System.out.println("Create at: " + createDate);

        String lastUpdateDate = "Null";

        Department department = new Department(departmentID, departmentName, createDate, lastUpdateDate);
        mapDepartment.put(departmentID, department);
        System.out.println("Create Department Successfully!!!");
    }

    public void updateDepartment() {
        showFile();
        System.out.print("Input Department ID: ");
        String departmentID = checkValid.input();

        if (!mapDepartment.containsKey(departmentID)) {
            System.out.println("Your input is not exist! ");
            return;
        }

        System.out.print("Input Name of Department: ");
        String departmentName = sc.nextLine().toUpperCase();
        if (departmentName == null || departmentName.isEmpty()) {
            System.out.println("keep old value");
            departmentName = ((Department) mapDepartment.get(departmentID)).getNameDepartment();
        }

        String createDate = mapDepartment.get(departmentID).getCreateDate();

        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        String lastUpdateDate = formatter.format(date);
        System.out.println("Last Update: " + lastUpdateDate);

        Department department = new Department(departmentID, departmentName, createDate, lastUpdateDate);
        mapDepartment.put(departmentID, department);
        System.out.println("Update Department Successfully!!!");
        saveToFile(filenameDepart);
        showFile();

    }

    public void readFromFile(String filename) {
        File file = new File(filename);
        if (!file.exists() || !file.isFile()) {
            System.out.println("Path doesn't exist !!!");
            System.out.println("");
            return;
        }
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(line, "|");

                String departmentID = stk.nextToken().trim();
                String departmentName = stk.nextToken().trim();
                String createDate = stk.nextToken().trim();
                String lastUpdateDate = stk.nextToken().trim();

                Department departmentFromFile = new Department(departmentID, departmentName, createDate, lastUpdateDate);
                mapDepartment.put(departmentID, departmentFromFile);
            }
            br.close();
            fr.close();
            System.out.println("read file success");
        } catch (Exception e) {
            System.out.println("Read file fail !!!");
            System.out.println("");
        }
    }

    public void showFile() {
        Collection<Department> departmentList = mapDepartment.values();
        System.out.println("=========  DEPARTMENT LIST  ==========");
        System.out.println("|---ID---|------ Name of Department -------|-------- CreateDate --------|-------- LastUpdateDate --------|");
        for (Department object : departmentList) {
            System.out.printf("|%-8s|%-33s|%-28s|%-32s|\n",
                    object.getDepartmentID(),
                    object.getNameDepartment(),
                    object.getCreateDate(),
                    object.getLastUpdateDate());
        }
    }

    public void saveToFile(String filename) {
        Collection<Department> departmentList = mapDepartment.values();
        File file = new File(filename);
        if (!file.exists() || !file.isFile()) {
            System.out.println("Path doesn't exist !!!");
            System.out.println("");
            return;
        }
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferWriter = new BufferedWriter(fileWriter);

            if (departmentList.isEmpty()) {
                System.out.println("List of book is null!");
                return;
            }
            for (Department object : departmentList) {
                bufferWriter.write(object.printOut());
                bufferWriter.newLine();
            }
            bufferWriter.close();
            fileWriter.close();
            System.out.println("Write successful");
        } catch (IOException ex) {
            System.out.println("Can't write the file !!!");
        }
    }

    public void removeDepartment() {
       DoctorService doctorService= new DoctorService();
       showFile();
       System.out.print("input ID: ");
       String departmentId = checkValid.input();
        if (doctorService.isExistDocinDepart(departmentId)) {
            return;
        }
        deleteDepartmentById(departmentId);
    }

    private void deleteDepartmentById(String departmentId) {

        if (!mapDepartment.containsKey(departmentId)) {
            System.out.println(departmentId + " dose not exit");
            return;
        }
        boolean flag;
        do {
            System.out.println("Do you really want to delete this department (Y/N)");
            System.out.print("Please choose (Y/N): ");
            String choose = checkValid.input();
            switch (choose) {
                case "Y":
                    mapDepartment.remove(departmentId);
                    System.out.println("Delete success");
                    saveToFile(filenameDepart);
                    showFile();
                    flag = false;
                    break;
                case "N":
                    System.out.println("Delete fail");
                    flag = false;
                    break;
                default:
                    System.out.println("please input again: ");
                    flag = true;
            }
        } while (flag);
    }

    public void searchInformationDepartment() {
        Collection<Department> departmentList = mapDepartment.values();
        if (departmentList.isEmpty()) {
            System.out.println("The list is empty");
            return;
        }

        showFile();
        System.out.print("Input Department ID: ");
        String departmentId = checkValid.input();

        if (!mapDepartment.containsKey(departmentId)) {
            System.out.println(departmentId + " dose not exist");
            return;
        }

        for (Department object : departmentList) {
            if (object.getDepartmentID().equals(departmentId)) {
                System.out.printf("|%-8s|%-33s|%-28s|%-32s|\n",
                        object.getDepartmentID(),
                        object.getNameDepartment(),
                        object.getCreateDate(),
                        object.getLastUpdateDate());
            }
        }
    }

    @Override
    public void readDataFromFile(String fileName) {
        readFromFile(fileName);
    }

    @Override
    public void addNew() {
        createDepartment();
    }

    @Override
    public void update() {
        updateDepartment();
    }

    @Override
    public void delete() {
        removeDepartment();
    }

    @Override
    public void searchInformation() {
        searchInformationDepartment();
    }

    @Override
    public void saveDataToFile(String fileName) {
        saveToFile(fileName);
    }

    @Override
    public void printFile() {
        showFile();
    }
}
