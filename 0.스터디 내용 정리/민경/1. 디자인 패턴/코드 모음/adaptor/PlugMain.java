package org.example.adaptor;

public class PlugMain {
    public static void main(String[] args) {
        JapanPlug japanPlug = new JapanPlug();
        KoreanPlug koreanPlug = new JapanToKoreaPlug(japanPlug);
        koreanPlug.provideElectricity();
    }
}
