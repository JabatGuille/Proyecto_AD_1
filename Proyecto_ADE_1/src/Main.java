import java.util.HashMap;
import java.util.Scanner;

import Objetos.Cliente;
import Objetos.Empleado;
import Objetos.VisitaGuiada;

public class Main {
    static HashMap<String, Cliente> clientes = new HashMap<>();
    static HashMap<String, Empleado> empleados = new HashMap<>();
    static HashMap<Integer, VisitaGuiada> visitasguiadas = new HashMap<>();

    public static void main(String[] args) {


        boolean bucle = true;
        while (bucle) {
            System.out.println("Escriba un numero para la opcion del menu:\n " +
                    "1: Sección Visitas guiadas.\n" +
                    "2: Sección Clientes.\n" +
                    "3: Sección Empleados.");
            Scanner scanner = new Scanner(System.in);
            String menu = scanner.next();
            switch (menu) {
                case "1": {
                    menu_visita_guiada();
                    break;
                }
                case "2": {
                    menu_cliente();
                    break;
                }
                case "3": {
                    menu_empleado();
                    break;
                }
                case "4": {
                    System.out.println("Saliendo de la aplicación");
                    bucle = false;
                    break;
                }
                default: {
                    System.out.println("Opcion no disponible en el menu");
                    break;
                }
            }
        }


    }

    public static void menu_visita_guiada() {
        boolean bucle = true;
        while (bucle) {
            System.out.println("Escriba un numero para la opcion del menu:\n " +
                    "1: Listar visitas guiadas\n" +
                    "2: Nueva visita guiada.\n" +
                    "3: Modificar visita guiada.\n" +
                    "4: Borrar visita guiada.\n" +
                    "5: Salir");
            Scanner scanner = new Scanner(System.in);
            String menu = scanner.next();
            switch (menu) {
                case "1": {
                    listar_visitas_guiadas();
                    bucle = false;
                    break;
                }
                case "2": {
                    nueva_visita_guiada();
                    bucle = false;
                    break;
                }
                case "3": {
                    modificar_visita_guiada();
                    bucle = false;
                    break;
                }
                case "4": {
                    borrar_visita_guiada();
                    bucle = false;
                    break;
                }
                case "5": {
                    System.out.println("Saliendo del menu visitas guiadas");
                    bucle = false;
                    break;
                }
                default: {
                    System.out.println("Opcion no disponible en el menu");
                    break;
                }
            }
        }

    }

    public static void menu_cliente() {
        boolean bucle = true;
        while (bucle) {
            System.out.println("Escriba un numero para la opcion del menu:\n " +
                    "1: Nuevo cliente.\n" +
                    "2: Modificar cliente.\n" +
                    "3: Borrar cliente.\n" +
                    "4: Salir");
            Scanner scanner = new Scanner(System.in);
            String menu = scanner.next();
            switch (menu) {
                case "1": {
                    nuevo_cliente();
                    bucle = false;
                    break;
                }
                case "2": {
                    modificar_cliente();
                    bucle = false;
                    break;
                }
                case "3": {
                    borrar_cliente();
                    bucle = false;
                    break;
                }
                case "4": {
                    System.out.println("Saliendo del menu clientes");
                    bucle = false;
                    break;
                }
                default: {
                    System.out.println("Opcion no disponible en el menu");
                    break;
                }
            }
        }
    }

    public static void menu_empleado() {
        boolean bucle = true;
        while (bucle) {
            System.out.println("Escriba un numero para la opcion del menu:\n " +
                    "1: Nuevo empleado.\n" +
                    "2: Modificar empleado.\n" +
                    "3: Borrar empleado.\n" +
                    "4: Salir");
            Scanner scanner = new Scanner(System.in);
            String menu = scanner.next();
            switch (menu) {
                case "1": {
                    nuevo_empleado();
                    bucle = false;
                    break;
                }
                case "2": {
                    modificar_empleado();
                    bucle = false;
                    break;
                }
                case "3": {
                    borrar_empleado();
                    bucle = false;
                    break;
                }
                case "4": {
                    System.out.println("Saliendo del menu empelados");
                    bucle = false;
                    break;
                }
                default: {
                    System.out.println("Opcion no disponible en el menu");
                    break;
                }
            }
        }
    }


    public static void listar_visitas_guiadas() {
        for (VisitaGuiada visita : visitasguiadas.values()) {
            System.out.println("VISITA:");
            System.out.println("Nº Visita: " + visita.getN_visita());
            System.out.println("Nombre: " + visita.getNombre());
            System.out.println("Nº maximo de clientes: " + visita.getN_max_cli());
            System.out.println("Punto de partida: " + visita.getPunto_partida());
            System.out.println("Curso: " + visita.getCurso());
            System.out.println("Tematica: " + visita.getTematica());
            System.out.println("Coste: " + visita.getCoste());
            System.out.println("Lugar de la visita:");
            System.out.println("Nº Visita: " + visita.getLugar().getLugar());
            System.out.println("Nº Visita: " + visita.getLugar().getNacionalidad());

            for (Cliente cliente : visita.getClientes()) {
                System.out.println("Cliente de la visita: " + visita.getN_visita());
                System.out.println("DNI: " + cliente.getDni());
                System.out.println("Nombre: " + cliente.getNombre());
                System.out.println("Apellido: " + cliente.getApellido());
                System.out.println("Edad: " + cliente.getEdad());
                System.out.println("Profesión: " + cliente.getProfesion());

            }

        }

    }

    public static void nuevo_empleado() {
    }

    public static void nueva_visita_guiada() {
    }

    public static void nuevo_cliente() {
    }

    public static void borrar_visita_guiada() {

        for (VisitaGuiada visita : visitasguiadas.values()) {
            System.out.println("VISITA:");
            System.out.println("Nº Visita: " + visita.getN_visita());
            System.out.println("Nombre: " + visita.getNombre());
            System.out.println("Nº maximo de clientes: " + visita.getN_max_cli());
            System.out.println("Punto de partida: " + visita.getPunto_partida());
            System.out.println("Curso: " + visita.getCurso());
            System.out.println("Tematica: " + visita.getTematica());
            System.out.println("Coste: " + visita.getCoste());
            System.out.println("Lugar de la visita:");
            System.out.println("Nº Visita: " + visita.getLugar().getLugar());
            System.out.println("Nº Visita: " + visita.getLugar().getNacionalidad());
        }
        System.out.println("Escriba el Nº de visita que quiere borrar");
        Scanner scanner = new Scanner(System.in);
        try {
            int numero = scanner.nextInt();
            if (visitasguiadas.containsKey(numero)) {
                visitasguiadas.get(numero).setEstado("Borrada");
            } else {
                System.out.println("Ese numero no esta en la lista de visitas guiadas");
            }
        } catch (NumberFormatException e) {
            System.out.println("Debes escribir un numero");
        }
    }

    public static void borrar_empleado() {
        for (Empleado empleado : empleados.values()) {
            System.out.println("EMPLEADO: ");
            System.out.println("DNI: " + empleado.getDni());
            System.out.println("Nombre: " + empleado.getNombre());
            System.out.println("Apellido: " + empleado.getApellido());
            System.out.println("Fecha nacimiento: " + empleado.getFecha_Nac());
            System.out.println("Fecha contratación: " + empleado.getFecha_cont());
            System.out.println("Nacionalidad: " + empleado.getNacionalidad());
            System.out.println("Cargo: " + empleado.getCargo());
        }
        System.out.println("Escriba el DNI del empleado que quiere borrar");
        Scanner scanner = new Scanner(System.in);
        String dni = scanner.next();
        if (empleados.containsKey(dni)) {
            empleados.get(dni).setEstado("Borrado");
        } else {
            System.out.println("El DNI escrito no esta en la lista");
        }
    }

    public static void borrar_cliente() {
        for (Cliente cliente : clientes.values()) {
            System.out.println("CLIENTE: ");
            System.out.println("DNI: " + cliente.getDni());
            System.out.println("Nombre: " + cliente.getNombre());
            System.out.println("Apellido: " + cliente.getApellido());
            System.out.println("Edad: " + cliente.getEdad());
            System.out.println("Profesión: " + cliente.getProfesion());
        }
        System.out.println("Escriba el DNI del cliente que quiere borrar");
        Scanner scanner = new Scanner(System.in);
        String dni = scanner.next();
        if (clientes.containsKey(dni)) {
            clientes.get(dni).setEstado("Borrado");
        } else {
            System.out.println("El DNI escrito no esta en la lista");
        }
    }

    public static void modificar_cliente() {
    }

    public static void modificar_visita_guiada() {
    }

    public static void modificar_empleado() {
    }

    public static void mostar_metadatos() {
    }

    public static void visualizar_datos_agencia() {
    }
}