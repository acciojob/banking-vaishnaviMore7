package com.driver;

import java.util.Random;

import static java.lang.Math.min;

public class BankAccount {
    private String name;
    private double balance;
    private double minBalance;

    public BankAccount(String name, double balance, double minBalance) {
        this.name = name;
        this.balance = balance;
        this.minBalance = minBalance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getMinBalance() {
        return minBalance;
    }

    public void setMinBalance(double minBalance) {
        this.minBalance = minBalance;
    }

    public String generateAccountNumber(int digits, int sum) throws Exception{
        if(sum < 0 || sum > digits*9) {
            throw new AccountNumberCannotBeGeneratedException("Account Number can not be generated");
        }
        String acNo = "";
        Random rand = new Random();
        int n;
        int remainingSum = sum;
        for(int i=0;i<digits;i++) {
            int max = min(remainingSum+1, 10); //5
            n = rand.nextInt(max);
            acNo += String.valueOf(n);
            remainingSum -= n;
        }
        return acNo;
        //Each digit of an account number can lie between 0 and 9 (both inclusive)
        //Generate account number having given number of 'digits' such that the sum of digits is equal to 'sum'
        //If it is not possible, throw "Account Number can not be generated" exception
    }

    public void deposit(double amount) {
        this.balance += amount;
        //add amount to balance

    }

    public void withdraw(double amount) throws Exception {
        if(this.balance - amount < this.minBalance) {
            throw new Exception("Insufficient Balance");
        }

        this.balance -= amount;


        // Remember to throw "Insufficient Balance" exception, if the remaining amount would be less than minimum balance
    }


}