package App.RentalVehicles;

import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);

    //User
    static ArrayList<User> users = new ArrayList<>(List.of(new User("A", "1111", 35000), new User("B", "2222", 30000)));
    private static User currentUser;

    //Admin
    static Admin admin = new Admin("root", "toor");

    //Vehicles
    static Vehicles vehicles = new Vehicles();
    static int vhlIndex;
    static int vhlType;

    //default vhl
    static {
        vehicles.cars.add(new AddVehicles("Car", "Mercedes-Benz GLA", "TN/04C/6302", 5, 5000));
        vehicles.cars.add(new AddVehicles("Car", "Audi RS Q8", "TN/16M/5245", 8, 3200));
        vehicles.bikes.add(new AddVehicles("Bike", "Kawasaki Ninja 300", "TN/08/E4654", 6, 4000));
        vehicles.bikes.add(new AddVehicles("Bike", "Royal Enfield Himalayan", "TN/87H/9822", 10, 2000));
        vehicles.cars.add(new AddVehicles("Car", "Range Rover Velar", "TN/32P/1715", 9, 6500));
        vehicles.cars.add(new AddVehicles("Car", "Volvo S60", "TN/06I/7721", 12, 3100));
        vehicles.cars.add(new AddVehicles("Car", "BMW X6 M50i", "TN/96U/2581", 4, 7000));
        vehicles.bikes.add(new AddVehicles("Bike", "KTM 390 Adventure", "TN/25G/3563", 8, 1500));
        vehicles.bikes.add(new AddVehicles("Bike", "Bajaj Pulsar RS200", "TN/85K/5485", 15, 1000));
    }

    //? Main
    public static void main(String[] args) {
        homeMenu();
    }

    //? Home menu
    private static void homeMenu() {
        boolean mainExit = false;
        while (!mainExit) {
            System.out.println("\n\n<-------- Welcome To Rental Vehicles --------->");
            System.out.println("""
                    1 - Admin
                    2 - User
                    3 - Exit""");
            System.out.print("Choose your option : ");
            String option = sc.nextLine();

            switch (option) {
                case "1":
                    admin();
                    break;
                case "2":
                    userLogin();
                    user();
                    break;
                case "3":
                    mainExit = true;
                    System.out.println("\tThank you for using us");
                    break;
                default:
                    System.out.println("\tInvalid input");
                    homeMenu();
            }
        }
    }

    //!---------------------------------- Admin ----------------------------------
    //? Admin menu
    private static void admin() {
        System.out.println("\n\n<------ Admin Login ------>");
        System.out.print("User name : ");
        String userName = sc.nextLine();
        System.out.print("Password : ");
        String password = sc.nextLine();

        if (!admin.name.equals(userName) || !admin.password.equals(password)) {
            System.out.println("\tWrong credentials, try again");
            admin();
        }

        boolean adminExit = false;
        while (!adminExit) {
            System.out.println("\n\n<------ Welcome, Admin ------>");
            System.out.println("""
                    1 - View vehicles
                    2 - Add vehicle
                    3 - Remove vehicle
                    4 - Update vehicle
                    5 - View borrowed vehicles
                    6 - Service
                    7 - Back""");
            System.out.print("Choose your option : ");
            String option = sc.nextLine();

            switch (option) {
                case "1":
                    viewVehiclesMenu("Admin", 1);
                    break;
                case "2":
                    addVehicle();
                    break;
                case "3":
                    removeVehicle();
                    break;
                case "4":
                    updateVehicle();
                    break;
                case "5":
                    viewRentedVehicle();
                    break;
                case "6":
                    service();
                    break;
                case "7":
                    adminExit = true;
                    break;
                default:
                    System.out.println("\tInvalid input");
            }
        }
    }

    //? Vehicles catalog
    private static void viewVehiclesMenu(String userType, int s) {
        boolean showVehiclesExit = false;
        while (!showVehiclesExit) {
            System.out.println("\n\n<------ " + userType + " ------>");
            System.out.println("""
                    1 - Cars
                    2 - Bikes
                    3 - Back""");
            System.out.print("Choose your option : ");
            String option = sc.nextLine();
            switch (option) {
                case "1":
                    showVehicles("Car", s);
                    vhlType = 1;
                    break;
                case "2":
                    showVehicles("Bike", s);
                    vhlType = 2;
                    break;
                case "3":
                    showVehiclesExit = true;
                    break;
                default:
                    System.out.println("\tInvalid input");
            }
        }
    }

    //? Show Vehicles
    private static void showVehicles(String type, int s) {
        if (vehicles.cars.isEmpty()) {
            System.out.println("\tN/A");
            return;
        }

        //decide vehicle type
        ArrayList<AddVehicles> vhl;
        if (type.equals("Car")) vhl = vehicles.cars;
        else vhl = vehicles.bikes;

        // show that vehicle
        System.out.println("\n-------------------------------------------------------------------------------");
        System.out.printf("%7s %26s %21s %20s", "S.NO", (vhl.get(0).type).toUpperCase() + " NAME", "NUMBER", "AVAILABILITY\n");
        System.out.println("-------------------------------------------------------------------------------");
        for (int i = 0; i < vhl.size(); i++)
            System.out.format("%5s %31s %20s %14s", (i + 1), vhl.get(i).name, vhl.get(i).number, vhl.get(i).qty + "\n");
        System.out.println("-------------------------------------------------------------------------------");

        if (s == 1) pressEnter();
    }

    //? Add vehicle
    private static void addVehicle() {
        System.out.println("\n<---- Add Vehicles ---->");
        System.out.println("""
                1 - Cars
                2 - Bikes
                3 - Back""");
        System.out.print("Select vehicle : ");
        int option = sc.nextInt();
        sc.nextLine();

        if (option == 1) {
            System.out.println("\n<------ Add Cars ----->");
            System.out.print("Car name : ");
            String carName = sc.nextLine();
            System.out.print("Number : ");
            String carNumber = sc.nextLine();
            System.out.print("KM : ");
            System.out.print("Quantity : ");
            int carQty = sc.nextInt();
            sc.nextLine();
            System.out.print("Rent per day : ");
            int carRent = sc.nextInt();
            sc.nextLine();

            vehicles.cars.add(new AddVehicles("Car", carName, carNumber, carQty, carRent));
            System.out.println("\t" + carName + " added");
            pressEnter();

        } else if (option == 2) {
            System.out.println("\n<------ Add Cars ----->");
            System.out.print("Bike name : ");
            String bikeName = sc.nextLine();
            System.out.print("Number : ");
            String bikeNumber = sc.nextLine();
            System.out.print("KM : ");
            System.out.print("Quantity : ");
            int bikeQty = sc.nextInt();
            sc.nextLine();
            System.out.print("Rent per day : ");
            int bikeRent = sc.nextInt();
            sc.nextLine();

            vehicles.bikes.add(new AddVehicles("Bike", bikeName, bikeNumber, bikeQty, bikeRent));
            System.out.println("\t" + bikeName + " added");
            pressEnter();

        } else {
            System.out.println("\tInvalid input");
            addVehicle();
        }
    }

    //? Remove vehicle
    private static void removeVehicle() {
        System.out.println("\n\n<------ Admin ------>");
        System.out.println("""
                1 - Cars
                2 - Bikes
                3 - Back""");
        System.out.print("Choose your option : ");
        String option = sc.nextLine();

        if (option.equals("1")) {
            vhlType = 1;
            showVehicles("Car", 0);
        } else if (option.equals("2")) {
            vhlType = 2;
            showVehicles("Bike", 0);
        }

        System.out.print("Select vehicle : ");
        vhlIndex = sc.nextInt() - 1;
        sc.nextLine();

        if (vhlType == 1) {
            System.out.println("\t" + vehicles.cars.get(vhlIndex).name + " removed");
            vehicles.cars.remove(vhlIndex);
        } else {
            System.out.println("\t" + vehicles.bikes.get(vhlIndex).name + " removed");
            vehicles.bikes.remove(vhlIndex);
        }

    }

    //? Update vehicle
    private static void updateVehicle() {
        System.out.println("\n\n<------ Admin ------>");
        System.out.println("""
                1 - Cars
                2 - Bikes
                3 - Back""");
        System.out.print("Choose your option : ");
        String option = sc.nextLine();

        if (option.equals("1")) {
            vhlType = 1;
            showVehicles("Car", 0);
        } else if (option.equals("2")) {
            vhlType = 2;
            showVehicles("Bike", 0);
        }

        System.out.print("Select vehicle : ");
        vhlIndex = sc.nextInt() - 1;
        sc.nextLine();

        if (vhlType == 1) {
            System.out.println("\n<------ Update Car ----->");
            System.out.print("(1)Car name / (2)Car number /(3)Availability : ");
            int op = sc.nextInt();
            sc.nextLine();

            if (op == 1) {
                System.out.print("\n New name : ");
                vehicles.cars.get(vhlIndex).name = sc.nextLine();
            } else if (op == 2) {
                System.out.print("\n New number : ");
                vehicles.cars.get(vhlIndex).number = sc.nextLine();
            } else if (op == 3) {
                System.out.print("\n New quantity : ");
                vehicles.cars.get(vhlIndex).qty = sc.nextInt();
                sc.nextLine();
            } else {
                System.out.println("\tInvalid input");
                updateVehicle();
            }
        } else {
            System.out.println("\n<------ Update Bike ----->");
            System.out.print("(1)Bike name / (2)Bike number /(3)Availability : ");
            int op = sc.nextInt();
            sc.nextLine();

            if (op == 1) {
                System.out.print("\n New name : ");
                vehicles.bikes.get(vhlIndex).name = sc.nextLine();
            } else if (op == 2) {
                System.out.print("\n New number : ");
                vehicles.bikes.get(vhlIndex).number = sc.nextLine();
            } else if (op == 3) {
                System.out.print("\n New quantity : ");
                vehicles.bikes.get(vhlIndex).qty = sc.nextInt();
                sc.nextLine();
            } else {
                System.out.println("\tInvalid input");
                updateVehicle();
            }
        }
    }

    //? View rented vehicles
    private static void viewRentedVehicle() {
        int isEmpty = 0;
        for (User user : users) if (user.rentedVehicles.isEmpty()) isEmpty++;

        if (isEmpty == users.size()) {
            System.out.println("\tN/A");
            return;
        }

        System.out.println("\n----------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%7s %11s %12s %26s %18s %19s %15s %18s %21s", "S.NO", "USER", "TYPE", "VEHICLE NAME", "NUMBER", "INITIAL KM", "KM LIMIT", "RENTED DATE", "RETURN DATE\n");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------");
        for (int i = 0, k = 1; i < users.size(); i++) {
            if (!users.get(i).rentedVehicles.isEmpty())
                for (int j = 0; j < users.get(i).rentedVehicles.size(); j++) {
                    ArrayList vhl = currentUser.rentedVehicles.get(i);
                    System.out.format("%5s %12s %13s %27s %20s %12s %17s %21s %20s", k, users.get(i).name, vhl.get(0), vhl.get(1), vhl.get(2), vhl.get(3), vhl.get(4), vhl.get(5), vhl.get(6) + "\n");
                    k++;
                }
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------");
        pressEnter();
    }

    //? Vehicle service
    private static void service() {
        if (vehicles.vehiclesInService.isEmpty()) {
            System.out.println("\tN/A");
            return;
        }
        System.out.println("\n----------------------------------------------------------------");
        System.out.printf("%7s %11s %18s %21s", "S.NO", "TYPE", "NAME", "NUMBER\n");
        System.out.println("----------------------------------------------------------------");
        for (int i = 0; i < vehicles.vehiclesInService.size(); i++) {
            AddVehicles ctVhl = vehicles.vehiclesInService.get(i);
            System.out.format("%5s %13s %24s %17s", "2", (i + 1), ctVhl.type, ctVhl.name, ctVhl.number + "\n");
        }
        System.out.println("----------------------------------------------------------------");

        System.out.print("Get back : ");
        int vhlInd = sc.nextInt() - 1;
        vehicles.vehiclesInService.get(vhlInd).qty = vehicles.vehiclesInService.get(vhlInd).qty++;
        vehicles.vehiclesInService.remove(vhlInd);
        pressEnter();
    }

    //!---------------------------------- User ----------------------------------
    //? User home menu
    private static void user() {
        boolean userHomeExit = false;
        while (!userHomeExit) {
            System.out.println("\n\n<-------- User -------->");
            System.out.println("""
                    1 - Login
                    2 - Create Account
                    3 - Back""");
            System.out.print("Choose your option : ");
            String option = sc.nextLine();

            switch (option) {
                case "1":
                    userLogin();
                    break;
                case "2":
                    userCreateAccount();
                    break;
                case "3":
                    userHomeExit = true;
                    break;
                default:
                    System.out.println("\tInvalid input");
                    user();
                    break;
            }
        }
    }

    //? User sign up
    private static void userCreateAccount() {
        System.out.println("\n\n<------ User Sign up ------>");
        System.out.print("User name : ");
        String newUserName = sc.nextLine();
        System.out.print("Password : ");
        String newUserPassword = sc.nextLine();
        System.out.print("Conform password : ");
        String newUserConformPassword = sc.nextLine();

        //check if user exist
        for (User user : users) {
            if (user.name.equals(newUserName)) {
                System.out.println("Account already exist, Login");
                userLogin();
                return;
            }
        }

        //check password
        if (!newUserPassword.equals(newUserConformPassword)) {
            System.out.println("\tPassword mismatch, try again");
            userCreateAccount();
            return;
        }
        System.out.print("Initial deposit amount :");
        int depositAmount = sc.nextInt();
        sc.nextLine();

        //add user
        users.add(new User(newUserName, newUserConformPassword, depositAmount));
        System.out.println("\tAccount created successfully");
        userLogin();
    }

    //? User login
    private static void userLogin() {
        System.out.println("\n\n<------ User Login ------>");
        System.out.print("User name : ");
        String userName = sc.nextLine();

        //check if user exist or not
        for (User user : users) {
            if (user.name.equals(userName)) {
                System.out.print("Password : ");
                String password = sc.nextLine();
                if (user.password.equals(password)) currentUser = user;
                else {
                    System.out.println("\tWrong password");
                    userLogin();
                    break;
                }
            }
        }
        if (currentUser == null) {
            System.out.println("\tYou don't have an account, please Sign up");
            userCreateAccount();
            return;
        }
        currentUser = users.get(0);
        //user personal menu
        boolean userMenuExit = false;
        while (!userMenuExit) {
            System.out.println("\n\n<------ Welcome, Mr." + currentUser.name + " ------>");
            System.out.println("""
                    1 - View vehicles
                    2 - Take vehicle
                    3 - Return vehicle
                    4 - Rented vehicles
                    5 - Deposit
                    6 - Back""");
            System.out.print("Choose your option : ");
            String option = sc.nextLine();

            switch (option) {
                case "1":
                    viewVehiclesMenu("Mr." + currentUser.name, 1);
                    viewVehiclesMenu("Mr.A", 1);
                    break;
                case "2":
                    if (currentUser.rentedVehicles.size() < 3) takeVehicle();
                    else
                        System.out.println("\tYou have three vehicles, please return one of them to take next vehicle");
                    break;
                case "3":
                    returnVehicle();
                    break;
                case "4":
                    borrowedHistory();
                    break;
                case "5":
                    deposit();
                    break;
                case "6":
                    userMenuExit = true;
                default:
                    System.out.println("\tInvalid input");
            }
        }
    }

    //? Take vehicle
    private static void takeVehicle() {
        if (currentUser.deposit < 30000) {
            System.out.println("\tYou don't have minimum deposit amount");
            return;
        }

        System.out.println("\n\n<----- Take vehicles ----->");
        System.out.println("""
                1 - Cars
                2 - Bikes
                3 - Back""");
        System.out.print("Choose your option : ");
        String option = sc.nextLine();

        if (option.equals("1")) {
            vhlType = 1;
            showVehicles("Car", 0);
        } else if (option.equals("2")) {
            vhlType = 2;
            showVehicles("Bike", 0);
        }

        System.out.print("Select vehicle : ");
        vhlIndex = sc.nextInt() - 1;
        sc.nextLine();

        System.out.print("Rental period : ");
        int days = sc.nextInt();
        sc.nextLine();

        //random date & KM
        String rentDate = RentDate();
        String returnDate = ReturnDate(rentDate, days);
        int currentKM = RandomKm();

        //get vehicle
        AddVehicles rentVhl;
        if (vhlType == 1) rentVhl = vehicles.cars.get(vhlIndex);
        else rentVhl = vehicles.bikes.get(vhlIndex);

        //add vehicle to user acc
        if (rentVhl.qty >= 1) rentVhl.qty -= 1;
        currentUser.rentedVehicles.add(new ArrayList<>(List.of(rentVhl.type, rentVhl.name, rentVhl.number, currentKM, currentKM + 500, rentDate, returnDate, days, rentVhl.rentPerDay)));
        System.out.println("\t" + rentVhl.name + " is yours Enjoy!");
        pressEnter();
    }

    //? Return vehicle
    private static void returnVehicle() {
        if (currentUser.rentedVehicles.isEmpty()) {
            System.out.println("\tN/A");
            return;
        }
        System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%7s %10s %26s %18s %19s %15s %18s %21s", "S.NO", "TYPE", "VEHICLE NAME", "NUMBER", "INITIAL KM", "KM LIMIT", "RENTED DATE", "RETURN DATE\n");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
        for (int i = 0; i < currentUser.rentedVehicles.size(); i++) {
            ArrayList vhl = currentUser.rentedVehicles.get(i);
            System.out.format("%5s %11s %27s %20s %12s %17s %21s %20s", (i + 1), vhl.get(0), vhl.get(1), vhl.get(2), vhl.get(3), vhl.get(4), vhl.get(5), vhl.get(6) + "\n");
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");

        System.out.print("Select vehicle : ");
        int vhlIndex = sc.nextInt() - 1;
        sc.nextLine();

        System.out.print("Current date : ");
        String returnDate = sc.nextLine();
        System.out.print("Current KM : ");
        int returnKM = sc.nextInt();
        sc.nextLine();

        System.out.print("Damage (1)No / (2)Low / (3)Medium / (4)High : ");
        int dmg = sc.nextInt();
        sc.nextLine();

        //cost
        int rent = (int) currentUser.rentedVehicles.get(vhlIndex).get(7) * (int) currentUser.rentedVehicles.get(vhlIndex).get(8);

        //damage amount
        int dmgAmt = 0;
        if (dmg == 2) {
            dmgAmt = ((rent / 100) * 20);
            System.out.println("\t₹" + dmgAmt + " penalty low damage");
        } else if (dmg == 3) {
            dmgAmt = ((rent / 100) * 35);
            System.out.println("\t₹" + dmgAmt + " penalty medium damage");
        } else if (dmg == 4) {
            dmgAmt = ((rent / 100) * 50);
            System.out.println("\t₹" + dmgAmt + " penalty high damage");
        }

        //check return period
        int dateOfReturn = Integer.parseInt(returnDate.split("/")[0]);
        int borrowDay = Integer.parseInt(String.valueOf(currentUser.rentedVehicles.get(vhlIndex).get(6)).split("/")[0]);
        int numOfDays = 0;

        while (true) if (dateOfReturn == borrowDay) break;
        else {
            borrowDay++;
            numOfDays++;
            if (borrowDay > 31) borrowDay = 1;
        }
        int fine = 0;
        if (numOfDays > 0) {
            fine = numOfDays * ((rent / 100) * 10);
            System.out.println("\t₹" + fine + " penalty for late return of " + numOfDays + " days");
        }

        //check return km
        if (returnKM > (int) currentUser.rentedVehicles.get(vhlIndex).get(4)) {
            rent += ((rent / 100) * 5);
            System.out.println("\t₹" + (rent / 100) * 10 + " penalty for km limit exceed");
        }
        rent += fine;
        rent += dmgAmt;
        System.out.println("\tTotal amount is ₹" + rent);

        //payment
        System.out.print("\n(1)Cash / (2)Security deposit : ");
        String op = sc.nextLine();
        if (op.equals("1")) {
            System.out.print("Enter amount : ");
            String pay = sc.nextLine();
            System.out.println("\tPayment successful");
        } else if (currentUser.deposit >= rent) {
            currentUser.deposit -= rent;
            System.out.println("\tPayment successful");
        } else System.out.println("\tInsufficient balance in security deposit!");

        String vhlName = (String) currentUser.rentedVehicles.get(vhlIndex).get(1);

        //check for service
        if (returnKM >= 1500) {
            double km = (double) returnKM / 1500;
            if (km <= 1.133) {
                if (currentUser.rentedVehicles.get(vhlIndex).get(0).equals("Car")) {
                    for (int i = 0; i < vehicles.cars.size(); i++)
                        if (vhlName.equals(vehicles.cars.get(i).name))
                            vehicles.vehiclesInService.add(vehicles.cars.get(i));
                } else for (int i = 0; i < vehicles.bikes.size(); i++)
                    if (vhlName.equals(vehicles.bikes.get(i).name))
                        vehicles.vehiclesInService.add(vehicles.bikes.get(i));
            } else {
                //inc count in gallery
                if (currentUser.rentedVehicles.get(vhlIndex).get(0).equals("Car")) {
                    for (int i = 0; i < vehicles.cars.size(); i++)
                        if (vhlName.equals(vehicles.cars.get(i).name)) vehicles.cars.get(i).qty += 1;
                } else for (int i = 0; i < vehicles.bikes.size(); i++)
                    if (vhlName.equals(vehicles.bikes.get(i).name)) vehicles.bikes.get(i).qty += 1;
            }
        }
        //remove vhl
        currentUser.rentedVehicles.remove(vhlIndex);
        pressEnter();
    }

    //? Borrowed vehicle
    private static void borrowedHistory() {
        if (currentUser.rentedVehicles.isEmpty()) {
            System.out.println("\tN/A");
            return;
        }
        System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%7s %10s %26s %18s %19s %15s %18s %21s", "S.NO", "TYPE", "VEHICLE NAME", "NUMBER", "CURRENT KM", "KM LIMIT", "RENTED DATE", "RETURN DATE\n");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
        for (int i = 0; i < currentUser.rentedVehicles.size(); i++) {
            ArrayList vhl = currentUser.rentedVehicles.get(i);
            System.out.format("%5s %11s %27s %20s %12s %17s %21s %20s", (i + 1), vhl.get(0), vhl.get(1), vhl.get(2), vhl.get(3), vhl.get(4), vhl.get(5), vhl.get(6) + "\n");
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
        pressEnter();
    }

    //? Deposit
    private static void deposit() {
        boolean depositExit = false;
        while (!depositExit) {
            System.out.println("\n\n<-------- " + currentUser.name + " -------->");
            System.out.println("""
                    1 - Show amount
                    2 - Add amount
                    3 - Back""");
            System.out.print("Choose your option : ");
            String option = sc.nextLine();

            switch (option) {
                case "1":
                    showAmount();
                    break;
                case "2":
                    addAmount();
                    break;
                case "3":
                    depositExit = true;
                    break;
                default:
                    System.out.println("\tInvalid input");
                    deposit();
            }
        }
    }

    //? Show deposit amount
    private static void showAmount() {
        System.out.println("\nSecurity deposit amount : ₹" + currentUser.deposit);
        pressEnter();
    }

    //? Add deposit amount
    private static void addAmount() {
        System.out.print("\nEnter amount : ₹");
        currentUser.deposit += sc.nextInt();
        sc.nextLine();
        System.out.println("\tAmount added in your deposit");
        pressEnter();
    }

    //? Random date (for borrow)
    private static String RentDate() {
        int day = (int) (Math.random() * 31) + 1;
        int month = (int) (Math.random() * 12) + 1;
        int year = 2022;
        return day + "/" + month + "/" + year;
    }

    //? Random date (for return)
    private static String ReturnDate(String date, int days) {
        String[] dateArr = date.split("/");
        int newDay = Integer.parseInt(dateArr[0]);
        int newMonth = Integer.parseInt(dateArr[1]);
        int newYear = Integer.parseInt(dateArr[2]);
        for (int i = 1; i <= days; i++) {
            if (newDay < 31) newDay++;
            else {
                newDay = 1;
                newMonth++;
                if (newMonth > 12) {
                    newMonth = 1;
                    newYear++;
                }
                --i;
            }
        }
        return newDay + "/" + newMonth + "/" + newYear;
    }

    //? Random KM (before rent)
    private static int RandomKm() {
        int min = 200;
        int max = 1200;
        return (int) ((Math.random() * (max - min)) + min);
    }

    //? Enter to continue
    private static void pressEnter() {
        System.out.print("Press Enter to continue...");
        sc.nextLine();
    }
}


