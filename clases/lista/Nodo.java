package clases.lista;

import clases.Proceso;

/**
 * Clase Nodo.
 */
public class Nodo {
    private Proceso proceso;    
    private Nodo siguiente;     
    private Nodo anterior;      
    private int index;

    /**
     * Constructor
     * @param proceso Recibe el proceso que almacenará el nodo
     */
    public Nodo( Proceso proceso ) {
        this.proceso = proceso;
        this.siguiente = null;
        this.anterior = null;
    }

    /**
     * Constructor
     * @param proceso Recibe el proceso que se almacenará en el nodo.
     * @param siguiente Recibe el nodo que le sigue.
     * @param anterior Recibe el nodo que le antecede.
     */
    public Nodo( Proceso proceso, Nodo siguiente, Nodo anterior ) {
        this.proceso = proceso;
        this.siguiente = siguiente;
        this.anterior = anterior;
    }

    /**
     * GETTERS
     * 
     * Regresan los valores de los atributos.
     */

    public Proceso getProceso() {
        return proceso;
    }

    public Nodo getAnterior() {
        return anterior;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public int getIndex() {
        return index;
    }

    /**
     * SETTERS
     * 
     * Asignan el valor del parámetro a los atributos.
     */
    public void setProceso(Proceso proceso) {
        this.proceso = proceso;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

    public void setAnterior(Nodo anterior) {
        this.anterior = anterior;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
