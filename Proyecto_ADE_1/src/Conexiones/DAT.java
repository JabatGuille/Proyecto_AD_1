package Conexiones;

import Objetos.Cliente;
import Objetos.Empleado;
import Objetos.Lugar;
import Objetos.VisitaGuiada;

import javax.xml.crypto.Data;
import java.io.*;
import java.util.HashMap;

public class DAT {

    public static HashMap<String, Cliente> recuperar_clientes() {
        HashMap<String, Cliente> clientes = new HashMap<>();
        return clientes;
    }

    public static HashMap<Integer, VisitaGuiada> recuperar_visitas_guiadas() {
        HashMap<Integer, VisitaGuiada> visitaguiadas = new HashMap<>();

        return visitaguiadas;
    }

    public static HashMap<String, Empleado> recuperar_empleados() {
        HashMap<String, Empleado> empleados = new HashMap<>();
        return empleados;
    }

    public static HashMap<Integer, Lugar> recuperar_lugar() {
        HashMap<Integer, Lugar> lugares = new HashMap<>();
        return lugares;
    }

    public static void guardar_clientes(HashMap<String, Cliente> clientes) {
    }

    public static void guardar_visitas_guiadas(HashMap<Integer, VisitaGuiada> visitaguiadas) {

    }

    public static void guardar_empleados(HashMap<String, Empleado> empleados) {
    }

    public static void guardar_lugar(HashMap<Integer, Lugar> lugares) {
    }

    public static void main(String[] args) {
        try {
            File dat = new File("src/Ficheros_DAT/lugar.dat");
            FileOutputStream fileOutputStream = new FileOutputStream(dat, true);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(new Lugar(3, "Lugar", "Nacionaldiad"));
            FileInputStream fileInputStream = new FileInputStream(dat);
            while (true) {
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                Lugar luga = (Lugar) objectInputStream.readObject();
                System.out.println(luga.toString());
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
