package org.example.revealing_module;

public class Counter {

    // 내부 상태는 private static 으로 은닉
    private static int count = 0;


    // 외부에 노출할 public 메서드
    public static void increment(){
        count++;
    }

    public static int getCount(){
        return count;
    }
}
