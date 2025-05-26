package Lab6;

import java.util.*;

public class Bank {
    private ArrayList<User> users;
    private ArrayList<User> unsorted;
    private ArrayList<Currency> currencies;
    Scanner sc = new Scanner(System.in);

    public Bank() {
        users = new ArrayList<>();
        unsorted = new ArrayList<>();
        currencies = new ArrayList<>();
        this.generateCurrencies();
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void generateUsers(int n) {
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            User user = new User(User.generateRandomName());
            for (int j = 0; j < random.nextInt(1 , 4); j++) {
                Currency currency = this.currencies.get(random.nextInt(this.currencies.size()));
                Account account = new Account(user, currency);
                double randomAmount = 100 + (10000 - 100) * random.nextDouble(); 
                account.addBalance(randomAmount);
                user.addAccount(account);
            }
            users.add(user);
        }
        unsorted = new ArrayList<>(users);
    }

    public void generateCurrencies() {
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            this.currencies.add(new Currency(Currency.fakeCurrencies.get(random.nextInt(Currency.fakeCurrencies.size()))));
        }
    }

    public ArrayList<Currency> getCurrencies() {
        return currencies;
    }

    public void sort(int option) {
        System.out.print("Enter sort limit: ");
        int limit = sc.nextInt();

        long start = System.currentTimeMillis();
        if (option == 1) {
            hybridSort(users, 0, users.size(), limit, true);
        } else if (option == 2) {
            hybridSort(users, 0, users.size(), limit, false);
        } else {
            System.out.println("Invalid option.");
            return;
        }
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;

        System.out.println("Sorting completed in " + timeElapsed + " ms.");
    }

    public void revertToUnsorted() {
        users = new ArrayList<>(unsorted);
        System.out.println("Reverted to the original unsorted array.");
    }

    public void displayUsers() {
        for (User user : users) {
            System.out.println(user);
        }
    }

    public void hybridSort(List<User> list, int from, int to, int limit, boolean sortById) {
        if (to - from >= limit) {
            User pivot = list.get(to - 1);
            int i = from;
            for (int j = from; j < to - 1; j++) {
                if (compareUsers(list.get(j), pivot, sortById) <= 0) {
                    User temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                    i++;
                }
            }
            User temp = list.get(i);
            list.set(i, list.get(to - 1));
            list.set(to - 1, temp);

            int pivotIndex = i;
            hybridSort(list, from, pivotIndex, limit, sortById);
            hybridSort(list, pivotIndex + 1, to, limit, sortById);
        } else {
            for (int i = from + 1; i < to; i++) {
                User key = list.get(i);
                int j = i - 1;
                while (j >= from && compareUsers(list.get(j), key, sortById) > 0) {
                    list.set(j + 1, list.get(j));
                    j--;
                }
                list.set(j + 1, key);
            }
        }
    }

    private int compareUsers(User u1, User u2, boolean sortById) {
        if (sortById) {
            return u1.getId().compareTo(u2.getId());
        } else {
            return Double.compare(u1.getTotalAmountInCommonCurrency(), u2.getTotalAmountInCommonCurrency());
        }
    }
}
