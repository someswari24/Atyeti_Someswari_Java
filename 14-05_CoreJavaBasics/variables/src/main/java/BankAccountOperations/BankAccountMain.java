package BankAccountOperations;

public class BankAccountMain {
    public static void main(String[] args) {
        BankAccount bankAccount1=new BankAccount(1234,2000);
        BankAccount bankAccount2=new BankAccount(1453,5000);

        BankAccount.setBankName("HDFC Bank");

        bankAccount1.deposit(3000);
        bankAccount2.deposit(345);

        bankAccount1.displayAccountDetails();
        bankAccount2.displayAccountDetails();
    }
}
