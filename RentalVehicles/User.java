package App.RentalVehicles;

import java.util.*;

public class User {
    String name;
    String password;
    int deposit;

    public User(String name, String password, int deposit) {
        this.name = name;
        this.password = password;
        this.deposit = deposit;
    }

    ArrayList<ArrayList> rentedVehicles = new ArrayList<>();
}
