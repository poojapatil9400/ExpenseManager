import java.util.Scanner;

  public class DailyExpenseTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExpenseManager manager = new ExpenseManager();
        boolean running = true;

        while (running) {
            System.out.println("\n--- Daily Expense Tracker ---");
            System.out.println("1. Add Expense");
            System.out.println("2. View All Expenses");
            System.out.println("3. View Summary");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();
                    System.out.print("Enter description: ");
                    String description = scanner.nextLine();
                    manager.addExpense(amount, category, description);
                    System.out.println("Expense added successfully!");
                    break;

                case 2:
                    manager.displayExpenses();
                    break;

                case 3:
                    System.out.print("Enter period (day/week/month): ");
                    String period = scanner.nextLine();
                    manager.displaySummary(period);
                    break;

                case 4:
                    running = false;
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
