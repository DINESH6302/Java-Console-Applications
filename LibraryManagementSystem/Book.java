package App.LibraryManagementSystem;

import java.util.*;

public class Book {

    String categoryName;

    ArrayList<String> bookName = new ArrayList<>();
    ArrayList<String> author = new ArrayList<>();
    ArrayList<String> year = new ArrayList<>();
    ArrayList<Integer> quantity = new ArrayList<>();

    public Book(String categoryName, ArrayList<String> bookName, ArrayList<String> author, ArrayList<String> year, ArrayList<Integer> quantity) {
        this.categoryName = categoryName;
        this.bookName = bookName;
        this.author = author;
        this.year = year;
        this.quantity = quantity;
    }
}
