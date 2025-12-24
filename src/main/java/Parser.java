package main.java;

import main.java.Exceptions.InvalidInputException;
public class Parser {

    public static ParsedCommand parse(String input) throws Exception {
        String[] parts = input.split("\\s*--\\s*", 3);
        String command = parts[0].toLowerCase();
        String arguments = parts.length> 1 ? parts[1] : "";
        int value = parts.length> 2 ? Integer.parseInt(parts[2]) : -1;
        switch (command) {
        case "expense":
        case "income":
        case "liste":
        case "listi":
        case"bye":
            return new ParsedCommand(command, arguments , value);
        default:
            throw new InvalidInputException();
        }
    }

    public static class ParsedCommand {
        public final String command;
        public final String args;
        public final int value;
        public ParsedCommand(String command, String args , int value) {
            this.command = command;
            this.args = args;
            this.value = value;
        }
    }
}
