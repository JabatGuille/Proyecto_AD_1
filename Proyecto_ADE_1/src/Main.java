import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Boolean bucle = true;
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
        Boolean bucle = true;
        while (bucle) {
            System.out.println("Escriba un numero para la opcion del menu:\n " +
                    "1: Nueva visita guiada.\n" +
                    "2: Modificar visita guiada.\n" +
                    "3: Borrar visita guiada.\n" +
                    "4: Salir");
            Scanner scanner = new Scanner(System.in);
            String menu = scanner.next();
            switch (menu) {
                case "1": {
                    nueva_visita_guiada();
                    bucle = false;
                    break;
                }
                case "2": {
                    modificar_visita_guiada();
                    bucle = false;
                    break;
                }
                case "3": {
                    borrar_visita_guiada();
                    bucle = false;
                    break;
                }
                case "4": {
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
        Boolean bucle = true;
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
        Boolean bucle = true;
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

    public static void visualizar_datos_agencia() {
    }

    public static void listar_visitas_guiadas() {
    }

    public static void nuevo_empleado() {
    }

    public static void nueva_visita_guiada() {
    }

    public static void nuevo_cliente() {
    }

    public static void borrar_visita_guiada() {
    }

    public static void borrar_empleado() {
    }

    public static void borrar_cliente() {
    }

    public static void modificar_cliente() {
    }

    public static void modificar_visita_guiada() {
    }

    public static void modificar_empleado() {
    }

    public static void mostar_metadatos() {
    }
}