package main.java.Commands;

import java.time.YearMonth;
import main.java.Ui;
import main.java.Expenses.Expense;
import main.java.MonthlyBudget.BudgetManager;
import main.java.MonthlyBudget.MonthlyBudget;
import main.java.Expenses.ExpenseList;
import main.java.Income.IncomeList;
import main.java.Income.Income;

public class CommandHandler {
    Ui ui = new Ui();

    public CommandHandler(){}

    public void HandleExpense(String[] args , ExpenseList expenses , BudgetManager budgets){
        if (args.length < 3) {
        ui.errorMessage("Usage: expense --<desc> --<amount> --<yyyy-mm>");
        return;
        }

        String desc = args[0];
        int amount = Integer.parseInt(args[1]);
        YearMonth month = YearMonth.parse(args[2]);

        Expense e = new Expense(desc, amount, month);
        expenses.addExpense(desc, amount , month , ui);
        
        if(budgets.getBudget(month) != null){
            budgets.registerExpense(e);
            MonthlyBudget monthBudget = budgets.getBudget(month);
            if(monthBudget.isNearLimit()){
                ui.nearLimit();
            }
            else if(monthBudget.isExceeded()){
                ui.budgetExceeded(monthBudget.exceededValue());
            }
            else{
                System.out.println("Error with budget checking");
            }
        }
    }

    public void handleBudget(String[] args , ExpenseList expenses , BudgetManager budgets){
        if (args.length < 2) {
        ui.errorMessage("Usage: budget --<amount> --<yyyy-mm>");
        return;
        }

        int amount = Integer.parseInt(args[0]);
        YearMonth month = YearMonth.parse(args[1]);

        budgets.setBudget(month, amount);
        ui.showBudgetAdded(amount, month);
        
        if(!expenses.isEmpty()){
            for(int i =0; i < expenses.Size() ; i++){
                Expense e = expenses.get(i);
                budgets.registerExpense(e);
            }
            MonthlyBudget monthBudget = budgets.getBudget(month);
            if(monthBudget.isNearLimit()){
                ui.nearLimit();
            }
            else{
                ui.budgetExceeded(monthBudget.exceededValue());
            }
        }
    }


    public void handleIncome(String[] args , IncomeList incomeList , BudgetManager budgets){
        if(args.length<3){
            ui.errorMessage("Usage: expense --<desc> --<amount> --<yyyy-mm>");
            return;
        }

        String desc = args[0];
        int amount = Integer.parseInt(args[1]);
        YearMonth month = YearMonth.parse(args[2]);

        Income income = new Income(desc , amount , month);
        incomeList.addIncome(desc, amount, month, ui);

        if(budgets.getBudget(month) != null){
            budgets.addIncome(income);
        }
    }

    public void handleTotal(String[] args , IncomeList incomeList , ExpenseList expenseList){
        if(args.length < 1){
            ui.errorMessage("Usage: list-total --<yyyy-mm>");
        }

        YearMonth month = YearMonth.parse(args[0]);
        
        int totalIncome = 0;
        for(int i = 0; i < incomeList.Size(); i++){
            Income income = incomeList.get(i);
            if(income.getMonth().equals(month)){
                totalIncome += income.getAmount();
            }
        }

        int totalExpense = 0;
        for(int j = 0; j < expenseList.Size() ; j++){
            Expense expense = expenseList.get(j);
            if(expense.getMonth().equals(month)){
                totalExpense += expense.getAmount();
            }
        }
        ui.valueMessage("Here is your total expense the month ", totalExpense);
        ui.valueMessage("Here is your total income the month ", totalIncome);

        int netTotal = totalIncome - totalExpense;
        ui.valueMessage("Here is your net total for the month ", netTotal);
    }
}
