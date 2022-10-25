package Objetos;

import java.util.HashMap;

public class Lugar {
    private int id;
    private String lugar;
    private String nacionalidad;
    private HashMap<Integer, Integer> visitas_numero = new HashMap<>();

    public Lugar(int id, String lugar, String nacionalidad) {
        this.id = id;
        this.lugar = lugar;
        this.nacionalidad = nacionalidad;
    }

    public Lugar() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public HashMap<Integer, Integer> getVisitas() {
        return visitas_numero;
    }

    public void setVisitas(Integer N_visita) {
        this.visitas_numero.put(N_visita, N_visita);
    }

    public void borrar_visita(int id) {
        visitas_numero.remove(id);
    }

    @Override
    public String toString() {
        return "Lugar{" +
                "id=" + id +
                ", lugar='" + lugar + '\'' +
                ", nacionalidad='" + nacionalidad + '\'' +
                '}';
    }
}
