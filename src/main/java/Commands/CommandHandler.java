package main.java.Commands;

import java.sql.Date;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;

import main.java.Ui;
import main.java.Exceptions.InvalidInputException;
import main.java.Expenses.Expense;
import main.java.MonthlyBudget.BudgetManager;
import main.java.MonthlyBudget.MonthlyBudget;
import main.java.Expenses.ExpenseList;
import main.java.Income.IncomeList;
import main.java.Income.Income;

public class CommandHandler {
    Ui ui = new Ui();

    public CommandHandler(){}

    public void HandleExpense(String[] args , ExpenseList expenses , BudgetManager budgets) throws InvalidInputException{
        if (args.length < 3) {
            throw new InvalidInputException("Usage: expense --<desc> --<amount> --<yyyy-mm>");
        }

        String desc = args[0];
        int amount;
        try{
            amount = Integer.parseInt(args[1]);
        } catch (NumberFormatException e){
            throw new InvalidInputException("Amount must be a number",e);
        }
        YearMonth month;
        try{
            month = YearMonth.parse(args[2]);
        } catch (DateTimeParseException e){
            throw new InvalidInputException("Month must be <yyyy-mm>",e);
        }
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

    public void handleBudget(String[] args , ExpenseList expenses , BudgetManager budgets) throws InvalidInputException{
        if (args.length < 2) {
        throw new InvalidInputException("Usage: budget --<amount> --<yyyy-mm>");
        }
        int amount;
        try{
            amount = Integer.parseInt(args[0]);
        } catch (NumberFormatException e){
            throw new InvalidInputException("Budget must be a valid amount" , e);
        }
        YearMonth month;
        try{
            month = YearMonth.parse(args[1]);
        } catch (DateTimeParseException e){
            throw new InvalidInputException("Month must be yyyy-mm", e);
        }

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


    public void handleIncome(String[] args , IncomeList incomeList , BudgetManager budgets) throws InvalidInputException{
        if(args.length<3){
            throw new InvalidInputException("Usage: expense --<desc> --<amount> --<yyyy-mm>");
        }

        String desc = args[0];
        int amount;
        try{
            amount = Integer.parseInt(args[1]);
        } catch(NumberFormatException e){
            throw new InvalidInputException("Amount must be a number", e);
        }
        YearMonth month;
        try{
            month = YearMonth.parse(args[2]);
        } catch(DateTimeParseException e){
            throw new InvalidInputException("Month must be <yyyy-mm>", e);
        }

        Income income = new Income(desc , amount , month);
        incomeList.addIncome(desc, amount, month, ui);

        if(budgets.getBudget(month) != null){
            budgets.addIncome(income);
        }
    }

    public void handleTotal(String[] args , IncomeList incomeList , ExpenseList expenseList) throws InvalidInputException{
        if(args.length < 1){
            throw new InvalidInputException("Usage: list-total --<yyyy-mm>");
        }

        YearMonth month;
        try{
            month = YearMonth.parse(args[0]);
        } catch(DateTimeParseException e){
            throw new InvalidInputException("Month must be <yyyy-mm>",e);
        }
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
