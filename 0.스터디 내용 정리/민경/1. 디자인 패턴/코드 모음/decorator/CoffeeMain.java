package org.example.decorator;

public class CoffeeMain {
    public static void main(String[] args) {
        Coffee coffee = new BasicCoffee();
        System.out.println(coffee.getDescription() + " : " + coffee.getCost());

        coffee = new MilkDecorator(coffee);
        System.out.println(coffee.getDescription() + " : " + coffee.getCost());

        coffee = new WhipDecorator(coffee);
        System.out.println(coffee.getDescription() + " : " + coffee.getCost());
    }
}
