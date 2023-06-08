package yang.ahorcado;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class AhorcadoAPP extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MenuFXML.fxml"));
        Parent mainPane = fxmlLoader.load();

        Scene scene = new Scene(mainPane);
        stage.setTitle("Ahorcado");
        stage.setScene(scene);
        stage.setResizable(false);

        File rutaIcon = new File("./src/main/java/yang/ahorcado/imagen/icon.png");
        Image icon = new Image(rutaIcon.toURI().toString());
        stage.getIcons().add(icon);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
