package App.Amazon;


import java.util.*;


public class Product {
    static Scanner sc = new Scanner(System.in);
    static String currentSeller = null;
    static int ascii = 58;
    static HashMap<String, String> phones = new HashMap<>();
    static HashMap<String, String> tv = new HashMap<>();
    static HashMap<String, String> shoes = new HashMap<>();

    public void showProducts(int cty) {
        System.out.println("-----------------------------------------------------------------------------");
        System.out.printf("%6s %21s %15s %13s %12s", "S.NO", "PRODUCT NAME", "PRICE", "QUANTITY", "SELLER");
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------");

        if (cty == 1) {
            int j = 1;
            for (Map.Entry m : phones.entrySet()) {
                String phKey = (String) m.getKey();
                String phValue = (String) m.getValue();
                String[] phArr = phValue.split("/");
                System.out.format("%4s %25s %15s %9s %11s", j++, phArr[0], phArr[1], phArr[2], phKey.substring(0, phKey.length() - 1));
                System.out.println();

            }
        } else if (cty == 2) {
            int j = 1;
            for (Map.Entry m : tv.entrySet()) {
                String tvKey = (String) m.getKey();
                String tvValue = (String) m.getValue();
                String[] tvArr = tvValue.split("/");
                System.out.format("%4s %25s %15s %9s %11s", j++, tvArr[0], tvArr[1], tvArr[2], tvKey.substring(0, tvKey.length() - 1));
                System.out.println();

            }
        } else if (cty == 3) {
            int j = 1;
            for (Map.Entry m : shoes.entrySet()) {
                String shKey = (String) m.getKey();
                String shValue = (String) m.getValue();
                String[] shArr = shValue.split("/");
                System.out.format("%4s %25s %15s %9s %11s", j++, shArr[0], shArr[1], shArr[2], shKey.substring(0, shKey.length() - 1));
                System.out.println();

            }
        }

        System.out.println("-----------------------------------------------------------------------------");
    }


    public void showCategory() {
        System.out.println("\n<------ CATEGORY ------>");
        String[] category = {"Phones", "Tv", "Shoes"};

        boolean categoryExit = false;
        int option = 0;
        while (!categoryExit) {

            for (int i = 0; i < category.length; i++) {
                System.out.println(i + 1 + ". " + category[i]);
            }
            System.out.println(category.length + 1 + ". " + "Back");
            System.out.print("Choose category : ");
            option = sc.nextInt();
            sc.nextLine();
            if (option == category.length + 1) categoryExit = true;
            else if (option > category.length) System.out.println("\tInvalid input");
            else showProducts(option);
        }
    }

    public static void initialProductAdd() {
        if (phones.isEmpty()) {
            phones.put("W1", "Samsung Ultra S21/₹89,999/21");
            phones.put("Z2", "OnePlus 9pro/₹72,000/16");
            phones.put("Y3", "iPhone 13pro/₹1,49,999/10");
        }
        if (tv.isEmpty()) {
            tv.put("X4", "Redmi L32M/₹41,940/15");
            tv.put("Z5", "Acer P series/₹21,999/34");
            tv.put("Y6", "Panasonic T63/₹19,599/26");
            tv.put("W7", "Sony Bravia/₹25,490/20");
        }
        if (shoes.isEmpty()) {
            shoes.put("Z9", "Nike V-black/₹4000/52");
            shoes.put("W8", "Generic Sneaker/₹1499/36");
            shoes.put("Y0", "Sparx Sd04/₹999/72");
        }
    }

    public static void addProducts(String currSeller) {
        currentSeller = currSeller;

        boolean addProExit = false;
        while (!addProExit) {
            System.out.println("\nSelect product category");
            System.out.println("""
                    1 - phones
                    2 - Tv
                    3 - Shoes
                    4 - Back""");
            System.out.print("Option : ");
            String option = sc.nextLine();

            switch (option) {
                case "1":
                    addPhone();
                    break;
                case "2":
                    addTv();
                    break;
                case "3":
                    addShoe();
                    break;
                case "4":
                    addProExit = true;
                    break;
                default:
                    System.out.println("\tInvalid option");
            }
        }
    }

    private static void addPhone() {
        System.out.print("Phone Brand : ");
        String phBrand = sc.nextLine();
        System.out.print("Price : ");
        String phPrice = sc.nextLine();
        System.out.print("Quantity : ");
        String phQty = sc.nextLine();

        boolean flag = true;
        for (Map.Entry m : phones.entrySet()) {
            String ph = (String) m.getValue();
            String[] phArr = ph.split("/");
            if (phBrand.equals(phArr[0])) {
                flag = false;
                break;
            }
        }
        if (flag) {
            String sellerUnique = currentSeller + (char) ascii++;
            phones.put(sellerUnique, phBrand + "/₹" + phPrice + "/" + phQty);
            System.out.println("\t" + phBrand + " added successfully");
        } else System.out.println("\t" + phBrand + " is already exist");
    }

    private static void addTv() {
        System.out.print("Tv Brand : ");
        String tvBrand = sc.nextLine();
        System.out.print("Price : ");
        String tvPrice = sc.nextLine();
        System.out.print("Quantity : ");
        String tvQty = sc.nextLine();

        boolean flag = true;
        for (Map.Entry m : tv.entrySet()) {
            String tvValue = (String) m.getValue();
            String[] tvArr = tvValue.split("/");
            if (tvBrand.equals(tvArr[0])) {
                flag = false;
                break;
            }
        }
        if (flag) {
            String sellerUnique = currentSeller + (char) ascii++;
            tv.put(sellerUnique, tvBrand + "/₹" + tvPrice + "/" + tvQty);
            System.out.println("\t" + tvBrand + " added successfully");
        } else System.out.println("\t" + tvBrand + " is already exist");
    }

    private static void addShoe() {
        System.out.print("Shoe Brand : ");
        String shBrand = sc.nextLine();
        System.out.print("Price : ");
        String shPrice = sc.nextLine();
        System.out.print("Quantity : ");
        String shQty = sc.nextLine();

        boolean flag = true;
        for (Map.Entry m : shoes.entrySet()) {
            String sh = (String) m.getValue();
            String[] shArr = sh.split("/");
            if (shBrand.equals(shArr[0])) {
                flag = false;
                break;
            }
        }
        if (flag) {
            String sellerUnique = currentSeller + (char) ascii++;
            shoes.put(sellerUnique, shBrand + "/₹" + shPrice + "/" + shQty);
            System.out.println("\t" + shBrand + " added successfully");
        } else System.out.println("\t" + shBrand + " is already exist");
    }

    public static void rmvDecProducts(String currSeller) {
        currentSeller = currSeller;
        boolean removeProExit = false;
        while (!removeProExit) {
            System.out.println("\nSelect product category");
            System.out.println("""
                    1 - phones
                    2 - Tv
                    3 - Shoes
                    4 - Back""");
            System.out.print("Option : ");
            String option = sc.nextLine();

            switch (option) {
                case "1":
                    rmvUpdPhone();
                    break;
                case "2":
                    rmvUpdTv();
                    break;
                case "3":
                    rmvUpdShoe();
                    break;
                case "4":
                    removeProExit = true;
                    break;
                default:
                    System.out.println("\tInvalid option");
            }
        }
    }

    private static void rmvUpdPhone() {
        System.out.print("\nRemove product(1)/Update quantity(2) : ");
        String decision = sc.nextLine();
        if (decision.equals("1") || decision.equals("2")) {
            System.out.print("Phone Name : ");
            String rUPhone = sc.nextLine();

            String keyOfProduct = null;
            String valueOfProduct = null;
            String[] product = new String[2];
            for (Map.Entry m : phones.entrySet()) {
                String key = (String) m.getKey();
                String value = (String) m.getValue();
                if (key.substring(0, key.length() - 1).equals(currentSeller)) {
                    product = value.split("/");
                    if (product[0].equals(rUPhone)) {
                        keyOfProduct = key;
                        valueOfProduct = value;
                        break;
                    } else Arrays.fill(product, null);
                }
            }
            if (decision.equals("1")) {
                if (keyOfProduct != null) {
                    phones.remove(keyOfProduct);
                    System.out.println("\t" + rUPhone + " removed successfully");
                } else System.out.println("\t" + rUPhone + " doesn't exist");

            } else {
                if (keyOfProduct != null) {
                    System.out.print("New quantity : ");
                    String newQty = sc.nextLine();
                    System.out.println("\t new quantity updated");
                    phones.replace(keyOfProduct, product[0] + "/" + product[1] + "/" + newQty);
                } else System.out.println("\t" + rUPhone + " doesn't exist");
            }
        } else System.out.println("\tInvalid input");
    }

    private static void rmvUpdTv() {
        System.out.print("\nRemove product(1)/Update quantity(2) : ");
        String decision = sc.nextLine();
        if (decision.equals("1") || decision.equals("2")) {
            System.out.print("Tv Name : ");
            String rUTv = sc.nextLine();

            String keyOfProduct = null;
            String valueOfProduct = null;
            String[] product = new String[2];
            for (Map.Entry m : tv.entrySet()) {
                String key = (String) m.getKey();
                String value = (String) m.getValue();
                if (key.substring(0, key.length() - 1).equals(currentSeller)) {
                    product = value.split("/");
                    if (product[0].equals(rUTv)) {
                        keyOfProduct = key;
                        valueOfProduct = value;
                        break;
                    } else Arrays.fill(product, null);
                }
            }
            if (decision.equals("1")) {
                if (keyOfProduct != null) {
                    tv.remove(keyOfProduct);
                    System.out.println("\t" + rUTv + " removed successfully");
                } else System.out.println("\t" + rUTv + " doesn't exist");

            } else {
                if (keyOfProduct != null) {
                    System.out.print("New quantity : ");
                    String newQty = sc.nextLine();
                    System.out.println("\t new quantity updated");
                    tv.replace(keyOfProduct, product[0] + "/" + product[1] + "/" + newQty);
                } else System.out.println("\t" + rUTv + " doesn't exist");
            }
        } else System.out.println("\tInvalid input");
    }

    private static void rmvUpdShoe() {
        System.out.print("\nRemove product(1)/Update quantity(2) : ");
        String decision = sc.nextLine();
        if (decision.equals("1") || decision.equals("2")) {
            System.out.print("Shoe Name : ");
            String rUShoe = sc.nextLine();

            String keyOfProduct = null;
            String valueOfProduct = null;
            String[] product = new String[2];
            for (Map.Entry m : shoes.entrySet()) {
                String key = (String) m.getKey();
                String value = (String) m.getValue();
                if (key.substring(0, key.length() - 1).equals(currentSeller)) {
                    product = value.split("/");
                    if (product[0].equals(rUShoe)) {
                        keyOfProduct = key;
                        valueOfProduct = value;
                        break;
                    } else Arrays.fill(product, null);
                }
            }
            if (decision.equals("1")) {
                if (keyOfProduct != null) {
                    shoes.remove(keyOfProduct);
                    System.out.println("\t" + rUShoe + " removed successfully");
                } else System.out.println("\t" + rUShoe + " doesn't exist");

            } else {
                if (keyOfProduct != null) {
                    System.out.print("New quantity : ");
                    String newQty = sc.nextLine();
                    System.out.println("\t new quantity updated");
                    shoes.replace(keyOfProduct, product[0] + "/" + product[1] + "/" + newQty);
                } else System.out.println("\t" + rUShoe + " doesn't exist");
            }
        } else System.out.println("\tInvalid input");
    }

    public static void compareProducts(String curSeller) {
        currentSeller = curSeller;
    }

    public void cart(String buyerName) {
        boolean cartExit = false;
        int option = 0;

        while (!cartExit) {
            System.out.println("\n<----- Mr." + buyerName + "'s Cart ----->");
            System.out.println("""
                    1 - View cart
                    2 - Add product
                    3 - Remove product
                    4 - Check out
                    5 - Clear cart
                    6 - Back""");
            System.out.print("Choose option : ");
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    Buyer.viewCart();
                    break;
                case 2:
                    Buyer.addProductToCart();
                    break;
                case 3:
                    Buyer.removeProductFromCart();
                    break;
                case 4:
                    Buyer.checkOutCart();
                    break;
                case 5:
                    Buyer.clearCart();
                    break;
                case 6:
                    cartExit = true;
                    break;
                default:
                    System.out.println("\tInvalid input");
            }
        }

    }

    public void buy() {
        boolean buyProExit = false;
        String[] category = {"Phones", "Tv", "Shoes"};
        while (!buyProExit) {
            System.out.println("\nSelect product category");
            System.out.println("""
                    1 - phones
                    2 - Tv
                    3 - Shoes
                    4 - Back""");
            System.out.print("Option : ");
            int option = sc.nextInt();
            sc.nextLine();
            if (option == category.length + 1) {
                buyProExit = true;
                continue;
            } else if (option > category.length) System.out.println("\tInvalid input");
            else showProducts(option);


            System.out.print("\nEnter product name to buy : ");
            String prtName = sc.nextLine();
            String[] getPrtName = new String[3];

            for (Map.Entry<String, String> m : phones.entrySet()) {
                String value = m.getValue();
                String[] phArr = value.split("/");
                if (phArr[0].equals(prtName)) getPrtName = phArr;
            }

            if (getPrtName[0] == null) {
                for (Map.Entry<String, String> m : tv.entrySet()) {
                    String value = m.getValue();
                    String[] tvArr = value.split("/");
                    if (tvArr[0].equals(prtName)) getPrtName = tvArr;
                }
            }

            if (getPrtName[0] == null) {
                for (Map.Entry<String, String> m : shoes.entrySet()) {
                    String value = m.getValue();
                    String[] shArr = value.split("/");
                    if (shArr[0].equals(prtName)) getPrtName = shArr;
                }
            }

            if (getPrtName[0] == null) {
                System.out.println("\t" + prtName + " is not available");
                return;
            }

            System.out.print("\nEnter product quantity : ");
            int prtQtu = sc.nextInt();
            String[] price = getPrtName[getPrtName.length - 2].substring(1, getPrtName[1].length()).split(",");
            String p = "";
            for (String s : price) p += s;
            int totPrice = prtQtu * Integer.parseInt(p);

            if (Buyer.buyerWallet.get(Buyer.buyerPosition) >= totPrice) {
                System.out.println("\nProduct : " + prtName + "\t\t" + "Total amount : " + totPrice);
                System.out.print("\tConform order(1) / Cancel order(2) : ");
                int op = sc.nextInt();
                if (op == 1) {
                    System.out.println("\t\tOrder completed, thank you!");

                    if (option == 1) {
                        for (Map.Entry<String, String> m : phones.entrySet()) {
                            String value = m.getValue();
                            String key = m.getKey();
                            String[] phArr = value.split("/");
                            if (phArr[0].equals(prtName))
                                phones.put(key, phones.get(key).replace(value, phArr[0] + "/" + phArr[1] + "/" + Integer.toString(Integer.parseInt(phArr[2]) - prtQtu)));
                        }
                    } else if (option == 2) {
                        for (Map.Entry<String, String> m : tv.entrySet()) {
                            String value = m.getValue();
                            String key = m.getKey();
                            String[] phArr = value.split("/");
                            if (phArr[0].equals(prtName))
                                tv.put(key, tv.get(key).replace(value, phArr[0] + "/" + phArr[1] + "/" + Integer.toString(Integer.parseInt(phArr[2]) - prtQtu)));
                        }
                    } else {
                        for (Map.Entry<String, String> m : shoes.entrySet()) {
                            String value = m.getValue();
                            String key = m.getKey();
                            String[] phArr = value.split("/");
                            if (phArr[0].equals(prtName))
                                shoes.put(key, shoes.get(key).replace(value, phArr[0] + "/" + phArr[1] + "/" + Integer.toString(Integer.parseInt(phArr[2]) - prtQtu)));
                        }
                    }

                    Buyer.buyerWallet.set(Buyer.buyerPosition, Buyer.buyerWallet.get(Buyer.buyerPosition) - totPrice);
                    addOrder(Buyer.buyerName.get(Buyer.buyerPosition), prtName, Integer.toString(prtQtu), Integer.toString(totPrice), "Completed");
                } else if (op == 2) {
                    System.out.println("\tOrder canceled");
                    addOrder(Buyer.buyerName.get(Buyer.buyerPosition), prtName, Integer.toString(prtQtu), Integer.toString(totPrice), "Canceled");
                } else System.out.println("\tInvalid input");
            } else System.out.println("\tYour wallet balance is low");
        }
    }

    private void addOrder(String buyerName, String prtName, String prtQty, String totPrice, String status) {
        if (Buyer.buyerOrders.isEmpty())
            Buyer.buyerOrders.put(buyerName, new ArrayList<>(List.of(prtName, prtQty, totPrice, "1/7/2021", status)));
        else {
            if (Buyer.buyerOrders.containsKey(buyerName)) {
                Buyer.buyerOrders.get(buyerName).addAll(Arrays.asList(prtName, prtQty, totPrice, "1/7/2021", status));
            } else
                Buyer.buyerOrders.put(buyerName, new ArrayList<>(List.of(prtName, prtQty, totPrice, "1/7/2021", status)));
        }

    }

    public void orders() {
        if (!Buyer.buyerOrders.isEmpty()) {
            if (Buyer.buyerOrders.containsKey(Buyer.buyerName.get(Buyer.buyerPosition))) {
                System.out.println("-----------------------------------------------------------------------------------------------------");
                System.out.printf("%6s %21s %18s %16s %10s %14s", "S.NO", "PRODUCT NAME", "QUANTITY", "TOTAL PRICE", "DATE", "STATUS");
                System.out.println();
                System.out.println("-----------------------------------------------------------------------------------------------------");
                ArrayList<String> orderList = Buyer.buyerOrders.get(Buyer.buyerName.get(Buyer.buyerPosition));
                for (int k = 0, i = 0, j = 1; k < orderList.size() / 5; i += 5, j++, k++) {
                    System.out.format("%4s %25s %13s %16s %15s %14s", j, orderList.get(i), orderList.get(i + 1), orderList.get(i + 2), orderList.get(i + 3), orderList.get(i + 4));
                    System.out.println();
                }
                System.out.println("-----------------------------------------------------------------------------------------------------");
            }
            System.out.println("\n\tYou don't have order details");
        } else {
            System.out.println("\n\tYou don't have order details");
        }

    }
}
