package Lab6;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
public class User {
    private String name;
    private String id;
    private double balance;
    private ArrayList<Account> accounts;
    Random random = new Random();

    public User(String name) {
        this.name = name;
        this.id = String.valueOf((int) (random.nextDouble() * 1000000000));
        this.balance = 0;
        this.accounts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }   

    public double getBalance() {
        return getTotalAmountInCommonCurrency();
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void removeAccount(Account account) {
        accounts.remove(account);
    }

    public double getTotalAmountInCommonCurrency(){
        double total = 0;
        for (Account ac : this.accounts){
            total += ac.getBalance();
        }
        return total;
    }

    public String toString(){
        return this.getId() + " " + this.getName() + " Total Amount: " + this.getBalance(); 
    }

    public static ArrayList<String> firstNames = new ArrayList<>(Arrays.asList(
        "Mehmet", "Ahmet", "Mustafa", "Ali", "Hasan", "Huseyin", "Osman", "Yusuf", "Murat", "Ibrahim",
        "Abdulrezzak", "Recep", "Ramazan", "Sahin", "Ferhat", "Harun", "Cem", "Berk", "Baran", "Burak",
        "Enes", "Emir", "Kaan", "Tuna", "Sehzade", "Baybars", "Kubilay", "Yavuz", "Beyazit", "Furkan",
        "Ziya", "Vecihi", "Seyfettin", "Tufan", "Onur", "Zafer", "Erkan", "Serkan", "Tolga", "Gokhan",
        "Zuhtu", "Hakki", "Sukru", "Ersin", "Ergin", "Halil", "Ekrem", "Nihat", "Bekir", "Rifat",
        "Fatma", "Ayse", "Zeynep", "Elif", "Merve", "Hatice", "Emine", "Rabia", "Hilal", "Seda",
        "Busra", "Gul", "Aylin", "Aysel", "Fadime", "Zubeyde", "Sukran", "Nuriye", "Pakize", "Fevziye",
        "Melike", "Gonca", "Tugce", "Nazli", "Sehnaz", "Necla", "Bedriye", "Gulsum", "Selma", "Canan",
        "Feride", "Perihan", "Sultan", "Mine", "Pelin", "Yasemin", "Ebru", "Berfin", "Ceyda", "Serap"
    ));
    

    public static ArrayList<String> lastNames = new ArrayList<>(Arrays.asList(
        "Yilmaz", "Kaya", "Demir", "Sahin", "Celik", "Yildiz", "Aydin", "Ozturk", "Arslan", "Dogan",
        "Kilic", "Aslan", "Yalcin", "Tas", "Koc", "Avci", "Bulut", "Bozkurt", "Bas", "Polat",
        "Turkmen", "Karaca", "Simsek", "Karahan", "Aksoy", "Bayraktar", "Gok", "Topal", "Ucar", "Korkmaz",
        "Cavusoglu", "Dinc", "Ergun", "Kaplan", "Gumus", "Tok", "Delibas", "Gurkan", "Altay", "Merdan",
        "Sari", "Ince", "Yorganci", "Coban", "Bakkaloglu", "Koseoglu", "Tasdelen", "Akbaba", "Kabakci", "Sakalli"
    ));
    

public static String generateRandomName(){
    Random random = new Random();
    return firstNames.get((int) random.nextDouble(0, firstNames.size())) + " " +
        lastNames.get((int) random.nextDouble(0, lastNames.size()));
}

}
