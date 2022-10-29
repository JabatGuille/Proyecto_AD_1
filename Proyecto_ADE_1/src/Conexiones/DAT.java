package Conexiones;

import Objetos.Cliente;
import Objetos.Empleado;
import Objetos.Lugar;
import Objetos.VisitaGuiada;
import java.io.*;
import java.util.HashMap;

public class DAT {

    public static HashMap<String, Cliente> recuperar_clientes() {
        try {
            HashMap<String, Cliente> clientes = new HashMap<>();
            File dat = new File("src/Ficheros_DAT/cliente.dat");
            FileInputStream fileInputStream = new FileInputStream(dat);
            while (fileInputStream.available() > 0) {
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                Cliente cliente = (Cliente) objectInputStream.readObject();
                clientes.put(cliente.getDni(), cliente);
            }
            return clientes;
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static HashMap<Integer, VisitaGuiada> recuperar_visitas_guiadas() {
        try {
            HashMap<Integer, VisitaGuiada> visitaguiadas = new HashMap<>();
            File dat = new File("src/Ficheros_DAT/visitaGuiada.dat");
            FileInputStream fileInputStream = new FileInputStream(dat);
            while (fileInputStream.available() > 0) {
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                VisitaGuiada visitaGuiada = (VisitaGuiada) objectInputStream.readObject();
                visitaguiadas.put(visitaGuiada.getN_visita(), visitaGuiada);
            }
            return visitaguiadas;
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static HashMap<String, Empleado> recuperar_empleados() {
        try {
            HashMap<String, Empleado> empleados = new HashMap<>();
            File dat = new File("src/Ficheros_DAT/empleado.dat");
            FileInputStream fileInputStream = new FileInputStream(dat);
            while (fileInputStream.available() > 0) {
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                Empleado empleado = (Empleado) objectInputStream.readObject();
                empleados.put(empleado.getDni(), empleado);
            }
            return empleados;
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static HashMap<Integer, Lugar> recuperar_lugar() {
        try {
            HashMap<Integer, Lugar> lugares = new HashMap<>();
            File dat = new File("src/Ficheros_DAT/lugar.dat");
            FileInputStream fileInputStream = new FileInputStream(dat);
            while (fileInputStream.available() > 0) {
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                Lugar luga = (Lugar) objectInputStream.readObject();
                lugares.put(luga.getId(), luga);
            }
            return lugares;
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void guardar_clientes(HashMap<String, Cliente> clientes) {
        try {
            File dat = new File("src/Ficheros_DAT/cliente.dat");
            FileOutputStream fileOutputStream = new FileOutputStream(dat);
            for (Cliente cliente : clientes.values()) {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(cliente);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void guardar_visitas_guiadas(HashMap<Integer, VisitaGuiada> visitaguiadas) {
        try {
            File dat = new File("src/Ficheros_DAT/visitaGuiada.dat");
            FileOutputStream fileOutputStream = new FileOutputStream(dat);
            for (VisitaGuiada visitaGuiada : visitaguiadas.values()) {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(visitaGuiada);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void guardar_empleados(HashMap<String, Empleado> empleados) {
        try {
            File dat = new File("src/Ficheros_DAT/empleado.dat");
            FileOutputStream fileOutputStream = new FileOutputStream(dat);
            for (Empleado empleado : empleados.values()) {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(empleado);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void guardar_lugar(HashMap<Integer, Lugar> lugares) {
        try {
            File dat = new File("src/Ficheros_DAT/lugar.dat");
            FileOutputStream fileOutputStream = new FileOutputStream(dat);
            for (Lugar lugar : lugares.values()) {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(lugar);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
