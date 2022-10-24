package Objetos;

import java.util.ArrayList;
import java.util.HashMap;

public class Lugar {
    private int id;
    private String lugar;
    private String nacionalidad;
    private HashMap<Integer,VisitaGuiada> visitas = new HashMap<>();

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

    public HashMap<Integer,VisitaGuiada> getVisitas() {
        return visitas;
    }

    public void setVisitas(VisitaGuiada visita) {
        this.visitas.put(visita.getN_visita(),visita);
    }

    public void borrar_visita(int id){
        visitas.remove(id);
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
