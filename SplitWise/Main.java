package App.SplitWise;

import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);

    //Users
    static ArrayList<User> user = new ArrayList<>(List.of(new User("A", "1111"), new User("B", "2222"), new User("C", "3333")));
    static User currentUser;

    public static void main(String[] args) {
        homeMenu();
    }

    //? Home menu
    private static void homeMenu() {
        boolean homeExit = false;
        while (!homeExit) {
            System.out.println("\n<-------- SPLIT WISE -------->");
            System.out.println("""
                    1 - Login
                    2 - create account
                    3 - Exit""");
            System.out.print("Choose option : ");
            String option = sc.nextLine();
            switch (option) {
                case "1":
                    login();
                    break;
                case "2":
                    signUp();
                    break;
                case "3":
                    homeExit = true;
                    break;
                default:
                    System.out.println("\t Invalid input");
                    homeMenu();
                    break;
            }
        }
    }

    //? Sign up
    private static void signUp() {
        System.out.println("\n<------ SignUp ------>");
        System.out.print("User name : ");
        String name = sc.nextLine();

        for (User value : user)
            if (value.name.equals(name)) {
                System.out.println("\tUser name already exist, please login");
                login();
                return;
            }

        System.out.print("Password : ");
        String password = sc.nextLine();
        user.add(new User(name, password));
        System.out.println("\t Account created successfully, login");
        login();
    }

    //? Login
    private static void login() {
        System.out.println("\n<------ Login ------>");
        System.out.print("User name : ");
        String name = sc.nextLine();
        System.out.print("Password : ");
        String password = sc.nextLine();

        for (User value : user)
            if (value.name.equals(name) && value.password.equals(password)) {
                currentUser = value;
                userMenu();
                return;
            }
        System.out.println("\tWrong credentials, try again");
        login();
    }

    //? User menu
    private static void userMenu() {
        boolean userMenu = false;
        while (!userMenu) {
            System.out.println("\n<-------- Welcome Mr." + currentUser.name + " -------->");
            System.out.println("""
                    1 - Non group expenses
                    2 - Group expenses
                    3 - Add friends
                    4 - Remove friends
                    5 - Back""");
            System.out.print("Choose option : ");
            String option = sc.nextLine();
            switch (option) {
                case "1":
                    nonGroupExpenses();
                    break;
                case "2":
                    groupExpenses();
                    break;
                case "3":
                    addFriend();
                    break;
                case "4":
                    removeFriend();
                    break;
                case "5":
                    userMenu = true;
                    break;
                default:
                    System.out.println("\t Invalid input");
                    userMenu();
                    break;
            }
        }
    }

    //!-------------------------------------- Non group expenses -----------------------------------
    //? Non group expenses
    private static void nonGroupExpenses() {
        boolean nonGroupExp = false;
        while (!nonGroupExp) {
            System.out.println("\n<------ Non Group ------>");
            System.out.println("""
                    1 - Add Expenses
                    2 - Balances
                    3 - Back""");
            System.out.print("Choose option : ");
            String option = sc.nextLine();
            switch (option) {
                case "1":
                    nonGroupAddExpenses();
                    break;
                case "2":
                    nonGroupBalances();
                    break;
                case "3":
                    nonGroupExp = true;
                    break;
                default:
                    System.out.println("\t Invalid input");
                    nonGroupExpenses();
                    break;
            }
        }
    }

    //? Non group add expenses
    private static void nonGroupAddExpenses() {
        if (currentUser.commonFriends.isEmpty()) {
            System.out.println("\tPlease add friends to continue");
            userMenu();
            return;
        }

        System.out.println("\n<----- Add Expenses ----->");
        //choose friend
        System.out.println("With me and : ");
        for (int i = 0; i < currentUser.commonFriends.size(); i++)
            System.out.println((i + 1) + ". " + currentUser.commonFriends.get(i).name);
        System.out.print("Select friend : ");
        int friend = sc.nextInt() - 1;
        sc.nextLine();
        User friendObj = currentUser.commonFriends.get(friend);

        //Expenses details
        System.out.print("\nDescription : ");
        String des = sc.nextLine();
        System.out.print("Amount : ");
        int totAmt = sc.nextInt();
        sc.nextLine();
        System.out.print("Paid by (0)me / (1)" + friendObj.name + " : ");
        int option = sc.nextInt();
        sc.nextLine();
        System.out.print("Split (0)Equally / (1)Unequally : ");
        int adjustSplit = sc.nextInt();
        sc.nextLine();

        int myAmt = 0;
        int friendAmt = 0;
        if (adjustSplit == 1) {
            System.out.print("Me ₹: ");
            myAmt = sc.nextInt();
            sc.nextLine();
            System.out.print(friendObj.name + " ₹: ");
            friendAmt = sc.nextInt();
            sc.nextLine();
        } else {
            myAmt = totAmt / 2;
            friendAmt = totAmt / 2;
        }

        String whoPaid;
        if (option == 0) whoPaid = "You";
        else whoPaid = friendObj.name;

        //add expenses in current user account
        currentUser.commonExpenses.add(new User.Expenses(friendObj.name, des, whoPaid, myAmt, friendAmt, totAmt));

        if (whoPaid.equals("You")) whoPaid = friendObj.name;
        else whoPaid = "You";
        friendObj.commonExpenses.add(new User.Expenses(currentUser.name, des, whoPaid, friendAmt, myAmt, totAmt));
        System.out.println("\tExpenses add successfully");
    }

    //? Non group balances
    private static void nonGroupBalances() {
        if (currentUser.commonExpenses.isEmpty()) {
            System.out.println("\tN/A");
            return;
        } else {
            System.out.println("\n<------ Balances ------->");
            for (int i = 0; i < currentUser.commonExpenses.size(); i++) {
                User.Expenses exp = currentUser.commonExpenses.get(i);
                if (exp.whoPaid.equals("You"))
                    System.out.println("\t" + (i + 1) + ". " + "You paid ₹" + exp.totAmt + " for " + exp.description + ", " + exp.friendName + " owes ₹" + exp.friendAmt);
                else
                    System.out.println("\t" + (i + 1) + ". " + exp.friendName + " paid ₹" + exp.totAmt + " for " + exp.description + ", " + "you owes ₹" + exp.myAmt);
            }
        }

        System.out.print("(0)Delete expense / (1)Back : ");
        int option = sc.nextInt();
        sc.nextLine();
        if (option == 0) {
            System.out.print("\nSelect expense : ");
            int expense = sc.nextInt() - 1;
            sc.nextLine();
            nonGroupDeleteExpenses(expense);
        } else if (option != 1) {
            System.out.println("\tInvalid option");
            nonGroupBalances();
        }
    }

    //? Non group remove expenses
    private static void nonGroupDeleteExpenses(int option) {
        //remove in friend obj
        fObj:
        for (User value : user)
            if (value.name.equals(currentUser.commonExpenses.get(option).friendName))
                for (int j = 0; j < value.commonExpenses.size(); j++)
                    if (value.commonExpenses.get(j).description.equals(currentUser.commonExpenses.get(option).description) && value.commonExpenses.get(j).totAmt == currentUser.commonExpenses.get(option).totAmt) {
                        value.commonExpenses.remove(j);
                        break fObj;
                    }

        // remove in current user
        System.out.println("\t" + currentUser.commonExpenses.get(option).description + " expense deleted");
        currentUser.commonExpenses.remove(option);
    }

    //!-------------------------------------- Group expenses -----------------------------------
    //? Group expenses
    private static void groupExpenses() {
        if (currentUser.commonFriends.isEmpty()) {
            System.out.println("\tPlease add common friends to continue");
            return;
        }

        boolean groupExp = false;
        while (!groupExp) {
            System.out.println("\n<------ Group ------>");
            System.out.println("""
                    1 - Add Expenses
                    2 - Balances
                    3 - Add friends
                    4 - Back""");
            System.out.print("Choose option : ");
            String option = sc.nextLine();
            switch (option) {
                case "1":
                    groupAddExpenses();
                    break;
                case "2":
                    groupBalances();
                    break;
                case "3":
                    groupFriends();
                    break;
                case "4":
                    groupExp = true;
                    break;
                default:
                    System.out.println("\t Invalid input");
                    nonGroupExpenses();
                    break;
            }
        }
    }

    //? Add group expenses
    private static void groupAddExpenses() {
        if (currentUser.groupFriends.isEmpty()) {
            System.out.println("\tAdd friends in group");
            return;
        }
        System.out.println("\n<----- Add Expenses ----->");

        //choose friends
        System.out.println("With me and : ");
        for (int i = 0; i < currentUser.groupFriends.size(); i++)
            System.out.println((i + 1) + ". " + currentUser.groupFriends.get(i).name);
        System.out.print("Select friends : ");
        String position = sc.nextLine();
        String[] fp = position.split(",");

        int[] friendsPosition = new int[fp.length];
        for (int i = 0; i < fp.length; i++)
            friendsPosition[i] = Integer.parseInt(fp[i]) - 1;

        //expenses details
        System.out.println("\n<----- Expense details ----->");
        System.out.print("Description : ");
        String des = sc.nextLine();
        System.out.print("Amount : ");
        int totAmt = sc.nextInt();
        sc.nextLine();


        //who paid
        System.out.print("Paid by (0)You");
        for (int i = 0; i < friendsPosition.length; i++)
            System.out.print(" / (" + (i + 1) + ")" + currentUser.groupFriends.get(i).name);
        System.out.print(" : ");
        int wp = sc.nextInt() - 1;
        sc.nextLine();
        String whoPaid;
        if (wp == -1) whoPaid = currentUser.name;
        else whoPaid = currentUser.groupFriends.get(wp).name;

        //split
        System.out.print("Split (0)Equally / (1)Unequally : ");
        int split = sc.nextInt();
        sc.nextLine();
        int myAmt = 0;

        if (split == 0) {
            myAmt = totAmt / (currentUser.groupFriends.size() + 1);

            ArrayList<User.Expenses> grpExp = new ArrayList<>(List.of(new User.Expenses(currentUser.name, des, whoPaid, myAmt, myAmt, totAmt)));

            for (int i = 0; i < currentUser.groupFriends.size(); i++) {
                int friendAmt = totAmt / (currentUser.groupFriends.size() + 1);
                grpExp.add(new User.Expenses(currentUser.groupFriends.get(i).name, des, whoPaid, friendAmt, myAmt, totAmt));
            }
            currentUser.groupExpenses.add(grpExp);
        } else {

        }

        //ad expense to friends pbj
        for (int i = 0; i < currentUser.groupFriends.size(); i++)
            currentUser.groupFriends.get(i).groupExpenses.add(currentUser.groupExpenses.get(currentUser.groupExpenses.size() - 1));

        // clear group
        for (User value : user)
            value.groupFriends.clear();

        System.out.println("\tExpense added successfully");
    }

    //? Group balances
    private static void groupBalances() {
        if (currentUser.groupExpenses.isEmpty()) {
            System.out.println("\tN/A");
            return;
        }

        for (int i = 0; i < currentUser.groupExpenses.size(); i++) {
            ArrayList<User.Expenses> crtExp = currentUser.groupExpenses.get(i);
            String des = crtExp.get(0).description;

            String whoPaid;
            if (crtExp.get(0).whoPaid.equals(currentUser.name)) whoPaid = "You";
            else whoPaid = crtExp.get(0).whoPaid;

            System.out.println("\t" + (i + 1) + ". " + des + " :-");
            System.out.print("\t\t" + whoPaid + " paid ₹" + crtExp.get(0).totAmt + " for " + des);

            for (int j = 0; j < crtExp.size(); j++)
                if (!crtExp.get(0).whoPaid.equals(crtExp.get(j).friendName)) {
                    String name;
                    if (currentUser.name.equals(crtExp.get(j).friendName)) name = "You";
                    else name = crtExp.get(j).friendName;
                    System.out.print(" " + name + " owes ₹" + crtExp.get(j).myAmt + " &");
                }
        }
    }

    //? Group friends
    private static void groupFriends() {
        System.out.println("\nAdd friends : ");
        for (int i = 0; i < currentUser.commonFriends.size(); i++)
            System.out.println("\t" + (i + 1) + ". " + currentUser.commonFriends.get(i).name);

        System.out.print("Select friends : ");
        String position = sc.nextLine();
        String[] friendPosition = position.split(",");

        for (String s : friendPosition) {
            int pos = Integer.parseInt(s) - 1;
            currentUser.groupFriends.add(currentUser.commonFriends.get(pos));
            currentUser.commonFriends.get(pos).groupFriends.add(currentUser);
        }

        for (int i = 0; i < currentUser.groupFriends.size(); i++)
            for (int j = 0; j < currentUser.groupFriends.size(); j++)
                if (!currentUser.groupFriends.get(i).name.equals(currentUser.groupFriends.get(j).name))
                    currentUser.groupFriends.get(i).groupFriends.add(currentUser.groupFriends.get(j));

        System.out.println("\tFriends added successfully");
    }

    //? Add friend
    private static void addFriend() {
        System.out.println("\n<----- Add Friend ----->");
        System.out.print("Friend name : ");
        String friendName = sc.nextLine();

        if (!currentUser.commonFriends.isEmpty()) {
            for (int i = 0; i < currentUser.commonFriends.size(); i++)
                if (currentUser.commonFriends.get(i).name.equals(friendName)) {
                    System.out.println("\t" + friendName + " is already in you friends list");
                    return;
                }
        }

        for (User value : user)
            if (friendName.equals(value.name)) {
                currentUser.commonFriends.add(value);
                value.commonFriends.add(currentUser);
                System.out.println("\t" + value.name + " added successfully");
                return;
            }
        System.out.println("\t" + friendName + " doesn't have account, Invite sent");
    }

    //? Remove friend
    private static void removeFriend() {
    }

}
