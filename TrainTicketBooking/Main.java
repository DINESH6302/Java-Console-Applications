package App.TrainTicketBooking;

import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);

    //Passengers
    static ArrayList<String> userName = new ArrayList<>(List.of("A", "B", "C", "D"));
    static ArrayList<String> password = new ArrayList<>(List.of("1010", "2020", "3030", "4040"));
    static ArrayList<Integer> wallet = new ArrayList<>(List.of(1000, 500, 800, 1300));
    static ArrayList<Passenger> passengers = new ArrayList<>();
    static String currentPassenger;

    //Trains
    static ArrayList<Train> train = new ArrayList<>(List.of(new Train("CHENNAI EXP", new String[]{"COIMBATORE", "TIRUPPUR", "ERODE", "SALEM", "ARAKKONAM", "CHENNAI CTL"}, 3)));
    static int currentTrain;
    static int seatNo;

    //? Home page
    private static void homePage() {
        boolean homeExit = false;
        while (!homeExit) {
            System.out.println("\n<------------- Welcome to 🚅BookMyTrain ------------->");
            System.out.println("""
                    1 - Admin
                    2 - Passenger
                    3 - Exit""");
            System.out.print("Choose your option : ");
            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    adminPage();
                    break;
                case 2:
                    passengerPage();
                    break;
                case 3:
                    System.out.println("\tThank you for choosing us!");
                    homeExit = true;
                    break;
            }
        }

    }

    //? Admin page
    private static void adminPage() {
        //Login
        System.out.println("\n<------ ADMIN ------>");
        System.out.print("User name : ");
        String adminName = sc.nextLine();
        System.out.print("Password : ");
        String adminPassword = sc.nextLine();
        if (adminName.equals("root") && adminPassword.equals("toor")) {
            boolean adminExit = false;
            while (!adminExit) {
                System.out.println("\n<------ ADMIN ------>");
                System.out.println("\tWelcome, Admin");
                System.out.println("""
                        1 - Manage train
                        2 - Add train
                        3 - Remove train
                        4 - Back""");
                System.out.print("Choose your option : ");
                int option = sc.nextInt();
                sc.nextLine();

                switch (option) {
                    case 1:
                        manageTrain();
                        break;
                    case 2:
                        addTrain();
                        break;
                    case 3:
                        removeTrain();
                        break;
                    case 4:
                        adminExit = true;
                        break;
                }
            }
        } else {
            System.out.println("Wrong credentials!, try again");
            adminPage();
        }
    }

    //! ---------------------------------- TRAIN ----------------------------------
    //? Add train
    private static void addTrain() {
        System.out.print("\nTrain name : ");
        String trainName = sc.nextLine();

        System.out.print("Number of station : ");
        int numOfStations = sc.nextInt();
        sc.nextLine();
        String[] station = new String[numOfStations];
        for (int i = 0; i < station.length; i++) {
            System.out.print("Station " + (i + 1) + " - ");
            station[i] = sc.nextLine();
        }

        System.out.print("Number of seats : ");
        int numOfSeats = sc.nextInt();

        train.add(new Train(trainName, station, numOfSeats));
        System.out.println("\t" + trainName + " successfully added");
    }

    //? Remove train
    private static void removeTrain() {
        //Choose train
        System.out.println();
        for (int i = 0; i < train.size(); i++) {
            System.out.println(" " + (i + 1) + ". " + train.get(i).trainName + " (" + train.get(i).destination + ")");
        }
        System.out.print("\nSelect Train : ");
        int option = (sc.nextInt() - 1);
        sc.nextLine();
        System.out.println("\t" + train.get(option).trainName + " successfully removed");
        train.remove(option);
    }

    //? Manage train
    private static void manageTrain() {
        //Choose train
        System.out.println();
        for (int i = 0; i < train.size(); i++) {
            System.out.println(" " + (i + 1) + ". " + train.get(i).trainName + " (" + train.get(i).destination + ")");
        }
        System.out.print("\nSelect Train : ");
        int option = (sc.nextInt() - 1);
        sc.nextLine();
        currentTrain = option;

        //Update train
        boolean manageTrainExit = false;
        while (!manageTrainExit) {
            System.out.println("\n<------ " + train.get(currentTrain).trainName + " ------>");
            System.out.println("""
                    1 - View bookings
                    2 - View route
                    3 - Update route
                    4 - Update number of seats
                    5 - Back""");
            System.out.print("Choose your option : ");
            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    viewBookings();
                    break;
                case 2:
                    viewRoute();
                    break;
                case 3:
                    updateRoute();
                    break;
                case 4:
                    updateSeats();
                    break;
                case 5:
                    manageTrainExit = true;
                    break;
            }
        }
    }

    //? View ticket bookings
    private static void viewBookings() {
        if (!passengers.isEmpty()) {
            System.out.println("-------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%6s %19s %24s %19s %17s %11s %15s", "S.NO", "PASSENGER NAME", "DESTINATION", "TIME", "SEAT NO", "PRICE", "STATUS");
            System.out.println();
            System.out.println("-------------------------------------------------------------------------------------------------------------------------");

            for (int i = 0, j = 1; i < passengers.size(); i++) {
                Passenger curPassenger = passengers.get(i);
                if (curPassenger.trainName.equals(train.get(currentTrain).trainName)) {
                    System.out.format("%4s %13s %38s %14s %12s %14s %16ss", j, curPassenger.name, (train.get(currentTrain).route[curPassenger.from - 1] + " - " + train.get(currentTrain).route[curPassenger.to - 1]), curPassenger.time, (curPassenger.seatNo + 1), ("₹" + curPassenger.ticketPrice), curPassenger.bookingStatus);
                    System.out.println();
                    j++;
                }
            }
            System.out.println("-------------------------------------------------------------------------------------------------------------------------");
        } else System.out.println("\tNo passenger list available");
    }

    //? View train route
    private static void viewRoute() {
        System.out.println("\nTrain name : " + train.get(currentTrain).trainName);
        System.out.println("Destination : " + train.get(currentTrain).destination);
        System.out.println("Train routes : ");
        for (int i = 0; i < train.get(currentTrain).route.length; i++) {
            System.out.println("\t" + (i + 1) + ". " + train.get(currentTrain).route[i]);
        }
    }

    //? Update train route
    private static void updateRoute() {
        System.out.print("\nNumber of stations : ");
        int numOfStations = sc.nextInt();
        sc.nextLine();
        train.get(currentTrain).route = new String[numOfStations];

        for (int i = 0; i < train.get(currentTrain).route.length; i++) {
            System.out.print("Station " + (i + 1) + " - ");
            String station = sc.nextLine();
            train.get(currentTrain).route[i] = station;
        }
        System.out.println("\tStations successfully updated");
    }

    //? Update train seats
    private static void updateSeats() {
        System.out.print("\nNumber of seats : ");
        int numOfSeat = sc.nextInt();
        sc.nextLine();
        train.get(currentTrain).numOfSeats = numOfSeat;
        System.out.println("\t" + "Seats successfully updated");
    }

    //! ---------------------------------- PASSENGER ----------------------------------
    //? Passenger page
    private static void passengerPage() {
        boolean passengerExit = false;
        while (!passengerExit) {
            System.out.println("\n<------ PASSENGER ------>");
            System.out.println("""
                    1 - Login
                    2 - Create Account
                    3 - Back""");
            System.out.print("Choose your option : ");
            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    login();
                    break;
                case 2:
                    createAccount();
                    break;
                case 3:
                    System.out.println("\tThank you for choosing us!");
                    passengerExit = true;
                    break;
            }
        }

    }

    //? Login
    private static void login() {
        System.out.println("\n<------ LOGIN ------->");
        System.out.print("UserName : ");
        String uName = sc.nextLine();
        System.out.print("Password : ");
        String uPass = sc.nextLine();

        if (userName.contains(uName)) {
            if (password.get(userName.indexOf(uName)).equals(uPass)) {
                currentPassenger = uName;
                userPage();
            } else {
                System.out.println("\tPassword mismatch, try again");
                login();
            }
        } else {
            System.out.println("\tYou don't have account, create new account");
            login();
        }
    }

    //? Create account
    private static void createAccount() {
        System.out.println("\n<------ SIGNUP ------->");
        System.out.print("UserName : ");
        String newUserName = sc.nextLine();
        System.out.print("Password : ");
        String newUserPass = sc.nextLine();
        System.out.print("Conform password : ");
        String conformPass = sc.nextLine();
        System.out.print("Wallet balance : ");
        int walletMoney = sc.nextInt();
        sc.nextLine();

        if (!newUserPass.equals(conformPass)) {
            System.out.println("\tPassword mismatch, try again");
            createAccount();
        } else if (userName.contains(newUserName))
            System.out.println("\tUser " + newUserName + " already exist, LOGIN");
        else {
            userName.add(newUserName);
            password.add(conformPass);
            wallet.add(walletMoney);
            System.out.println("\tAccount created successfully, login into your account");
            login();
        }
    }

    //! ---------------------------------- TICKET BOOKING PROCESS ----------------------------------
    //? User home page
    private static void userPage() {
        boolean userPageExit = false;
        while (!userPageExit) {
            System.out.println("\n<------ Welcome Mr." + currentPassenger + " ------>");
            System.out.println("""
                    1 - Book ticket
                    2 - View bookings
                    3 - Cancel ticket
                    4 - Wallet
                    5 - Back""");
            System.out.print("Choose your option : ");
            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    if (train.get(currentTrain).waitingList.size() < 3) bookTicket();
                    else System.out.println("\tBooking has temporarily closed, come after some time");
                    break;
                case 2:
                    viewTicketBookings();
                    break;
                case 3:
                    cancelTicket();
                    break;
                case 4:
                    wallet();
                    break;
                case 5:
                    userPageExit = true;
                    break;
            }
        }
    }

    //? Book ticket
    private static void bookTicket() {
        //Choose train
        System.out.println();
        for (int i = 0; i < train.size(); i++)
            System.out.println(" " + (i + 1) + ". " + train.get(i).trainName + " (" + train.get(i).destination + ")");
        System.out.print("\nSelect train : ");
        int op = (sc.nextInt() - 1);
        sc.nextLine();
        currentTrain = op;

        //Show train info
        System.out.println("\nTrain name : " + train.get(currentTrain).trainName);
        System.out.println("Destination : " + train.get(currentTrain).destination);
        System.out.println("Stations : ");
        for (int i = 0; i < train.get(currentTrain).route.length; i++)
            System.out.println("\t" + (i + 1) + ". " + train.get(currentTrain).route[i]);

        //select station
        System.out.print("\nFROM : ");
        int from = sc.nextInt();
        System.out.print(" TO  : ");
        int to = sc.nextInt();
        sc.nextLine();
        if (from >= to) {
            System.out.println("\tChoose stations correctly");
            bookTicket();
            return;
        }

        //Check duplicate bookings
        if (!train.get(currentTrain).passengerList.isEmpty())
            for (int i = 0; i < train.get(currentTrain).passengerList.size(); i++) {
                Passenger passengerPosition = train.get(currentTrain).passengerList.get(i);
                if (passengerPosition.name.equals(currentPassenger))
                    if (passengerPosition.from == from && passengerPosition.to == to) {
                        System.out.println("\tYou have already booked ticket for the same destination");
                        bookTicket();
                        return;
                    }
            }
        //Ticket price
        int ticketPrice = (to - from) * 100;

        //Check wallet
        int walletAmt = wallet.get(userName.indexOf(currentPassenger));
        if (walletAmt >= ticketPrice) {
            String bookingStatus = "Filled";
            //Check seat availability
            if (fillSeats(from - 1, to, -1)) {
                wallet.set(userName.indexOf(currentPassenger), walletAmt - ticketPrice);
                train.get(currentTrain).passengerList.add(new Passenger(currentPassenger, from, to, train.get(currentTrain).trainName, ticketPrice, (seatNo - 1), randomTime(), walletAmt, "Filled"));
                System.out.println("\tYour booking has filled, seat number is " + seatNo);
            } else {
                bookingStatus = "Waiting list";
                train.get(currentTrain).waitingList.add(new Passenger(currentPassenger, from, to, train.get(currentTrain).trainName, ticketPrice, (seatNo - 1), randomTime(), walletAmt, "Waiting list"));
                System.out.println("\tCurrently no seats are available, you are in waiting list");
            }
            //Add passenger
            passengers.add(new Passenger(currentPassenger, from, to, train.get(currentTrain).trainName, ticketPrice, (seatNo - 1), randomTime(), walletAmt, bookingStatus));
        } else System.out.println("\tInsufficient fund in your wallet");
    }

    //? Fill seats
    private static boolean fillSeats(int from, int to, int cancel) {
        int crtTrain = currentTrain;
        if (cancel != -1)
            for (int i = 0; i < train.size(); i++)
                if (passengers.get(cancel).trainName.equals(train.get(i).trainName)) crtTrain = i;


        for (int i = 0; i < train.get(crtTrain).seat.length; i++) {
            int isFree = 0;
            for (int j = from; j < to; j++)
                if (train.get(crtTrain).seat[i][j] != 0) isFree++;
            if (isFree <= 1) {
                for (int k = from; k < to; k++)
                    train.get(crtTrain).seat[i][k] = 1;
                seatNo = i + 1;
                train.get(crtTrain).seatManage.add(new Passenger(currentPassenger, from, to));
                return true;
            } else if (isFree == 2) for (int j = 0; j < train.get(crtTrain).seatManage.size(); j++) {
                boolean isSameBoarding = false;
                for (int k = 0; k < train.get(crtTrain).seatManage.size(); k++)
                    if (train.get(crtTrain).seatManage.get(k).from == from) {
                        isSameBoarding = true;
                        break;
                    }

                if (!isSameBoarding && train.get(crtTrain).seatManage.get(i).to == from + 1) {
                    train.get(crtTrain).seat[i][from] = 0;
                    fillSeats(from, to, -1);
                    return true;
                }
            }
        }
        return false;
    }

    //? View booked tickets
    private static void viewTicketBookings() {
        int flag = 0;
        for (Passenger passenger : passengers) if (passenger.name.equals(currentPassenger)) flag++;

        if (flag != 0) {
            System.out.println("-------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%6s %17s %29s %19s %17s %11s %13s", "S.NO", "TRAIN NAME", "DESTINATION", "TIME", "SEAT NO", "PRICE", "STATUS");
            System.out.println();
            System.out.println("-------------------------------------------------------------------------------------------------------------------------");
            for (int i = 0; i < passengers.size(); i++) {
                if (passengers.get(i).name.equals(currentPassenger)) {
                    int crTrain = 0;
                    for (int k = 0; k < train.size(); k++)
                        if (train.get(k).trainName.equals(passengers.get(i).trainName)) crTrain = k;

                    System.out.format("%4s %20s %33s %15s %12s %14s %14s", i + 1, passengers.get(i).trainName, train.get(crTrain).route[passengers.get(i).from - 1] + " - " + train.get(crTrain).route[passengers.get(i).to - 1], passengers.get(i).time, passengers.get(i).seatNo + 1, ("₹" + passengers.get(i).ticketPrice), passengers.get(i).bookingStatus);
                    System.out.println();
                }
            }
            System.out.println("-------------------------------------------------------------------------------------------------------------------------");
        } else System.out.println("\tYou don't have any bookings");
    }

    //? Cancel ticket
    private static void cancelTicket() {
        if (passengers.isEmpty()) System.out.println("\tYou don't have any bookings");
        else {
            //Show bookings
            viewTicketBookings();

            //Remove from lists
            System.out.print("Cancel ticket : ");
            int cancel = (sc.nextInt() - 1);

            int crtTrain = 0;
            for (int i = 0; i < train.size(); i++)
                if (passengers.get(cancel).trainName.equals(train.get(i).trainName)) crtTrain = i;


            String nameTODelete = passengers.get(cancel).name;
            int fromTODelete = passengers.get(cancel).from;
            int toTODelete = passengers.get(cancel).to;
            int seatTODelete = passengers.get(cancel).seatNo;
            int ticketAmt = passengers.get(cancel).ticketPrice;

            //removes from passengerList
            for (Train obj : train) {
                for (int i = 0; i < obj.passengerList.size(); i++) {
                    Passenger psList = obj.passengerList.get(i);
                    if (psList.name.equals(nameTODelete) && psList.to == toTODelete && psList.from == fromTODelete)
                        obj.passengerList.remove(i);
                }
            }

            //removes from passengers
            passengers.remove(cancel);

            //removes from seatManage
            for (Train obj : train) {
                for (int i = 0; i < obj.seatManage.size(); i++) {
                    Passenger psList = obj.seatManage.get(i);
                    if (psList.name.equals(nameTODelete) && psList.to == toTODelete && (psList.from + 1) == fromTODelete)
                        obj.seatManage.remove(i);
                }
            }

            //Clear seat
            for (int j = fromTODelete - 1; j < toTODelete; j++)
                train.get(crtTrain).seat[seatTODelete][j] = 0;

            //Refund amount
            int userInd = userName.indexOf(currentPassenger);
            wallet.set(userInd, wallet.get(userInd) + ticketAmt);
            System.out.println("\tTicket canceled, amount refunded to your wallet");

            //Clear waiting list
            if (!train.get(crtTrain).waitingList.isEmpty()) {
                for (int i = 0; i < train.get(crtTrain).waitingList.size(); i++) {
                    Passenger waitingListPassenger = train.get(crtTrain).waitingList.get(i);
                    fillSeats((waitingListPassenger.from - 1), waitingListPassenger.to, cancel);

                    wallet.set(userName.indexOf(waitingListPassenger.name), waitingListPassenger.wallet - (waitingListPassenger.to - waitingListPassenger.from) * 100);
                    train.get(crtTrain).passengerList.add(new Passenger(waitingListPassenger.name, waitingListPassenger.from, waitingListPassenger.to, waitingListPassenger.trainName, waitingListPassenger.ticketPrice, (seatNo - 1), waitingListPassenger.time, waitingListPassenger.wallet, "Filled"));
                    //Change passenger
                    for (Passenger passenger : passengers)
                        if (passenger.name.equals(waitingListPassenger.name)) passenger.bookingStatus = "Filled";
                }
            }
        }
    }

    //? Random time
    private static String randomTime() {
        String min = Integer.toString((int) ((Math.random() * 60.0) + 1));
        String hr = Integer.toString((int) ((Math.random() * 12.0) + 1));

        if (min.length() <= 1) min = "0" + min;
        if (hr.length() <= 1) hr = "0" + hr;

        return hr + ":" + min;
    }

    //? wallet
    private static void wallet() {
        boolean userWalletExit = false;
        while (!userWalletExit) {
            System.out.println("\n<------ Mr." + currentPassenger + " Wallet ------>");
            System.out.println("""
                    1 - Show balance
                    2 - Add balance
                    3 - Back""");
            System.out.print("Choose your option : ");
            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                //show balance
                case 1:
                    System.out.println("\n\tYour wallet balance is : ₹" + wallet.get(userName.indexOf(currentPassenger)));
                    break;

                //add balance
                case 2:
                    System.out.print("\nEnter amount : ");
                    int amt = sc.nextInt();
                    sc.nextLine();
                    wallet.set(userName.indexOf(currentPassenger), wallet.get(userName.indexOf(currentPassenger)) + amt);
                    System.out.println("\tAmount added successfully");
                    break;
                case 3:
                    userWalletExit = true;
                    break;
            }
        }
    }


    //? Main
    public static void main(String[] args) {
        homePage();
    }
}
