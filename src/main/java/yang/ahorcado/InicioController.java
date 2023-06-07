package yang.ahorcado;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import yang.ahorcado.clase.Fichero;
import yang.ahorcado.clase.Jugador;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class InicioController {

    @FXML
    private ComboBox<Jugador> boxJugador;
    @FXML
    private ImageView imagen;

    @FXML
    private ComboBox<String> boxPalara;

    @FXML
    private TextField puntacion;

    @FXML
    void iniciar(ActionEvent event) {
        Jugador jugador = boxJugador.getSelectionModel().getSelectedItem();
        String palabra="";
        if(jugador==null){
            mostrarError("Error de carga", "No ha selecionado ninguno  jugador.");
        }
        try {
            palabra = Fichero.randPalabra(boxPalara.getSelectionModel().getSelectedItem());
        }catch (Exception e){
            mostrarError("Error de carga", "No ha selecionado ninguno  fichero de palabra.");
        }

        int punto = 0;
        try {
            punto = Integer.parseInt(puntacion.getText().trim());
        } catch (NumberFormatException e) {
            mostrarError("Error de entrada", "La puntuación ingresada no es válida.");
        }

        if (jugador != null && palabra != "" && punto>0) {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("JuegoFXML.fxml"));
                Parent root = loader.load();
                JuegoController controlador = loader.getController();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Juego Ahorcado");
                controlador.setJugador(jugador);
                controlador.setPalabra(palabra);
                controlador.setPuntacion(punto);
                stage.showAndWait();
                // Restablecer los campos después de que el juego haya terminado
                resetearCampos();

                Jugador newJugador=controlador.getJugador();
                Fichero.modificarJugador(newJugador,jugador);

            } catch (IOException e) {
                // Manejo del error si ocurre una excepción al cargar el archivo FXML
                mostrarError("Error de carga", "No se pudo cargar el archivo FXML del juego.");
            }
        }
    }
    private void resetearCampos() {
        boxJugador.getSelectionModel().clearSelection();
        boxPalara.getSelectionModel().clearSelection();
        puntacion.setText("");
    }
    private void mostrarError(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    void initialize() {
        ArrayList<Jugador> jugadores= Fichero.leerJugadores();
        boxJugador.getItems().addAll(jugadores);

        ArrayList<String> palabras=Fichero.getNombresFicheros();
        boxPalara.getItems().addAll(palabras);
        File ruta=new File("./src/main/java/yang/ahorcado/imagen/icon2.png");
        Image imag=new Image(ruta.getAbsolutePath());
        imagen.setImage(imag);
    }

}
