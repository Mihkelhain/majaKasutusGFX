package com.majakasutusgfx.kontrollerid;

import com.majakasutusgfx.mudelid.Kodumasin;
import com.majakasutusgfx.mudelid.Masin;
import com.majakasutusgfx.mudelid.Tuba;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MajaKasutusController {
    @FXML
    private ObservableList<Tuba> toad = FXCollections.observableArrayList();
    @FXML
    public ComboBox<Tuba> toaComboBox;
    @FXML
    private TextField toaNimi;
    @FXML
    private Label dropDownTuba;
    @FXML
    private ListView<Tuba> toaList;
    @FXML
    private VBox rootPane;

    @FXML
    private void initialize() {
        toaComboBox.setItems(toad);
        toaComboBox.getSelectionModel().selectedItemProperty().addListener((obs, vanaTuba, uusTuba) -> {
            valiTuba(uusTuba);
        });
    }

    public void valiTuba(Tuba tuba) {
        if (tuba != null) {
            dropDownTuba.setText("Tuba: " + tuba.getNimi());
        } else {
            dropDownTuba.setText("");
        }
    }

    @FXML
    private void looTuba() {
        String tubaNimi = toaNimi.getText().trim();
        if (tubaNimi.isEmpty()){
            näitaTeavitus("Palun sisesta toa nimi");
            return;
        }
        Tuba uusTuba = new Tuba(tubaNimi);
        toad.add(uusTuba);
        toaNimi.clear();
        näitaTeavitus("Tuba: " + tubaNimi + " loodi edukalt");
        toaComboBox.getSelectionModel().select(uusTuba);
    }

    private void näitaTeavitus(String sisu) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(sisu);
        alert.showAndWait();
    }
    //TODO Errori jaoks proper throw
    @FXML
    public void salvestaFail() throws IOException {
        List<Tuba> tubad =  toad;
        try(BufferedWriter f = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("MKsave.txt"),"UTF-8"))){
            for (Tuba MajTuba : tubad) {
                List<Masin> masinad = MajTuba.getMasinadToas();
                f.write(MajTuba.toString()+ ";");
                f.write(masinad.toArray().length + ";");
                for (Masin masin : masinad){
                    f.write(masin.toString() + ";");
                }
                f.newLine();
            }
        näitaTeavitus("Fail salvestati ära");
    }
        catch(Exception e){
            näitaTeavitus("Fail ei saanud salvestatud" + e.getMessage());
        }
    }


    public void laeFail() throws IOException {
        try(BufferedReader f = new BufferedReader(new FileReader("MKsave.txt"))){
            String line;
            while((line = f.readLine()) != null){
                String[] osad = line.split(";");
                for (Tuba checkTuba : toad) {
                    if (osad[0].equals(checkTuba.getNimi())) {
                        toad.remove(checkTuba);
                    }
                }
                Tuba tuba = new Tuba(osad[0]);

                int masinArv = Integer.parseInt(osad[1]);
                if(masinArv == 0){
                    toad.add(tuba);
                    continue;}
                for (int i = 2; i < masinArv + 2; i++) {
                    String[] mas = osad[i].split(",");
                    String nimi = mas[0];
                    String brand = mas[1];
                    String energEff = mas[2];
                    double kiloWH = Double.parseDouble(mas[3]);
                    Kodumasin kodumasin = new Kodumasin(nimi, brand, energEff, kiloWH);
                    tuba.lisaMasin(kodumasin);
                    toad.add(tuba);
                }

            }
            }


    }

    public void setToad(ObservableList<Tuba> toad) {
        this.toad = toad;
        toaComboBox.setItems(this.toad);
    }

    @FXML
    private void näitaToadVaade() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/majakasutusgfx/ToadVaade.fxml"));
            Parent view = loader.load();
            toadKontroller ToadKontroller = loader.getController();
            ToadKontroller.setToad(toad);
            ToadKontroller.setValitudTuba(toaComboBox.getSelectionModel().getSelectedItem());

            rootPane.getChildren().setAll(view);
        } catch (IOException e) {
            e.getMessage();
        }
    }
}