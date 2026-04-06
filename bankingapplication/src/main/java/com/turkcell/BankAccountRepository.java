package com.turkcell;

public interface BankAccountRepository {
    void register(String accountNumber, String fullName, String password);

    BankAccount find(String accountNumber);

    BankAccount login(String accountNumber, String password);

    void showBalance(BankAccount account);

    void addMoney(BankAccount account, double amount);

    void takeMoney(BankAccount account, double amount);

    void showAllAccounts();
}