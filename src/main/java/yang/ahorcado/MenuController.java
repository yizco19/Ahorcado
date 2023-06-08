package yang.ahorcado;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MenuController {
    @FXML
    private Button btnJugador;

    @FXML
    private Button btnJugar;

    @FXML
    private Button btnPalabras;

    @FXML
    private Button btnPartidas;

    @FXML
    private Button btnRank;

    @FXML
    private Button btnSalir;

    @FXML
    private BorderPane mainPane;

    @FXML
    void initialize() {
        loadView("InicioFXML.fxml");
    }

    @FXML
    void abrirJugador(ActionEvent event) {
        loadView("JugadorFXML.fxml");
    }

    @FXML
    void abrirPalabra(ActionEvent event) {
        loadView("PalabrasFXML.fxml");
    }

    @FXML
    void abrirPartida(ActionEvent event) {

        loadView("PartidaFXML.fxml");
    }

    @FXML
    void abrirRank(ActionEvent event) {
        loadView("RankFXML.fxml");
    }

    @FXML
    void jugar(ActionEvent event) {
        loadView("InicioFXML.fxml");
    }

    @FXML
    void salir(ActionEvent event) {
        System.exit(0);
    }

    void loadView(String ruta) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/yang/ahorcado/" + ruta));
            Node view = loader.load();
            mainPane.setCenter(view);
            mainPane.setPrefHeight(500);
        } catch (IOException e) {
            showError("Error al cargar la vista.");
        }
    }


    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}


