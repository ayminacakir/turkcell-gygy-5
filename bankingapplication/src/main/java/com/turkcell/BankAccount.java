package com.turkcell;

public class BankAccount {
    private String accountNumber;
    private String fullName;
    private String password;
    private double balance;

    public BankAccount() {
    }

    public BankAccount(String accountNumber, String fullName, String password, double balance) {
        this.accountNumber = accountNumber;
        this.fullName = fullName;
        this.password = password;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        if (balance < 0) {
            System.out.println("Bakiye negatif olamaz. 0 olarak ayarlandı.");
            this.balance = 0;
            return;
        }
        this.balance = balance;
    }
}