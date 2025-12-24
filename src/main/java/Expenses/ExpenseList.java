package main.java.Expenses;

import java.util.ArrayList;
import java.util.List;

import main.java.Ui;
import main.java.Exceptions.InvalidInputException;

public class ExpenseList {
    private final ArrayList<Expense> expenses = new ArrayList<>();

    public ExpenseList() {}
    public ExpenseList(List<Expense> inputList) throws InvalidInputException{
        if(inputList !=null){
            expenses.addAll(inputList);
        }
    }

    public boolean isEmpty(){
        return expenses.isEmpty();
    }

    public int Size(){
        return expenses.size();
    }

    public Expense get(int id){
        return expenses.get(id);
    }

    public void addExpense(String description , int price,  Ui ui ){
       Expense expense =new Expense(description, price);
       expenses.add(expense);
       ui.showAddedExpense(description, price);
    }

    public void listExpense(Ui ui){
        if(isEmpty()){
            ui.showEmptyList("Expense");
        }
        else{
            ui.dottedLines();
            ui.showMessage("expenses");
            for (int i = 0; i < expenses.size(); i++) {
                System.out.println((i + 1) + ". " + expenses.get(i));
            }
            ui.dottedLines();
        }
    }
}
