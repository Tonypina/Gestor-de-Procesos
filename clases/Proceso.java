package clases;

public class Proceso {
    private int id;
    private String nombre;
    private int tamano;
    private int _rafaga;
    private int _tiempoLlegada;
    private int rafaga;
    private int tiempoLlegada;
    private int tiempoTerminado;
    private int tiempoEspera;

    public Proceso( int id, String nombre, int tamano, int rafaga, int tiempoLlegada ) {
        this.id = id;
        this.nombre = nombre;
        this.tamano = tamano;
        this.rafaga = rafaga;
        this.tiempoLlegada = tiempoLlegada;
        this._rafaga = rafaga;
        this._tiempoLlegada = tiempoLlegada;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getRafaga() {
        return rafaga;
    }

    public int get_rafaga() {
        return _rafaga;
    }

    public int getTamano() {
        return tamano;
    }

    public int getTiempoLlegada() {
        return tiempoLlegada;
    }

    public int get_tiempoLlegada() {
        return _tiempoLlegada;
    }

    public int getTiempoTerminado() {
        return tiempoTerminado;
    }

    public int getTiempoEspera() {
        return tiempoEspera;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void set_rafaga(int _rafaga) {
        this._rafaga = _rafaga;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }

    public void set_tiempoLlegada(int _tiempoLlegada) {
        this._tiempoLlegada = _tiempoLlegada;
    }

    public void setTiempoTerminado(int tiempoTerminado) {
        this.tiempoTerminado = tiempoTerminado;
    }

    public void setTiempoEspera(int tiempoEspera) {
        this.tiempoEspera = tiempoEspera;
    }
}
