package Lab6;
public class Account {
    private double balance;
    private User user;
    private Currency currency;
    public Account (User user, Currency currency){
        this.user = user;
        this.balance = 0;
        this.currency = currency;
    }

    public void addBalance (double money){
        this.balance += money;
    }

    public double getBalance(){
        return this.balance * this.currency.getRate();
    }

    public Currency getCurrency(){
        return this.currency;
    }
}
