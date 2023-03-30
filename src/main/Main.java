/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import data.DepartmentService;
import data.DoctorService;
import validInformation.CheckValid;

/**
 *
 * @author Duy
 */
public class Main {

    public static void main(String[] args) {
        DoctorService doctorService = new DoctorService();
        DepartmentService departmentService = new DepartmentService();
        CheckValid checkValid = new CheckValid();  
        
        String filenameDepart = "department.dat";
        String filenameDoctor = "doctor.dat";
        
        int choice;
        do {
            mainMenu();
            choice = checkValid.inputChoice();
            switch (choice) {
                case 1: //show all
                    subMenu();
                    choice = checkValid.inputSubChoice();
                    switch (choice) {
                        case 1:
                            departmentService.readDataFromFile(filenameDepart);
                            departmentService.printFile();
                            break;
                        case 2:
                            doctorService.readFromFile(filenameDoctor);
                            doctorService.printFile();
                            break;
                    }
                    break;
                case 2: //add new
                    subMenu();
                    choice = checkValid.inputSubChoice();
                    switch (choice) {
                        case 1:
                            departmentService.addNew();
                            departmentService.showContinueCreating();
                            break;
                        case 2:
                            doctorService.addNew();
                            doctorService.showContinueCreating();
                            break;
                    }
                    break;
                case 3: // Update Information
                    subMenu();
                    choice = checkValid.inputSubChoice();
                    switch (choice) {
                        case 1:
                            departmentService.update();
                            break;
                        case 2:
                            doctorService.update();
                            break;
                    }
                    break;
                case 4:// Delete
                    subMenu();
                    choice = checkValid.inputSubChoice();
                    switch (choice) {
                        case 1:
                            departmentService.delete();
                            break;
                        case 2:
                            doctorService.delete();
                            break;
                    }
                    break;
                case 5://Search Information
                    subMenu();
                    choice = checkValid.inputSubChoice();
                    switch (choice) {
                        case 1:
                            departmentService.searchInformation();
                            break;
                        case 2:
                            doctorService.searchInformation();
                            break;
                    }
                    break;
                case 6: // Store Data to File
                    subMenu();
                    choice = checkValid.inputSubChoice();
                    switch (choice) {
                        case 1:
                            departmentService.saveDataToFile(filenameDepart);
                            break;
                        case 2:
                            doctorService.saveDataToFile(filenameDoctor);
                            break;
                    }
                    break;
            }
        } while (choice >= 1 && choice <= 6);
    }

    private static void mainMenu() {
        System.out.println("---------HOSPITAL MANAGEMENT---------");
        System.out.println("1 .Show All");
        System.out.println("2 .Add New");
        System.out.println("3 .Update Information");
        System.out.println("4 .Delete");
        System.out.println("5 .Search Information");
        System.out.println("6 .Store Data to File");
        System.out.println("Other - exit program");
        System.out.print("ENTER YOUR CHOICE: ");
    }

    private static void subMenu() {
        System.out.println("1 .Go to DepartmentService");
        System.out.println("2 .Go to DoctorService");
        System.out.print("ENTER YOUR CHOICE: ");
    }
}
