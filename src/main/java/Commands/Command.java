package main.java.Commands;

public class Command {
    private final String action;
    private final String[] args;

    public Command (String action , String[] args){
        this.action = action;
        this.args = args;
    }

    public String getAction() {return action;}
    public String[] getArgs() {return args;}
    
}
