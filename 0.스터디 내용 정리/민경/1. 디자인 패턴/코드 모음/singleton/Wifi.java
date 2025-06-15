package org.example.singleton;

public class Wifi {
    private static Wifi wifi;

    private Wifi(){}

    public static Wifi requestWifi(){
        if (wifi == null)
            wifi = new Wifi();

        return wifi;
    }
}
