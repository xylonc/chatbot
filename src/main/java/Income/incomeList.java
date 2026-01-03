package main.java.Income;

import java.util.ArrayList;
import java.util.List;
import java.time.YearMonth;
import main.java.Ui;

public class IncomeList {
    private final ArrayList<Income> incomes = new ArrayList<>();
    private static int TotalIncome = 0;

    public IncomeList(){}
    public IncomeList(List<Income> inputList){
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

    public Income get(int id){
        return incomes.get(id);
    }

    public void addIncome(String description , int price,  YearMonth month, Ui ui ){
       Income income =new Income(description, price, month);
       incomes.add(income);
       TotalIncome += price;
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

    public int getTotalIncome(){
        return TotalIncome;
    }
}
