package main.java.com.turkcell;

public class Car {
    public int year;
    public String model;
    protected String brand;
    // erişim belirteci: o alana kimlerin erişebileceğini belirleyen sistem
    private double price;
    // public => her yerden erişilebilir
    // private => sadece tanımlanan sınıf içerisinden erişilebilir.
    // protected => sadece tanımlanan sınıf ve o sınıftan (türetilen) sınıflardan
    // erişilebilir.
    // protected => aynı paketteki sınıflardan erişilebilir.

    // Encapsulation
    //
    public double getPrice() {
        // get işlemlerini kontrol eden mekanizma
        return price;
    }

    public void setPrice(double price) {
        // classın kendisi = price
        if (price < 0) {
            System.out.println("Fiyat negatif olamaz!");
            return;
        }
        this.price = price;
    }
}