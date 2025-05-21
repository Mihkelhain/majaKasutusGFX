package com.majakasutusgfx.kontrollerid;

import com.majakasutusgfx.mudelid.Tuba;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;

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

    public void salvestaFail() {
        //TODO ye ma ei teinud seda ka veel
    }

    public void laeFail() {
        //TODO ye ma ei teinud seda veel
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
            e.printStackTrace();
        }
    }
}