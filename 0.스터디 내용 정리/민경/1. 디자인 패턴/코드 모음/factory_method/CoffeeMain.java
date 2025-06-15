package org.example.factory_method;

public class CoffeeMain {
    public static void main(String[] args) {
        Coffee americano = CoffeeFactory.getCoffee(CoffeeType.AMERICANO, 2000);
        Coffee latte = CoffeeFactory.getCoffee(CoffeeType.LATTE, 3000);

        System.out.println(americano);
        System.out.println(latte);
    }
}
