package App.RentalVehicles;

import java.util.*;

public class Vehicles {
    ArrayList<AddVehicles> cars = new ArrayList<>();
    ArrayList<AddVehicles> bikes = new ArrayList<>();
    ArrayList<AddVehicles> vehiclesInService = new ArrayList<>();

}

class AddVehicles {
    String type;
    String name;
    String number;
    int qty;
    int rentPerDay;

    public AddVehicles(String type, String name, String number, int qty, int rentPerDay) {
        this.type = type;
        this.name = name;
        this.number = number;
        this.qty = qty;
        this.rentPerDay = rentPerDay;
    }
}
