package App.Amazon;

import java.util.*;

public class Admin {
    static Scanner sc = new Scanner(System.in);
    String adminName = "root";
    String adminPassword = "toor";
    Seller seller;
    static int option = 0;
    static ArrayList<String[]> sellerReq = new ArrayList<>();

    public void options(Seller seller) {
        boolean adminExit = false;
        this.seller = seller;
        while (!adminExit) {
            System.out.println("\n<----- Welcome, Admin ----->");
            System.out.println(""" 
                    1 - Add Seller
                    2 - Seller request
                    3 - Remove Seller
                    4 - Back""");
            System.out.print("Choose option : ");
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    addSeller();
                    break;
                case 2:
                    checkRequest();
                    break;
                case 3:
                    removeSeller();
                    break;
                case 4:
                    adminExit = true;
                    break;
                default:
                    System.out.println("\tInvalid input");
            }
        }
    }

    public void addSeller() {
        System.out.print("New seller name : ");
        String newSellerName = sc.nextLine();
        System.out.print("New seller password : ");
        String newSellerPassword = sc.nextLine();
        if (seller.sellerName.contains(newSellerName)) {
            System.out.println("\tSeller already exist!");
            return;
        }
        seller.setSellerName(newSellerName);
        seller.setSellerId(newSellerPassword);
        System.out.println("Seller added successfully");
    }

    public void removeSeller() {
        seller.setSellerName("");
        seller.setSellerId("");
        System.out.print("Seller name : ");
        String existSellerName = sc.nextLine();
        System.out.print("Seller password : ");
        String existSellerPassword = sc.nextLine();
        if (seller.sellerName.contains(existSellerName)) {
            seller.sellerName.remove(existSellerName);
            seller.sellerId.remove(existSellerPassword);
            System.out.println("Mr." + existSellerName + " has removed");
        } else System.out.println("\tSeller doesn't exist!");
    }

    public void checkRequest() {
        if (sellerReq.isEmpty()) System.out.println("\tNo seller requests");
        else {
            for (int i = 0; i < sellerReq.size(); i++) {
                String tempName = sellerReq.get(i)[0];
                String tempPass = sellerReq.get(i)[1];
                System.out.println("Mr." + tempName + " is requested");
                System.out.print("Approve(1)/Reject(0) : ");
                String opp = sc.nextLine();
                if (opp.equals("1")) {
                    seller.setSellerName(tempName);
                    seller.setSellerId(tempPass);
                    System.out.println("\tMr." + tempName + "has approved");
                } else System.out.println("\tMr." + tempName + " has rejected");
            }
            sellerReq.clear();
            seller.pendingReq.clear();
        }
    }
}
