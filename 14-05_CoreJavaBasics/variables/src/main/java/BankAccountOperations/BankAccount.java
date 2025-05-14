package BankAccountOperations;

public class BankAccount {
    private int accountNumber;
    private double balance;
    private static String bankName="SBI Bank";

    public BankAccount(int accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public static String getBankName() {
        return bankName;
    }

    public static void setBankName(String bankName) {
        BankAccount.bankName = bankName;
    }

    public void deposit(double amount){
       double newBalance=this.balance+amount;// local variable to calculate new balance
       this.balance=newBalance;
        System.out.println("Deposited Amount: "+amount+" Total balance: "+newBalance);
    }

    public void displayAccountDetails() {
        System.out.println("Bank Name: "+bankName);
        System.out.println("Account Number: "+accountNumber);
        System.out.println("Total balance: "+balance);
    }
}
