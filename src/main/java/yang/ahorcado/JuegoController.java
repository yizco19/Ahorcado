package yang.ahorcado;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import yang.ahorcado.clase.Fichero;
import yang.ahorcado.clase.Jugador;
import yang.ahorcado.clase.Partida;

import java.util.ArrayList;

public class JuegoController {



    @FXML
    private ImageView imagenVida;

    @FXML
    private Text textJugador;

    @FXML
    private Text textPalabra;

    @FXML
    private Text textVida;



    @FXML
    void btnClick(ActionEvent event) {
        Button button=(Button)  event.getSource();
        char ca=button.getText().trim().charAt(0);
        button.setVisible(false);

        if(comprobaExisteLetra(ca)){
            ponePalabra();
        }else{
            completado();
            vida--;
            imagenVida.setImage(Fichero.getImagen(vida));
            setTextVida();
        }
        comprobaWinLoss(button);

    }

    private void comprobaWinLoss(Button button) {
        boolean win = false;
        if(completado() || vida==0){
            if(vida==0){
                jugador.addPartidas_perdidas();
                mostraResultado(Alert.AlertType.CONFIRMATION, "LOSS");

            }
            if(completado()){
                jugador.addPartidas_ganadas();
                jugador.addPunto(puntacion);
                mostraResultado(Alert.AlertType.CONFIRMATION, "WIN");
                win=true;


            }
            Fichero.agregarPartida(jugador.getNombre(),palabra,win);
            Stage currentstage = (Stage) button.getScene().getWindow();
            currentstage.close();

        }
    }

    private void mostraResultado(Alert.AlertType confirmation, String estado) {
        Alert alert = new Alert(confirmation);
        alert.setTitle(estado);
        alert.setHeaderText(jugador.getNombre()+" : " + estado);
        alert.setContentText("La palabra es : " + palabra);
        alert.showAndWait();
    }



    String palabra;
    int puntacion;

    Jugador jugador;
    boolean[] adivinado;

    int vida;

    public void setJugador(Jugador jugador) {
        this.jugador=jugador;
        textJugador.setText(textJugador.getText() +jugador.getNombre());
    }

    public  Jugador getJugador(){
        return  jugador;
    }

    public  void  setTextVida(){
        textVida.setText("Vida:"+String.valueOf(vida));
    }
    public void setPalabra(String palabra) {
        String textoConTildes = "áéíóú";
        String textoSinTildes = quitarTildes(textoConTildes);


        this.palabra = palabra;
        adivinado = new boolean[palabra.length()];
        ponePalabra();

    }
    public static String quitarTildes(String texto) {
        String normalized = java.text.Normalizer.normalize(texto, java.text.Normalizer.Form.NFD);
        return normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPuntacion(int puntacion) {
        this.puntacion = puntacion;
    }

    public int getPuntacion() {
        return puntacion;
    }


    private boolean comprobaExisteLetra(char letra) {
        // TODO Auto-generated method stub
        boolean existe = false;
        for(int i=0;i<palabra.length();i++) {
            char car=palabra.charAt(i);

            if(palabra.charAt(i)==letra) {
                adivinado[i]=true;
                existe=true;
            }
        }
        return existe;
    }
    boolean completado(){
        for(int i=0;i<adivinado.length;i++) {
            if(adivinado[i]==false) return false;
        }
        return true;
    }

    void ponePalabra(){
        String showPalabra="";
        for (int i = 0; i < palabra.length(); i++) {
            if (adivinado[i]) {
                showPalabra+=palabra.charAt(i);
            } else {
                showPalabra+="-";
            }
        }
        textPalabra.setText(showPalabra);
    }



    @FXML
    void initialize() {
        vida=6;
        imagenVida.setImage(Fichero.getImagen(vida));

    }




}
