package com.turkcell;

import java.util.ArrayList;
import java.util.List;

public class InMemoryBankAccountRepository implements BankAccountRepository {

    private List<BankAccount> accounts = new ArrayList<>();

    public void register(String accountNumber, String fullName, String password) {
        if (find(accountNumber) != null) {
            System.out.println("Bu hesap numarası zaten kullanılıyor.");
            return;
        }

        accounts.add(new BankAccount(accountNumber, fullName, password, 0));
        System.out.println("Hesabınız başarıyla oluşturuldu.");
    }

    public BankAccount find(String accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public BankAccount login(String accountNumber, String password) {
        BankAccount account = find(accountNumber);

        if (account != null && account.getPassword().equals(password)) {
            System.out.println("Giriş başarılı. Hoş geldiniz  " + account.getFullName());
            return account;
        }

        System.out.println("Hesap numarası veya şifre hatalı.");
        return null;
    }

    public void showBalance(BankAccount account) {
        System.out.println("Bakiyeniz:" + account.getBalance() + " TL");
    }

    public void addMoney(BankAccount account, double amount) {
        if (amount <= 0) {
            System.out.println("Geçersiz tutar.");
            return;
        }

        account.setBalance(account.getBalance() + amount);
        System.out.println("Yeni bakiye:" + account.getBalance() + " TL");
    }

    public void takeMoney(BankAccount account, double amount) {
        if (amount <= 0) {
            System.out.println("Geçersiz tutar.");
            return;
        }

        if (amount > account.getBalance()) {
            System.out.println("Yetersiz bakiye.");
            return;
        }

        account.setBalance(account.getBalance() - amount);
        System.out.println("Yeni bakiye:" + account.getBalance() + " TL");
    }

    public void showAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("Hiç kayıtlı müşteri yok.");
            return;
        }

        System.out.println("\n--- KAYITLI TÜM HESAPLAR ---");
        for (BankAccount account : accounts) {
            System.out.println(account.getAccountNumber() + " - "
                    + account.getFullName() + " - "
                    + account.getBalance() + " TL");
        }
    }
}