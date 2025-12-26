package main.java.Expenses;

import java.time.YearMonth;

public class Expense {
    private final String description;
    private final  int price;
    private final YearMonth month;


    public Expense(String description , int price , YearMonth month ){
        this.description = description;
        this.price = price;
        this.month = month;
    }

    public int getAmount() {return price;}
    public YearMonth getMonth() {return month;}

    public String toString() {
        return description + " |Expense: " + price ; 
    }
}
