package main.java.MonthlyBudget;

import java.time.Year;
import java.time.YearMonth;

public class MonthlyBudget {
    private final YearMonth month;
    private final int limit;
    private int spent;
    private int income;

    public MonthlyBudget(YearMonth month, int limit) {
        this.month = month;
        this.limit = limit;
        this.spent = 0;
        this.income = 0;
    }

    public void addExpense(int amount) {
        spent += amount;
    }

    public void addIncome(int amount){
        income += amount;
    }

    public boolean isExceeded() {
        return spent > limit;
    }

    public boolean isNearLimit() {
        return spent >= 0.8 * limit && spent <= limit;
    }

    public int remaining() {
        return limit - spent;
    }

    public int exceededValue(){
        return spent - limit;
    }

    public int endMonthTotal(){
        return income - spent;
    }

    public int monthSpending(MonthlyBudget budget){
        return budget.spent;
    }

    public int MonthIncome(MonthlyBudget budget){
        return budget.income;
    }
}
