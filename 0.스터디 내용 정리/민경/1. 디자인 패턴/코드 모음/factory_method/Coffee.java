package org.example.factory_method;

abstract class Coffee {
    public abstract int getPrice();

    @Override
    public String toString(){
        return "Hi this coffee is " + this.getPrice();
    }
}
