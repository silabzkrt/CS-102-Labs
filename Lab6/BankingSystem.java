package Lab6;
import java.util.Scanner;

public class BankingSystem {
    public static void startScreen(Bank bank) {
        System.out.println("Welcome to The Bank!");
        System.out.println("Current Currencies:");
        for (Currency cr : bank.getCurrencies()) {
            System.out.println(cr.getName() + ": " + cr.getRate());
        }
    }

    public static void choiceScreen() {
        System.out.println("\nWhat do you want to do?");
        System.out.println("1. Generate Random Users");
        System.out.println("2. List Users");
        System.out.println("3. Show user with ID");
        System.out.println("4. Set conversion rates");
        System.out.println("5. Sort users");
        System.out.println("6. Reset to the original unsorted array");
        System.out.println("0. Exit");
    }

    public static void main(String[] args) {
        Bank bank = new Bank();
        startScreen(bank);

        try (Scanner sc = new Scanner(System.in)) {
            boolean cont = true;
            while (cont) {
                choiceScreen();
                System.out.print("Option: ");
                if (!sc.hasNextInt()) { // Validate input
                    System.out.println("Invalid input. Please enter a number between 0 and 6.");
                    sc.next(); // Consume invalid input
                    continue;
                }
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Generate how many?: ");
                        if (!sc.hasNextInt()) { // Validate input
                            System.out.println("Invalid input. Please enter a valid number.");
                            sc.next(); // Consume invalid input
                            continue;
                        }
                        int number = sc.nextInt();
                        bank.generateUsers(number);
                        System.out.println("Generating " + number + " users.");
                        break;

                    case 2:
                        for (User user : bank.getUsers()) {
                            System.out.println(user);
                        }
                        break;

                    case 3:
                        System.out.print("Enter User ID: ");
                        if (!sc.hasNext()) { 
                            System.out.println("Invalid input. Please enter a valid ID.");
                            sc.next(); 
                            continue;
                        }
                        String id = sc.next();
                        boolean found = false;
                        for (User user : bank.getUsers()) {
                            if (user.getId().equals(id)) {
                                System.out.println(user);
                                for (int i = 0; i < user.getAccounts().size(); i++) {
                                    Account account = user.getAccounts().get(i);
                                    System.out.println((i + 1) + " Type: " + account.getCurrency().getName()
                                            + " Amount: " + account.getBalance()
                                            + " Common: " + account.getBalance() * account.getCurrency().getRate());
                                }
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            System.out.println("User not found!");
                        }
                        break;

                    case 4:
                        for (Currency cr : bank.getCurrencies()) {
                            System.out.print("Set " + cr.getName() + ": ");
                            if (!sc.hasNextDouble()) { // Validate input
                                System.out.println("Invalid input. Please enter a valid rate.");
                                sc.next(); // Consume invalid input
                                continue;
                            }
                            double rate = sc.nextDouble();
                            cr.setRate(rate);
                        }
                        break;

                    case 5:
                        System.out.println("Choose sort type:");
                        System.out.println("1. By ID");
                        System.out.println("2. By Total Amount");
                        System.out.print("Option: ");
                        if (!sc.hasNextInt()) { 
                            System.out.println("Invalid input. Please enter 1 or 2.");
                            sc.next(); 
                            continue;
                        }
                        int option = sc.nextInt();
                        if (option != 1 && option != 2) {
                            System.out.println("Invalid option. Please choose 1 or 2.");
                            continue;
                        }
                        bank.sort(option);
                        break;

                    case 6:
                        bank.revertToUnsorted();
                        break;

                    case 0:
                        cont = false;
                        System.out.println("Exiting the application. Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid choice. Please enter a number between 0 and 6.");
                        break;
                }
            }
        }
    }
}