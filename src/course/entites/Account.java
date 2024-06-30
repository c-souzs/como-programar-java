package course.entites;

public class Account {
    private static int amountAccounts = 1;
    private int id;
    private String name;
    private double balance;

    public Account(String name, double initialBalance) {
        this.name = name;
        deposit(initialBalance);

        id = amountAccounts;
        amountAccounts++;
    }

    public Account(String name) {
        this.name = name;

        id = amountAccounts;
        amountAccounts++;
    }

    public void deposit(double value) {
        if(value > 0) balance+=value;
    }

    public void withdraw(double value) {
        if(value <= balance) {
            double valueWithRate = value + 5.0;
            balance-=valueWithRate;
        }
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return String.format("-Id: %d %n -Name: %s -Balance R$ %.2f", id, name, balance);
    }
}
