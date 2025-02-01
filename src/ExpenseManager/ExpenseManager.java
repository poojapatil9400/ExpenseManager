import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExpenseManager {
    private List<Expense> expenses;
    private final String fileName = "expenses.txt";

    public ExpenseManager() {
        this.expenses = new ArrayList<>();
        loadExpenses();
    }

    public void addExpense(double amount, String category, String description) {
        Expense expense = new Expense(amount, category, description);
        expenses.add(expense);
        saveExpenses();
    }

    public void displayExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded.");
            return;
        }
        System.out.println("Date\t\tAmount\tCategory\tDescription");
        for (Expense e : expenses) {
            System.out.printf("%s\t%.2f\t%s\t%s%n", e.getDate(), e.getAmount(), e.getCategory(), e.getDescription());
        }
    }

    public void displaySummary(String period) {
        LocalDate today = LocalDate.now();
        double total = 0;

        for (Expense e : expenses) {
            if (period.equalsIgnoreCase("day") && e.getDate().isEqual(today)) {
                total += e.getAmount();
            } else if (period.equalsIgnoreCase("week") && e.getDate().isAfter(today.minusDays(7))) {
                total += e.getAmount();
            } else if (period.equalsIgnoreCase("month") && e.getDate().getMonth().equals(today.getMonth())) {
                total += e.getAmount();
            }
        }

        System.out.printf("Total expenses for the %s: %.2f%n", period, total);
    }

    private void saveExpenses() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Expense e : expenses) {
                writer.write(e.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving expenses: " + e.getMessage());
        }
    }

    private void loadExpenses() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                expenses.add(Expense.fromString(line));
            }
        } catch (FileNotFoundException e) {
            // File not found, no data to load
        } catch (IOException e) {
            System.out.println("Error loading expenses: " + e.getMessage());
        }
    }
}
