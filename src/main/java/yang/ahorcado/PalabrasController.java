package yang.ahorcado;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import yang.ahorcado.clase.Fichero;

import java.io.File;
import java.util.ArrayList;

public class PalabrasController {

    @FXML
    private ComboBox<String> boxPalara;

    @FXML
    private Button btnInsertar;

    @FXML
    private ListView<String> listaFichero;

    @FXML
    private TextField textPalabra;



    @FXML
    void Insertar(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar archivo");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos de texto", "*.txt"));
        File archivoSeleccionado = fileChooser.showOpenDialog(btnInsertar.getScene().getWindow());
        if (archivoSeleccionado != null) {
            String rutaFichero = archivoSeleccionado.getAbsolutePath();
            Fichero.agregarArchivoPalabra(rutaFichero);
            ArrayList<String> nombresFicheros=Fichero.getNombresFicheros();
            boxPalara.getItems().setAll(nombresFicheros);
        }
    }

    @FXML
    void mostrar(ActionEvent event) {
        String nombreFichero=boxPalara.getSelectionModel().getSelectedItem();
        ArrayList<String> palabras=Fichero.leerPalabras(nombreFichero);
        listaFichero.getItems().setAll(palabras);

    }

    @FXML
    void addPalabra(ActionEvent event) {
        String newPalabra=textPalabra.getText().toUpperCase();
        String nombreFichero=boxPalara.getSelectionModel().getSelectedItem();
        Fichero.addPalabra(newPalabra,nombreFichero);
        listaFichero.getItems().add(newPalabra);
        textPalabra.clear();
    }


    @FXML
    void initialize() {
        ArrayList<String> nombresFicheros= Fichero.getNombresFicheros();
        boxPalara.getItems().setAll(nombresFicheros);
        boxPalara.getSelectionModel().selectFirst();
        ArrayList<String> palabras=Fichero.leerPalabras(nombresFicheros.get(0));
        listaFichero.getItems().setAll(palabras);


    }

}
