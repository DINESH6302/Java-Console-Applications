package App.Amazon;

import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);

    static Buyer buyer = new Buyer();
    static Admin admin = new Admin();
    static Seller seller = new Seller();
    static Product product = new Product();


    public static void main(String[] args) {
        boolean mainExit = false;
        int option = 0;
        while (!mainExit) {
            System.out.println("\n<------ HOME ------>");
            System.out.println(""" 
                    1 - Admin
                    2 - Seller
                    3 - Buyer
                    4 - Close""");
            System.out.print("Choose option : ");
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    adminLogin();
                    break;
                case 2:
                    seller.setSellerName("");
                    seller.setSellerId("");
                    seller.options();
                    break;
                case 3:
                    buyer.setBuyerName("");
                    buyer.setBuyerId("");
                    buyer.setBuyerWallet(-1);
                    buyer.options(product);
                    break;
                case 4:
                    mainExit = true;
                    break;
                default:
                    System.out.println("\tInvalid input");
            }
        }
    }

    public static void adminLogin() {
        System.out.println("\n<------ ADMIN ------>");
        System.out.print("Username : ");
        String adminName = sc.nextLine();
        System.out.print("Password : ");
        String adminId = sc.nextLine();

        if (adminName.equals(admin.adminName) && adminId.equals(admin.adminPassword)) {
            admin.options(seller);
        } else System.out.println("Wrong credentials!");
    }
}
