package Conexiones;

import Objetos.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class XML {

    public static HashMap<String, Cliente> recuperar_clientes() {
        HashMap<String, Cliente> clientes = new HashMap<>();
        try {
            XStream xstream = new XStream();
            xstream.addPermission(AnyTypePermission.ANY);
            xstream.alias("Clientes", ListaClientes.class);
            xstream.alias("Cliente", Cliente.class);
            xstream.addImplicitCollection(ListaClientes.class, "lista");
            ListaClientes listadoTodas = (ListaClientes) xstream.fromXML(new FileInputStream("src/Ficheros_XML/cliente.xml"));
            List<Cliente> listaPersonas;
            listaPersonas = listadoTodas.getClientes();
            for (Cliente p : listaPersonas) {
                clientes.put(p.getDni(), p);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return clientes;
    }

    public static HashMap<Integer, VisitaGuiada> recuperar_visitas_guiadas() {
        HashMap<Integer, VisitaGuiada> visitaguiadas = new HashMap<>();
        try {
            XStream xstream = new XStream();
            xstream.addPermission(AnyTypePermission.ANY);
            xstream.alias("VisitasGuiadas", ListaVisitaGuiada.class);
            xstream.alias("VisitaGuiada", VisitaGuiada.class);
            xstream.addImplicitCollection(ListaVisitaGuiada.class, "lista");
            ListaVisitaGuiada listadoTodas = (ListaVisitaGuiada) xstream.fromXML(new FileInputStream("src/Ficheros_XML/visitaGuiada.xml"));
            List<VisitaGuiada> listaPersonas;
            listaPersonas = listadoTodas.getVisitaGuiadas();
            for (VisitaGuiada p : listaPersonas) {
                visitaguiadas.put(p.getN_visita(), p);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return visitaguiadas;
    }

    public static HashMap<String, Empleado> recuperar_empleados() {
        HashMap<String, Empleado> empleados = new HashMap<>();
        try {
            XStream xstream = new XStream();
            xstream.addPermission(AnyTypePermission.ANY);
            xstream.alias("Empleados", ListaEmpleados.class);
            xstream.alias("Empleado", Empleado.class);
            xstream.addImplicitCollection(ListaEmpleados.class, "lista");
            ListaEmpleados listadoTodas = (ListaEmpleados) xstream.fromXML(new FileInputStream("src/Ficheros_XML/empleado.xml"));
            List<Empleado> listaPersonas;
            listaPersonas = listadoTodas.getEmpleados();
            for (Empleado p : listaPersonas) {
                empleados.put(p.getDni(), p);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return empleados;
    }

    public static HashMap<Integer, Lugar> recuperar_lugar() {
        HashMap<Integer, Lugar> lugares = new HashMap<>();
        try {
            XStream xstream = new XStream();
            xstream.addPermission(AnyTypePermission.ANY);
            xstream.alias("Lugares", ListaLugares.class);
            xstream.alias("Lugar", Lugar.class);
            xstream.addImplicitCollection(ListaLugares.class, "lista");
            ListaLugares listadoTodas = (ListaLugares) xstream.fromXML(new FileInputStream("src/Ficheros_XML/lugar.xml"));
            List<Lugar> listaPersonas;
            listaPersonas = listadoTodas.getLugares();
            for (Lugar p : listaPersonas) {
                lugares.put(p.getId(), p);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return lugares;
    }

    public static void guardar_clientes(HashMap<String, Cliente> clientes) {
        try {
            XStream xstream = new XStream();
            xstream.alias("Clientes", ListaClientes.class);
            xstream.alias("Cliente", Cliente.class);
            xstream.addImplicitCollection(ListaClientes.class, "lista");
            ListaClientes listaper = new ListaClientes();
            listaper.lista = new ArrayList<>(clientes.values());
            xstream.toXML(listaper, new FileOutputStream("src/Ficheros_XML/cliente.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void guardar_visitas_guiadas(HashMap<Integer, VisitaGuiada> visitaguiadas) {
        try {
            XStream xstream = new XStream();
            xstream.alias("VisitasGuiadas", ListaVisitaGuiada.class);
            xstream.alias("VisitaGuiada", VisitaGuiada.class);
            xstream.addImplicitCollection(ListaVisitaGuiada.class, "lista");
            ListaVisitaGuiada listaper = new ListaVisitaGuiada();
            listaper.lista = new ArrayList<>(visitaguiadas.values());
            xstream.toXML(listaper, new FileOutputStream("src/Ficheros_XML/visitaGuiada.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void guardar_empleados(HashMap<String, Empleado> empleados) {
        try {
            XStream xstream = new XStream();
            xstream.alias("Empleados", ListaEmpleados.class);
            xstream.alias("Empleado", Empleado.class);
            xstream.addImplicitCollection(ListaEmpleados.class, "lista");
            ListaEmpleados listaper = new ListaEmpleados();
            listaper.lista = new ArrayList<>(empleados.values());
            xstream.toXML(listaper, new FileOutputStream("src/Ficheros_XML/empleado.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void guardar_lugar(HashMap<Integer, Lugar> lugares) {
        try {
            XStream xstream = new XStream();
            xstream.alias("Lugares", ListaLugares.class);
            xstream.alias("Lugar", Lugar.class);
            xstream.addImplicitCollection(ListaLugares.class, "lista");
            ListaLugares listaper = new ListaLugares();
            listaper.lista = new ArrayList<>(lugares.values());
            xstream.toXML(listaper, new FileOutputStream("src/Ficheros_XML/lugar.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

   /* public static void main(String[] args) {
        HashMap<String, Cliente> clientes = new HashMap<>();
        HashMap<String, Empleado> empleados = new HashMap<>();
        HashMap<Integer, VisitaGuiada> visitasguiadas = new HashMap<>();
        HashMap<Integer, Lugar> lugares = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            Cliente cliente = new Cliente("DNI" + i, "Nombre", "Apellido", 12, "Profesion","");
            cliente.setVisitas(i);
            clientes.put(cliente.getDni(), cliente);
        }
        for (int i = 0; i < 5; i++) {
            Empleado empleado = new Empleado("DNI" + i, "Nombre", "Apellido", "fecha_NAC", "fecha_cont", "Nacionalidad", "Cargo","");
            empleado.setVisitas(i);
            empleados.put(empleado.getDni(), empleado);
        }
        for (int i = 0; i < 5; i++) {
            VisitaGuiada visitaGuiada = new VisitaGuiada(i, "Nombre", 12, "Punto partida", "Curso", "Tematica", 12.0, i, "12","");
            visitaGuiada.setEmpleado("DNI"+i);
            visitaGuiada.setLugar(i);
            visitaGuiada.setClientes("DNI" + i);
            visitasguiadas.put(visitaGuiada.getN_visita(), visitaGuiada);

        }
        for (int i = 0; i < 5; i++) {
            Lugar lugar = new Lugar(i, "Lugar", "Nacionalidad");
            lugar.setVisitas(i);
            lugares.put(lugar.getId(), lugar);
        }
        guardar_clientes(clientes);
        guardar_empleados(empleados);
        guardar_visitas_guiadas(visitasguiadas);
        guardar_lugar(lugares);

    }
*/
}

