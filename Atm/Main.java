package App.Atm;

import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    public static Admin admin = new Admin();
    public static User user = new User();
    static boolean quite = false;
    static int option = 0;

    public static void main(String[] args) {
        user.setUserName("");
        user.setUserPin("");
        user.setUserBalance(-1);
        user.setUserBank("");
        while (!quite) {
            System.out.println("\nAvailable Options:");
            System.out.println("""
                    1 - Admin login
                    2 - User login
                    3 - Exit
                    Choose your option:""");
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    System.out.print("---Admin Portal---\nusername : ");
                    String adminName = sc.nextLine();
                    System.out.print("password : ");
                    String adminId = sc.nextLine();
                    if (!admin.password.equals(adminId) || !admin.userName.equals(adminName)) {
                        System.out.println("Wrong Credentials, try again...");
                    } else {
                        admin.adminOption(user);
                    }
                    admin.adminOption(user);
                    break;
                case 2:
                    System.out.print("---User Portal---\nusername : ");
                    String userName = sc.nextLine();
                    System.out.print("password : ");
                    String userId = sc.nextLine();

                    int userIndex = findUser(userName, userId, user);
                    if (userIndex == -1) {
                        System.out.println("User not found!");
                    } else if (userIndex == -2) {
                        System.out.println("Invalid password");
                    } else {
                        user.userOptions(userIndex, admin);
                    }

                    break;
                case 3:
                    quite = true;
                    System.out.println("Shutting down....");
                    break;
                default:
                    System.out.println("Invalid input");
            }
        }
    }

    private static int findUser(String uName, String uId, User user) {
        int userIndex = -1;
        for (int i = 0; i < user.userName.size(); i++) {
            if (uName.equals(user.userName.get(i))) userIndex = i;
        }
        if (userIndex == -1)
            return -1;

        if (!uId.equals(user.userPin.get(userIndex))) userIndex = -2;
        return userIndex;
    }

}
