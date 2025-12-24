package main.java.Expenses;

public class Expense {
    protected String description;
    protected int price;
    protected String date;

    public Expense(String description , int price ){
        this.description = description;
        this.price = price;
    }

    public String toString() {
        return description + " |Expense: " + price ; 
    }
}
