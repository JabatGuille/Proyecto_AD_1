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
    private static int mockId = 999999999;
    private static String pathClientes = "src/Ficheros_XML/Clientes.xml";
    private static String pathLugares = "src/Ficheros_XML/Lugares.xml";
    private static String pathEmpleados = "src/Ficheros_XML/Empleados.xml";
    private static String pathVisitas = "src/Ficheros_XML/VisitasGuiadas.xml";


    /**
     * recuperar_clientes se encarga de recuperar los clientes y gestionarlos en la BBDD
     *
     * @return
     */
    public static HashMap<String, Cliente> recuperar_clientes() {
        HashMap<String, Cliente> clientes = new HashMap<>();
        conexion();
        if (col != null) {
            try {
                FileWriter ficheroWriter = new FileWriter(pathClientes);

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
                    ficheroWriter.write((String) r.getContent());
                }
                ficheroWriter.close();
                col.close();
                XStream xstream = new XStream();
                xstream.addPermission(AnyTypePermission.ANY);
                xstream.alias("Clientes", ListaClientes.class);
                xstream.alias("Cliente", Cliente.class);
                xstream.addImplicitCollection(ListaClientes.class, "lista");
                FileInputStream fichero = new FileInputStream(pathClientes);
                BufferedReader br = new BufferedReader(new FileReader(pathClientes));
                if (br.readLine() != null) {
                    ListaClientes listadoTodas = (ListaClientes) xstream.fromXML(fichero);
                    List<Cliente> listaPersonas;
                    listaPersonas = listadoTodas.getClientes();
                    if (listaPersonas != null) {
                        for (Cliente p : listaPersonas) {
                            if (p.getVisitas().containsKey(mockId)) {
                                p.borrar_visita(mockId);
                            }
                            clientes.put(p.getDni(), p);
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("Error en el fichero");
            } catch (IOException e) {
                System.out.println("Error en la lectura");
            } catch (XMLDBException e) {
                System.out.println("Error con el XML");
            }
        } else {
            System.out.println("Error en la BBDD");
        }
        return clientes;
    }

    /**
     * insertarCLiente se encarga de insertar un cliente en la BBDD
     *
     * @param cliente
     */
    public static void insertarCLiente(Cliente cliente) {
        if (!comprobarCliente(cliente.getDni())) {
            conexion();
            if (col != null) {
                String nuevocli = "<Cliente><dni>" + cliente.getDni() + "</dni>"
                        + "<nombre>" + cliente.getNombre() + "</nombre>" +
                        "<apellido>" + cliente.getApellido() + "</apellido>"
                        + "<edad>" + cliente.getEdad() + "</edad>" +
                        "<profesion>" + cliente.getProfesion() + "</profesion>" +
                        "<estado>" + cliente.getEstado() + "</estado>" +
                        "<visitas__numero>" + "<entry>";
                if (cliente.getVisitas().size() == 0) {
                    cliente.setVisitas(mockId);
                } else if (cliente.getVisitas().size() >= 2) {
                    cliente.borrar_visita(mockId);
                }
                for (Integer cli : cliente.getVisitas().values()) {
                    nuevocli = nuevocli + "<int>" + cli + "</int>" +
                            "<int>" + cli + "</int>";
                }
                nuevocli = nuevocli + "</entry>" +
                        "</visitas__numero>" +
                        "</Cliente>";

                try {
                    XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
                    System.out.printf("Inserto: %s \n", nuevocli);
                    //Consulta para insertar --> update insert ... into
                    ResourceSet result = servicio.query("update insert " + nuevocli + " into /Clientes");
                    col.close(); //borramos
                    System.out.println("Cliente insertado.");
                } catch (Exception e) {
                    System.out.println("Error al insertar cliente.");
                    e.printStackTrace();
                }
            } else {
                System.out.println("Error en la BBDD");
            }
        } else {
            System.out.println("Existe el cliente");
        }
    }

    /**
     * comprobarCliente se encarga de comprobar si existe un cliente en la BBDD y retorna un bool dependiendo el resultado
     *
     * @param DNI
     * @return
     */
    public static boolean comprobarCliente(String DNI) {
        //Devuelve true si el dep existe
        conexion();
        if (col != null) {
            try {
                XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
                //Consulta para consultar la información de un departamento
                ResourceSet result = servicio.query("/Clientes/Cliente[dni='" + DNI + "']");
                ResourceIterator i;
                i = result.getIterator();
                col.close();
                if (!i.hasMoreResources()) {
                    return false;
                } else {
                    return true;
                }
            } catch (Exception e) {
                System.out.println("Error al consultar.");
                // e.printStackTrace();
            }
        } else {
            System.out.println("Error en la conexión. Comprueba datos.");
        }

        return false;

    }

    /**
     * modificarCliente se encarga de modificar un cliente de la BBDD
     *
     * @param cliente
     */
    public static void modificarCLiente(Cliente cliente) {
        if (!comprobarCliente(cliente.getDni())) {
            conexion();
            if (col != null) {
                try {
                    System.out.printf("Actualizo el cliente: %s\n", cliente.getDni());
                    XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
                    //Consulta para modificar/actualizar un valor --> update value
                    ResourceSet result = servicio.query(
                            "update value /Clientes/Cliente[dni='" + cliente.getDni() + "']/nombre with data('" + cliente.getNombre() + "') ");
                    result = servicio.query(
                            "update value /Clientes/Cliente[dni='" + cliente.getDni() + "']/apellido with data('" + cliente.getApellido() + "') ");
                    result = servicio.query(
                            "update value /Clientes/Cliente[dni='" + cliente.getDni() + "']/edad with data('" + cliente.getEdad() + "') ");
                    result = servicio.query(
                            "update value /Clientes/Cliente[dni='" + cliente.getDni() + "']/profesion with data('" + cliente.getProfesion() + "') ");

                    col.close();
                    System.out.println("Cliente actualizado.");
                } catch (Exception e) {
                    System.out.println("Error al actualizar.");
                    e.printStackTrace();
                }
            } else {
                System.out.println("Error en la conexión. Comprueba datos.");
            }
        } else {
            System.out.println("El cliente NO EXISTE.");
        }
    }

    /**
     * insertarVisitaGuiada se encarga de insertar una visitaGuiada
     *
     * @param visitaGuiada
     */
    public static void insertarVisitaGuiada(VisitaGuiada visitaGuiada) {
        if (!comprobarVisita(visitaGuiada.getN_visita())) {
            conexion();
            if (col != null) {
                String nuevavis = "<VisitaGuiada><n__visita>" + visitaGuiada.getN_visita() + "</n__visita>"
                        + "<nombre>" + visitaGuiada.getNombre() + "</nombre>" +
                        "<n__max__cli>" + visitaGuiada.getN_max_cli() + "</n__max__cli>"
                        + "<punto__partida>" + visitaGuiada.getPunto_partida() + "</punto__partida>" +
                        "<curso>" + visitaGuiada.getCurso() + "</curso>" +
                        "<tematica>" + visitaGuiada.getTematica() + "</tematica>" +
                        "<coste>" + visitaGuiada.getCoste() + "</coste>" +
                        "<estado>" + visitaGuiada.getEstado() + "</estado>" +
                        "<lugar__id>" + visitaGuiada.getLugar() + "</lugar__id>" +
                        "<empleado__dni>" + visitaGuiada.getEmpleado() + "</empleado__dni>" +
                        "<horario>" + visitaGuiada.getHorario() + "</horario>" +
                        "<clientes__dni>" + "<entry>";
                System.out.println("  <VisitaGuiada>\n" +
                        "    <n__visita>" + visitaGuiada.getN_visita() + "</n__visita>\n" +
                        "    <nombre>" + visitaGuiada.getNombre() + "</nombre>\n" +
                        "    <n__max__cli>" + visitaGuiada.getN_max_cli() + "</n__max__cli>\n" +
                        "    <punto__partida>" + visitaGuiada.getPunto_partida() + "</punto__partida>\n" +
                        "    <curso>" + visitaGuiada.getCurso() + "</curso>\n" +
                        "    <tematica>" + visitaGuiada.getTematica() + "</tematica>\n" +
                        "    <coste>" + visitaGuiada.getCoste() + "</coste>\n" +
                        "    <estado/>" + visitaGuiada.getEstado() + "</estado>\n" +
                        "    <lugar__id>" + visitaGuiada.getLugar() + "</lugar__id>\n" +
                        "    <empleado__dni>" + visitaGuiada.getEmpleado() + "</empleado__dni>\n" +
                        "    <horario>12</horario>\n" +
                        "    <clientes__dni>\n" +
                        "      <entry>\n" +
                        "        <string>DNI0</string>\n" +
                        "        <string>DNI0</string>\n" +
                        "      </entry>\n" +
                        "    </clientes__dni>\n" +
                        "  </VisitaGuiada>");
                if (visitaGuiada.getClientes().size() == 0) {
                    visitaGuiada.setClientes("vacio");
                } else if (visitaGuiada.getClientes().size() >= 2) {
                    visitaGuiada.borrar_cliente("vacio");
                }
                for (String vis : visitaGuiada.getClientes().values()) {
                    nuevavis = nuevavis + "<string>" + vis + "</string>" +
                            "<string>" + vis + "</string>";
                }
                nuevavis = nuevavis + "</entry>" +
                        "</clientes__dni>" +
                        "</VisitaGuiada>";

                try {
                    XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
                    System.out.printf("Inserto: %s \n", nuevavis);
                    //Consulta para insertar --> update insert ... into
                    ResourceSet result = servicio.query("update insert " + nuevavis + " into /VisitasGuiadas");
                    col.close(); //borramos
                    System.out.println("VisitaGuiada insertado.");
                } catch (Exception e) {
                    System.out.println("Error al insertar la visita.");
                    e.printStackTrace();
                }
            } else {
                System.out.println("Error en la BBDD");
            }
        } else {
            System.out.println("Existe la visitaGuiada");
        }
    }

    /**
     * recuperar_visitas_guiadas se encarga de recuperar las visitas de la BBDD y gestionarlas en los objetos
     *
     * @return
     */
    public static HashMap<Integer, VisitaGuiada> recuperar_visitas_guiadas() {
        HashMap<Integer, VisitaGuiada> visitaguiadas = new HashMap<>();
        conexion();
        if (col != null) {
            try {
                FileWriter ficheroWriter = new FileWriter(pathVisitas);

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
                    ficheroWriter.write((String) r.getContent());
                }
                ficheroWriter.close();
                col.close();
                XStream xstream = new XStream();
                xstream.addPermission(AnyTypePermission.ANY);
                xstream.alias("VisitasGuiadas", ListaVisitaGuiada.class);
                xstream.alias("VisitaGuiada", VisitaGuiada.class);
                xstream.addImplicitCollection(ListaVisitaGuiada.class, "lista");
                FileInputStream fichero = new FileInputStream(pathVisitas);
                BufferedReader br = new BufferedReader(new FileReader(pathVisitas));
                if (br.readLine() != null) {
                    ListaVisitaGuiada listadoTodas = (ListaVisitaGuiada) xstream.fromXML(fichero);
                    List<VisitaGuiada> listaPersonas;
                    listaPersonas = listadoTodas.getVisitaGuiadas();
                    if (listaPersonas != null) {
                        for (VisitaGuiada p : listaPersonas) {
                            if (p.getClientes().containsKey("vacio")) {
                                p.borrar_cliente("vacio");
                            }
                            visitaguiadas.put(p.getN_visita(), p);
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("Error en el fichero");
            } catch (IOException e) {
                System.out.println("Error en la lectura");
            } catch (XMLDBException e) {
                System.out.println("Error con el XML");
            }
        } else {
            System.out.println("Error en la BBDD");
        }
        return visitaguiadas;
    }

    /**
     * comprobarVisita se encarga de buscar una visita en la BBDD y retorna un bool dependendiendo el resultado
     *
     * @param id
     * @return
     */
    public static boolean comprobarVisita(int id) {
        //Devuelve true si el dep existe
        conexion();
        if (col != null) {
            try {
                XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
                //Consulta para consultar la información de un departamento
                ResourceSet result = servicio.query("/VisitasGuiadas/VisitasGuiada[n__visita='" + id + "']");
                ResourceIterator i;
                i = result.getIterator();
                col.close();
                if (!i.hasMoreResources()) {
                    return false;
                } else {
                    return true;
                }
            } catch (Exception e) {
                System.out.println("Error al consultar.");
                // e.printStackTrace();
            }
        } else {
            System.out.println("Error en la conexión. Comprueba datos.");
        }

        return false;

    }

    /**
     * modificarVista se encarga de modificar una vista
     *
     * @param visita
     */
    public static void modificarVisita(VisitaGuiada visita) {
        if (!comprobarVisita(visita.getN_visita())) {
            conexion();
            if (col != null) {
                try {
                    System.out.printf("Actualizo la visita: %s\n", visita.getN_visita());
                    XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
                    //Consulta para modificar/actualizar un valor --> update value
                    ResourceSet result = servicio.query(
                            "update value /VisitasGuiadas/VisitaGuiada[n__visita='" + visita.getN_visita() + "']/nombre with data('" + visita.getNombre() + "') ");
                    result = servicio.query(
                            "update value /VisitasGuiadas/VisitaGuiada[n__visita='" + visita.getN_visita() + "']/n__max__cli with data('" + visita.getN_max_cli() + "') ");
                    result = servicio.query(
                            "update value /VisitasGuiadas/VisitaGuiada[n__visita='" + visita.getN_visita() + "']/punto__partida with data('" + visita.getPunto_partida() + "') ");
                    result = servicio.query(
                            "update value /VisitasGuiadas/VisitaGuiada[n__visita='" + visita.getN_visita() + "']/curso with data('" + visita.getCurso() + "') ");
                    result = servicio.query(
                            "update value /VisitasGuiadas/VisitaGuiada[n__visita='" + visita.getN_visita() + "']/tematica with data('" + visita.getTematica() + "') ");
                    result = servicio.query(
                            "update value /VisitasGuiadas/VisitaGuiada[n__visita='" + visita.getN_visita() + "']/coste with data('" + visita.getCoste() + "') ");
                    result = servicio.query(
                            "update value /VisitasGuiadas/VisitaGuiada[n__visita='" + visita.getN_visita() + "']/lugar__id with data('" + visita.getLugar() + "') ");
                    result = servicio.query(
                            "update value /VisitasGuiadas/VisitaGuiada[n__visita='" + visita.getN_visita() + "']/horario with data('" + visita.getHorario() + "') ");

                    col.close();
                    System.out.println("Visita actualizada.");
                } catch (Exception e) {
                    System.out.println("Error al actualizar.");
                    e.printStackTrace();
                }
            } else {
                System.out.println("Error en la conexión. Comprueba datos.");
            }
        } else {
            System.out.println("La visita NO EXISTE.");
        }
    }

    /**
     * recuperar_empleados se encarga de recuperar los datos de los empleados y gestionarlos enn los objetos
     *
     * @return
     */
    public static HashMap<String, Empleado> recuperar_empleados() {
        HashMap<String, Empleado> empleados = new HashMap<>();
        try {
            FileWriter ficheroWriter = new FileWriter(pathEmpleados);

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
                ficheroWriter.write((String) r.getContent());
            }
            ficheroWriter.close();
            col.close();
            XStream xstream = new XStream();
            xstream.addPermission(AnyTypePermission.ANY);
            xstream.alias("Empleados", ListaEmpleados.class);
            xstream.alias("Empleado", Empleado.class);
            xstream.addImplicitCollection(ListaEmpleados.class, "lista");
            FileInputStream fichero = new FileInputStream(pathEmpleados);
            BufferedReader br = new BufferedReader(new FileReader(pathEmpleados));
            if (br.readLine() != null) {
                ListaEmpleados listadoTodas = (ListaEmpleados) xstream.fromXML(fichero);
                List<Empleado> listaPersonas;
                listaPersonas = listadoTodas.getEmpleados();
                if (listaPersonas != null) {
                    for (Empleado p : listaPersonas) {
                        if (p.getVisitas().containsKey(mockId)) {
                            p.borrar_visita(mockId);
                        }
                        empleados.put(p.getDni(), p);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error en el fichero");
        } catch (IOException e) {
            System.out.println("Error en la lectura");
        } catch (XMLDBException e) {
            System.out.println("Error con el XML");
        }
        return empleados;
    }

    /**
     * insertarEmpleado se encarga de insertar un empleado en la BBDD
     *
     * @param empleado
     */
    public static void insertarEmpleado(Empleado empleado) {
        if (!comprobarEmpleado(empleado.getDni())) {
            conexion();
            if (col != null) {
                String nuevoempl = "<Empleado><dni>" + empleado.getDni() + "</dni>"
                        + "<nombre>" + empleado.getNombre() + "</nombre>" +
                        "<apellido>" + empleado.getApellido() + "</apellido>"
                        + "<fecha__Nac>" + empleado.getFecha_Nac() + "</fecha__Nac>" +
                        "<fecha__cont>" + empleado.getFecha_cont() + "</fecha__cont>" +
                        "<nacionalidad>" + empleado.getNacionalidad() + "</nacionalidad>" +
                        "<cargo>" + empleado.getCargo() + "</cargo>" +
                        "<estado>" + empleado.getNacionalidad() + "</estado>" +
                        "<visitas__numero>" + "<entry>";
                if (empleado.getVisitas().size() == 0) {
                    empleado.setVisitas(mockId);
                } else if (empleado.getVisitas().size() >= 2) {
                    empleado.borrar_visita(mockId);
                }
                for (Integer cli : empleado.getVisitas().values()) {
                    nuevoempl = nuevoempl + "<int>" + cli + "</int>" +
                            "<int>" + cli + "</int>";
                }
                nuevoempl = nuevoempl + "</entry>" +
                        "</visitas__numero>" +
                        "</Empleado>";

                try {
                    XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
                    System.out.printf("Inserto: %s \n", nuevoempl);
                    //Consulta para insertar --> update insert ... into
                    ResourceSet result = servicio.query("update insert " + nuevoempl + " into /Empleados");
                    col.close(); //borramos
                    System.out.println("Empleado insertado.");
                } catch (Exception e) {
                    System.out.println("Error al insertar empleado.");
                    e.printStackTrace();
                }
            } else {
                System.out.println("Error en la BBDD");
            }
        } else {
            System.out.println("El empleado ya existe");
        }
    }

    /**
     * comprobarEmpleado recibiendo un DNI comprueba si el empleado existe, retorna un bool dependiendo el resultado
     *
     * @param DNI
     * @return
     */
    public static boolean comprobarEmpleado(String DNI) {
        //Devuelve true si el dep existe
        conexion();
        if (col != null) {
            try {
                XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
                //Consulta para consultar la información de un departamento
                ResourceSet result = servicio.query("/Empleados/Empleado[dni='" + DNI + "']");
                ResourceIterator i;
                i = result.getIterator();
                col.close();
                if (!i.hasMoreResources()) {
                    return false;
                } else {
                    return true;
                }
            } catch (Exception e) {
                System.out.println("Error al consultar.");
                // e.printStackTrace();
            }
        } else {
            System.out.println("Error en la conexión. Comprueba datos.");
        }

        return false;

    }

    /**
     * modificarEmpleado se encarga de modificar un empleado
     *
     * @param empleado
     */
    public static void modificarEmpleado(Empleado empleado) {
        if (!comprobarEmpleado(empleado.getDni())) {
            conexion();
            if (col != null) {
                try {
                    System.out.printf("Actualizo el empleado: %s\n", empleado.getDni());
                    XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
                    //Consulta para modificar/actualizar un valor --> update value
                    ResourceSet result = servicio.query(
                            "update value /Empleados/Empleado[dni='" + empleado.getDni() + "']/nombre with data('" + empleado.getNombre() + "') ");
                    result = servicio.query(
                            "update value /Empleados/Empleado[dni='" + empleado.getDni() + "']/apellido with data('" + empleado.getApellido() + "') ");
                    result = servicio.query(
                            "update value /Empleados/Empleado[dni='" + empleado.getDni() + "']/fecha__Nac with data('" + empleado.getFecha_Nac() + "') ");
                    result = servicio.query(
                            "update value /Empleados/Empleado[dni='" + empleado.getDni() + "']/fecha__cont with data('" + empleado.getFecha_cont() + "') ");
                    result = servicio.query(
                            "update value /Empleados/Empleado[dni='" + empleado.getDni() + "']/nacionalidad with data('" + empleado.getNacionalidad() + "') ");
                    result = servicio.query(
                            "update value /Empleados/Empleado[dni='" + empleado.getDni() + "']/cargo with data('" + empleado.getCargo() + "') ");

                    col.close();
                    System.out.println("Cliente actualizado.");
                } catch (Exception e) {
                    System.out.println("Error al actualizar.");
                    e.printStackTrace();
                }
            } else {
                System.out.println("Error en la conexión. Comprueba datos.");
            }
        } else {
            System.out.println("El cliente NO EXISTE.");
        }
    }

    /**
     * recuperar_lugar se encarga de recibir toda la coleccion para poder gestionarla en los objetos
     *
     * @return
     */
    public static HashMap<Integer, Lugar> recuperar_lugar() {
        conexion();
        HashMap<Integer, Lugar> lugares = new HashMap<>();
        if (col != null) {
            try {
                FileWriter ficheroWriter = new FileWriter(pathLugares);

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
                    ficheroWriter.write((String) r.getContent());
                }
                ficheroWriter.close();
                col.close();
                XStream xstream = new XStream();
                xstream.addPermission(AnyTypePermission.ANY);
                xstream.alias("Lugares", ListaLugares.class);
                xstream.alias("Lugar", Lugar.class);
                xstream.addImplicitCollection(ListaLugares.class, "lista");
                FileInputStream fichero = new FileInputStream(pathLugares);
                BufferedReader br = new BufferedReader(new FileReader(pathLugares));
                if (br.readLine() != null) {
                    ListaLugares listadoTodas = (ListaLugares) xstream.fromXML(fichero);
                    List<Lugar> listaPersonas;
                    listaPersonas = listadoTodas.getLugares();
                    if (listaPersonas != null) {
                        for (Lugar p : listaPersonas) {
                            if (p.getVisitas().containsKey(mockId)) {
                                p.borrar_visita(mockId);
                            }
                            lugares.put(p.getId(), p);
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("Error en el fichero");
            } catch (IOException e) {
                System.out.println("Error en la lectura");
            } catch (XMLDBException e) {
                System.out.println("Error con el XML");
            }
        } else {
            System.out.println("Error en la BBDD");
        }
        return lugares;
    }

    /**
     * insertarLugar recibiendo un lugar lo inserta en la BBDD
     *
     * @param lugar
     */
    public static void insertarLugar(Lugar lugar) {
        conexion();
        if (col != null) {
            if (!comprobarLugar(lugar.getId())) {
                String nuevolugar = "<Lugar><id>" + lugar.getId() + "</id>"
                        + "<lugar>" + lugar.getLugar() + "</lugar>" +
                        "<nacionalidad>" + lugar.getNacionalidad() + "</nacionalidad>" +
                        "<visitas__numero>" + "<entry>";
                if (lugar.getVisitas().size() == 0) {
                    lugar.setVisitas(mockId);
                } else if (lugar.getVisitas().size() >= 2) {
                    if (lugar.getVisitas().containsKey(mockId)) {
                        lugar.borrar_visita(mockId);
                    }
                }
                for (Integer cli : lugar.getVisitas().values()) {
                    nuevolugar = nuevolugar + "<int>" + cli + "</int>" +
                            "<int>" + cli + "</int>";
                }
                nuevolugar = nuevolugar + "</entry>" +
                        "</visitas__numero>" +
                        "</Lugar>";

                try {
                    XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
                    System.out.printf("Inserto: %s \n", nuevolugar);
                    //Consulta para insertar --> update insert ... into
                    ResourceSet result = servicio.query("update insert " + nuevolugar + " into /Lugares");
                    col.close(); //borramos
                    System.out.println("Lugar insertado.");
                } catch (Exception e) {
                    System.out.println("Error al insertar lugar.");
                    e.printStackTrace();
                }
            } else {
                System.out.println("Error en la BBDD");
            }
        } else {
            System.out.println("El lugar ya existe");
        }
    }

    /**
     * comprobarLugar recibiendo un id comprueba que el lugar el existe retorna un bool dependiendo la respuesta
     *
     * @param id
     * @return
     */
    public static boolean comprobarLugar(int id) {
        //Devuelve true si el dep existe
        conexion();
        if (col != null) {
            try {
                XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
                //Consulta para consultar la información de un departamento
                ResourceSet result = servicio.query("/Lugares/Lugar[id='" + id + "']");
                ResourceIterator i;
                i = result.getIterator();
                col.close();
                if (!i.hasMoreResources()) {
                    return false;
                } else {
                    return true;
                }
            } catch (Exception e) {
                System.out.println("Error al consultar.");
                // e.printStackTrace();
            }
        } else {
            System.out.println("Error en la conexión. Comprueba datos.");
        }

        return false;

    }

    /**
     * comprobarFicheroExist se encarga de verificar que la BBDD tiene los ficheros necesarios y si no los tiene los crea y sube
     */
    public static void comprobarFicherosExist() {
        conexion();
        if (col != null) {
            try {
                if (col.getResourceCount() == 0) {
                    HashMap<String, Cliente> clientes = new HashMap<>();
                    HashMap<String, Empleado> empleados = new HashMap<>();
                    HashMap<Integer, VisitaGuiada> visitasguiadas = new HashMap<>();
                    HashMap<Integer, Lugar> lugares = new HashMap<>();

                    XMLResource res = null;
                    File f = null;

                    XStream xstream = new XStream();
                    xstream.alias("VisitasGuiadas", ListaVisitaGuiada.class);
                    xstream.alias("VisitaGuiada", VisitaGuiada.class);
                    xstream.addImplicitCollection(ListaVisitaGuiada.class, "lista");
                    ListaVisitaGuiada listaVisitaGuiada = new ListaVisitaGuiada();
                    listaVisitaGuiada.lista = new ArrayList<>(visitasguiadas.values());
                    xstream.toXML(listaVisitaGuiada, new FileOutputStream(pathVisitas));

                    res = (XMLResource) col.createResource("VisitasGuiadas.xml", "XMLResource");
                    f = new File(pathVisitas);
                    res.setContent(f);
                    col.storeResource(res);

                    xstream = new XStream();
                    xstream.alias("Clientes", ListaClientes.class);
                    xstream.alias("Cliente", Cliente.class);
                    xstream.addImplicitCollection(ListaClientes.class, "lista");
                    ListaClientes listaClientes = new ListaClientes();
                    listaClientes.lista = new ArrayList<>(clientes.values());
                    xstream.toXML(listaClientes, new FileOutputStream(pathClientes));

                    res = (XMLResource) col.createResource("Clientes.xml", "XMLResource");
                    f = new File(pathClientes);
                    res.setContent(f);
                    col.storeResource(res);

                    xstream = new XStream();
                    xstream.alias("Empleados", ListaEmpleados.class);
                    xstream.alias("Empleado", Empleado.class);
                    xstream.addImplicitCollection(ListaEmpleados.class, "lista");
                    ListaEmpleados listaEmpleados = new ListaEmpleados();
                    listaEmpleados.lista = new ArrayList<>(empleados.values());
                    xstream.toXML(listaEmpleados, new FileOutputStream(pathEmpleados));

                    res = (XMLResource) col.createResource("Empleados.xml", "XMLResource");
                    f = new File(pathEmpleados);
                    res.setContent(f);
                    col.storeResource(res);

                    xstream = new XStream();
                    xstream.alias("Lugares", ListaLugares.class);
                    xstream.alias("Lugar", Lugar.class);
                    xstream.addImplicitCollection(ListaLugares.class, "lista");
                    ListaLugares listaLugares = new ListaLugares();
                    listaLugares.lista = new ArrayList<>(lugares.values());
                    xstream.toXML(listaLugares, new FileOutputStream(pathLugares));

                    res = (XMLResource) col.createResource("Lugares.xml", "XMLResource");
                    f = new File(pathLugares);
                    res.setContent(f);
                    col.storeResource(res);


                    // Listamos la colección para ver que en efecto se ha añadido
                    for (String colRe : col.listResources())
                        System.out.println(colRe);

                    col.close();
                }

            } catch (FileNotFoundException e) {
                System.out.println("Archivo no encontrado");
            } catch (XMLDBException e) {
                System.out.println("Error con el XML");
            }
        }
    }

    /**
     * borrarCliente recibiendo el DNI del cliente se encarga aplicar el estado borrado al cliente
     *
     * @param DNI
     */
    public static void borrarCliente(String DNI) {
        if (comprobarCliente(DNI)) {
            conexion();
            if (col != null) {
                try {
                    System.out.printf("Borrando el cliente: %s\n", DNI);
                    XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
                    //Consulta para modificar/actualizar un valor --> update value
                    ResourceSet result = servicio.query(
                            "update value /Clientes/Cliente[dni='" + DNI + "']/estado with data('Borrado') ");

                    col.close();
                    System.out.println("Cliente borrado.");
                } catch (Exception e) {
                    System.out.println("Error al actualizar.");
                    e.printStackTrace();
                }
            } else {
                System.out.println("Error en la conexión. Comprueba datos.");
            }
        } else {
            System.out.println("El Cliente NO EXISTE.");
        }
    }

    /**
     * borrarEmpleado recibiendo el DNI del empleado se encarga aplicar el estado borrado al empleado
     *
     * @param DNI
     */
    public static void borrarEmpleado(String DNI) {
        if (comprobarEmpleado(DNI)) {
            conexion();
            if (col != null) {
                try {
                    System.out.printf("Borrando el empleado: %s\n", DNI);
                    XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
                    //Consulta para modificar/actualizar un valor --> update value
                    ResourceSet result = servicio.query(
                            "update value /Empleados/Empleado[dni='" + DNI + "']/estado with data('Borrado') ");

                    col.close();
                    System.out.println("Empleado borrado.");
                } catch (Exception e) {
                    System.out.println("Error al actualizar.");
                    e.printStackTrace();
                }
            } else {
                System.out.println("Error en la conexión. Comprueba datos.");
            }
        } else {
            System.out.println("El Empleado NO EXISTE.");
        }
    }

    /**
     * borrarVista recibiendo el numero de la vista se encarga aplicar el estado borrado a la vista
     *
     * @param numero
     */
    public static void borrarVisita(int numero) {
        if (comprobarVisita(numero)) {
            conexion();
            if (col != null) {
                try {
                    System.out.printf("Borrando la visita: %s\n", numero);
                    XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
                    //Consulta para modificar/actualizar un valor --> update value
                    ResourceSet result = servicio.query(
                            "update value /VisitasGuiadas/VisitaGuiada[n__visita='" + numero + "']/estado with data('Borrado') ");

                    col.close();
                    System.out.println("Visita borrada.");
                } catch (Exception e) {
                    System.out.println("Error al actualizar.");
                    e.printStackTrace();
                }
            } else {
                System.out.println("Error en la conexión. Comprueba datos.");
            }
        } else {
            System.out.println("La visita NO EXISTE.");
        }
    }

    /**
     * modificarRelacionLugar se encarga de borrar un lugar usando su id, al borrarlo luego se puede insertar con las relaciones
     *
     * @param lugarId
     */
    public static void modificarRelacionLugar(int lugarId) {
        conexion();
        if (col != null) {
            try {
                XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
                //Consulta para borrar un departamento --> update delete
                ResourceSet result = servicio.query(
                        "update delete /Lugares/Lugar[id=" + lugarId + "]");
                col.close();
            } catch (Exception e) {
                System.out.println("Error al mmodificar.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Error en la conexión. Comprueba datos.");
        }
    }

    /**
     * modificarRelacionVisita se encarga de borrar una visita usando su id, al borrarlo luego se puede insertar con las relaciones
     *
     * @param visitas
     */
    public static void modificarRelacionVisitas(int visitas) {
        conexion();
        if (col != null) {
            try {
                XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
                //Consulta para borrar un departamento --> update delete
                ResourceSet result = servicio.query(
                        "update delete /VisitasGuiadas/VisitaGuiada[n__visita=" + visitas + "]");
                col.close();
            } catch (Exception e) {
                System.out.println("Error al mmodificar.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Error en la conexión. Comprueba datos.");
        }
    }

    /**
     * modificarRelacionEmpleado se encarga de borrar un empleado usando su dni, al borrarlo luego se puede insertar con las relaciones
     *
     * @param dni
     */
    public static void modificarRelacionEmpleado(String dni) {
        conexion();
        if (col != null) {
            try {
                XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
                //Consulta para borrar un departamento --> update delete
                ResourceSet result = servicio.query(
                        "update delete /Empleados/Empleado[dni='" + dni + "']");
                col.close();
            } catch (Exception e) {
                System.out.println("Error al mmodificar.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Error en la conexión. Comprueba datos.");
        }
    }

    /**
     * modificarRelacionCliente se encarga de borrar un cliente usando su dni, al borrarlo luego se puede insertar con las relaciones
     *
     * @param dni
     */
    public static void modificarRelacionClientes(String dni) {
        conexion();
        if (col != null) {
            try {
                XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
                //Consulta para borrar un departamento --> update delete
                ResourceSet result = servicio.query(
                        "update delete /Clientes/Cliente[dni='" + dni + "']");
                col.close();
            } catch (Exception e) {
                System.out.println("Error al mmodificar.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Error en la conexión. Comprueba datos.");
        }
    }

    /**
     * conexion se encarga de realizar la conexión con exist y bajar la colección
     */
    public static void conexion() {
        String driver = "org.exist.xmldb.DatabaseImpl"; //Driver para eXist

        try {
            Class cl = Class.forName(driver); //Cargar del driver
            Database database = (Database) cl.getDeclaredConstructor().newInstance(); //Instancia de la BD
            DatabaseManager.registerDatabase(database);
            col = DatabaseManager.getCollection(URI, user, pass);
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
}



