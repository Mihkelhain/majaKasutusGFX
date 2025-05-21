module com.example.majakasutusgfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.majakasutusgfx to javafx.fxml;
    exports com.majakasutusgfx;
    exports com.majakasutusgfx.kontrollerid;
    opens com.majakasutusgfx.kontrollerid to javafx.fxml;
}