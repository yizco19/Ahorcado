package yang.ahorcado.clase;

import javafx.beans.property.SimpleStringProperty;

public class Partida {
    int codigo;
    SimpleStringProperty jugador;
    SimpleStringProperty palabra;

    boolean adivinada;

    public Partida(int codigo, String jugador, String palabra, boolean adivinada) {
        this.codigo = codigo;
        this.jugador = new SimpleStringProperty(jugador);
        this.palabra = new SimpleStringProperty(palabra);
        this.adivinada = adivinada;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getJugador() {
        return jugador.get();
    }

    public void setJugador(String jugador) {
        this.jugador.set(jugador);
    }

    public String getPalabra() {
        return palabra.get();
    }

    public void setPalabra(String palabra) {
        this.palabra.set(palabra);
    }

    public boolean isAdivinada() {
        return adivinada;
    }

    public void setAdivinada(boolean adivinada) {
        this.adivinada = adivinada;
    }

    public String toData() {
        return getCodigo()+";"+getJugador()+";"+getPalabra()+";"+Boolean.toString(isAdivinada());
    }
}
