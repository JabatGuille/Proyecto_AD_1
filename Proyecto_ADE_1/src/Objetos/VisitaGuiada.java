package Objetos;

import java.util.HashMap;

public class VisitaGuiada {
    private int n_visita;
    private String nombre;
    private int n_max_cli;
    private String punto_partida;
    private String curso;
    private String tematica;
    private Double coste;
    private String estado;
    private Lugar lugar;
    private Empleado empleado = null;
    private String horario;

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    private HashMap<String, Cliente> clientes = new HashMap<>();

    public VisitaGuiada(int n_visita, String nombre, int n_max_cli, String punto_partida, String curso, String tematica, Double coste, Lugar lugar, String horario) {
        this.n_visita = n_visita;
        this.nombre = nombre;
        this.n_max_cli = n_max_cli;
        this.punto_partida = punto_partida;
        this.curso = curso;
        this.tematica = tematica;
        this.coste = coste;
        this.lugar = lugar;
        this.horario = horario;
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

    public HashMap<String, Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(Cliente usuario) {
        this.clientes.put(usuario.getDni(), usuario);
    }

    public Lugar getLugar() {
        return lugar;
    }

    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
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
        clientes.remove(DNI);
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
}
