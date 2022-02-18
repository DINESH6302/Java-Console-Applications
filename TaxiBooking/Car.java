package App.TaxiBooking;

import java.util.ArrayList;

public class Car {
    int carId;
    int earnings;
    int freeTime;
    char currentArea;
    ArrayList<Booking> bookingList = new ArrayList<>();

    public Car(int carId) {
        this.carId = carId;
        this.earnings = 0;
        this.currentArea = 'A';
        this.freeTime = 6;
    }
}
