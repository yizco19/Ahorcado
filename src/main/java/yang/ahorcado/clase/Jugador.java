package yang.ahorcado.clase;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.ImageView;


public class Jugador {
    SimpleStringProperty nombre;
    int partidas_ganadas;
    int partidas_perdidas;
    int punto;

    ImageView foto;

    public Jugador(String nombre, int partidas_ganadas, int partidas_perdidas, int punto, ImageView foto) {
        this.nombre = new SimpleStringProperty(nombre);
        this.partidas_ganadas = partidas_ganadas;
        this.partidas_perdidas = partidas_perdidas;
        this.punto = punto;
        this.foto = foto;
    }
    public Jugador(String nombre, int partidas_ganadas, int partidas_perdidas, int punto) {
        this.nombre = new SimpleStringProperty(nombre);
        this.partidas_ganadas = partidas_ganadas;
        this.partidas_perdidas = partidas_perdidas;
        this.punto = punto;
    }

    public Jugador(String nombre) {
        this.nombre = new SimpleStringProperty(nombre);
        this.partidas_ganadas = 0;
        this.partidas_perdidas = 0;
        this.punto = 0;
    }

    public String getNombre() {
        return nombre.get();
    }



    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public int getPartidas_ganadas() {
        return partidas_ganadas;
    }

    public void setPartidas_ganadas(int partidas_ganadas) {
        this.partidas_ganadas = partidas_ganadas;
    }

    public int getPartidas_perdidas() {
        return partidas_perdidas;
    }

    public void setPartidas_perdidas(int partidas_perdidas) {
        this.partidas_perdidas = partidas_perdidas;
    }
    public  void  addPartidas_perdidas(){
        this.partidas_perdidas++;
    }
    public  void  addPartidas_ganadas(){
        this.partidas_ganadas++;
    }
    public  void  addPunto(int punto){
        this.punto+=punto;
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

    @Override
    public String toString() {
        return nombre.get();
    }

    public String toData() {
        boolean imagen=getFoto()!=null;
        if(foto==null){
            return nombre.get()+";"+partidas_ganadas+";"+partidas_perdidas+";"+punto;
        }
        return  nombre.get()+";"+partidas_ganadas+";"+partidas_perdidas+";"+punto+";"+foto.getImage().getUrl();

    }
}
