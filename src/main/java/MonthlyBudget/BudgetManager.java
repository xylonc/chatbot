package main.java.MonthlyBudget;

import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;


import main.java.Expenses.Expense;
import main.java.Income.Income;

public class BudgetManager {
    private final Map<YearMonth, MonthlyBudget> budgets = new HashMap<>();
    private final Map<YearMonth, MonthlyBudget> incomes = new HashMap<>();

    public BudgetManager() {}

    public void setBudget(YearMonth month, int limit) {
        MonthlyBudget createdBudget = new MonthlyBudget(month , limit);
        budgets.put(month, createdBudget);
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

    public void addIncome(Income income){
         MonthlyBudget budget = budgets.get(income.getMonth());
         if(budget != null){
            budget.addIncome(income.getAmount());
         }
    }

    public int returnTotal(MonthlyBudget budget){
        return budget.endMonthTotal();
    }
}
