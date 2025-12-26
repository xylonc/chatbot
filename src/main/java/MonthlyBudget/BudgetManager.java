package main.java.MonthlyBudget;

import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;
import main.java.Expenses.Expense;

public class BudgetManager {
    private final Map<YearMonth, MonthlyBudget> budgets = new HashMap<>();

    public void setBudget(YearMonth month, int limit) {
        budgets.put(month, new MonthlyBudget(month, limit));
    }

    public void registerExpense(Expense expense) {
        MonthlyBudget budget = budgets.get(expense.getMonth());
        if (budget != null) {
            budget.addExpense(expense.getAmount());
        }
    }

    public MonthlyBudget getBudget(YearMonth month) {
        return budgets.get(month);
    }
}
