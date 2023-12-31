package com.driver;

import java.util.Random;

public class BankAccount {

    private String name;
    private double balance;
    private double minBalance;

    public BankAccount(String name, double balance, double minBalance) {
            this.name=name;
            this.balance=balance;
            this.minBalance=minBalance;
    }

    public String generateAccountNumber(int digits, int sum) throws Exception{
        if(sum<0 || digits*9<sum){
            throw new AccountNumberCannotBeGeneratedException("Account Number can not be generated");
        }
        //Each digit of an account number can lie between 0 and 9 (both inclusive)
        //Generate account number having given number of 'digits' such that the sum of digits is equal to 'sum'
        //If it is not possible, throw "Account Number can not be generated" exception
        String acNo="";
        Random random=new Random();
        int n;
        int remaningSum=sum;
        for(int i=0;i<digits;i++){
            int max=Math.min(remaningSum+1,10);
            n= random.nextInt(max);
            acNo+=String.valueOf(n);
            remaningSum-=n;
        }

        return acNo;
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

    public void deposit(double amount) {
        //add amount to balance
        this.balance+=amount;

    }

    public void withdraw(double amount) throws Exception {
        if(this.balance-amount <this.minBalance){
            throw new Exception("Insufficient Balance");
        }
        // Remember to throw "Insufficient Balance" exception, if the remaining amount would be less than minimum balance
        this.balance-=amount;
    }

}