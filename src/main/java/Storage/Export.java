package main.java.Storage;

import main.java.Expenses.ExpenseList;
import main.java.Income.IncomeList;
import main.java.Ui;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import main.java.Exceptions.ChatBotexception;
import main.java.Exceptions.InvalidInputException;

public class Export {
    Ui ui = new Ui();

    public void execute(String[] args, ExpenseList expenses, IncomeList incomes) throws ChatBotexception{
        String fileName = resolveFileName(args);
        String content = buildExportText(expenses, incomes);
        exportToFile(content, fileName);

        ui.showMessage("Succesfully exported to: " + fileName);
    }


    public String buildExportText(
            ExpenseList expenses,
            IncomeList incomes
    ) {
        StringBuilder sb = new StringBuilder();

        sb.append("EXPENSES\n");
        for (int i = 0; i < expenses.Size(); i++) {
            sb.append(i + ". ")
              .append(expenses.get(i).toString())
              .append("\n");
        }

        sb.append("\nINCOME\n");
        for (int i = 0; i < incomes.Size(); i++) {
            sb.append(i + ". ")
              .append(incomes.get(i).toString())
              .append("\n");
        }

        return sb.toString();
    }

     public void exportToFile(String content, String fileName) throws ChatBotexception {
        try {
            Path path = Paths.get(fileName);

            // If user gives "exports/chat.txt", create folders
            Path parent = path.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }

            Files.writeString(
                    path,
                    content,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING
            );
        } catch (IOException e) {
            throw new ChatBotexception("Failed to export data to file: " + fileName, e);
        }
    }

     private String resolveFileName(String[] args) throws InvalidInputException {
        if (args == null || args.length == 0) {
            return "chat-export.txt";
        }

        if (args.length > 1) {
            throw new InvalidInputException("Usage: export OR export --<filename>");
        }

        String name = args[0].trim();
        if (name.isEmpty()) {
            throw new InvalidInputException("Filename cannot be empty. Example: export --data.txt");
        }

        if (!name.toLowerCase().endsWith(".txt")) {
            name += ".txt";
        }

        return name;
    }
}
