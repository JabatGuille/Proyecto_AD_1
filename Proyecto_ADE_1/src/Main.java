import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Objetos.Cliente;
import Objetos.Empleado;
import Objetos.Lugar;
import Objetos.VisitaGuiada;

public class Main {
    static HashMap<String, Cliente> clientes = new HashMap<>();
    static HashMap<String, Empleado> empleados = new HashMap<>();
    static HashMap<Integer, VisitaGuiada> visitasguiadas = new HashMap<>();
    static HashMap<Integer, Lugar> lugares = new HashMap<>();

    public static void main(String[] args) {
        boolean bucle = true;
        String menu;
        Scanner scanner = new Scanner(System.in);
        while (bucle) {
            System.out.println("Escriba un numero para la opcion del menu:\n" +
                    "1: Sección Visitas guiadas.\n" +
                    "2: Sección Clientes.\n" +
                    "3: Sección Empleados.\n" +
                    "4: Salir");
            menu = scanner.next();
            switch (menu) {
                case "1": {
                    menu_visita_guiada(scanner);
                    break;
                }
                case "2": {
                    menu_cliente(scanner);
                    break;
                }
                case "3": {
                    menu_empleado(scanner);
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
        scanner.close();
    }

    public static void menu_visita_guiada(Scanner scanner) {
        boolean bucle = true;
        while (bucle) {
            System.out.println("Escriba un numero para la opcion del menu:\n" +
                    "1: Listar visitas guiadas\n" +
                    "2: Nueva visita guiada.\n" +
                    "3: Modificar visita guiada.\n" +
                    "4: Borrar visita guiada.\n" +
                    "5: Añadir clientes y empleado a la visita guiada" +
                    "6: Salir");
            String menu = scanner.next();
            switch (menu) {
                case "1": {
                    listar_visitas_guiadas();
                    bucle = false;
                    break;
                }
                case "2": {
                    nueva_visita_guiada(scanner);
                    bucle = false;
                    break;
                }
                case "3": {
                    modificar_visita_guiada(scanner);
                    bucle = false;
                    break;
                }
                case "4": {
                    borrar_visita_guiada(scanner);
                    bucle = false;
                    break;
                }
                case "5": {
                    añadir_cliente_emple_visitaguiada(scanner);
                    bucle = false;
                    break;
                }
                case "6": {
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

    public static void añadir_cliente_emple_visitaguiada(Scanner scanner) {
    }

    public static void menu_cliente(Scanner scanner) {
        boolean bucle = true;
        while (bucle) {
            System.out.println("Escriba un numero para la opcion del menu:\n" +
                    "1: Nuevo cliente.\n" +
                    "2: Modificar cliente.\n" +
                    "3: Borrar cliente.\n" +
                    "4: Salir");
            String menu = scanner.next();
            switch (menu) {
                case "1": {
                    nuevo_cliente(scanner);
                    bucle = false;
                    break;
                }
                case "2": {
                    modificar_cliente(scanner);
                    bucle = false;
                    break;
                }
                case "3": {
                    borrar_cliente(scanner);
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

    public static void menu_empleado(Scanner scanner) {
        boolean bucle = true;
        while (bucle) {
            System.out.println("Escriba un numero para la opcion del menu:\n" +
                    "1: Nuevo empleado.\n" +
                    "2: Modificar empleado.\n" +
                    "3: Borrar empleado.\n" +
                    "4: Salir");
            String menu = scanner.next();
            switch (menu) {
                case "1": {
                    nuevo_empleado(scanner);
                    bucle = false;
                    break;
                }
                case "2": {
                    modificar_empleado(scanner);
                    bucle = false;
                    break;
                }
                case "3": {
                    borrar_empleado(scanner);
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

    public static void nueva_visita_guiada(Scanner scanner) {
        boolean bucle = true;
        String nombre = "";
        int n_max_cli = 0;
        String punto_partida = "";
        String curso = "";
        String tematica = "";
        Double coste = 0.0;
        int lugarid = 0;
        bucle = true;
        while (bucle) {
            System.out.println("Escriba el Nombre de la visita");
            nombre = scanner.next();
            if (!nombre.equals("")) {
                bucle = false;
            } else {
                System.out.println("Nombre no puede ser vacio");
            }
        }
        bucle = true;
        while (bucle) {
            System.out.println("Escriba el numero maximo de clientes");
            try {
                n_max_cli = scanner.nextInt();
                if (n_max_cli > 0) {
                    bucle = false;
                } else {
                    System.out.println("El numero maximo de clientes debe ser mayor de 0");
                }
            } catch (NumberFormatException e) {
                System.out.println("Debes escribir un numero");
            }

        }
        bucle = true;
        while (bucle) {
            System.out.println("Escriba el punto de partida");
            punto_partida = scanner.next();
            if (!punto_partida.equals("")) {
                bucle = false;
            } else {
                System.out.println("El punto de partida no puede estar vacio");
            }
        }
        bucle = true;
        while (bucle) {
            System.out.println("Escriba el curso");
            curso = scanner.next();
            if (!curso.equals("")) {
                bucle = false;
            } else {
                System.out.println("Curso no puede ser vacio");
            }
        }
        bucle = true;
        while (bucle) {
            System.out.println("Escriba la tematica");
            tematica = scanner.next();
            if (!tematica.equals("")) {
                bucle = false;
            } else {
                System.out.println("Tematica no puede ser vacio");
            }
        }
        bucle = true;
        while (bucle) {
            System.out.println("Escriba el coste");
            try {
                coste = scanner.nextDouble();
                if (coste > 0) {
                    bucle = false;
                } else {
                    System.out.println("El coste debe ser mayor de 0");
                }
            } catch (NumberFormatException e) {
                System.out.println("Debes escribir un numero");
            }

        }
        lugarid = crear_lugar(scanner);
        System.out.println("Datos de la nueva visita guiada: \n" +
                "Nombre: " + nombre + "\n" +
                "Numero maximo de clientes: " + n_max_cli + "\n" +
                "Punto de partida: " + punto_partida + "\n" +
                "Curso: " + curso + "\n" +
                "Tematica: " + tematica + "\n" +
                "Coste: " + coste + "\n"
        );
        System.out.println("Lugar\n" +
                "Lugar: " + lugares.get(lugarid) + "\n" +
                "Nacionalidad: " + lugares.get(lugarid));
        System.out.println("Es correcto? y/N");
        String comprobación = scanner.next();
        if (comprobación.toLowerCase().equals("y")) {
            System.out.println("Creando visita guiada");
            visitasguiadas.put(visitasguiadas.size(), new VisitaGuiada(nombre, n_max_cli, punto_partida, curso, tematica, coste, lugares.get(lugarid)));
        } else {
            System.out.println("Cancelando operación, redirigiendo al menu");
        }
    }

    public static int crear_lugar(Scanner scanner) {
        boolean bucle = true;
        int id = 0;
        while (bucle) {
            if (lugares.size() == 0) {
                System.out.println("No hay lugares, debe crear el lugar");
                String nlugar = scanner.next();
                if (!nlugar.equals("")) {
                    System.out.println("Escriba la nacionalidad del lugar");
                    String nacionalidad = scanner.next();
                    if (!nacionalidad.equals("")) {
                        id = lugares.size();
                        lugares.put(id, new Lugar(id, nlugar, nacionalidad));
                        bucle = false;
                        return id;
                    } else {
                        System.out.println("Nacionalidad no puede estar vacio");
                    }
                } else {
                    System.out.println("No puedes poner un lugar vacio");
                }
            }
        }
        return id;
    }

    public static void borrar_visita_guiada(Scanner scanner) {

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

    public static void nuevo_empleado(Scanner scanner) {
        boolean bucle = true;
        String DNI = "";
        String nombre = "";
        String apellido = "";
        String fecha_nac = "";
        String fecha_cont = "";
        String nacionalidad = "";
        String cargo = "";
        while (bucle) {
            System.out.println("Escriba el DNI del empleado");
            DNI = scanner.next();
            Pattern pat = Pattern.compile("[0-9]{7,8}[A-Z a-z]");
            Matcher mat = pat.matcher(DNI);
            if (mat.matches()) {
                bucle = false;
            } else {
                System.out.println("DNI: " + DNI + " incorrecto");
            }
        }
        bucle = true;
        while (bucle) {
            System.out.println("Escriba el Nombre del empleado");
            nombre = scanner.next();
            if (!nombre.equals("")) {
                bucle = false;
            } else {
                System.out.println("Nombre no puede ser vacio");
            }
        }
        bucle = true;
        while (bucle) {
            System.out.println("Escriba el Apellido del empleado");
            apellido = scanner.next();
            if (!apellido.equals("")) {
                bucle = false;
            } else {
                System.out.println("Apellido no puede ser vacio");
            }
        }
        bucle = true;
        while (bucle) {
            System.out.println("Escriba la fecha de nacimiento del empleado");
            fecha_nac = scanner.next();
            Pattern pat = Pattern.compile("^([0-2][0-9]||3[0-1])-(0[0-9]||1[0-2])-([0-9][0-9])?[0-9][0-9]$");
            Matcher mat = pat.matcher(fecha_nac);
            if (mat.matches()) {
                bucle = false;
            } else {
                System.out.println("Formato de fecha no permitido, formato permitido: dd-mm-yyyy");
            }
        }
        fecha_cont = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        bucle = true;
        while (bucle) {
            System.out.println("Escriba la nacionalidad del empleado");
            nacionalidad = scanner.next();
            if (!nacionalidad.equals("")) {
                bucle = false;
            } else {
                System.out.println("Nacionalidad no puede ser vacio");
            }
        }
        bucle = true;
        while (bucle) {
            System.out.println("Escriba el cargo del empleado");
            cargo = scanner.next();
            if (!cargo.equals("")) {
                bucle = false;
            } else {
                System.out.println("Cargo no puede ser vacio");
            }
        }
        System.out.println("Datos del empeleado nuevo: \n" +
                "DNI: " + DNI + "\n" +
                "Nombre: " + nombre + "\n" +
                "Apellido: " + apellido + "\n" +
                "Fecha nacimiento: " + fecha_nac + "\n" +
                "Fecha contratación: " + fecha_cont + "\n" +
                "Nacionalidad: " + nacionalidad + "\n" +
                "Cargo: " + cargo + "\n"

        );
        System.out.println("Es correcto? y/N");
        String comprobación = scanner.next();
        if (comprobación.toLowerCase().equals("y")) {
            System.out.println("Creando empleado");
            empleados.put(DNI, new Empleado(DNI, nombre, apellido, fecha_nac, fecha_cont, nacionalidad, cargo));
        } else {
            System.out.println("Cancelando operación, redirigiendo al menu");
        }
    }

    public static void borrar_empleado(Scanner scanner) {
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
        String dni = scanner.next();
        if (empleados.containsKey(dni)) {
            empleados.get(dni).setEstado("Borrado");
        } else {
            System.out.println("El DNI escrito no esta en la lista");
        }
    }

    public static void nuevo_cliente(Scanner scanner) {
        boolean bucle = true;
        String DNI = "";
        String nombre = "";
        String apellido = "";
        int edad = 0;
        String profesion = "";
        while (bucle) {
            System.out.println("Escriba el DNI del cliente");
            DNI = scanner.next();
            Pattern pat = Pattern.compile("[0-9]{7,8}[A-Z a-z]");
            Matcher mat = pat.matcher(DNI);
            if (mat.matches()) {
                bucle = false;
            } else {
                System.out.println("DNI: " + DNI + " incorrecto");
            }
        }
        bucle = true;
        while (bucle) {
            System.out.println("Escriba el Nombre del cliente");
            nombre = scanner.next();
            if (!nombre.equals("")) {
                bucle = false;
            } else {
                System.out.println("Nombre no puede ser vacio");
            }
        }
        bucle = true;
        while (bucle) {
            System.out.println("Escriba el Apellido del cliente");
            apellido = scanner.next();
            if (!apellido.equals("")) {
                bucle = false;
            } else {
                System.out.println("Apellido no puede ser vacio");
            }
        }
        bucle = true;
        while (bucle) {
            System.out.println("Escriba la edad del cliente");
            try {
                edad = scanner.nextInt();
                if (edad > 0) {
                    bucle = false;
                } else {
                    System.out.println("Edad no puede ser menor de 0");
                }
            } catch (NumberFormatException e) {
                System.out.println("Debes escribir un numero");
            }
        }
        bucle = true;
        while (bucle) {
            System.out.println("Escriba la profesión del cliente");
            profesion = scanner.next();
            if (!profesion.equals("")) {
                bucle = false;
            } else {
                System.out.println("Profesion no puede ser vacio");
            }
        }
        System.out.println("Datos del cliente nuevo: \n" +
                "DNI: " + DNI + "\n" +
                "Nombre: " + nombre + "\n" +
                "Apellido: " + apellido + "\n" +
                "Edad: " + edad + "\n" +
                "Profesión: " + profesion + "\n"
        );
        System.out.println("Es correcto? y/N");
        String comprobación = scanner.next();
        if (comprobación.toLowerCase().equals("y")) {
            System.out.println("Creando usuario");
            clientes.put(DNI, new Cliente(DNI, nombre, apellido, edad, profesion));
        } else {
            System.out.println("Cancelando operación, redirigiendo al menu");
        }
    }

    public static void borrar_cliente(Scanner scanner) {
        for (Cliente cliente : clientes.values()) {
            System.out.println("CLIENTE: ");
            System.out.println("DNI: " + cliente.getDni());
            System.out.println("Nombre: " + cliente.getNombre());
            System.out.println("Apellido: " + cliente.getApellido());
            System.out.println("Edad: " + cliente.getEdad());
            System.out.println("Profesión: " + cliente.getProfesion());
        }
        System.out.println("Escriba el DNI del cliente que quiere borrar");
        String dni = scanner.next();
        if (clientes.containsKey(dni)) {
            clientes.get(dni).setEstado("Borrado");
        } else {
            System.out.println("El DNI escrito no esta en la lista");
        }
    }

    public static void modificar_cliente(Scanner scanner) {
    }

    public static void modificar_visita_guiada(Scanner scanner) {
    }

    public static void modificar_empleado(Scanner scanner) {
    }

    public static void mostar_metadatos() {
    }

    public static void visualizar_datos_agencia() {
    }
}