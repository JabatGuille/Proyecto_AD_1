package Objetos;

import java.util.HashMap;

public class Empleado {
    private String dni;
    private String nombre;
    private String apellido;
    private String fecha_Nac;
    private String fecha_cont;
    private String nacionalidad;
    private String cargo;
    private String estado;

    private HashMap<Integer, VisitaGuiada> visitas = new HashMap<>();

    public Empleado(String dni, String nombre, String apellido, String fecha_Nac, String fecha_cont, String nacionalidad, String cargo) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_Nac = fecha_Nac;
        this.fecha_cont = fecha_cont;
        this.nacionalidad = nacionalidad;
        this.cargo = cargo;
    }

    public Empleado() {
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFecha_Nac() {
        return this.fecha_Nac;
    }

    public void setFecha_Nac(String fecha_Nac) {
        this.fecha_Nac = fecha_Nac;
    }

    public String getFecha_cont() {
        return this.fecha_cont;
    }

    public void setFecha_cont(String fecha_cont) {
        this.fecha_cont = fecha_cont;
    }

    public String getNacionalidad() {
        return this.nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getCargo() {
        return this.cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public HashMap<Integer, VisitaGuiada> getVisitas() {
        return visitas;
    }

    public void setVisitas(VisitaGuiada visitas) {
        this.visitas.put(visitas.getN_visita(), visitas);
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "DNI='" + dni + '\'' +
                ", Nombre='" + nombre + '\'' +
                ", Apellido='" + apellido + '\'' +
                ", Fecha_Nac='" + fecha_Nac + '\'' +
                ", Fecha_cont='" + fecha_cont + '\'' +
                ", Nacionalidad='" + nacionalidad + '\'' +
                ", Cargo='" + cargo + '\'' +
                '}';
    }
}
