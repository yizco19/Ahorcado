package yang.ahorcado;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import yang.ahorcado.clase.Fichero;
import yang.ahorcado.clase.Rank;

import java.util.ArrayList;

public class RankController {

    @FXML
    private TableColumn<Rank, ImageView> columImagen;

    @FXML
    private TableColumn<Rank, String> columNombre;

    @FXML
    private TableColumn<Rank, String> columPunto;

    @FXML
    private TableView<Rank> tablaRank;
    @FXML
    void initialize() {

        columNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columPunto.setCellValueFactory(new PropertyValueFactory<>("punto"));
        columImagen.setCellValueFactory(new PropertyValueFactory<>("foto"));
        ArrayList<Rank> ranks= Fichero.conseguirRank();

        for (Rank rank : ranks) {
            if(rank.getFoto()!=null){
                ImageView resizedImage = resizeImage(rank.getFoto());
                rank.setFoto(resizedImage);
            }

            tablaRank.getItems().add(rank);
        }


    }

    private ImageView resizeImage(ImageView imageView) {
        imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);
        return imageView;
    }

}
