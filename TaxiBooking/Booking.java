package App.TaxiBooking;

public class Booking {
    int userId;
    char pickupArea;
    char dropArea;
    int pickupTime;
    int amount;

    public Booking(int userId, char pickupArea, char dropArea, int pickupTime, int amount) {
        this.userId = userId;
        this.pickupArea = pickupArea;
        this.dropArea = dropArea;
        this.pickupTime = pickupTime;
        this.amount = amount;
    }
}
