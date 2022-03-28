package clases.lista;

import clases.Proceso;

/**
 * Clase Lista
 */
public class Lista {
    private Nodo primero;   // Apuntador al primer nodo de la lista.
    private Nodo ultimo;    // Apuntador al último nodo de la lista.
    private Nodo cursor;    // Apuntador dinámico de nodos.
    private int length;     // Tamaño de la lista.

    /**
     * Constructor de Lista
     */
    public Lista() {
        this.primero = null;
        this.ultimo = null;
        this.length = 0;
    }

    /**
     * Método insertar
     * @brief Inserta un proceso en la lista.
     * @param proceso El proceso a insertar.
     */
    public void insertar( Proceso proceso ) {
        Nodo nuevoProceso = new Nodo( proceso );
        
        if ( this.primero == null ) {
            
            nuevoProceso.setIndex(0);
            this.primero = nuevoProceso;
            this.ultimo = nuevoProceso;
            this.length++;
            return;
        } 
        
        if ( this.primero.equals(this.ultimo) ) {
            
            this.primero.setSiguiente(nuevoProceso);
            nuevoProceso.setAnterior(this.primero);
            nuevoProceso.setIndex(1);
            this.ultimo = nuevoProceso;
            this.length++;
            return;
        }
        
        this.ultimo.setSiguiente(nuevoProceso);
        nuevoProceso.setAnterior(this.ultimo);
        nuevoProceso.setIndex(this.ultimo.getIndex() + 1);
        this.ultimo = nuevoProceso;
        length++;
    }

    /**
     * Método sacar
     * @brief Saca el proceso que se encuentre hasta delante de la lista.
     * @return Regresa el proceso de enfrente.
     */
    public Proceso sacar() {
        
        if ( this.primero != null ) {
            Nodo temp = this.primero;
            
            if ( this.primero.getSiguiente() == null ) {
    
                this.primero = null;
                this.ultimo = null;
                this.length--;
    
                return temp.getProceso();
            }

            this.cursor = this.primero;
            for ( int i = 0; i < this.length-1; i++ ) {
                this.cursor.getSiguiente().setIndex(this.cursor.getIndex());;
                this.cursor = this.cursor.getSiguiente();
            }

            this.primero = this.primero.getSiguiente();
            this.primero.setAnterior( null );
            this.length--;

            return temp.getProceso();
        }

        return null;
    }

    // public Proceso sacar( int index ) {
    //     this.cursor = this.primero;

    //     if ( this.cursor == null || this.length < index ) {
    //         return null;
    //     }

    //     for ( int i = 0; i < index; i++ ) {
    //         this.cursor = this.cursor.getSiguiente();
    //     }

    //     Nodo tempAnt = this.cursor.getAnterior();
    //     Nodo tempSig = this.cursor.getSiguiente();

    //     tempAnt.setSiguiente(tempSig);
    //     tempSig.setAnterior(tempAnt);
        
    //     tempAnt.getAnterior().setSiguiente(tempAnt);
    //     tempSig.getSiguiente().setAnterior(tempSig);

    //     this.length--;

    //     return this.cursor.getProceso();
    // }

    /**
     * Método peak.
     * @brief Obtiene los datos del procesos que esté hasta enfrente sin sacarlo de la lista.
     * @return Regresa los datos del proceso de hasta enfrente.
     */
    public Proceso peak() {
        return this.primero.getProceso();
    }

    // public Proceso peak( int index ) {
    //     this.cursor = this.primero;
    
    //     if ( this.cursor == null || this.length <= index ) {
    //         return null;
    //     }
        
    //     for ( int i = 0; i < this.length; i++ ) {
            
    //         if ( this.cursor.getIndex() == index ) {
    //             System.out.println("El indice es: " + this.cursor.getIndex() + " y es igual a: " + index);
    //             return this.cursor.getProceso();
    //         }

    //         System.out.println("El indice del siguiente es: " + this.cursor.getSiguiente().getIndex());
    //         this.cursor = this.cursor.getSiguiente();
    //         System.out.println("El indice se actualiza a: " + this.cursor.getIndex());
    //     }
            
    //     return null;
    // }

    /**
     * Método listar.
     * @brief Lista todos los nombres de los procesos en orden en una cadena.
     * @return Regresa la cadena de los nombres de los procesos en la lista.
     */
    public String listar() {

        this.cursor = this.primero;
        String cadena = "";

        for ( int i = 0; i < this.length; i++ ) {
            cadena += this.cursor.getProceso().getNombre() + ", ";
            this.cursor = this.cursor.getSiguiente();
        }

        return cadena;
    }

    /**
     * Método length.
     * @brief Getter del atributo length.
     * @return Regresa el tamaño actual de la lista.
     */
    public int getLength() {
        return length;
    }
}
