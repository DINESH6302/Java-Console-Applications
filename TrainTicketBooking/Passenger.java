package App.TicketBooking;

public class Passenger {
    String name;
    int from;
    int to;
    int wallet;
    String trainName;
    int seatNo;
    String time;
    int ticketPrice;
    String bookingStatus;

    public Passenger(String name, int from, int to, String trainName, int ticketPrice, int seatNo, String time, int wallet, String bookingStatus) {
        this.name = name;
        this.from = from;
        this.to = to;
        this.trainName = trainName;
        this.ticketPrice = ticketPrice;
        this.seatNo = seatNo;
        this.time = time;
        this.wallet = wallet;
        this.bookingStatus = bookingStatus;
    }

    public Passenger(String name, int from, int to) {
        this.name = name;
        this.from = from;
        this.to = to;
    }
}
