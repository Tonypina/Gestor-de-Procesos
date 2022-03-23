package lista;
import procesos.Proceso;

public class Nodo {
    private Proceso proceso;
    private Nodo siguiente;
    private Nodo anterior;

    public Nodo( Proceso proceso ) {
        this.proceso = proceso;
        this.siguiente = null;
        this.anterior = null;
    }

    public Nodo( Proceso proceso, Nodo siguiente, Nodo anterior ) {
        this.proceso = proceso;
        this.siguiente = siguiente;
        this.anterior = anterior;
    }

    public Proceso getProceso() {
        return proceso;
    }

    public Nodo getAnterior() {
        return anterior;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setProceso(Proceso proceso) {
        this.proceso = proceso;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

    public void setAnterior(Nodo anterior) {
        this.anterior = anterior;
    }
}