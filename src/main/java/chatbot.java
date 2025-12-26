package main.java;

import main.java.Exceptions.InvalidInputException;
import main.java.Expenses.ExpenseList;
import main.java.Income.Income;
import main.java.Income.IncomeList;
import main.java.MonthlyBudget.MonthlyBudget;
import main.java.Commands.*;
//todo : add in setbudget ->but first do parser 
public class Chatbot {
    public static void main(String[] args) throws Exception{
        Xylon.xylon();
        Ui ui = new Ui();
        ExpenseList expenseList = new ExpenseList();
        IncomeList incomeList = new IncomeList();
        MonthlyBudget Budgets;
        CommandHandler CmdH = new CommandHandler();

        while(true){
            String input = ui.readCommand();
            Command cmd;
            try{
                cmd = Parser.parse(input);
            }
            catch (InvalidInputException e){
                ui.errorMessage("wot , I dont understand that command");
                continue;
            }
            switch(cmd.getAction()){
                case "expense":
                    CmdH.HandleExpense(cmd.getArgs(), expenseList);
                    break;
                case "liste":
                    expenseList.listExpense(ui);
                    break;
                //case "income":
                    //incomeList.addIncome(parsedCommand.args, parsedCommand.value, parsedCommand.month, ui);
                    //break;
                case "listi":
                    incomeList.listIncome(ui);
                    break;
                case "budget":
                    CmdH.handleBudget(cmd.getArgs(), expenseList);
                    break;
                case "bye":
                    ui.dottedLines();
                    System.out.println(" ");
                    ui.byeMessage();
                    ui.dottedLines();
                    return;
            }
        }
    }
}