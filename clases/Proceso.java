package clases;

/**
 * Clase Proceso
 */
public class Proceso {
    private int id;                 // Id del proceso.
    private String nombre;          // Nombre del proceso.
    private int tamano;             // Tamaño del proceso.
    private int _rafaga;            // Tiempo de ráfaga variable del proceso.
    private int _tiempoLlegada;     // Tiempo de llegada varianble del proceso.
    private int rafaga;             // Tiempo de ráfaga original del proceso.
    private int tiempoLlegada;      // Tiempo de llegada orignal del proceso.
    private int tiempoTerminado;    // Tiempo de finalización del proceso.
    private int tiempoEspera;       // Tiempo de espera del proceso.
    private int tiempocarga;
    private int tiemposalida;
    private int tejecutado;

    /**
     * Constructor
     * @param id Recibe el id del proceso a crear.
     * @param nombre Recibe el nombre del proceso a crear.
     * @param tamano Recibe el tamaño del proceso a crear.
     * @param rafaga Recibe la ráfaga del proceso a crear.
     * @param tiempoLlegada Recibe el tiempo de llegada del proceso a crear.
     */
    public Proceso( int id, String nombre, int tamano, int rafaga, int tiempoLlegada ) {
        this.id = id;
        this.nombre = nombre;
        this.tamano = tamano;
        this.rafaga = rafaga;
        this.tiempoLlegada = tiempoLlegada;
        this._rafaga = rafaga;
        this._tiempoLlegada = tiempoLlegada;
        this.tiempoEspera = 0;
        this.tiempoTerminado = 0;
        this.tiempocarga=0;
        this.tiemposalida=0;
        this.tejecutado=0;
    }

    /**
     * GETTERS
     * 
     * Obtienen el valor de los atributos respectivos.
     * @return 
     */

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

    public int getTiempoCarga(){
        return tiempocarga;
    }
    
    public int getTiempoSalida(){
        return tiemposalida;
    }
    
    public int getTiempoEjecutado(){
        return tejecutado;
    }
    
    /**
     * SETTERS
     * 
     * Asignan el valor del parámetro en el atributo respectivo.
     */

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
    
    public void setTiempoCarga(int tiempocarga){
        this.tiempocarga=tiempocarga;
    }
    public void setTiempoSalida(int tiemposalida){
        this.tiemposalida=tiemposalida;
    }
    
    public void setTiempoEjecutado(int tejecutado){
        this.tejecutado=this.tejecutado+tejecutado;
    }
}
