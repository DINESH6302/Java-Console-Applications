package App.Atm;

import java.util.*;

public class AtmMachine {
    static Scanner sc = new Scanner(System.in);
    int[] notes = {2000, 500, 200, 100};
    int[] money = new int[notes.length];
    int totalMoney = 0;
    String atmBank = "SBI";

    public void addCash() {
        for (int i = 0; i < notes.length; i++) {
            System.out.print("Enter number of " + notes[i] + " notes : ");
            int m = sc.nextInt();
            sc.nextLine();
            money[i] = money[i] + m;
            totalMoney += money[i] * notes[i];
        }
        System.out.println("\tCash added successfully");
    }

    public void showBalance() {
        totalMoney = 0;
        for (int i = 0; i < notes.length; i++) {
            totalMoney += money[i] * notes[i];
            System.out.println(notes[i] + " : " + money[i]);
        }
        System.out.println("\tAvailable amount is " + totalMoney);
    }

    public void withdraw(int amt) {
        totalMoney -= amt;
    }

    public void deposit(int amt) {
        totalMoney += amt;
    }

    public void decreaseNotes(int note, int num) {
        money[note] -= num;
    }

    public void increaseNotes(int note, int num) {
        money[note] += num;
    }
}
