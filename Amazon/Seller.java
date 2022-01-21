package App.Amazon;

import java.util.*;

public class Seller {
    static Scanner sc = new Scanner(System.in);
    ArrayList<String> sellerName = new ArrayList<>();
    ArrayList<String> sellerId = new ArrayList<>();
    ArrayList<String> pendingReq = new ArrayList<>();
    static int sellerPosition = 0;

    public void setSellerName(String sName) {
        if (sellerName.isEmpty()) sellerName.addAll(Arrays.asList("W", "X", "Y", "Z"));
        if (!sName.equals("")) sellerName.add(sName);
    }

    public void setSellerId(String sId) {
        if (sellerId.isEmpty()) sellerId.addAll(Arrays.asList("1010", "2020", "3030", "4040"));
        if (!sId.equals("")) sellerId.add(sId);
    }

    public void options() {
        boolean sellerExit = false;
        int option = 0;

        while (!sellerExit) {
            System.out.println("\n<----- SELLER ----->");
            System.out.println("""
                    1 - Login
                    2 - Create account
                    3 - Back""");
            System.out.print("Choose option : ");
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    if (Product.phones.isEmpty()) Product.initialProductAdd();
                    login();
                    break;
                case 2:
                    createAccount();
                    break;
                case 3:
                    sellerExit = true;
                    break;
                default:
                    System.out.println("\tInvalid input");
            }
        }
    }

    public void login() {
        System.out.println("\n<------ LOGIN ------>");
        System.out.print("Username : ");
        String sName = sc.nextLine();
        System.out.print("Password : ");
        String password = sc.nextLine();

        sellerPosition = sellerName.indexOf(sName);
        if (!pendingReq.contains(sName)) {
            if (sellerPosition == -1) System.out.println("\tAccount not exist");
            else if (!password.equals(sellerId.get(sellerPosition))) System.out.println("\tWrong password");
            else sellerPage();
        } else {
            System.out.println("\tApproval is pending");
        }
    }

    public void createAccount() {
        System.out.println("\n<------ CREATE ACCOUNT ------>");
        System.out.print("Username : ");
        String newSellerName = sc.nextLine();
        System.out.print("Password : ");
        String newUsePin = sc.nextLine();
        System.out.print("Conform Password : ");
        String newUsePinConform = sc.nextLine();

        if (sellerName.contains(newSellerName)) System.out.println("\t" + newSellerName + " is already exist!");
        else {
            String[] req = new String[2];
            req[0] = newSellerName;
            req[1] = newUsePin;
            Admin.sellerReq.add(req);
            pendingReq.add(newSellerName);
            System.out.println("Request send to admin, please wait to approve");
        }
    }

    public void sellerPage() {
        boolean sellerPageExit = false;
        int option = 0;

        while (!sellerPageExit) {
            System.out.println("\n<----- Welcome, Mr." + sellerName.get(sellerPosition) + "----->");
            System.out.println("""
                    1 - Add product
                    2 - Remove/Update product
                    3 - Compare product
                    4 - Back""");
            System.out.print("Choose option : ");
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    Product.addProducts(sellerName.get(sellerPosition));
                    break;
                case 2:
                    Product.rmvDecProducts(sellerName.get(sellerPosition));
                    break;
                case 3:
                    Product.compareProducts(sellerName.get(sellerPosition));
                    break;
                case 4:
                    sellerPageExit = true;
                    break;
                default:
                    System.out.println("\tInvalid input");
            }
        }
    }
}
