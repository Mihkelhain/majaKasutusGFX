package com.majakasutusgfx.mudelid;

public class Kodumasin implements Masin {
    private String nimetus;
    private String brand;
    private String energiaEfektiivsus;
    private double kwH;
    private String seletus;

    public Kodumasin(String nimetus, String brand, String energiaEfektiivsus, double kwH) {
        this.nimetus = nimetus;
        this.brand = brand;
        this.energiaEfektiivsus = energiaEfektiivsus;
        this.kwH = kwH;
        this.seletus = null;
    }
    public void seletaMasin(){
        if(seletus == null){
            System.out.println("Pange sisse enda masina esimene informatsioon");
        }
        else {
            System.out.println("Kirjutage ümber enda masina informatsioon");
        }
    }
    public double elektriKasutus(){
        double energiaK = 0;
        switch(energiaEfektiivsus.toLowerCase()){
            case("a"):
                energiaK = kwH * 0.5;
            case("b"):
                energiaK = kwH * 0.6;
            case("c"):
                energiaK = kwH * 0.7;
            case("d"):
                energiaK = kwH * 0.8;
            case("e"):
                energiaK = kwH;
        }
        return energiaK;
    }
    @Override
    public String toString() {
        return nimetus + "," + brand + "," + energiaEfektiivsus + "," + kwH;
    }
}
