package org.example.revealing_module;

public class CounterMain {
    public static void main(String[] args) {
        Counter.increment();
        Counter.increment();
        System.out.println(Counter.getCount());
    }
}
