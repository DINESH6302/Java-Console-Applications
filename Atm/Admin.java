package App.Atm;

import java.util.*;

public class Admin extends AtmMachine {
    static Scanner sc = new Scanner(System.in);
    String userName = "admin";
    String password = "1234";
    User newUser;

    public void adminOption(User user) {
        newUser = user;
        boolean adminExit = false;
        int option = 0;
        while (!adminExit) {
            System.out.println("\nWelcome, Admin");
            System.out.println("""
                    1 - Add cash
                    2 - Show balance
                    3 - Add new user
                    4 - Back
                    Choose your option:""");
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    super.addCash();
                    break;
                case 2:
                    super.showBalance();
                    break;
                case 3:
                    addUser();
                    break;
                case 4:
                    adminExit = true;
                    break;
                default:
                    System.out.println("Invalid input");
            }
        }
    }

    public void addUser() {
        System.out.print("User name : ");
        String newUserName = sc.nextLine();
        System.out.print("User pin : ");
        String newUserPin = sc.nextLine();
        System.out.print("User bank : ");
        String newUserBank = sc.nextLine();
        System.out.print("Initial balance : ");
        int newUserBalance = sc.nextInt();
        newUser.setUserName(newUserName);
        newUser.setUserPin(newUserPin);
        newUser.setUserBalance(newUserBalance);
        newUser.setUserBank(newUserBank);
        System.out.println("\tUser " + newUserName + " successfully added");
    }

}
