package org.example.decorator;

public class WhipDecorator extends CoffeeDecorator{
    public WhipDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Whip";
    }

    @Override
    public int getCost() {
        return coffee.getCost() + 700;
    }

}
