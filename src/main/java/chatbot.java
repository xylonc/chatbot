package main.java;

import main.java.Exceptions.InvalidInputException;
import main.java.Expenses.ExpenseList;
import main.java.Income.income;
import main.java.Income.incomeList;

public class chatbot {
    public static void main(String[] args) throws Exception{
        Xylon.xylon();
        Ui ui = new Ui();
        ExpenseList expenseList = new ExpenseList();
        incomeList incomeList = new incomeList();

        while(true){
            String input = ui.readCommand();
            Parser.ParsedCommand parsedCommand;
            try{
                parsedCommand = Parser.parse(input);
            }
            catch (InvalidInputException e){
                ui.errorMessage("wot , I dont understand that command");
                continue;
            }
            switch(parsedCommand.command){
                case "expense":
                    expenseList.addExpense(parsedCommand.args , parsedCommand.value , ui);
                    break;
                case "liste":
                    expenseList.listExpense(ui);
                    break;
                case "income":
                    incomeList.addIncome(parsedCommand.args, parsedCommand.value, ui);
                    break;
                case "listi":
                    incomeList.listIncome(ui);
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
