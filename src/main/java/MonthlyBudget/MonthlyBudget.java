package main.java.MonthlyBudget;

import java.time.YearMonth;

public class MonthlyBudget {
    private final YearMonth month;
    private final int limit;
    private int spent;

    public MonthlyBudget(YearMonth month, int limit) {
        this.month = month;
        this.limit = limit;
        this.spent = 0;
    }

    public void addExpense(int amount) {
        spent += amount;
    }

    public boolean isExceeded() {
        return spent > limit;
    }

    public boolean isNearLimit() {
        return spent >= 0.8 * limit;
    }

    public int remaining() {
        return limit - spent;
    }
}
