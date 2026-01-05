package main.java.Storage;

import main.java.Expenses.ExpenseList;
import main.java.Income.IncomeList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import main.java.Exceptions.ChatBotexception;

public class Export {
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
            Files.writeString(path, content);
        } catch (IOException e) {
            throw new ChatBotexception("Failed to export data to file: " + fileName, e);
        }
    }
}
