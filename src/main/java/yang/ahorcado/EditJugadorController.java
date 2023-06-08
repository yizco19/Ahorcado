package yang.ahorcado;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import yang.ahorcado.clase.Jugador;

import java.io.File;

public class EditJugadorController {



    Jugador editJugador;
    @FXML
    private ImageView foto;

    @FXML
    private TextField textNombre;

    @FXML
    private TextField textPartidaGanada;

    @FXML
    private TextField textPartidaPerdida;

    @FXML
    private TextField textPunto;


    @FXML
    void abrirImagen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar imagen");
        // Agregar filtros si se desea limitar los tipos de archivos mostrados
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter(
                "Archivos de imagen", "*.jpg", "*.jpeg", "*.png", "*.gif");
        fileChooser.getExtensionFilters().add(imageFilter);
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            // Cargar la imagen seleccionada en el ImageView "foto"
            Image imagen = new Image(selectedFile.toURI().toString());
            foto.setImage(imagen);
            editJugador.setFoto(new ImageView(imagen));
        }
    }

    @FXML
    void cancelar(ActionEvent event) {
        Stage currentStage = (Stage) textNombre.getScene().getWindow();
        currentStage.close();
        cancelar=true;
    }

    @FXML
    void guardar(ActionEvent event) {
        Stage currentStage = (Stage) textNombre.getScene().getWindow();
        currentStage.close();
    }
    boolean cancelar;

    boolean isCancelar(){
        return cancelar;
    }


    public void setJugador(Jugador jugador) {
        textNombre.setText(jugador.getNombre());
        textPartidaGanada.setText(String.valueOf(jugador.getPartidas_ganadas()));
        textPartidaPerdida.setText(String.valueOf(jugador.getPartidas_perdidas()));
        textPunto.setText(String.valueOf(jugador.getPunto()));
        if(jugador.getFoto()!=null) foto.setImage(jugador.getFoto().getImage());
        this.editJugador=new Jugador(jugador);

    }

    public  Jugador  getJugador(){
        editJugador.setNombre(textNombre.getText());


        return editJugador;
    }
}
