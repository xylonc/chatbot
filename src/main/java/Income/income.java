package main.java.Income;

public class income {
    protected String description;
    protected int value;

    public income(String description , int value){
        this.description = description;
        this.value = value;
    }

    @Override
    public String toString() {
        return description + "|Income: " + value;
    }
}
