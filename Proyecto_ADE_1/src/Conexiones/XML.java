package Conexiones;

import Objetos.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XPathQueryService;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class XML {

    private static final String URI = "xmldb:exist://localhost:8080/exist/xmlrpc/db/Proyecto";
    private static final String user = "admin";
    private static final String pass = "1234";
    private static Collection col = null;

    public static HashMap<String, Cliente> recuperar_clientes() {

        HashMap<String, Cliente> clientes = new HashMap<>();
        try {
            FileWriter ficheroWriter = new FileWriter("src/Ficheros_XML/Clientes.xml");

            XPathQueryService servicio;
            servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            //Preparamos la consulta
            ResourceSet result = servicio.query("/Clientes");
            // recorrer los datos del recurso.
            ResourceIterator i;
            i = result.getIterator();
            if (!i.hasMoreResources()) {
                System.out.println(" LA CONSULTA NO DEVUELVE NADA O ESTÁ MAL ESCRITA");
            }
            while (i.hasMoreResources()) {
                Resource r = i.nextResource();
                System.out.println("--------------------------------------------");
                ficheroWriter.write((String) r.getContent());
            }
            ficheroWriter.close();
            col.close();
            XStream xstream = new XStream();
            xstream.addPermission(AnyTypePermission.ANY);
            xstream.alias("Clientes", ListaClientes.class);
            xstream.alias("Cliente", Cliente.class);
            xstream.addImplicitCollection(ListaClientes.class, "lista");
            FileInputStream fichero = new FileInputStream("src/Ficheros_XML/Clientes.xml");
            BufferedReader br = new BufferedReader(new FileReader("src/Ficheros_XML/Clientes.xml"));
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
        } catch (XMLDBException e) {
            throw new RuntimeException(e);
        }
        return clientes;
    }

    public static HashMap<Integer, VisitaGuiada> recuperar_visitas_guiadas() {
        HashMap<Integer, VisitaGuiada> visitaguiadas = new HashMap<>();
        try {
            FileWriter ficheroWriter = new FileWriter("src/Ficheros_XML/VisitasGuiadas.xml");

            XPathQueryService servicio;
            servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            //Preparamos la consulta
            ResourceSet result = servicio.query("/VisitasGuiadas");
            // recorrer los datos del recurso.
            ResourceIterator i;
            i = result.getIterator();
            if (!i.hasMoreResources()) {
                System.out.println(" LA CONSULTA NO DEVUELVE NADA O ESTÁ MAL ESCRITA");
            }
            while (i.hasMoreResources()) {
                Resource r = i.nextResource();
                System.out.println("--------------------------------------------");
                ficheroWriter.write((String) r.getContent());
            }
            ficheroWriter.close();
            col.close();
            XStream xstream = new XStream();
            xstream.addPermission(AnyTypePermission.ANY);
            xstream.alias("VisitasGuiadas", ListaVisitaGuiada.class);
            xstream.alias("VisitaGuiada", VisitaGuiada.class);
            xstream.addImplicitCollection(ListaVisitaGuiada.class, "lista");
            FileInputStream fichero = new FileInputStream("src/Ficheros_XML/VisitasGuiadas.xml");
            BufferedReader br = new BufferedReader(new FileReader("src/Ficheros_XML/VisitasGuiadas.xml"));
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
        } catch (XMLDBException e) {
            throw new RuntimeException(e);
        }
        return visitaguiadas;
    }

    public static HashMap<String, Empleado> recuperar_empleados() {
        HashMap<String, Empleado> empleados = new HashMap<>();
        try {
            FileWriter ficheroWriter = new FileWriter("src/Ficheros_XML/Empleados.xml");

            XPathQueryService servicio;
            servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            //Preparamos la consulta
            ResourceSet result = servicio.query("/Empleados");
            // recorrer los datos del recurso.
            ResourceIterator i;
            i = result.getIterator();
            if (!i.hasMoreResources()) {
                System.out.println(" LA CONSULTA NO DEVUELVE NADA O ESTÁ MAL ESCRITA");
            }
            while (i.hasMoreResources()) {
                Resource r = i.nextResource();
                System.out.println("--------------------------------------------");
                ficheroWriter.write((String) r.getContent());
            }
            ficheroWriter.close();
            col.close();
            XStream xstream = new XStream();
            xstream.addPermission(AnyTypePermission.ANY);
            xstream.alias("Empleados", ListaEmpleados.class);
            xstream.alias("Empleado", Empleado.class);
            xstream.addImplicitCollection(ListaEmpleados.class, "lista");
            FileInputStream fichero = new FileInputStream("src/Ficheros_XML/Empleados.xml");
            BufferedReader br = new BufferedReader(new FileReader("src/Ficheros_XML/Empleados.xml"));
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
        } catch (XMLDBException e) {
            throw new RuntimeException(e);
        }
        return empleados;
    }

    public static HashMap<Integer, Lugar> recuperar_lugar() {
        HashMap<Integer, Lugar> lugares = new HashMap<>();
        try {
            FileWriter ficheroWriter = new FileWriter("src/Ficheros_XML/Lugares.xml");

            XPathQueryService servicio;
            servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            //Preparamos la consulta
            ResourceSet result = servicio.query("/Lugares");
            // recorrer los datos del recurso.
            ResourceIterator i;
            i = result.getIterator();
            if (!i.hasMoreResources()) {
                System.out.println(" LA CONSULTA NO DEVUELVE NADA O ESTÁ MAL ESCRITA");
            }
            while (i.hasMoreResources()) {
                Resource r = i.nextResource();
                System.out.println("--------------------------------------------");
                ficheroWriter.write((String) r.getContent());
            }
            ficheroWriter.close();
            col.close();
            XStream xstream = new XStream();
            xstream.addPermission(AnyTypePermission.ANY);
            xstream.alias("Lugares", ListaLugares.class);
            xstream.alias("Lugar", Lugar.class);
            xstream.addImplicitCollection(ListaLugares.class, "lista");
            FileInputStream fichero = new FileInputStream("src/Ficheros_XML/Lugares.xml");
            BufferedReader br = new BufferedReader(new FileReader("src/Ficheros_XML/Lugares.xml"));
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
        } catch (XMLDBException e) {
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
            xstream.toXML(listaper, new FileOutputStream("src/Ficheros_XML/Clientes.xml"));
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
            xstream.toXML(listaper, new FileOutputStream("src/Ficheros_XML/empleados.xml"));
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
            DatabaseManager.registerDatabase(database);
            col = DatabaseManager.getCollection(URI, user, pass);
            System.out.println(col.getResourceCount());
            System.out.println("count" + col.getResource("lugar.xml"));

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

    public static void prueba() {
        try {
            XPathQueryService servicio;
            servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            //Preparamos la consulta
            ResourceSet result = servicio.query("/Clientes");
            // recorrer los datos del recurso.
            ResourceIterator i;
            i = result.getIterator();
            if (!i.hasMoreResources()) {
                System.out.println(" LA CONSULTA NO DEVUELVE NADA O ESTÁ MAL ESCRITA");
            }
            while (i.hasMoreResources()) {
                Resource r = i.nextResource();
                System.out.println("--------------------------------------------");
                System.out.println((String) r.getContent());
            }
            col.close();
        } catch (XMLDBException e) {
            System.out.println(" ERROR AL CONSULTAR DOCUMENTO.");
            e.printStackTrace();
        }
    }

    public static void prueba2() {
        try {
            System.out.printf("Conecta");
            // Inicializamos el recurso
            XMLResource res = null;

            // Creamos el recurso -> recibe 2 parámetros tipo String:
            // s: nombre.xml (si lo dejamos null, pondrá un nombre aleatorio)
            // s1: tipo recurso (en este caso, siempre será XMLResource)
            res = (XMLResource) col.createResource("Clientes.xml", "XMLResource");

            // Elegimos el fichero .xml que queremos añadir a la colección
            File f = new File("src/Ficheros_XML/Clientes.xml");

            // Fijamos como contenido ese archivo .xml elegido
            res.setContent(f);
            col.storeResource(res); // lo añadimos a la colección

            // Listamos la colección para ver que en efecto se ha añadido
            for (String colRe : col.listResources())
                System.out.println(colRe);

            col.close();
        } catch (Exception e) {
            System.out.println("Error al consultar.");
            // e.printStackTrace();
        }
    }

    public static void prueba3() {
        HashMap<String, Cliente> clientes = new HashMap<>();
        try {
            FileWriter fichero = new FileWriter("src/Ficheros_XML/Clientes.xml");

            XPathQueryService servicio;
            servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            //Preparamos la consulta
            ResourceSet result = servicio.query("/Clientes");
            // recorrer los datos del recurso.
            ResourceIterator i;
            i = result.getIterator();
            if (!i.hasMoreResources()) {
                System.out.println(" LA CONSULTA NO DEVUELVE NADA O ESTÁ MAL ESCRITA");
            }
            while (i.hasMoreResources()) {
                Resource r = i.nextResource();
                System.out.println("--------------------------------------------");
                fichero.write((String) r.getContent());
            }
            fichero.close();
            col.close();
/*
            XStream xstream = new XStream();
            xstream.addPermission(AnyTypePermission.ANY);
            xstream.alias("Clientes", ListaClientes.class);
            xstream.alias("Cliente", Cliente.class);
            xstream.addImplicitCollection(ListaClientes.class, "lista");
            BufferedReader br = new BufferedReader(new FileReader("src/Ficheros_XML/Clientes.xml"));
            if (br.readLine() != null) {
                ListaClientes listadoTodas = (ListaClientes) xstream.fromXML(fichero);
                List<Cliente> listaPersonas;
                listaPersonas = listadoTodas.getClientes();
                for (Cliente p : listaPersonas) {
                    clientes.put(p.getDni(), p);
                }
            }
*/
        } catch (FileNotFoundException e) {
            System.out.println("Error en el fichero");
        } catch (IOException e) {
            System.out.println("Error en la lectura");
        } catch (XMLDBException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        conexion();
        prueba();
        //   prueba2();
        HashMap<String, Cliente> a =   recuperar_clientes();
        HashMap<Integer, VisitaGuiada> b =   recuperar_visitas_guiadas();
        HashMap<String, Empleado> c =    recuperar_empleados();
        HashMap<Integer, Lugar> d = recuperar_lugar();
        System.out.println();
    }
}



