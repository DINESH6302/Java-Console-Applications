package App.Atm;

import java.util.*;

public class User {
    static Scanner sc = new Scanner(System.in);
    Admin admin;
    static int counter_moneyTransfer = 0;
    static int withdraw_limit = 0;
    int userPosition;

    ArrayList<String> userName = new ArrayList<>();
    ArrayList<String> userPin = new ArrayList<>();
    ArrayList<Integer> userBalance = new ArrayList<>();
    ArrayList<String> userBank = new ArrayList<>();

    ArrayList<Object> allUserStatement = new ArrayList<>();
    ArrayList<String> statement = new ArrayList<>();

    public void setUserName(String uName) {
        userName.addAll(Arrays.asList("A", "B", "C", "D"));
        if (!uName.equals(""))
            userName.add(uName);
    }

    public void setUserPin(String uPin) {
        userPin.addAll(Arrays.asList("1111", "2222", "3333", "4444"));
        if (!uPin.equals(""))
            userPin.add(uPin);
    }

    public void setUserBalance(Integer uBal) {
        userBalance.addAll(Arrays.asList(22000, 16000, 64000, 10000));
        if (!uBal.equals(-1))
            userBalance.add(uBal);
    }

    public void setUserBank(String uBank) {
        userBank.addAll(Arrays.asList("SBI", "HDFC", "SBI", "Kotak"));
        if (!uBank.equals(""))
            userBank.add(uBank);
    }

    public void userOptions(int userPosition, Admin admin) {
        this.admin = admin;
        this.userPosition = userPosition;
        boolean userExit = false;
        int option = 0;
        statement.clear();

        while (!userExit) {
            System.out.println("\nWelcome, Mr." + userName.get(userPosition));
            System.out.println("""
                    1 - Deposit
                    2 - Withdraw
                    3 - Check balance
                    4 - Transfer money
                    5 - Change pin
                    6 - Mini statement
                    7 - Back
                    Choose your action:""");

            option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1:
                    deposit();
                    break;
                case 2:
                    withdraw();
                    break;
                case 3:
                    checkBalance();
                    break;
                case 4:
                    moneyTransfer();
                    break;
                case 5:
                    changePin();
                    break;
                case 6:
                    miniStatement();
                    break;
                case 7:
                    userExit = true;
                    break;
                default:
                    System.out.println("\tInvalid Input");

            }
        }
    }

    private void deposit() {
        System.out.print("Enter amount : ");
        int amt = sc.nextInt();
        sc.nextLine();
        admin.deposit(amt);
        addStatement(amt + " deposited");
        System.out.println("\tAmount deposited successfully");
    }

    private void withdraw() {
        System.out.print("Enter amount : ");
        int amt = sc.nextInt();
        int amtTemp = amt;
        sc.nextLine();

        if (admin.totalMoney < amt)
            System.out.println("\tWithdraw is failed!, low amount in ATM");
        else if (userBalance.get(userPosition) >= amt) {
            if (!userBank.get(userPosition).equals(admin.atmBank))
                withdraw_limit++;
            if (withdraw_limit > 2) {
                System.out.println("\tYou reached max withdraw limit");
                return;
            }
            userBalance.set(userPosition, userBalance.get(userPosition) - amt);
            admin.withdraw(amt);
            int[] withdrawAmtArr = {0, 0, 0, 0};
            for (int i = 0; i < admin.notes.length; i++) {
                if (amt >= admin.notes[i] * admin.money[i] && admin.money[i] >= 1) {
                    amt -= admin.notes[i] * admin.money[i];
                    if (admin.money[i] >= 1)
                        withdrawAmtArr[i] = admin.money[i];
                    admin.money[i] = 0;
                } else if (amt <= admin.notes[i] * admin.money[i] && admin.money[i] >= 1) {
                    int numOfNotes = amt / admin.notes[i];
                    amt -= admin.notes[i] * numOfNotes;
                    admin.money[i] -= numOfNotes;
                    if (numOfNotes >= 1)
                        withdrawAmtArr[i] = numOfNotes;
                }
            }
            if (amt != 0) {
                System.out.println("Some currency notes are unavailable, try again later");
                Arrays.fill(withdrawAmtArr, 0);
            } else
                for (int j = 0; j < withdrawAmtArr.length; j++) {
                    if (withdrawAmtArr[j] != 0)
                        System.out.println(admin.notes[j] + " : " + withdrawAmtArr[j]);
                }
            addStatement(amtTemp + " withdrew");
            System.out.println("\tAmount withdrew successfully");
        } else
            System.out.println("\tWithdraw is failed!, your current balance is " + userBalance.get(userPosition));
    }

    private void checkBalance() {
        System.out.println("\tYour balance is : " + userBalance.get(userPosition));
    }

    private void moneyTransfer() {
        System.out.print("Enter recipient name : ");
        String recipientName = sc.nextLine();
        System.out.print("Enter recipient bank : ");
        String recipientBank = sc.nextLine();
        System.out.print("Enter amount : ");
        int recipientAmount = sc.nextInt();
        int recipientPosition = getReciPosition(recipientName);
        if (recipientPosition == -1)
            System.out.println("User not found!");
        else {
            if (!userBank.get(userPosition).equals(userBank.get(recipientPosition)))
                counter_moneyTransfer++;
            if (counter_moneyTransfer > 2) {
                System.out.println("Tax for the transaction : 20");
                userBalance.set(userPosition, userBalance.get(userPosition) - 20);
            }
            if (userBalance.get(userPosition) >= recipientAmount) {
                userBalance.set(userPosition, userBalance.get(userPosition) - recipientAmount);
                userBalance.set(recipientPosition, userBalance.get(recipientPosition) + recipientAmount);
                addStatement(recipientAmount + " transferred to Mr." + recipientName);
                System.out.println("Transaction succeed");
            } else
                System.out.println("Insufficient amount");
        }
    }

    private void changePin() {
        System.out.print("Enter new pin : ");
        int newPin = sc.nextInt();
        sc.nextLine();
        System.out.print("Conform pin : ");
        int conformPin = sc.nextInt();
        sc.nextLine();

        if (newPin == conformPin) {
            userPin.set(userPosition, Integer.toString(newPin));
            addStatement(" Pin changed");
            System.out.println("\tPassword changed successfully");
        } else
            System.out.println("\tPassword mismatch, try again...");
    }

    private void addStatement(String mov) {
        statement.add(mov);
    }

    private void miniStatement() {
        ArrayList<String> tempStatement = (ArrayList<String>) statement.clone();
        allUserStatement.add(tempStatement);
        if (statement.isEmpty() && allUserStatement.isEmpty()) {
            System.out.println("No statement available");
            return;
        }
        System.out.println(allUserStatement.get(userPosition));
//        for (int i = 0; i < allUserStatement.size(); i++) {
//        }
    }

    private int getReciPosition(String reciName) {
        int reciIndex = -1;
        for (int i = 0; i < userName.size(); i++) {
            if (reciName.equals(userName.get(i))) reciIndex = i;
        }
        return reciIndex;
    }
}
