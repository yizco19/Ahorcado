package yang.ahorcado.clase;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.*;

public class Fichero {

    private static File rutaPartida=new File("./src/main/java/yang/ahorcado/texto/partidas.txt");
    private static File rutaJugador=new File("./src/main/java/yang/ahorcado/texto/jugadores.txt");
    private static File carpeta=new File("./src/main/java/yang/ahorcado/texto/palabra");

    private static String rutaImagen="./src/main/java/yang/ahorcado/imagen";


    public static ArrayList<Jugador> leerJugadores(){
        ArrayList<Jugador> listaJugadores = new ArrayList<Jugador>();
        try  {
            Scanner scanner = new Scanner(new FileReader(rutaJugador));
            String linea;
            while (scanner.hasNextLine()) {
                linea=scanner.nextLine();
                if (!linea.trim().isEmpty()){

                    String[] campos = linea.split(";");
                    String nombre = campos[0];
                    int partidasGanadas = Integer.parseInt(campos[1]);
                    int partidasPerdidas = Integer.parseInt(campos[2]);
                    int punto = Integer.parseInt(campos[3]);

                    Jugador jugador;
                    if (campos.length==5){
                        ImageView foto=new ImageView(new Image(campos[4]));

                         jugador = new Jugador(nombre, partidasGanadas, partidasPerdidas, punto,foto);
                    }else {
                         jugador = new Jugador(nombre, partidasGanadas, partidasPerdidas, punto);
                    }


                    listaJugadores.add(jugador);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de jugadores: " + e.getMessage());
        }
        return listaJugadores;

    }

    public static int contarPartida()  {
        int lineas = 1;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(rutaPartida));


            while (reader.readLine() != null) lineas++;

            reader.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return lineas;
    }

    public static ArrayList<Partida> leerPartidas() {
        ArrayList<Partida> partidas = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(rutaPartida);

            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                if (!linea.trim().isEmpty()) {
                    String[] datos = linea.split(";");
                    int numeroPartida = Integer.parseInt(datos[0]);
                    String jugador = datos[1];
                    String palabra = datos[2];
                    boolean adivinada = Boolean.valueOf(datos[3]);
                    Partida partida = new Partida(numeroPartida, jugador, palabra, adivinada);
                    partidas.add(partida);
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("El archivo de partidas no existe");
        }

        return partidas;
    }

    public static ArrayList<String> leerPalabras(String nombre) {
        ArrayList<String> palabras = new ArrayList<>();
        File rutapalabra=new File("./src/main/java/yang/ahorcado/texto/palabra/"+nombre);
        try {
            Scanner scanner = new Scanner(new FileReader(rutapalabra));

            // Recorre todas las líneas del archivo y añade las palabras al ArrayList
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                if (!linea.trim().isEmpty()) {
                    palabras.add(linea); // Se añade la palabra al ArrayList
                }
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Error al abrir el archivo: " + e.getMessage());
        }

        return palabras;
    }

    public static ArrayList<String> getNombresFicheros(){

        // Obtener todos los archivos en la carpeta
        File[] archivos = carpeta.listFiles();

        // Lista para almacenar los nombres de archivo
        ArrayList<String> nombresArchivos = new ArrayList<>();

        // Recorrer los archivos y guardar sus nombres en la lista
        if (archivos != null) {
            for (File archivo : archivos) {
                if (archivo.isFile()) {
                    nombresArchivos.add(archivo.getName());
                }
            }
        }

        return nombresArchivos;

    }

    public static void agregarJugadorFichero(Jugador jugadorNuevo)  {
        FileWriter fw;
        try {
            fw = new FileWriter(rutaJugador, true);

            BufferedWriter bw = new BufferedWriter(fw);

            // Escribir un jugador en una línea del archivo
            bw.newLine();

            bw.write(jugadorNuevo.toData());

            bw.close();
            fw.close();
        }catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public static void eliminarJugadorFichero(Jugador jugadorSeleccionado) {
        try {
            ArrayList<Jugador> jugadores = leerJugadores();

            jugadores.removeIf(jugador -> jugador.getNombre().equals(jugadorSeleccionado.getNombre()));

            FileWriter fw = new FileWriter(rutaJugador);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Jugador jugador : jugadores) {
                bw.write(jugador.toData());
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println("Error al eliminar el jugador del archivo: " + e.getMessage());
        }
    }

    public static String randPalabra(String ficheroPalabras) {
        // TODO Auto-generated method stub
        ArrayList<String> palabras = leerPalabras(ficheroPalabras);
        Random rand=new Random();
        return palabras.get(rand.nextInt(palabras.size()));
    }

    public static void modificarJugador(Jugador newJugador, Jugador oldJugador) {
        try {
            // Leer todos los jugadores del archivo
            ArrayList<Jugador> jugadores = leerJugadores();

            // Buscar el jugador antiguo y reemplazarlo con el nuevo jugador
            for (int i = 0; i < jugadores.size(); i++) {
                Jugador jugador = jugadores.get(i);
                if (jugador.getNombre().equals(oldJugador.getNombre())) {
                    jugadores.set(i, newJugador);
                    break;
                }
            }

            // Escribir la lista actualizada de jugadores en el archivo
            FileWriter fw = new FileWriter(rutaJugador);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Jugador jugador : jugadores) {
                bw.write(jugador.toData());
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println("Error al modificar el jugador en el archivo: " + e.getMessage());
        }
    }

    public static void addPalabra(String palabra, String ficheroPalabras) {
        File rutapalabra = new File("./src/main/java/yang/ahorcado/texto/palabra/" + ficheroPalabras);
        try {
            FileWriter fw = new FileWriter(rutapalabra, true);
            BufferedWriter bw = new BufferedWriter(fw);

            // Escribir la nueva palabra en una nueva línea del archivo
            bw.newLine();
            bw.write(palabra);


            bw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println("Error al agregar la palabra al archivo: " + e.getMessage());
        }
    }

    public static void agregarArchivoPalabra(String rutaFichero) {
        try {
            File archivo = new File(rutaFichero);
            if (archivo.exists()) {
                File destino = new File(carpeta, archivo.getName());
                Files.copy(archivo.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Archivo de palabras copiado exitosamente.");
            } else {
                System.out.println("El archivo de palabras no existe en la ruta especificada.");
            }
        } catch (IOException e) {
            System.out.println("Error al copiar el archivo de palabras: " + e.getMessage());
        }

    }

    public  static  ArrayList<Rank> conseguirRank(){
        ArrayList<Jugador> jugadores=leerJugadores();
        ArrayList<Rank> ranks=new ArrayList<>();
        Collections.sort(jugadores, new Comparator<Jugador>() {
            @Override
            public int compare(Jugador jugador1, Jugador jugador2) {
                return Integer.compare(jugador2.getPunto(), jugador1.getPunto());
            }
        });
        for ( Jugador jugador:jugadores){
            if(jugador.getFoto()==null){
                ranks.add((new Rank(jugador.getNombre(),jugador.getPunto())));
            }else{
                ranks.add(new Rank(jugador.getNombre(),jugador.getPunto(),jugador.getFoto()));
            }

        }
        return ranks;
    }


    public static void agregarPartida(String nombre,String palabra, boolean win) {
        Partida partida=new Partida(contarPartida(),nombre,palabra,win);

        FileWriter fw;
        try {
            fw = new FileWriter(rutaPartida, true);

            BufferedWriter bw = new BufferedWriter(fw);

            // Escribir un jugador en una línea del archivo


            bw.write(partida.toData());

            bw.newLine();

            bw.close();
            fw.close();
        }catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void modificarPartida(Jugador newJugador, Jugador jugador) {
        try {
            // Leer todos los partidas del archivo
            ArrayList<Partida> partidas = leerPartidas();

            // Buscar el jugador antiguo y reemplazarlo con el nuevo jugador
            for (int i = 0; i < partidas.size(); i++) {
                Partida partida = partidas.get(i);
                if (partida.getJugador().equals(jugador.getNombre())) {
                    partida.setJugador(newJugador.getNombre());
                    break;
                }
            }

            // Escribir la lista actualizada de jugadores en el archivo
            FileWriter fw = new FileWriter(rutaPartida);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Partida partida : partidas) {
                bw.write(partida.toData());
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println("Error al modificar el jugador en el archivo: " + e.getMessage());
        }
    }

    public static Image getImagen(int vida) {
        String rutaImagenVida = rutaImagen + "/vida" + vida + ".png";
        File fichero = new File(rutaImagenVida);
        Image image = new Image(fichero.toURI().toString());

        return image;
    }

}
