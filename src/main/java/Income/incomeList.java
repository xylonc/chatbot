package main.java.Income;

import java.util.ArrayList;
import java.util.List;
import main.java.Ui;

public class incomeList {
    private final ArrayList<income> incomes = new ArrayList<>();

    public incomeList(){}
    public incomeList(List<income> inputList){
        if(inputList != null){
            incomes.addAll(inputList);
        }
    }

    public boolean isEmpty(){
        return incomes.isEmpty();
    }

    public int Size(){
        return incomes.size();
    }

    public income get(int id){
        return incomes.get(id);
    }

    public void addIncome(String description , int price,  Ui ui ){
       income income =new income(description, price);
       incomes.add(income);
       ui.showAddedIncome(description, price);
    }

    public void listIncome(Ui ui){
        if(isEmpty()){
            ui.showEmptyList("income");
        }
        ui.dottedLines();
        ui.showMessage("income");
        System.out.println("cha ching!");
        for (int i = 0; i < incomes.size(); i++) {
            System.out.println((i + 1) + ". " + incomes.get(i));
        }
        ui.dottedLines();
    }
}
