package com.driver;

public class SavingsAccount extends BankAccount{
    double rate;
    double maxWithdrawalLimit;

    private int nWithdraws;

    public SavingsAccount(String name, double balance, double maxWithdrawalLimit, double rate) {
        super(name, balance, 0);
        this.maxWithdrawalLimit = maxWithdrawalLimit;
        this.rate = rate;
        this.nWithdraws = 0;
        // minimum balance is 0 by default
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getMaxWithdrawalLimit() {
        return maxWithdrawalLimit;
    }

    public void setMaxWithdrawalLimit(double maxWithdrawalLimit) {
        this.maxWithdrawalLimit = maxWithdrawalLimit;
    }

    public int getnWithdraws() {
        return nWithdraws;
    }

    public void setnWithdraws(int nWithdraws) {
        this.nWithdraws = nWithdraws;
    }

    public void withdraw(double amount) throws Exception {
        if(nWithdraws >= maxWithdrawalLimit) {
            throw new Exception("Maximum Withdraw Limit Exceed");
        }
        if(amount > super.getBalance()) {
            throw new Exception("Insufficient Balance");
        }
        super.setBalance(super.getBalance()-amount);
        nWithdraws++;
        // Might throw the following errors:
        // 1. "Maximum Withdraw Limit Exceed" : If the amount exceeds maximum withdrawal limit
        // 2. "Insufficient Balance" : If the amount exceeds balance

    }

    public double getSimpleInterest(int years){
        return super.getBalance()+ rate * years * super.getBalance();
        // Return the final amount considering that bank gives simple interest on current amount

    }

    public double getCompoundInterest(int times, int years){
        return super.getBalance() * Math.pow( 1 + rate/times, times*years);
        // balance * (1+r/5)^n
        // Return the final amount considering that bank gives compound interest on current amount given times per year
    }

}
