package org.example.factory_method;

public class CoffeeFactory {
    public static Coffee getCoffee(CoffeeType type, int price){
        switch (type){
            case LATTE -> {
                return new Latte(price);
            }
            case AMERICANO -> {
                return new Americano(price);
            }
        }
        return null;
    }
}
