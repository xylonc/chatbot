package main.java.Commands;

import java.time.YearMonth;
import main.java.Ui;
import main.java.Expenses.Expense;
import main.java.MonthlyBudget.BudgetManager;
import main.java.MonthlyBudget.MonthlyBudget;
import main.java.Expenses.ExpenseList;

public class CommandHandler {
    Ui ui = new Ui();
    BudgetManager budgets = new BudgetManager();

    public CommandHandler(){}

    public void HandleExpense(String[] args , ExpenseList expenses ){
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
            if(monthBudget.isExceeded()){
                ui.budgetExceeded(monthBudget.exceededValue());
            }
            else if(monthBudget.isNearLimit()){
                ui.nearLimit();
            }
            else{
                System.out.println("Error with budget checking");
            }
        }
    }

    public void handleBudget(String[] args , ExpenseList expenses){
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
        }
    }
}
