package org.example.adaptor;

public class JapanToKoreaPlug implements KoreanPlug{

    private JapanPlug japanPlug;

    public JapanToKoreaPlug(JapanPlug japanPlug){
        this.japanPlug = japanPlug;
    }
    @Override
    public void provideElectricity() {
        System.out.println("Converting...");
        japanPlug.sendElectricity();
    }
}
