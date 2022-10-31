package org.example;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BankTest {

    @Test
    public void test_balance_of_40dot50_plus_deposit_of_20dot0_equals_60dot50() throws InvalidDepositAmountException, InsufficientFundsException{
        //Arrange
        BankAccount bankAccount = new BankAccount("John Doe");
        bankAccount.deposit(40.50); //To get balance to 40.50

        double expectedBalance = 60.50;
        double actualBalance;

        //Act
        bankAccount.deposit(20.00); //To get balance to 60.50
        actualBalance = bankAccount.getBalance();

        //Assert
        assertEquals(expectedBalance, actualBalance);

    }

    @Test
    public void test_balance_of_100dot0_minus_withdraw_of_101dot0_is_rejected_when_no_overdraft() throws InvalidDepositAmountException, InsufficientFundsException{
        //Arrange
        BankAccount bankAccount = new BankAccount("John Doe");
        bankAccount.deposit(100.00); //Initialise balance

        double withdrawAmount = 101.00;
        double expectedBalance = 100.00;
        double actualBalance;

        //Act & Assert
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(withdrawAmount));
        actualBalance = bankAccount.getBalance();
        assertEquals(expectedBalance, actualBalance);
    }
    @Test
    public void test_balance_of_100dot0_minus_withdraw_of_101dot0_is_not_rejected_when_overdraft_limit_of_100dot0() throws InvalidDepositAmountException, InsufficientFundsException, InvalidWithdrawAmountException{
        //Arrange
        BankAccount bankAccount = new BankAccount("John Doe");
        bankAccount.deposit(100.00); //Initialise balance
        bankAccount.setMinimumBalance(-100.00);

        double expectedBalance = -1.00;
        double actualBalance;

        //Act

        bankAccount.withdraw(101.00); //To get balance to -1.00
        actualBalance = bankAccount.getBalance();

        //Assert
        assertEquals(expectedBalance, actualBalance);
    }

    @Test
    public void test_balance_of_100dot0_minus_withdraw_of_201dot0_is_rejected_when_overdraft_limit_of_100dot0() throws InsufficientFundsException, InvalidDepositAmountException, InvalidWithdrawAmountException  {
        //Arrange
        BankAccount bankAccount = new BankAccount("John Doe");
        bankAccount.deposit(100.00); //Initialise balance
        bankAccount.setMinimumBalance(100.00);
        double expectedBalance = 100.00;
        double actualBalance;
        double withdrawAmount = 201.00;


        //Act & Assert
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(withdrawAmount));
        actualBalance = bankAccount.getBalance();
        assertEquals(expectedBalance, actualBalance);
    }


    @Test
    public void test_balance_of_100dot0_minus_withdraw_of_100dot0_is_not_rejected() throws InvalidDepositAmountException, InsufficientFundsException, InvalidWithdrawAmountException {
        //Assemble
        BankAccount bankAccount = new BankAccount("John Doe");
        bankAccount.deposit(100.00); //Initialise balance

        double withdrawalAmount = 100.00;

        double expectedBalance = 0.00;
        double actualBalance;

        //Act
        bankAccount.withdraw(withdrawalAmount);
        actualBalance = bankAccount.getBalance();

        //Assert
        assertEquals(expectedBalance, actualBalance);

    }

//    @Test
//    public void test_deposit_amount_is_2_decimal_places() throws InvalidDepositAmountException{
//        //Arrange
//        BankAccount bankAccount = new BankAccount("John Doe");
//
//        double actualDeposit = 102.555;
//        double expectedBalance = 0.00;
//        double actualBalance;
//
//        //Act & Assert
//        assertThrows(InvalidDepositAmountException.class, () -> bankAccount.deposit(actualDeposit));
//        actualBalance = bankAccount.getBalance();
//        assertEquals(expectedBalance, actualBalance);
//    }

    @Test
    public void test_balance_of_20_deposit_amount_of_negative_10_throws_InvalidDepositError() throws InvalidDepositAmountException, InsufficientFundsException {
        //Arrange
        BankAccount bankAccount = new BankAccount("John Doe");

        bankAccount.deposit(20.00);
        double actualDeposit = -10.00;
        double expectedBalance = 20.00;
        double actualBalance;

        //Act & Assert
        assertThrows(InvalidDepositAmountException.class, () -> bankAccount.deposit(actualDeposit));
        actualBalance = bankAccount.getBalance();
        assertEquals(expectedBalance, actualBalance);
    }


}
