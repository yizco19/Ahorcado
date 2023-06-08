package yang.ahorcado;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import yang.ahorcado.clase.Fichero;
import yang.ahorcado.clase.Jugador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class JugadorController {

    @FXML
    private Button btnSalir;

    @FXML
    private TableView<Jugador> tablaJugador;

    @FXML
    private TableColumn<Jugador, ImageView> columImagen;

    @FXML
    private TableColumn<Jugador, String> columNombre;

    @FXML
    private TableColumn<Jugador, String> columPartidaLoss;

    @FXML
    private TableColumn<Jugador, String> columPartidaWin;

    @FXML
    private TableColumn<Jugador, String> columPunto;

    @FXML
    void add(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Añadir Jugador");
        dialog.setHeaderText("Añadir Jugador");
        dialog.setContentText("Introduce tu nombre:");
        Optional<String> result=dialog.showAndWait();

        if(result.isPresent()){
            String nombre=result.get().trim();
            Jugador jugador=new Jugador(nombre);
            tablaJugador.getItems().add(jugador);
            Fichero.agregarJugadorFichero(jugador);
        }
    }

    @FXML
    void delete(ActionEvent event) {
        Jugador jugadorSeleccionado = tablaJugador.getSelectionModel().getSelectedItem();
        if (jugadorSeleccionado != null) {
            tablaJugador.getItems().remove(jugadorSeleccionado);
            Fichero.eliminarJugadorFichero(jugadorSeleccionado);
        }
    }

    @FXML
    void edit(ActionEvent event) {
        int indice =tablaJugador.getSelectionModel().getSelectedIndex();
        Jugador jugador = tablaJugador.getSelectionModel().getSelectedItem();
        if(jugador!=null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("EditJugadorFXML.fxml"));
                Parent root = loader.load();
                EditJugadorController controlador=loader.getController();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Modificar persona");
                controlador.setJugador(jugador);
                stage.showAndWait();
                Jugador newJugador=controlador.getJugador();
                if(!controlador.isCancelar()) tablaJugador.getItems().set(indice,newJugador);
                Fichero.modificarJugador(newJugador,jugador);
                Fichero.modificarPartida(newJugador,jugador);

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }


    @FXML
    void initialize() {
        columImagen.setCellValueFactory(new PropertyValueFactory<>("foto"));
        columNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columPartidaLoss.setCellValueFactory(new PropertyValueFactory<>("partidas_perdidas"));
        columPartidaWin.setCellValueFactory(new PropertyValueFactory<>("partidas_ganadas"));
        columPunto.setCellValueFactory(new PropertyValueFactory<>("punto"));
        ArrayList<Jugador> jugadores = Fichero.leerJugadores();

        for (Jugador jugador : jugadores) {
            if(jugador.getFoto()!=null){
                ImageView resizedImage = resizeImage(jugador.getFoto());
                jugador.setFoto(resizedImage);
            }


            tablaJugador.getItems().add(jugador);
        }

    }

    /**
     * Redimensionar el imagen de cada jugador a 100*100
     * @param imageView
     * @return
     */
    private ImageView resizeImage(ImageView imageView) {
        imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);
        return imageView;
    }



}
