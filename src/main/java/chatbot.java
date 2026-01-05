package main.java;

import main.java.Exceptions.InvalidInputException;
import main.java.Expenses.ExpenseList;
import main.java.Income.Income;
import main.java.Income.IncomeList;
import main.java.MonthlyBudget.BudgetManager;
import main.java.MonthlyBudget.MonthlyBudget;
import main.java.Commands.*;
 
public class Chatbot {
    public static void main(String[] args) throws Exception{
        try{
            Xylon.xylon();
        } catch (Exception e){
            System.out.println("Failed to start chatbot");
            return;
        }
        Ui ui = new Ui();
        ExpenseList expenseList = new ExpenseList();
        IncomeList incomeList = new IncomeList();
        BudgetManager budgets = new BudgetManager();
        CommandHandler CmdH = new CommandHandler();

        while(true){
            String input = ui.readCommand();
            Command cmd;
            try{
                cmd = Parser.parse(input);
                
                switch(cmd.getType()){
                    case EXPENSE:
                        CmdH.HandleExpense(cmd.getArgs(), expenseList , budgets);
                        break;
                    case LIST_EXPENSE:
                        expenseList.listExpense(ui);
                        break;
                    case INCOME:
                        CmdH.handleIncome(cmd.getArgs(), incomeList , budgets);
                        break;
                    case LIST_INCOME:
                        incomeList.listIncome(ui);
                        break;
                    case BUDGET:
                        CmdH.handleBudget(cmd.getArgs(), expenseList , budgets);
                        break;
                    case LIST_TOTAL:
                        CmdH.handleTotal(cmd.getArgs() ,incomeList , expenseList);
                        break;
                    case EXPORT:
                        CmdH.
                    case BYE:
                        ui.dottedLines();
                        System.out.println(" ");
                        ui.byeMessage();
                        ui.dottedLines();
                        return;
                    default:
                        throw new InvalidInputException("wot , I dont understand that command");
                }
            } catch (InvalidInputException e){
                ui.errorMessage(e.getMessage());
            } catch (Exception e){
                ui.errorMessage("Unexpected error occured");
            }

        }
    }
}