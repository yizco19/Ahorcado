package yang.ahorcado.clase;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.ImageView;

public class Rank {
    SimpleStringProperty nombre;
    int punto;
    ImageView foto;

    public Rank(String nombre, int punto) {
        this.nombre = new SimpleStringProperty(nombre);
        this.punto = punto;
    }

    public Rank(String nombre, int punto,ImageView foto) {
        this.nombre = new SimpleStringProperty(nombre);
        this.punto = punto;
        this.foto=foto;
    }


    public String getNombre() {
        return nombre.get();
    }

    public SimpleStringProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public int getPunto() {
        return punto;
    }

    public void setPunto(int punto) {
        this.punto = punto;
    }

    public ImageView getFoto() {
        return foto;
    }

    public void setFoto(ImageView foto) {
        this.foto = foto;
    }
}
