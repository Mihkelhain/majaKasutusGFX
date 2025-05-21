package com.majakasutusgfx.mudelid;

public class VeeKasutaja extends Kodumasin {
    private double veemahutavus;

    public VeeKasutaja(String nimetus, String brand, String energiaEfektiivsus, double kwH, double veemahutavus) {
        super(nimetus, brand, energiaEfektiivsus, kwH);
        this.veemahutavus = veemahutavus;
    }

    public VeeKasutaja(String nimetus, String brand, String energiaEfektiivsus, double kwH) {
        super(nimetus, brand, energiaEfektiivsus, kwH);
    }

    public double getVeeKasutus() {
        return veemahutavus;
    }

    public void setVeeKasutus(double veeKasutus) {
        this.veemahutavus = veemahutavus;
    }

    @Override
    public String toString() {
        return super.toString() + ", veemahutavus on " + veemahutavus + " liitrit";
    }
}
