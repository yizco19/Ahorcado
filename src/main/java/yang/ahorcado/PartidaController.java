package yang.ahorcado;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import yang.ahorcado.clase.Fichero;
import yang.ahorcado.clase.Partida;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PartidaController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;



    @FXML
    private Button btnSalir;

    @FXML
    private Button buscar;


    @FXML
    private ComboBox<String> boxJugador;

    @FXML
    private TableColumn<Partida, String> columCodigo;

    @FXML
    private TableColumn<Partida, String> columJugador;

    @FXML
    private TableColumn<Partida, String> columPalabra;

    @FXML
    private TableColumn<Partida, String> columnAdivinada;

    @FXML
    private TableView<Partida> tablaPartida;

    @FXML
    void buscar(ActionEvent event) {
        String jugadorSeleccionado=boxJugador.getSelectionModel().getSelectedItem();
        ArrayList<Partida> partidas= Fichero.leerPartidas();

        for (int i = partidas.size() - 1; i >= 0; i--) {
            String nombreJugador = partidas.get(i).getJugador();
            if (!jugadorSeleccionado.equals(nombreJugador)) {
                partidas.remove(i);
            }
        }


        tablaPartida.getItems().setAll(partidas);


    }



    private static File rutaPalabra;

    public static File getRutaPalabra() {
        return rutaPalabra;
    }

    public static void setRutaPalabra(File rutaPalabra) {
        PartidaController.rutaPalabra = rutaPalabra;
    }

    @FXML
    void initialize() {
        columCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        columJugador.setCellValueFactory(new PropertyValueFactory<>("jugador"));
        columPalabra.setCellValueFactory(new PropertyValueFactory<>("palabra"));
        columnAdivinada.setCellValueFactory(new PropertyValueFactory<>("adivinada"));
        ArrayList<Partida> partidas= Fichero.leerPartidas();
        tablaPartida.getItems().addAll(partidas);
        ArrayList<String> jugadores=obtenerNombresJugadores(partidas);
        boxJugador.getItems().setAll(jugadores);

    }

    public static ArrayList<String> obtenerNombresJugadores(ArrayList<Partida> partidas) {
        ArrayList<String> nombresJugadores = new ArrayList<>();

        for (Partida partida : partidas) {
            String jugador = partida.getJugador();
            if (!nombresJugadores.contains(jugador)) {
                nombresJugadores.add(jugador);
            }
        }

        return nombresJugadores;
    }


}
