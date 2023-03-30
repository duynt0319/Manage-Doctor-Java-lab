/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validInformation;

import java.util.Scanner;

/**
 *
 * @author Duy
 */
public class CheckValid {
    
    private final Scanner sc = new Scanner(System.in);

    public String input() {
        String input;
        boolean flag;
        do {
            input = sc.nextLine().toUpperCase();
            if (input == null || input.isEmpty()) {
                System.out.print("your input must be valid! Please input again: ");
                flag = true;
            } else {
               break;
            }
        } while (flag);
        return input;
    }
    
      public String inputGender() {
        boolean flag;
        do {         
            String choose = sc.nextLine().toUpperCase();
            if (choose == null || choose.isEmpty()) {
                System.out.println("your input must be valid! Please input last name again!!");
                flag = true;
            } else {
                switch (choose) {
                    case "M":
                        return "Male";
                    case "F":
                        return "Female";
                    default:
                        System.out.println("Please choose again!!");
                        flag = true;
                }
            }
        } while (flag);
        return null;
    }
    public String inputGenderForUpdate() {
        boolean flag = true;
        do {
            String choose = sc.nextLine().toUpperCase();
            switch (choose) {
                case "M":
                    return "Male";
                case "F":
                    return "Female";
            }
            if (" ".trim().equals(choose)) {
                break;
            } else if (!"M".equals(choose) || !"F".equals(choose)) {
                System.out.print("Please choose again!!");
                flag = true;
            }
        } while (flag);
        return null;
    }
    public int inputChoice() {
        int choice;
        do {
            try {
                Scanner scanner = new Scanner(System.in);
                choice = scanner.nextInt();
                if (choice >= 1) {
                    break;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Your choice is not a number!");
                System.out.print("Please choose a number from 1 to 6: ");
            }
        } while (true);
        return choice;
    }
    
    public int inputSubChoice() {
        int choice;
        do {
            try {
                Scanner scanner = new Scanner(System.in);
                choice = scanner.nextInt();
                if (choice >= 1) {
                    break;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Your choice is not a number!");
                System.out.println("Please choose a number from 1 to 2");
                System.out.print("If out of range back to main menu: ");
            }
        } while (true);
        return choice;
    }
}
