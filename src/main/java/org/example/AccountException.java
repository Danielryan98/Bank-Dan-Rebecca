package org.example;

class InsufficientFundsException extends Exception{

    public static String InsufficientFundsException(){
        return "Action denied. Reason: Insufficient funds.";
    }
}

class InvalidDepositAmountException extends Exception{

    public static String InvalidDepositAmountException(){
        return "Action denied. Reason: Invalid deposit amount.";
    }
}

class InvalidWithdrawAmountException extends Exception{

    public static String InvalidWithdrawAmountException(){
        return "Action denied. Reason: Invalid withdraw amount.";
    }
}
