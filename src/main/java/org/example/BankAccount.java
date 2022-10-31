package org.example;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BankAccount {

    private String accountHolderName;
    private double balance;
    private double minimumBalance;
    
    public BankAccount(String accountHolderName) {
        this(accountHolderName, 0.00);
    }

    public BankAccount(String accountHolderName, double minimumBalance){
        balance = 0.00;
        this.setMinimumBalance(minimumBalance);
        this.setAccountHolderName(accountHolderName);
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double changeInBalance) throws InsufficientFundsException {
            if((this.balance + changeInBalance) >= this.minimumBalance){
                this.balance += changeInBalance;
            } else {
                throw new InsufficientFundsException();
            }
    }

    public String getAccountHolderName() {
        return this.accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public double getMinimumBalance() {
        return this.minimumBalance;
    }

    public void setMinimumBalance(double minimumBalance) {
        this.minimumBalance = minimumBalance;
    }


    public void deposit(double depositAmount) throws InvalidDepositAmountException, InsufficientFundsException{
        Pattern p = Pattern.compile("[0-9]\\.[0-9][0-9]|[1-9]+[0-9]*\\.[0-9][0-9]");
        String roundedDepositAmount = String.format("%.2f", depositAmount);
        Matcher m = p.matcher(roundedDepositAmount);

        if(m.matches()){
            setBalance(depositAmount);
        } else {
            throw new InvalidDepositAmountException();
        }
    }

    public void withdraw(double withdrawAmount) throws InsufficientFundsException, InvalidWithdrawAmountException{
        Pattern p = Pattern.compile("[0-9]\\.[0-9][0-9]|[1-9]+[0-9]*\\.[0-9][0-9]");
        String roundedWithdrawAmount = String.format("%.2f", withdrawAmount);
        Matcher m = p.matcher(roundedWithdrawAmount);

        if(m.matches()){
                setBalance(-withdrawAmount);
        } else {
            throw new InvalidWithdrawAmountException();
        }
    }



}
