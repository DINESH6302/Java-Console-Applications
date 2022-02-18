package App.TaxiBooking;

import java.util.*;

public class Main {
    static ArrayList<Car> carList = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static int userId = 1;
    static final int numOfCars = 4;

    public static void main(String[] args) {
        // create cars obj
        for (int i = 1; i <= numOfCars; i++)
            carList.add(new Car(i));

        // home menu
        homeMenu();
    }

    // ? Home menu
    private static void homeMenu() {
        boolean mainExit = false;
        while (!mainExit) {
            System.out.println("\n<--------- Welcome to Speed Cabs -------->");
            System.out.println("""
                    1 - Book Taxi
                    2 - Taxi Details
                    3 - Exit""");
            System.out.print("Enter your choice : ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    bookTaxi();
                    break;
                case "2":
                    taxiDetails();
                    break;
                case "3":
                    mainExit = true;
                    break;
                default:
                    System.out.println("\tWrong Input, try again");
                    homeMenu();
            }
        }
        System.out.println("\tThank you using speed cab");
    }

    // ? Taxi booking
    private static void bookTaxi() {
        System.out.print("\nPickup Area : ");
        char pickup = sc.next().charAt(0);
        System.out.print("Drop Area : ");
        char drop = sc.next().charAt(0);
        System.out.print("Pickup Time : ");
        int pickupTime = sc.nextInt();
        sc.nextLine();

        // check area is valid
        if (pickup < 'A' || pickup > 'F' || drop < 'A' || drop > 'F') {
            System.out.println("\tInvalid location, try again");
            bookTaxi();
            return;
        }
        if (pickupTime < 6) {
            System.out.println("\tEnter time above 6.00am");
            bookTaxi();
            return;
        }

        // * assign taxi
        ArrayList<Car> freeCars = new ArrayList<Car>();
        int temp = Integer.MAX_VALUE;
        for (int i = 0; i < carList.size(); i++) {
            Car c = carList.get(i);
            // check if car is near
            if (Math.abs(c.currentArea - pickup) <= temp) {
                // check car can come at right time
                if (Math.abs(c.currentArea - pickup) + c.freeTime <= pickupTime)
                    freeCars.add(c);
                else {
                    temp = Integer.MAX_VALUE;
                    continue;
                }
                temp = Math.abs(c.currentArea - pickup);
            }
        }
        // get car for user
        if (freeCars.isEmpty()) {
            System.out.println("No car available right now, please come after some time");
            return;
        } else {
            Collections.sort(freeCars, (a, b) -> a.earnings - b.earnings);
            for (int i = 1; i < freeCars.size(); i++) {
                if (freeCars.get(i).currentArea == pickup)
                    Collections.swap(freeCars, 0, i);
            }
        }
        Car curTaxi = freeCars.get(0);

        System.out.println("\tTaxi successfully booked");
        System.out.println("\tUser id : " + userId);
        System.out.println("\tTaxi - " + curTaxi.carId + " is assigned for you");

        // update free time
        calcTime(curTaxi, pickup, drop);
        curTaxi.currentArea = drop;

        // add user in list
        curTaxi.bookingList.add(new Booking(userId++, pickup, drop, pickupTime, bill(curTaxi, pickup, drop)));
    }

    // ? Update free time
    private static void calcTime(Car curTaxi, char pickup, char drop) {
        curTaxi.freeTime += Math.abs(pickup - drop);
    }

    // ? calculate bill
    private static int bill(Car curTaxi, char pickup, char drop) {
        int amt = Math.abs(pickup - drop) < 3 ? 100 : ((Math.abs(pickup - drop) - 3) * 15) * 10 + 100;
        System.out.println("\tTotal amount is : " + amt);
        curTaxi.earnings += amt;
        return amt;
    }

    // ? Taxi details
    private static void taxiDetails() {
        System.out.println();
        for (int i = 0; i < carList.size(); i++) {
            System.out.println("\tTaxi : " + carList.get(i).carId);
            System.out.println(" --------------------------------------------------------------");
            System.out.printf("%10s %12s %9s %13s %12s ", "User ID", "Pickup", "Drop", "PickupTime", "Earning\n");
            System.out.println("--------------------------------------------------------------");
            for (int j = 0; j < carList.get(i).bookingList.size(); j++) {
                Booking bookings = carList.get(i).bookingList.get(j);
                System.out.format("%7s %12s %10s %11s %14s ", bookings.userId, bookings.pickupArea, bookings.dropArea,
                        bookings.pickupTime, bookings.amount + "\n");
            }
            System.out.println("--------------------------------------------------------------");
            System.out.println();
            System.out.println();
        }
    }
}
