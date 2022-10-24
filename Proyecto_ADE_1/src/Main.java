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
        listar_visitas_guiadas();
        System.out.println("Escriba el Numero de visita que desea añadir empleado");
        boolean bucle = true;
        String comprobacion = "";
        int N_visita = 0;
        while (bucle) {
            try {
                System.out.println("Escriba el Numero de visita que desea añadir empleado");
                N_visita = scanner.nextInt();
                if (visitasguiadas.containsKey(N_visita)) {
                    if (visitasguiadas.get(N_visita).getEmpleado() == null) {
                        bucle = false;
                    } else {
                        System.out.println("La visita ya tiene empleado");
                        System.out.println("Esriba y si se ha equivocado, añadira clientes a la visita");
                        comprobacion = scanner.next();
                        if (comprobacion.equalsIgnoreCase("Y")) {
                            bucle = false;
                        }
                    }
                } else {
                    System.out.println("El numero de visita indicado no existe");
                }
            } catch (NumberFormatException e) {
                System.out.println("Debe escribir un numero");
            }
        }
        if (comprobacion.equalsIgnoreCase("Y")) {
            listar_empleados();
            System.out.println("Escriba el DNI del empleado que quiere añadir");
            String DNI = "";
            bucle = true;
            while (bucle) {
                System.out.println("Escriba el DNI del empleado");
                DNI = scanner.next();
                Pattern pat = Pattern.compile("[0-9]{7,8}[A-Z a-z]");
                Matcher mat = pat.matcher(DNI);
                if (mat.matches()) {
                    if (empleados.containsKey(DNI)) {
                        bucle = false;
                        System.out.println("Añadiendo empleado a la visita");
                        visitasguiadas.get(N_visita).setEmpleado(empleados.get(DNI));
                        empleados.get(DNI).setVisitas(visitasguiadas.get(N_visita));
                    } else {
                        System.out.println("El DNI no existe " + DNI);
                    }
                } else {
                    System.out.println("DNI: " + DNI + " incorrecto");
                }
            }
        }
        añadir_clientes(N_visita, scanner);
    }

    public static void añadir_clientes(Integer N_visita, Scanner scanner) {
        listar_clientes();
        System.out.println("Escriba el DNI del cliente que quiere añadir");
        while (visitasguiadas.get(N_visita).getClientes().size() == visitasguiadas.get(N_visita).getN_max_cli()) {
            System.out.println("Escriba el DNI del cliente");
            String DNI = scanner.next();
            Pattern pat = Pattern.compile("[0-9]{7,8}[A-Z a-z]");
            Matcher mat = pat.matcher(DNI);
            if (mat.matches()) {
                if (clientes.containsKey(DNI)) {
                    System.out.println("Añadiendo cliente");
                    clientes.get(DNI).setVisitas(visitasguiadas.get(N_visita));
                    visitasguiadas.get(N_visita).setClientes(clientes.get(DNI));
                }
            } else {
                System.out.println("DNI: " + DNI + " incorrecto");
            }
        }
        System.out.println("Terminando de añadir clientes, numero maximo de clientes en la visita\n" +
                "Clientes en visita: " + visitasguiadas.get(N_visita).getClientes().size() + "\n" +
                "Clientes maximos: " + visitasguiadas.get(N_visita).getN_max_cli());
    }

    public static void menu_cliente(Scanner scanner) {
        boolean bucle = true;
        while (bucle) {
            System.out.println("Escriba un numero para la opcion del menu:\n" +
                    "1: Listar clientes\n" +
                    "2: Nuevo cliente.\n" +
                    "3: Modificar cliente.\n" +
                    "4: Borrar cliente.\n" +
                    "5: Salir");
            String menu = scanner.next();
            switch (menu) {
                case "1": {
                    listar_clientes();
                    bucle = false;
                    break;
                }
                case "2": {
                    nuevo_cliente(scanner);
                    bucle = false;
                    break;
                }
                case "3": {
                    modificar_cliente(scanner);
                    bucle = false;
                    break;
                }
                case "4": {
                    borrar_cliente(scanner);
                    bucle = false;
                    break;
                }
                case "5": {
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

    private static void listar_clientes() {
        for (Cliente cliente : clientes.values()) {
            if (!cliente.getEstado().equals("Borrado")) {
                System.out.println("CLIENTE");
                System.out.println("DNI: " + cliente.getDni());
                System.out.println("Nombre: " + cliente.getNombre());
                System.out.println("Apellido: " + cliente.getApellido());
                System.out.println("Edad: " + cliente.getEdad());
                System.out.println("Profesión: " + cliente.getProfesion());
                for (VisitaGuiada visita : cliente.getVisitas().values()) {
                    System.out.println("VISITA");
                    System.out.println("Numero Visita: " + visita.getN_visita());
                    System.out.println("Horario: " + visita.getHorario());
                }
            }
        }
    }

    public static void menu_empleado(Scanner scanner) {
        boolean bucle = true;
        while (bucle) {
            System.out.println("Escriba un numero para la opcion del menu:\n" +
                    "1: Listar empleados\n" +
                    "2: Nuevo empleado.\n" +
                    "3: Modificar empleado.\n" +
                    "4: Borrar empleado.\n" +
                    "5: Salir");
            String menu = scanner.next();
            switch (menu) {
                case "1": {
                    listar_empleados();
                    bucle = false;
                    break;
                }
                case "2": {
                    nuevo_empleado(scanner);
                    bucle = false;
                    break;
                }
                case "3": {
                    modificar_empleado(scanner);
                    bucle = false;
                    break;
                }
                case "4": {
                    borrar_empleado(scanner);
                    bucle = false;
                    break;
                }
                case "5": {
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

    private static void listar_empleados() {
        for (Empleado empleado : empleados.values()) {
            if (!empleado.getEstado().equals("Borrado")) {
                System.out.println("EMPLEADO");
                System.out.println("DNI: " + empleado.getDni());
                System.out.println("Nombre: " + empleado.getNombre());
                System.out.println("Apellido: " + empleado.getApellido());
                System.out.println("Fecha Nacimiento: " + empleado.getFecha_Nac());
                System.out.println("Fecha Contratación: " + empleado.getFecha_cont());
                System.out.println("Nacionalidad: " + empleado.getNacionalidad());
                System.out.println("Cargo: " + empleado.getCargo());
                for (VisitaGuiada visita : empleado.getVisitas().values()) {
                    System.out.println("VISITAS");
                    System.out.println("Numero visita: " + visita.getN_visita());
                    System.out.println("Horario: " + visita.getHorario());
                }
            }
        }
    }


    public static void listar_visitas_guiadas() {
        for (VisitaGuiada visita : visitasguiadas.values()) {
            if (!visita.getEstado().equals("Borrado")) {
                System.out.println("VISITA:");
                System.out.println("Nº Visita: " + visita.getN_visita());
                System.out.println("Nombre: " + visita.getNombre());
                System.out.println("Nº maximo de clientes: " + visita.getN_max_cli());
                System.out.println("Punto de partida: " + visita.getPunto_partida());
                System.out.println("Curso: " + visita.getCurso());
                System.out.println("Tematica: " + visita.getTematica());
                System.out.println("Coste: " + visita.getCoste());
                System.out.println("Horario: " + visita.getHorario());
                System.out.println("Lugar de la visita:");
                System.out.println("Nº Visita: " + visita.getLugar().getLugar());
                System.out.println("Nº Visita: " + visita.getLugar().getNacionalidad());
                System.out.println("EMPLEADO:");
                System.out.println("DNI " + visita.getEmpleado().getDni());
                System.out.println("Nombre " + visita.getEmpleado().getNombre());
                System.out.println("Apellido " + visita.getEmpleado().getApellido());
                System.out.println("Fecha Nacimiento " + visita.getEmpleado().getFecha_Nac());
                System.out.println("Fecha Contratación " + visita.getEmpleado().getFecha_cont());
                System.out.println("Nacionalidad " + visita.getEmpleado().getNacionalidad());
                System.out.println("Cargo " + visita.getEmpleado().getCargo());
                for (Cliente cliente : visita.getClientes().values()) {
                    System.out.println("Cliente de la visita: " + visita.getN_visita());
                    System.out.println("DNI: " + cliente.getDni());
                    System.out.println("Nombre: " + cliente.getNombre());
                    System.out.println("Apellido: " + cliente.getApellido());
                    System.out.println("Edad: " + cliente.getEdad());
                    System.out.println("Profesión: " + cliente.getProfesion());
                }
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
        double coste = 0.0;
        int lugarid = 0;
        String horario = "";
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
        bucle = true;
        while (bucle) {
            System.out.println("Escriba el horario");
            horario = scanner.next();
            Pattern pat = Pattern.compile("^([01]?[0-9]|2[0-3]):[0-5][0-9]$");
            Matcher mat = pat.matcher(horario);
            if (mat.matches()) {
                bucle = false;
            } else {
                System.out.println("El formato escrito no es correcto, el formato es 24h");
            }
        }
        System.out.println("Escriba Y si quiere crear un lugar");
        if (scanner.next().equalsIgnoreCase("Y") || lugares.size() == 0) {
            if (lugares.size() == 0) {
                System.out.println("No existen lugares para poder añadir, llevando al menu de crear lugares");
            }
            lugarid = crear_lugar(scanner);
        }
        System.out.println("Datos de la nueva visita guiada: \n" +
                "Nombre: " + nombre + "\n" +
                "Numero maximo de clientes: " + n_max_cli + "\n" +
                "Punto de partida: " + punto_partida + "\n" +
                "Curso: " + curso + "\n" +
                "Tematica: " + tematica + "\n" +
                "Coste: " + coste + "\n" +
                "Horario: " + horario
        );
        System.out.println("Lugar\n" +
                "Lugar: " + lugares.get(lugarid) + "\n" +
                "Nacionalidad: " + lugares.get(lugarid));
        System.out.println("Es correcto? y/N");
        String comprobación = scanner.next();
        if (comprobación.toLowerCase().equals("y")) {
            System.out.println("Modificando visita guiada");
            int visitaid = visitasguiadas.size();
            visitasguiadas.put(visitaid, new VisitaGuiada(visitaid, nombre, n_max_cli, punto_partida, curso, tematica, coste, lugares.get(lugarid), horario));
            lugares.get(lugarid).setVisitas(visitasguiadas.get(visitaid));
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
        listar_visitas_guiadas();
        System.out.println("Escriba el Nº de visita que quiere borrar");
        try {
            int numero = scanner.nextInt();
            if (visitasguiadas.containsKey(numero)) {
                visitasguiadas.get(numero).setEstado("Borrada");
                for (Cliente cliente : clientes.values()) {
                    for (VisitaGuiada visita : cliente.getVisitas().values()) {
                        if (visita.getN_visita() == numero) {
                            cliente.getVisitas().remove(numero);
                        }
                    }
                }
                for (Empleado empleado : empleados.values()) {
                    for (VisitaGuiada visita : empleado.getVisitas().values()) {
                        if (visita.getN_visita() == numero) {
                            empleado.getVisitas().remove(numero);
                        }
                    }
                }
                for (Lugar lugar : lugares.values()) {
                    for (VisitaGuiada visita : lugar.getVisitas().values()) {
                        if (visita.getN_visita() == numero) {
                            lugar.getVisitas().remove(numero);
                        }
                    }
                }


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
        listar_empleados();
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
        listar_clientes();
        System.out.println("Escriba el DNI del cliente que quiere borrar");
        String dni = scanner.next();
        if (clientes.containsKey(dni)) {
            clientes.get(dni).setEstado("Borrado");
        } else {
            System.out.println("El DNI escrito no esta en la lista");
        }
    }

    public static void modificar_cliente(Scanner scanner) {
        listar_clientes();
        boolean bucle = true;
        String cliente_dni = "";
        while (bucle) {
            System.out.println("Escriba el DNI del cliente que quiere modificar");
            cliente_dni = scanner.next();
            Pattern pat = Pattern.compile("[0-9]{7,8}[A-Z a-z]");
            Matcher mat = pat.matcher(cliente_dni);
            if (mat.matches()) {
                if (clientes.containsKey(cliente_dni)) {
                    if (!clientes.get(cliente_dni).getEstado().equals("Borrado")) {
                        bucle = false;
                    } else {
                        System.out.println("El cliente no existe");
                    }
                } else {
                    System.out.println("El cliente no existe ");
                }
            } else {
                System.out.println("DNI: " + cliente_dni + " incorrecto");
            }
        }
        bucle = true;
        String DNI = "";
        String nombre = "";
        String apellido = "";
        int edad = 0;
        String profesion = "";
        while (bucle) {
            System.out.println("Escriba el nuevo DNI del cliente, si no quiere cambiarlo escriba el mismo");
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
            System.out.println("Escriba el nuevo Nombre del cliente, si no quiere cambiarlo escriba el mismo");
            nombre = scanner.next();
            if (!nombre.equals("")) {
                bucle = false;
            } else {
                System.out.println("Nombre no puede ser vacio");
            }
        }
        bucle = true;
        while (bucle) {
            System.out.println("Escriba el nuevo Apellido del cliente, si no quiere cambiarlo escriba el mismo");
            apellido = scanner.next();
            if (!apellido.equals("")) {
                bucle = false;
            } else {
                System.out.println("Apellido no puede ser vacio");
            }
        }
        bucle = true;
        while (bucle) {
            System.out.println("Escriba la nueva edad del cliente, si no quiere cambiarlo escriba el mismo");
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
            System.out.println("Escriba la nueva profesión del cliente, si no quiere cambiarlo escriba el mismo");
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
        String comprobacion = scanner.next();
        if (comprobacion.equalsIgnoreCase("y")) {
            System.out.println("Modificando usuario");
            clientes.get(cliente_dni).setDni(DNI);
            clientes.get(cliente_dni).setNombre(nombre);
            clientes.get(cliente_dni).setApellido(apellido);
            clientes.get(cliente_dni).setEdad(edad);
            clientes.get(cliente_dni).setProfesion(profesion);
            Cliente cliente = clientes.get(cliente_dni);
            for (VisitaGuiada visi : clientes.get(cliente_dni).getVisitas().values()) {
                visi.getClientes().remove(cliente_dni);
                visi.setClientes(cliente);
            }
            for (VisitaGuiada visi : visitasguiadas.values()) {
                visi.getClientes().remove(cliente_dni);
                visi.setClientes(cliente);
            }
            clientes.remove(cliente_dni);
            clientes.put(cliente.getDni(), cliente);
        } else {
            System.out.println("Cancelando operación, redirigiendo al menu");
        }
    }

    public static void modificar_visita_guiada(Scanner scanner) {
        listar_visitas_guiadas();
        System.out.println("Escriba el Numero de visita que desea modificar");
        boolean bucle = true;
        int N_visita = 0;
        while (bucle) {
            try {
                N_visita = scanner.nextInt();
                if (visitasguiadas.containsKey(N_visita)) {
                    if (!visitasguiadas.get(N_visita).getEstado().equals("Borrado")) {
                        bucle = false;
                    } else {
                        System.out.println("La visita no existe");
                    }
                } else {
                    System.out.println("El numero de visita indicado no existe");
                }
            } catch (NumberFormatException e) {
                System.out.println("Debe escribir un numero");
            }
        }
        bucle = true;
        String nombre = "";
        int n_max_cli = 0;
        String punto_partida = "";
        String curso = "";
        String tematica = "";
        double coste = 0.0;
        int lugarid = 0;
        String horario = "";
        while (bucle) {
            System.out.println("Escriba el Nombre de la visita, si no lo quiere modificar escriba el mismo nombre");
            nombre = scanner.next();
            if (!nombre.equals("")) {
                bucle = false;
            } else {
                System.out.println("Nombre no puede ser vacio");
            }
        }
        bucle = true;
        while (bucle) {
            System.out.println("Escriba el numero maximo de clientes, si no lo quiere modificar escriba el mismo nombre");
            try {
                n_max_cli = scanner.nextInt();
                if (n_max_cli > 0) {
                    bucle = false;
                } else {
                    System.out.println("El numero maximo de clientes debe ser mayor de 0, si no lo quiere modificar escriba el mismo nombre");
                }
            } catch (NumberFormatException e) {
                System.out.println("Debes escribir un numero");
            }

        }
        bucle = true;
        while (bucle) {
            System.out.println("Escriba el punto de partida, si no lo quiere modificar escriba el mismo nombre");
            punto_partida = scanner.next();
            if (!punto_partida.equals("")) {
                bucle = false;
            } else {
                System.out.println("El punto de partida no puede estar vacio");
            }
        }
        bucle = true;
        while (bucle) {
            System.out.println("Escriba el curso, si no lo quiere modificar escriba el mismo nombre");
            curso = scanner.next();
            if (!curso.equals("")) {
                bucle = false;
            } else {
                System.out.println("Curso no puede ser vacio");
            }
        }
        bucle = true;
        while (bucle) {
            System.out.println("Escriba la tematica, si no lo quiere modificar escriba el mismo nombre");
            tematica = scanner.next();
            if (!tematica.equals("")) {
                bucle = false;
            } else {
                System.out.println("Tematica no puede ser vacio");
            }
        }
        bucle = true;
        while (bucle) {
            System.out.println("Escriba el coste, si no lo quiere modificar escriba el mismo nombre");
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
        bucle = true;
        while (bucle) {
            System.out.println("Escriba el horario, si no lo quiere modificar escriba el mismo nombre");
            horario = scanner.next();
            Pattern pat = Pattern.compile("^([01]?[0-9]|2[0-3]):[0-5][0-9]$");
            Matcher mat = pat.matcher(horario);
            if (mat.matches()) {
                bucle = false;
            } else {
                System.out.println("El formato escrito no es correcto, el formato es 24h");
            }
        }
        System.out.println("Escriba Y si quiere modificar el lugar, le lleva al menu de crear lugares");
        if (scanner.next().equalsIgnoreCase("Y") || lugares.size() == 0) {
            if (lugares.size() == 0) {
                System.out.println("No existen lugares para poder añadir, llevando al menu de crear lugares");
            }
            lugarid = crear_lugar(scanner);
        }
        System.out.println("Datos de la nueva visita guiada: \n" +
                "Nombre: " + nombre + "\n" +
                "Numero maximo de clientes: " + n_max_cli + "\n" +
                "Punto de partida: " + punto_partida + "\n" +
                "Curso: " + curso + "\n" +
                "Tematica: " + tematica + "\n" +
                "Coste: " + coste + "\n" +
                "Horario: " + horario
        );
        System.out.println("Lugar\n" +
                "Lugar: " + lugares.get(lugarid) + "\n" +
                "Nacionalidad: " + lugares.get(lugarid));
        System.out.println("Es correcto? y/N");
        String comprobacion = scanner.next();
        if (comprobacion.equalsIgnoreCase("y")) {
            System.out.println("Modificando visita guiada");
            lugares.get(visitasguiadas.get(N_visita).getLugar().getId()).borrar_visita(N_visita);
            VisitaGuiada visita = visitasguiadas.get(N_visita);
            visita.setNombre(nombre);
            visita.setN_max_cli(n_max_cli);
            visita.setPunto_partida(punto_partida);
            visita.setCurso(curso);
            visita.setTematica(tematica);
            visita.setCoste(coste);
            visita.setHorario(horario);
            visita.setLugar(lugares.get(lugarid));
            visitasguiadas.put(N_visita, visita);
            lugares.get(lugarid).setVisitas(visitasguiadas.get(N_visita));
            for (Empleado empleado : empleados.values()) {
                if (empleado.getVisitas().containsKey(N_visita)) {
                    empleado.setVisitas(visita);
                }
            }
            for (Cliente cliente : clientes.values()) {
                if (cliente.getVisitas().containsKey(visita.getN_visita())) {
                    cliente.setVisitas(visita);
                }
            }
        } else {
            System.out.println("Cancelando operación, redirigiendo al menu");
        }
    }

    public static void modificar_empleado(Scanner scanner) {
        listar_empleados();
        boolean bucle = true;
        String empleado_DNI = "";
        while (bucle) {
            System.out.println("Escriba el DNI del empleado que quiere modificar");
            empleado_DNI = scanner.next();
            Pattern pat = Pattern.compile("[0-9]{7,8}[A-Z a-z]");
            Matcher mat = pat.matcher(empleado_DNI);
            if (mat.matches()) {
                bucle = false;
            } else {
                System.out.println("DNI: " + empleado_DNI + " incorrecto");
            }
        }
        String DNI = "";
        String nombre = "";
        String apellido = "";
        String fecha_nac = "";
        String fecha_cont = "";
        String nacionalidad = "";
        String cargo = "";
        bucle = true;
        while (bucle) {
            System.out.println("Escriba el nuevo DNI del empleado, si no quiere cambiarlo escriba el mismo");
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
            System.out.println("Escriba el nuevo Nombre del empleado, si no quiere cambiarlo escriba el mismo");
            nombre = scanner.next();
            if (!nombre.equals("")) {
                bucle = false;
            } else {
                System.out.println("Nombre no puede ser vacio");
            }
        }
        bucle = true;
        while (bucle) {
            System.out.println("Escriba el nuevo Apellido del empleado, si no quiere cambiarlo escriba el mismo");
            apellido = scanner.next();
            if (!apellido.equals("")) {
                bucle = false;
            } else {
                System.out.println("Apellido no puede ser vacio");
            }
        }
        bucle = true;
        while (bucle) {
            System.out.println("Escriba la nueva fecha de nacimiento del empleado, si no quiere cambiarlo escriba el mismo");
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
            System.out.println("Escriba la nueva nacionalidad del empleado, si no quiere cambiarlo escriba el mismo");
            nacionalidad = scanner.next();
            if (!nacionalidad.equals("")) {
                bucle = false;
            } else {
                System.out.println("Nacionalidad no puede ser vacio");
            }
        }
        bucle = true;
        while (bucle) {
            System.out.println("Escriba el nuevo cargo del empleado, si no quiere cambiarlo escriba el mismo");
            cargo = scanner.next();
            if (!cargo.equals("")) {
                bucle = false;
            } else {
                System.out.println("Cargo no puede ser vacio");
            }
        }
        System.out.println("Datos del empeleado modificado: \n" +
                "DNI: " + DNI + "\n" +
                "Nombre: " + nombre + "\n" +
                "Apellido: " + apellido + "\n" +
                "Fecha nacimiento: " + fecha_nac + "\n" +
                "Fecha contratación: " + fecha_cont + "\n" +
                "Nacionalidad: " + nacionalidad + "\n" +
                "Cargo: " + cargo + "\n"

        );
        System.out.println("Es correcto? y/N");
        String comprobacion = scanner.next();
        if (comprobacion.equalsIgnoreCase("y")) {
            System.out.println("Modificando empleado");
            Empleado empleado = empleados.get(empleado_DNI);
            empleado.setDni(DNI);
            empleado.setNombre(nombre);
            empleado.setApellido(apellido);
            empleado.setFecha_Nac(fecha_nac);
            empleado.setFecha_cont(fecha_cont);
            empleado.setNacionalidad(nacionalidad);
            empleado.setCargo(cargo);
            for (VisitaGuiada visita : empleados.get(empleado_DNI).getVisitas().values()) {
                visita.setEmpleado(empleado);
            }
            empleados.remove(empleado_DNI);
            empleados.put(empleado.getDni(), empleado);
            for (VisitaGuiada visitas : visitasguiadas.values()) {
                visitas.setEmpleado(empleado);
            }
        } else {
            System.out.println("Cancelando operación, redirigiendo al menu");
        }
    }

    public static void mostar_metadatos() {
    }

    public static void visualizar_datos_agencia() {
    }
}