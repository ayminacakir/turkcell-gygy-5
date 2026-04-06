package com.turkcell;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankAccountRepository repository = new InMemoryBankAccountRepository();

        while (true) {
            System.out.println("\n=== BANKA UYGULAMASINA HOŞ GELDİNİZ ===");
            System.out.println("1 - Yeni hesap oluştur");
            System.out.println("2 - Hesaba giriş yap");
            System.out.println("3 - Tüm hesapları listele");
            System.out.println("4 - Uygulamadan çık");
            System.out.print("Lütfen bir seçim yapın: ");
            int secim = scanner.nextInt();
            scanner.nextLine();

            if (secim == 1) {
                System.out.print("Hesap numaranızı girin: ");
                String accountNumber = scanner.nextLine();

                System.out.print("Ad ve soyadınızı girin: ");
                String fullName = scanner.nextLine();

                System.out.print("Şifrenizi belirleyin: ");
                String password = scanner.nextLine();

                repository.register(accountNumber, fullName, password);

            } else if (secim == 2) {
                System.out.print("Hesap numaranızı girin: ");
                String loginNumber = scanner.nextLine();

                System.out.print("Şifrenizi girin: ");
                String loginPassword = scanner.nextLine();

                BankAccount account = repository.login(loginNumber, loginPassword);

                if (account != null) {
                    boolean devam = true;

                    while (devam) {
                        System.out.println("\n--- HESAP İŞLEMLERİ ---");
                        System.out.println("1 - Bakiye sorgula");
                        System.out.println("2 - Para yatır");
                        System.out.println("3 - Para çek");
                        System.out.println("4 - Çıkış yap");
                        System.out.print("Lütfen yapmak istediğiniz işlemi seçin: ");
                        int islem = scanner.nextInt();

                        if (islem == 1) {
                            repository.showBalance(account);

                        } else if (islem == 2) {
                            System.out.print("Yatırmak istediğiniz tutarı girin: ");
                            double yatirilan = scanner.nextDouble();
                            repository.addMoney(account, yatirilan);

                        } else if (islem == 3) {
                            System.out.print("Çekmek istediğiniz tutarı girin: ");
                            double cekilen = scanner.nextDouble();
                            repository.takeMoney(account, cekilen);

                        } else if (islem == 4) {
                            devam = false;
                            System.out.println("Hesabınızdan çıkış yaptınız.");

                        } else {
                            System.out.println("Geçersiz seçim yaptınız, lütfen tekrar deneyin.");
                        }
                    }
                    scanner.nextLine();
                }

            } else if (secim == 3) {
                repository.showAllAccounts();

            } else if (secim == 4) {
                System.out.println("Uygulamadan çıkılıyor... İyi günler dileriz.");
                scanner.close();
                break;

            } else {
                System.out.println("Geçersiz seçim yaptınız, lütfen tekrar deneyin.");
            }
        }
    }
}