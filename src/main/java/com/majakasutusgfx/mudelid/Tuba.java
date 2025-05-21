package com.majakasutusgfx.mudelid;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Tuba {
    private List<Masin> masinadToas = new ArrayList<>();
    private String nimi;
    public Tuba(String nimi) {
        this.nimi = nimi;
        this.masinadToas = masinadToas;
    }

    public void lisaMasin(Masin masin){
        masinadToas.add(masin);
    }
    public void eemaldaMasin(int mitmes_seade){
        masinadToas.remove(mitmes_seade);
    }

    public ObservableList<Masin> välastaMasinad() {
        return FXCollections.observableArrayList(masinadToas);
    }
    public int masinateArv(){
        return masinadToas.size();
    }

    public String getNimi() {
        return nimi;
    }

    @Override
    public String toString() {
        return nimi;
    }
}
