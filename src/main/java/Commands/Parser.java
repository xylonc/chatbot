package main.java.Commands;

import main.java.Exceptions.InvalidInputException;
import java.util.Arrays;

public class Parser {

    public static Command parse(String input) throws InvalidInputException {
        if(input == null){
            throw new InvalidInputException("Input cannot be empty please input valid commands:(");
        }
        String trimmed = input.trim();
        
        String[] parts = trimmed.split("\\s*--\\s*");

        String action = parts[0].toLowerCase();
        String[] args = Arrays.copyOfRange(parts, 1, parts.length);

        return new Command(action, args);
    }
}
