package main.java.com.turkcell;

public class OOP {
    public static void main(String[] args) {
        // car1 => car instance'ı
        Car car1 = new Car(); // new => yeni bir instance oluşturma keywordü
        car1.brand = "BMW";
        car1.model = "X5";
        car1.year = 2020;
        // car1.price = 500000; // set
        car1.setPrice(500000); // set
        System.out.println(car1.getPrice()); // get
    }
}