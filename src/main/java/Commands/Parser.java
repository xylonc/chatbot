package main.java.Commands;

import java.time.Year;
import java.time.YearMonth;

import main.java.Exceptions.InvalidInputException;
import java.util.Arrays;

public class Parser {

    public static Command parse(String input) throws InvalidInputException {
        String trimmed = input.trim();
        String[] parts = trimmed.split("\\s+");

        String action = parts[0].toLowerCase();
        String[] args = Arrays.copyOfRange(parts, 1, parts.length);

        return new Command(action, args);
    }
}
