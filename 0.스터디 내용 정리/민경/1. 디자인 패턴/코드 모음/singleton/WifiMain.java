package org.example.singleton;

public class WifiMain {
    public static void main(String[] args) {
        Wifi wifi1 = Wifi.requestWifi();
        Wifi wifi2 = Wifi.requestWifi();
        Wifi wifi3 = Wifi.requestWifi();

        System.out.println(wifi1);
        System.out.println(wifi2);
        System.out.println(wifi3);
    }
}