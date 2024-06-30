package chapterThree;

public class Account {
    private String name;
    private double balance;


    public Account(String name, double balance) {
        this.name = name;

        if(balance > 0.0) this.balance = balance;
    }

    public void deposit(double value) {
        if (value > 0.0) balance+=value;
    }

    public double getBalance() {
        return  balance;
    }

    public String getName() {
        return  name;
    }
}
