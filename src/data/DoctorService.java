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

/**
 *
 * @author Duy
 */
public class DoctorService implements FunctionInterface {

    private static Map<String, Doctor> mapDoctor = new HashMap<>();
    private Scanner sc = new Scanner(System.in);
    DepartmentService departmentService = new DepartmentService();
    CheckValid checkValid = new CheckValid();
    Doctor doctor = new Doctor();
    String fileDoctor = "doctor.dat";

    public boolean isDoctorIdDuplicate(String inputID) {
        return mapDoctor.containsKey(inputID);
    }

    public void showContinueCreating() {
        boolean checkTrueFlase = true;
        do {
            System.out.println("Do you want to continuous create new doctor or go back to the main menu:");
            System.out.print("Choose [Y/N]: ");
            String continuousOrGoBack = sc.nextLine().toUpperCase();
            switch (continuousOrGoBack) {
                case "Y":
                    createDoctor();
                    break;
                case "N":
                    System.out.println("THIS IS YOUR MENU");
                    checkTrueFlase = false;
                    break;
            }
        } while (checkTrueFlase);
    }

    public void createDoctor() {

        System.out.print("Input DoctorID: ");
        String doctorID = checkValid.input();

        if (mapDoctor.containsKey(doctorID)) {
            System.out.println("Your input is already exist! ");
            return;
        }

        System.out.print("Input Name of Doctor: ");
        String doctorName = checkValid.input();

        System.out.println("Please input student's gender: (Male or Female)");
        System.out.print("Please choose (M/F): ");
        String sex = checkValid.inputGender();

        System.out.print("Input address: ");
        String address = checkValid.input();

        String inputIdDepartment;
        do {
            System.out.print("Input Id Department: ");
            inputIdDepartment = checkValid.input();
            if (departmentService.isMapDepartNull()) {
                System.out.println("Department is null");
                return;
            }
            if (departmentService.checkID(inputIdDepartment)) {
                break;
            }
            System.out.println("Department id is not exist!");
        } while (!departmentService.checkID(inputIdDepartment));

        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        String createDate = date.format(formatter);
        System.out.println("create at " + createDate);

        String lastUpdateDate = "Null";

        Doctor objectDoctor = new Doctor(doctorID, doctorName, sex, address, inputIdDepartment, createDate, lastUpdateDate);
        mapDoctor.put(doctorID, objectDoctor);
        System.out.println("Create doctor Successfully!!!");
    }

    public void updateDoctor() {
        showFile();
        System.out.print("Input DoctorID: ");
        String doctorID = checkValid.input();

        if (!mapDoctor.containsKey(doctorID)) {
            System.out.println("Your input is not exist! ");
            return;
        }

        System.out.print("Input Name of Doctor: ");
        String doctorName = sc.nextLine().toUpperCase();
        if (doctorName == null || doctorName.isEmpty()) {
            System.out.println("keep the old value");
            doctorName = mapDoctor.get(doctorID).getNameDoctor();
        }

        System.out.println("Please input student's gender: (Male or Female)");
        System.out.print("Please choose (M/F): ");
        String sex = checkValid.inputGenderForUpdate();
        if (sex == null || sex.isEmpty()) {
            sex = mapDoctor.get(doctorID).getSex();
            System.out.println("keep the old value");

        }

        System.out.print("Input address: ");
        String address = sc.nextLine().toUpperCase();
        if (address == null || address.isEmpty()) {
            System.out.println("keep the old value");
            address = mapDoctor.get(doctorID).getNameDoctor();
        }

        String inputIdDepartment;
        do {
            System.out.print("Input Id Department: ");
            inputIdDepartment = sc.nextLine().toUpperCase();
            if (inputIdDepartment == null || inputIdDepartment.isEmpty()) {
                System.out.println("keep the old value");
                inputIdDepartment = mapDoctor.get(doctorID).getDepartmentID();
                break;
            }
            if (departmentService.checkID(inputIdDepartment)) {
                break;
            }
            System.out.println("Your input is not exist!");
        } while (!departmentService.checkID(inputIdDepartment));

        String createDate = mapDoctor.get(doctorID).getCreateDate();

        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        String lastUpdateDate = date.format(formatter);
        System.out.println("create at " + lastUpdateDate);

        Doctor objectDoctor = new Doctor(doctorID, doctorName, sex, address, inputIdDepartment, createDate, lastUpdateDate);
        mapDoctor.put(doctorID, objectDoctor);
        System.out.println("Create doctor Successfully!!!");
        saveToFile(fileDoctor);
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

                String doctorID = stk.nextToken().trim();
                String doctorName = stk.nextToken().trim();
                String sex = stk.nextToken().trim();
                String address = stk.nextToken().trim();
                String inputIdDepartment = stk.nextToken().trim();
                String createDate = stk.nextToken().trim();
                String lastUpdateDate = stk.nextToken().trim();

                Doctor objectDoctor = new Doctor(doctorID, doctorName, sex, address, inputIdDepartment, createDate, lastUpdateDate);
                mapDoctor.put(doctorID, objectDoctor);
            }
            fr.close();
            br.close();
            System.out.println("Read file success");
        } catch (Exception e) {
            System.out.println("Read file fail !!!");
            System.out.println("");
        }
    }

    public void showFile() {
        Collection<Doctor> dt = mapDoctor.values();
        System.out.println("-------------DOCTOR LIST-------------");
        System.out.println("|---ID---|------ Name of Doctor -------|--- SEX ---|-------- Address  -------|--- DepartmentID ---|-------- CreateDate --------|-------- LastUpdateDate --------|");
        for (Doctor doctor : dt) {
            System.out.printf("|%-8s|%-29s|%-11s|%-25s|%-20s|%-28s|%-32s|\n",
                    doctor.getDoctorID(),
                    doctor.getNameDoctor(),
                    doctor.getSex(),
                    doctor.getAddress(),
                    doctor.getDepartmentID(),
                    doctor.getCreateDate(),
                    doctor.getLastUpdateDate());
        }
    }

    public void saveToFile(String filename) {
        Collection<Doctor> doctorList = mapDoctor.values();
        
        File file = new File(filename);
        if (!file.exists() || !file.isFile()) {
            System.out.println("Path doesn't exist !!!");
            System.out.println("");
            return;
        }
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferWriter = new BufferedWriter(fileWriter);

            if (doctorList.isEmpty()) {
                System.out.println("List of book is null!");
                return;
            }
            for (Doctor object : doctorList) {
                bufferWriter.write(object.printOut());
                bufferWriter.newLine();
            }
            bufferWriter.close();
            System.out.println("Write successful");
        } catch (IOException ex) {
            System.out.println("Can't write the file !!!");
        }
    }

    private void printDoctorExistOrNot(String doctorId) {
        if (mapDoctor.containsKey(doctorId) != true) {
            System.out.println("Doctor Id dose not exist");
        } else {
            System.out.println("Doctor Id exist");
        }
    }

    public void removeDoctor() {
        showFile();
        System.out.print("Input ID: ");
        String doctorId = checkValid.input();
        deleteDoctorById(doctorId);
    }

    private void deleteDoctorById(String doctorId) {
        printDoctorExistOrNot(doctorId);
        if (!mapDoctor.containsKey(doctorId)) {
            return;
        }
        boolean flag;
        do {
            System.out.println("Do you really want to delete this doctor (Y/N)");
            System.out.print("Please choose (Y/N): ");
            String choose = sc.nextLine().toUpperCase();
            switch (choose) {
                case "Y":
                    deleteDoctor(doctorId);
                    System.out.println("Delete success");
                    saveToFile(fileDoctor);
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

    private void deleteDoctor(String doctorId) {
        mapDoctor.remove(doctorId);
    }


    public void searchInformationDoctor() {
        Collection<Doctor> docList = mapDoctor.values();
        if (docList.isEmpty()) {
            System.out.println("The list is empty");
            return;
        }
        showFile();
        System.out.print("input the name of doctor: ");
        String nameDoctor = checkValid.input();
        
        System.out.println("|---ID---|------ Name of Doctor -------|--- SEX ---|-------- Address  -------|--- DepartmentID ---|-------- CreateDate --------|-------- LastUpdateDate --------|");
        for (Doctor objectDoctor : docList) {
            if (objectDoctor.getNameDoctor().contains(nameDoctor)) {
                System.out.printf("|%-8s|%-29s|%-11s|%-25s|%-20s|%-28s|%-32s|\n",
                        objectDoctor.getDoctorID(),
                        objectDoctor.getNameDoctor(),
                        objectDoctor.getSex(),
                        objectDoctor.getAddress(),
                        objectDoctor.getDepartmentID(),
                        objectDoctor.getCreateDate(),
                        objectDoctor.getLastUpdateDate());
            }
        }
    }
    public boolean isExistDocinDepart(String deparmentId){
        for (String key : mapDoctor.keySet()) {
            if (mapDoctor.get(key).getDepartmentID().equals(deparmentId)) {
                System.out.println("Docter till in department");
                return true;
            }
        }
        return false;
    }

    @Override
    public void readDataFromFile(String fileName) {
        readFromFile(fileName);
    }

    @Override
    public void addNew() {
        createDoctor();
    }

    @Override
    public void update() {
        updateDoctor();
    }

    @Override
    public void delete() {
        removeDoctor();
    }

    @Override
    public void searchInformation() {
        searchInformationDoctor();
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
