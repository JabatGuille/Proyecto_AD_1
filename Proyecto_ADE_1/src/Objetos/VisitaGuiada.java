package Objetos;

import java.io.Serializable;
import java.util.HashMap;

public class VisitaGuiada implements Serializable {
    private int n_visita;
    private String nombre;
    private int n_max_cli;
    private String punto_partida;
    private String curso;
    private String tematica;
    private Double coste;
    private String estado;
    private int lugar_id;
    private String empleado_dni;
    private String horario;
    private HashMap<String, String> clientes_dni = new HashMap<>();

    public String getEmpleado() {
        return empleado_dni;
    }

    public void setEmpleado(String empleado_dni) {
        this.empleado_dni = empleado_dni;
    }


    public VisitaGuiada(int n_visita, String nombre, int n_max_cli, String punto_partida, String curso, String tematica, Double coste, int lugar_id, String horario,String estado) {
        this.n_visita = n_visita;
        this.nombre = nombre;
        this.n_max_cli = n_max_cli;
        this.punto_partida = punto_partida;
        this.curso = curso;
        this.tematica = tematica;
        this.coste = coste;
        this.lugar_id = lugar_id;
        this.horario = horario;
        this.estado = estado;
        this.empleado_dni = "";
    }

    public VisitaGuiada() {
    }

    public int getN_visita() {
        return n_visita;
    }

    public void setN_visita(int n_visita) {
        this.n_visita = n_visita;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getN_max_cli() {
        return n_max_cli;
    }

    public void setN_max_cli(int n_max_cli) {
        this.n_max_cli = n_max_cli;
    }

    public String getPunto_partida() {
        return punto_partida;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setPunto_partida(String punto_partida) {
        this.punto_partida = punto_partida;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public Double getCoste() {
        return coste;
    }

    public void setCoste(Double coste) {
        this.coste = coste;
    }

    public HashMap<String, String> getClientes() {
        return clientes_dni;
    }

    public void setClientes(String usuario_dni) {
        this.clientes_dni.put(usuario_dni, usuario_dni);
    }

    public int getLugar() {
        return lugar_id;
    }

    public void setLugar(int lugar_id) {
        this.lugar_id = lugar_id;
    }

    @Override
    public String toString() {
        return "VisitaGuiada{" +
                "n_visita=" + n_visita +
                ", nombre='" + nombre + '\'' +
                ", n_max_cli=" + n_max_cli +
                ", punto_partida='" + punto_partida + '\'' +
                ", curso='" + curso + '\'' +
                ", tematica='" + tematica + '\'' +
                ", coste=" + coste +
                '}';
    }

    public void borrar_cliente(String DNI) {
        clientes_dni.remove(DNI);
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
}
