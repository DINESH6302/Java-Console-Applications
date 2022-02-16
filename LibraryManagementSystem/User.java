package App.LibraryManagementSystem;

import java.util.*;

public class User {
    String name;
    String password;
    int amount;

    public User(String name, String password, int amount) {
        this.name = name;
        this.password = password;
        this.amount = amount;
    }

    ArrayList<ArrayList<String>> borrowedBooks = new ArrayList<>();
    ArrayList<ArrayList<String>> borrowedHistory = new ArrayList<>();
    ArrayList<ArrayList<String>> penaltyList = new ArrayList<>();
}
