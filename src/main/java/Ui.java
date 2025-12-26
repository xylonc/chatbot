package main.java;

import java.time.YearMonth;
import java.util.Scanner;

public class Ui {

    private final Scanner scanner = new Scanner(System.in);

    public String readCommand(){
        return scanner.nextLine();
    }

    public void dottedLines(){
        System.out.println("-".repeat(30));
    }

    public void showMessage(String categoryName){
        System.out.println("Here are your " + categoryName + " bro:\n");
    }

    public void showEmptyList(String categoryName){
        System.out.println(categoryName + " is empty \n");
    }

    public void showAddedExpense(String description ,int value) {
        System.out.print("Added the expense " + description + " -" + value);
        System.out.print("\n");
    }

    public void showAddedIncome(String description ,int value) {
        System.out.print("Added the income " + description + " +" + value +"\n");
    }

    public void showBudgetAdded(int value , YearMonth month){
        System.out.println("Added the budget of " + value + " for the month of " + month);
    }

    public void budgetExceeded(int value){
        System.out.println("GG bro your budget this month has exceeded by $" + value );
    }

    public void nearLimit(){
        System.out.println("Careful you are reaching your budget for this month");
    }

    public void errorMessage(String errorMessage){
        System.out.println(errorMessage);
    }

    public void byeMessage(){
        System.out.println("See you again soon!\n");
    }
}
    