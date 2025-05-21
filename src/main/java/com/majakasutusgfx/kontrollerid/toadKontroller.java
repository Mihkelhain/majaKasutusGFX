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

import java.io.IOException;

public class toadKontroller {
    @FXML
    private ObservableList<Tuba> toad = FXCollections.observableArrayList();
    @FXML
    public ComboBox<Tuba> toaComboBox;
    @FXML
    private TextField toaNimi;
    @FXML
    private TableView<Masin> masinadTable;
    @FXML
    private TextField masinaNimi;
    @FXML
    private TextField brand;
    @FXML
    private TextField energiaEfektiivisus;
    @FXML
    private TextField kWH;
    @FXML
    private Label dropDownTuba;
    @FXML
    private VBox rootPane;

    private Tuba valitudTuba;

    public void setToad(ObservableList<Tuba> toad) {
        this.toad = toad;
        toaComboBox.setItems(this.toad);
    }

    public void setValitudTuba(Tuba tuba) {
        this.valitudTuba = tuba;
        if (tuba != null) {
            toaComboBox.getSelectionModel().select(tuba);
            valiTuba(tuba);
        }
    }

    @FXML
    private void initialize() {
        toaComboBox.getSelectionModel().selectedItemProperty().addListener((obs, vanaTuba, uusTuba) -> {
            valiTuba(uusTuba);
            if (uusTuba != null) {
                masinadTable.setItems(uusTuba.välastaMasinad());
            } else {
                masinadTable.setItems(FXCollections.emptyObservableList());
            }
        });
    }

    public void valiTuba(Tuba tuba) {
        this.valitudTuba = tuba;
        if (tuba != null) {
            dropDownTuba.setText("Tuba: " + tuba.getNimi());
            masinadTable.setItems(tuba.välastaMasinad());
        } else {
            dropDownTuba.setText("");
            masinadTable.setItems(FXCollections.emptyObservableList());
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

    @FXML
    private void looMasin() {
        if (valitudTuba == null) {
            näitaTeavitus("Palun vali enne tuba");
            return;
        }

        String masinaNim = masinaNimi.getText().trim();
        String brandMas = brand.getText().trim();
        String energEff = energiaEfektiivisus.getText().trim();
        String kilWhTund = kWH.getText().trim();

        if (masinaNim.isEmpty() || brandMas.isEmpty() || energEff.isEmpty() || kilWhTund.isEmpty()) {
            näitaTeavitus("Peab täitma kõik kohad");
            return;
        }
        Kodumasin kodMasin = new Kodumasin(masinaNim, brandMas, energEff, Double.parseDouble(kilWhTund));
        valitudTuba.lisaMasin(kodMasin);
        masinaNimi.clear();
        brand.clear();
        energiaEfektiivisus.clear();
        kWH.clear();
        näitaTeavitus("Masin loodi edukalt tuppa: " + valitudTuba + " \n nimi" + masinaNim + " " + brandMas + " " + energEff + " " + kilWhTund);
    }

    private void näitaTeavitus(String sisu) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(sisu);
        alert.showAndWait();
    }

    @FXML
    private void tagasi() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/majakasutusgfx/PeaVaade.fxml"));
            Parent view = loader.load();
            MajaKasutusController majaKasRetController = loader.getController();
            majaKasRetController.setToad(toad);

            rootPane.getChildren().setAll(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}