package App.Amazon;

import java.util.*;

public class Buyer {
    static Scanner sc = new Scanner(System.in);
    static Product products = new Product();
    static int buyerPosition = 0;

    static ArrayList<String> buyerName = new ArrayList<>();
    static ArrayList<String> buyerId = new ArrayList<>();
    static ArrayList<Integer> buyerWallet = new ArrayList<>();
    static HashMap<String, ArrayList<String>> buyerCart = new HashMap<>();
    static HashMap<String, ArrayList<String>> buyerOrders = new HashMap<>();


    public void setBuyerName(String bName) {
        if (buyerName.isEmpty()) buyerName.addAll(Arrays.asList("A", "B", "C", "D"));
        if (!bName.equals("")) buyerName.add(bName);
    }

    public void setBuyerId(String bId) {
        if (buyerId.isEmpty()) buyerId.addAll(Arrays.asList("1111", "2222", "3333", "4444"));
        if (!bId.equals("")) buyerId.add(bId);
    }

    public void setBuyerWallet(int bWallet) {
        if (buyerWallet.isEmpty()) buyerWallet.addAll(Arrays.asList(60000, 140000, 42000, 15000));
        if (bWallet != -1) buyerWallet.add(bWallet);
    }

    public void options(Product product) {
        products = product;
        boolean buyerExit = false;
        int option = 0;

        while (!buyerExit) {
            System.out.println("\n<----- BUYER ----->");
            System.out.println("""
                    1 - Login
                    2 - Create account
                    3 - Back""");
            System.out.print("Choose option : ");
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    Product.initialProductAdd();
                    login();
                    break;
                case 2:
                    createAccount();
                    break;
                case 3:
                    buyerExit = true;
                    break;
                default:
                    System.out.println("\tInvalid input");
            }
        }
    }

    public void login() {
        System.out.println("\n<------ LOGIN ------>");
        System.out.print("Username : ");
        String bName = sc.nextLine();
        System.out.print("Password : ");
        String password = sc.nextLine();

        buyerPosition = buyerName.indexOf(bName);
        if (buyerPosition == -1) System.out.println("\tAccount not exist");
        else if (!password.equals(buyerId.get(buyerPosition))) System.out.println("\tWrong password");
        else buyerPage();
    }

    public void createAccount() {
        System.out.println("\n<------ SIGNUP ------>");
        System.out.print("Username : ");
        String newBuyerName = sc.nextLine();
        System.out.print("Password : ");
        String newBuyerPin = sc.nextLine();
        System.out.print("Conform Password : ");
        String newBuyerPinConform = sc.nextLine();
        System.out.print("Wallet balance : ");
        int newBuyerWallet = sc.nextInt();

        if (buyerName.contains(newBuyerName)) System.out.println("\t" + newBuyerName + " is already exist!");
        else if (newBuyerPin.equals(newBuyerPinConform)) {
            setBuyerName(newBuyerName);
            setBuyerId(newBuyerPin);
            setBuyerWallet(newBuyerWallet);
            System.out.println("\tAccount created successfully!");
        }

    }

    public void buyerPage() {
        boolean buyerPageExit = false;
        int option = 0;

        while (!buyerPageExit) {
            System.out.println("\n<---Welcome Mr." + buyerName.get(buyerPosition) + "--->");
            System.out.println("""
                    1 - Product Category
                    2 - Buy
                    3 - Orders
                    4 - Cart
                    5 - wallet
                    6 - Back""");
            System.out.print("Choose option : ");
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    products.showCategory();
                    break;
                case 2:
                    products.buy();
                    break;
                case 3:
                    products.orders();
                    break;
                case 4:
                    products.cart(buyerName.get(buyerPosition));
                    break;
                case 5:
                    wallet();
                    break;
                case 6:
                    buyerPageExit = true;
                    break;
                default:
                    System.out.println("\tInvalid input");
            }
        }
    }

    public static void viewCart() {
        if (buyerCart.containsKey(buyerName.get(buyerPosition))) {
            for (Map.Entry<String, ArrayList<String>> m : buyerCart.entrySet()) {
                String key = m.getKey();
                ArrayList<String> value = m.getValue();
                if (key.equals(buyerName.get(buyerPosition))) {
                    System.out.println("-----------------------------------------------------------------");
                    System.out.printf("%6s %21s %15s %13s", "S.NO", "PRODUCT NAME", "PRICE", "QUANTITY");
                    System.out.println();
                    System.out.println("-----------------------------------------------------------------");

                    for (int k = 0, i = 0, j = 1; k < value.size() / 3; i += 3, j++, k++) {
                        System.out.format("%4s %25s %15s %9s", j, value.get(i), value.get(i + 1), value.get(i + 2));
                        System.out.println();
                    }

                    System.out.println("-----------------------------------------------------------------");
                }
            }
        } else System.out.println("\n\tYour cart is empty");
    }

    public static void addProductToCart() {
        boolean addProToCartExit = false;
        while (!addProToCartExit) {
            System.out.println("\nSelect category to add");
            System.out.println("""
                    1 - Phones
                    2 - Tv
                    3 - Shoes
                    4 - Back""");
            System.out.print("Option : ");
            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    addPhoneToCart(option);
                    break;
                case 2:
                    addTvToCart(option);
                    break;
                case 3:
                    addShoeToCart(option);
                    break;
                case 4:
                    addProToCartExit = true;
                    break;
                default:
                    System.out.println("\tInvalid option");
            }
        }

    }

    public static void removeProductFromCart() {
        if (buyerCart.containsKey(buyerName.get(buyerPosition))) {
            viewCart();
            System.out.print("\nEnter product name : ");
            String itemForRemove = sc.nextLine();
            ArrayList<String> currArrList = buyerCart.get(buyerName.get(buyerPosition));

            if (currArrList.contains(itemForRemove)) {
                ArrayList<String> temp = new ArrayList<>();
                int ind = currArrList.indexOf(itemForRemove);
                temp.add(currArrList.get(ind));
                temp.add(currArrList.get(ind + 1));
                temp.add(currArrList.get(ind + 2));
                currArrList.removeAll(temp);
                System.out.println("\n\t" + itemForRemove + " has removed");
                if (currArrList.isEmpty()) buyerCart.remove(buyerName.get(buyerPosition));
            } else System.out.println("\n\t" + itemForRemove + " is not in cart");

        } else System.out.println("\n\t No items in your cart");
    }

    private static void addPhoneToCart(int cty) {
        products.showProducts(cty);
        System.out.print("\nEnter product name : ");
        String phoneForCart = sc.nextLine();
        System.out.print("\nEnter number of quantity : ");
        String qtyOfProduct = sc.nextLine();

        for (Map.Entry<String, String> m : Product.phones.entrySet()) {
            String phValue = m.getValue();
            String[] phArr = phValue.split("/");
            phArr[2] = qtyOfProduct;
            if (phoneForCart.equals(phArr[0])) {
                if (!buyerCart.isEmpty()) {
                    ArrayList<String> currArrList = buyerCart.get(buyerName.get(buyerPosition));
                    if (!buyerCart.containsKey(buyerName.get(buyerPosition))) {
                        buyerCart.put(buyerName.get(buyerPosition), new ArrayList<>(List.of(phArr[0], phArr[1], phArr[2])));
                        System.out.println("\t" + phoneForCart + " added to cart");
                    } else if (currArrList.contains(phoneForCart)) {
                        int ind = currArrList.indexOf(phoneForCart);
                        String incQty = currArrList.get(ind + 2);
                        currArrList.set(ind + 2, Integer.toString((Integer.parseInt(incQty)) + Integer.parseInt(qtyOfProduct)));
                        System.out.println("\t" + phoneForCart + " quantity increased");
                    } else {
                        currArrList.addAll(List.of(phArr[0], phArr[1], phArr[2]));
                        System.out.println("\t" + phoneForCart + " added to cart");
                    }
                } else {
                    buyerCart.put(buyerName.get(buyerPosition), new ArrayList<>(List.of(phArr[0], phArr[1], phArr[2])));
                    System.out.println("\t" + phoneForCart + " added to cart");
                }
            }
        }
    }

    private static void addTvToCart(int cty) {
        products.showProducts(cty);
        System.out.print("\nEnter product name : ");
        String tvForCart = sc.nextLine();
        System.out.print("\nEnter number of quantity : ");
        String qtyOfProduct = sc.nextLine();

        for (Map.Entry<String, String> m : Product.tv.entrySet()) {
            String tvValue = m.getValue();
            String[] tvArr = tvValue.split("/");
            tvArr[2] = qtyOfProduct;
            if (tvForCart.equals(tvArr[0])) {
                if (!buyerCart.isEmpty()) {
                    ArrayList<String> currArrList = buyerCart.get(buyerName.get(buyerPosition));
                    if (!buyerCart.containsKey(buyerName.get(buyerPosition))) {
                        buyerCart.put(buyerName.get(buyerPosition), new ArrayList<>(List.of(tvArr[0], tvArr[1], tvArr[2])));
                        System.out.println("\t" + tvForCart + " added to cart");
                    } else if (currArrList.contains(tvForCart)) {
                        int ind = currArrList.indexOf(tvForCart);
                        String incQty = currArrList.get(ind + 2);
                        currArrList.set(ind + 2, Integer.toString((Integer.parseInt(incQty)) + Integer.parseInt(qtyOfProduct)));
                        System.out.println("\t" + tvForCart + " quantity increased");
                    } else {
                        currArrList.addAll(List.of(tvArr[0], tvArr[1], tvArr[2]));
                        System.out.println("\t" + tvForCart + " added to cart");
                    }
                } else {
                    buyerCart.put(buyerName.get(buyerPosition), new ArrayList<>(List.of(tvArr[0], tvArr[1], tvArr[2])));
                    System.out.println("\t" + tvForCart + " added to cart");
                }
            }
        }
    }

    private static void addShoeToCart(int cty) {
        products.showProducts(cty);
        System.out.print("\nEnter product name : ");
        String shoeForCart = sc.nextLine();
        System.out.print("\nEnter number of quantity : ");
        String qtyOfProduct = sc.nextLine();

        for (Map.Entry<String, String> m : Product.shoes.entrySet()) {
            String shValue = m.getValue();
            String[] shArr = shValue.split("/");
            shArr[2] = qtyOfProduct;
            if (shoeForCart.equals(shArr[0])) {
                if (!buyerCart.isEmpty()) {
                    ArrayList<String> currArrList = buyerCart.get(buyerName.get(buyerPosition));
                    if (!buyerCart.containsKey(buyerName.get(buyerPosition))) {
                        buyerCart.put(buyerName.get(buyerPosition), new ArrayList<>(List.of(shArr[0], shArr[1], shArr[2])));
                        System.out.println("\t" + shoeForCart + " added to cart");
                    } else if (currArrList.contains(shoeForCart)) {
                        int ind = currArrList.indexOf(shoeForCart);
                        String incQty = currArrList.get(ind + 2);
                        currArrList.set(ind + 2, Integer.toString((Integer.parseInt(incQty)) + Integer.parseInt(qtyOfProduct)));
                        System.out.println("\t" + shoeForCart + " quantity increased");
                    } else {
                        currArrList.addAll(List.of(shArr[0], shArr[1], shArr[2]));
                        System.out.println("\t" + shoeForCart + " added to cart");
                    }
                } else {
                    buyerCart.put(buyerName.get(buyerPosition), new ArrayList<>(List.of(shArr[0], shArr[1], shArr[2])));
                    System.out.println("\t" + shoeForCart + " added to cart");
                }
            }
        }
    }

    public static void checkOutCart() {
        if (buyerCart.containsKey(buyerName.get(buyerPosition))) {
            ArrayList<String> productList = buyerCart.get(buyerName.get(buyerPosition));
            if (!productList.isEmpty()) {
                int cartValue = 0;
                System.out.println("\nList of products in cart :");
                for (int i = 0, j = 0; j < productList.size() / 3; i += 3, j++) {
                    System.out.println("\t" + j + 1 + ". " + productList.get(i));
                    String[] price = productList.get(i + 1).substring(1).split(",");
                    String priceStr = "";
                    for (String e : price) priceStr += e;
                    cartValue += Integer.parseInt(priceStr);
                }
                System.out.println("\nCart value : " + cartValue);
                System.out.println("\nPress 1 for check out / Press 2 for cancel :");
                int op = sc.nextInt();
                sc.nextLine();
                if (op == 1) {
                    if (cartValue <= buyerWallet.get(buyerPosition)) {
                        System.out.println("\tCart successfully checked out");
                        buyerWallet.set(buyerPosition, buyerWallet.get(buyerPosition) - cartValue);
                    } else
                        System.out.println("\tLow balance in your cart");

                } else
                    System.out.println("\tCheck out canceled");
            } else
                System.out.println("\n\tNo items in your cart");
        } else
            System.out.println("\n\tNo items in your cart");
    }

    public static void clearCart() {
        if (buyerCart.containsKey(buyerName.get(buyerPosition))) {
            if (buyerCart.get(buyerName.get(buyerPosition)).isEmpty())
                System.out.println("\n\t No products in your cart");
            else {
                buyerCart.get(buyerName.get(buyerPosition)).clear();
                System.out.println("\n\t Your cart has cleared");
            }
        } else
            System.out.println("\n\t No products in your cart");
    }

    public void wallet() {
        boolean walletExit = false;
        int option = 0;

        while (!walletExit) {
            System.out.println("\n<--- Mr." + buyerName.get(buyerPosition) + " wallet --->");
            System.out.println("""
                    1 - Show balance
                    2 - Add fund
                    3 - Back""");
            System.out.print("Choose option : ");
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    showBalance();
                    break;
                case 2:
                    addFund();
                    break;
                case 3:
                    walletExit = true;
                    break;
                default:
                    System.out.println("\tInvalid input");
            }
        }
    }

    private void showBalance() {
        System.out.println("\n\tYour amazon pay balance is : ₹" + buyerWallet.get(buyerPosition));
    }

    private void addFund() {
        System.out.print("\nEnter amount : ");
        int amt = sc.nextInt();
        buyerWallet.set(buyerPosition, buyerWallet.get(buyerPosition) + amt);
        System.out.println("\t amount added successfully");
    }
}
