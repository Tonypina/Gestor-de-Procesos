package procesos;

public class Proceso {
    private int id;
    private String nombre;
    private int tamano;
    private int rafaga;
    private int prioridad;
    private int tiempoLlegada;

    public Proceso( int id, String nombre, int tamano, int rafaga, int prioridad, int tiempoLlegada ) {
        this.id = id;
        this.nombre = nombre;
        this.tamano = tamano;
        this.rafaga = rafaga;
        this.prioridad = prioridad;
        this.tiempoLlegada = tiempoLlegada;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public int getRafaga() {
        return rafaga;
    }

    public int getTamano() {
        return tamano;
    }

    public int getTiempoLlegada() {
        return tiempoLlegada;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public void setRafaga(int rafaga) {
        this.rafaga = rafaga;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }

    public void setTiempoLlegada(int tiempoLlegada) {
        this.tiempoLlegada = tiempoLlegada;
    }
}
