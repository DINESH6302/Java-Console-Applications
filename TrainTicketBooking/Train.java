package App.TicketBooking;

import java.util.*;

public class Train {
    String trainName;
    String destination;
    String[] route;
    int numOfSeats;
    int[][] seat;

    public Train(String trainName, String[] route, int numOfSeats) {
        this.trainName = trainName;
        this.route = route;
        this.numOfSeats = numOfSeats;
        seat = new int[numOfSeats][route.length];
        setDestination();
    }

    private void setDestination() {
        this.destination = route[0] + " ⮂ " + route[route.length - 1];
    }

    ArrayList<Passenger> seatManage = new ArrayList<>();
    ArrayList<Passenger> passengerList = new ArrayList<>();
    ArrayList<Passenger> waitingList = new ArrayList<>();
}
