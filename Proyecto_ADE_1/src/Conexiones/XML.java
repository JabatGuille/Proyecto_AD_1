package Conexiones;

import Objetos.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class XML {

    private static final String URI = "xmldb:exist://localhost:8080/exist/xmlrpc/Proyecto";
    private static final String user = "admin";
    private static final String pass = "1234";

    public static HashMap<String, Cliente> recuperar_clientes() {
        HashMap<String, Cliente> clientes = new HashMap<>();
        try {
            XStream xstream = new XStream();
            xstream.addPermission(AnyTypePermission.ANY);
            xstream.alias("Clientes", ListaClientes.class);
            xstream.alias("Cliente", Cliente.class);
            xstream.addImplicitCollection(ListaClientes.class, "lista");
            FileInputStream fichero = new FileInputStream("src/Ficheros_XML/cliente.xml");
            BufferedReader br = new BufferedReader(new FileReader("src/Ficheros_XML/cliente.xml"));
            if (br.readLine() != null) {
                ListaClientes listadoTodas = (ListaClientes) xstream.fromXML(fichero);
                List<Cliente> listaPersonas;
                listaPersonas = listadoTodas.getClientes();
                for (Cliente p : listaPersonas) {
                    clientes.put(p.getDni(), p);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error en el fichero");
        } catch (IOException e) {
            System.out.println("Error en la lectura");
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
            FileInputStream fichero = new FileInputStream("src/Ficheros_XML/visitaGuiada.xml");
            BufferedReader br = new BufferedReader(new FileReader("src/Ficheros_XML/visitaGuiada.xml"));
            if (br.readLine() != null) {
                ListaVisitaGuiada listadoTodas = (ListaVisitaGuiada) xstream.fromXML(fichero);
                List<VisitaGuiada> listaPersonas;
                listaPersonas = listadoTodas.getVisitaGuiadas();
                for (VisitaGuiada p : listaPersonas) {
                    visitaguiadas.put(p.getN_visita(), p);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error en el fichero");
        } catch (IOException e) {
            System.out.println("Error en la lectura");
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
            FileInputStream fichero = new FileInputStream("src/Ficheros_XML/empleado.xml");
            BufferedReader br = new BufferedReader(new FileReader("src/Ficheros_XML/empleado.xml"));
            if (br.readLine() != null) {
                ListaEmpleados listadoTodas = (ListaEmpleados) xstream.fromXML(fichero);
                List<Empleado> listaPersonas;
                listaPersonas = listadoTodas.getEmpleados();
                for (Empleado p : listaPersonas) {
                    empleados.put(p.getDni(), p);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error en el fichero");
        } catch (IOException e) {
            System.out.println("Error en la lectura");
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
            FileInputStream fichero = new FileInputStream("src/Ficheros_XML/lugar.xml");
            BufferedReader br = new BufferedReader(new FileReader("src/Ficheros_XML/lugar.xml"));
            if (br.readLine() != null) {
                ListaLugares listadoTodas = (ListaLugares) xstream.fromXML(fichero);
                List<Lugar> listaPersonas;
                listaPersonas = listadoTodas.getLugares();
                for (Lugar p : listaPersonas) {
                    lugares.put(p.getId(), p);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error en el fichero");
        } catch (IOException e) {
            System.out.println("Error en la lectura");
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
        } catch (FileNotFoundException e) {
            System.out.println("Error en el fichero");
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
        } catch (FileNotFoundException e) {
            System.out.println("Error en el fichero");
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
        } catch (FileNotFoundException e) {
            System.out.println("Error en el fichero");
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
        } catch (FileNotFoundException e) {
            System.out.println("Error en el fichero");
        }

    }

    public static void conexion() {
        String driver = "org.exist.xmldb.DatabaseImpl"; //Driver para eXist


        try {
            Class cl = Class.forName(driver); //Cargar del driver
            Database database = (Database) cl.getDeclaredConstructor().newInstance(); //Instancia de la BD
            DatabaseManager.registerDatabase(database); //Registro del driver
            Collection col = DatabaseManager.getCollection(URI, user, pass);
            System.out.println(Arrays.toString(col.listResources()));
            System.out.println(col.);
        } catch (XMLDBException e) {
            System.out.println("Error al inicializar la BD eXist.");
            //e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Error en el driver.");
            //e.printStackTrace();
        } catch (InstantiationException | IllegalAccessException e) {
            System.out.println("Error al instanciar la BBDD.");
            //e.printStackTrace();
        } catch (InvocationTargetException e) {
            System.out.println("Error al instanciar ");
        } catch (NoSuchMethodException e) {
            System.out.println("Error en la BBDD");
        }
    }

    public static void main(String[] args) {
        conexion();
    }
}


