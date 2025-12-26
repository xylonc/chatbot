package main.java.Income;

import java.time.Year;
import java.time.YearMonth;

public class Income {
    private final String description;
    private final int value;
    private final YearMonth month;


    public Income(String description , int value , YearMonth month){
        this.description = description;
        this.value = value;
        this.month = month;
    }

    public int getAmount() {return value;}
    public YearMonth getMonth() {return month;}

    @Override
    public String toString() {
        return description + "|Income: " + value;
    }
}
