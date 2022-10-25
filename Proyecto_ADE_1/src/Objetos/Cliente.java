package Objetos;

import java.util.HashMap;

public class Cliente {
    private String dni;
    private String nombre;
    private String apellido;
    private int edad;
    private String profesion;
    private String estado;
    private String contraseña;

    private HashMap<Integer, Integer> visitas_numero = new HashMap<>();

    public Cliente(String dni, String nombre, String apellido, int edad, String profesion) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.profesion = profesion;
    }

    public Cliente() {
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public HashMap<Integer, Integer> getVisitas() {
        return visitas_numero;
    }

    public void setVisitas(Integer N_visita) {
        this.visitas_numero.put(N_visita, N_visita);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                ", profesion='" + profesion + '\'' +
                '}';
    }
}
