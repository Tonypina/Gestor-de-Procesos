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

    /**
     * Método peak.
     * @brief Obtiene los datos del procesos que esté hasta enfrente sin sacarlo de la lista.
     * @return Regresa los datos del proceso de hasta enfrente.
     */
    public Proceso peak() {
        return this.primero.getProceso();
    }

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
     * Método merge_sort
     * 
     * @brief Ordena la lista por tiempo de llegada de los procesos
     * @return Regresa la lista ordenada.
     */
    public Lista merge_sort(){

        this.cursor = this.primero;

        // Caso base
        if ( this.length <= 1 )    
            return this;

        // Caso recursivo
        Lista izq = new Lista();
        Lista der = new Lista();

        for ( int i = 0; i < this.length; i++ ) {
            
            if ( i < (this.length)/2 )
                izq.insertar(this.cursor.getProceso());
            
            else
                der.insertar(this.cursor.getProceso());

            this.cursor = this.cursor.getSiguiente();
        }

        izq = izq.merge_sort();
        der = der.merge_sort();

        return merge( izq, der );
    }

    private Lista merge( Lista izq, Lista der ) {
        Lista resultado = new Lista();

        while ( izq.getLength() > 0 && der.getLength() > 0 ) {
            if ( izq.getPrimero().getProceso().getTiempoLlegada() <= der.getPrimero().getProceso().getTiempoLlegada() )
                resultado.insertar( izq.sacar() );
            else
                resultado.insertar( der.sacar() );
        }

        while ( izq.getLength() > 0 ) {
            resultado.insertar( izq.sacar() );
        }

        while ( der.getLength() > 0 ) {
            resultado.insertar( der.sacar() );
        }

        return resultado;
    }

    /**
     * Método getLength.
     * @brief Getter del atributo length.
     * @return Regresa el tamaño actual de la lista.
     */
    public int getLength() {
        return length;
    }

    /**
     * Método getPrimero
     * @brief Getter del primer nodo de la lista.
     * @return Regresa el primer nodo de la lista.
     */
    public Nodo getPrimero() {
        return primero;
    }
}
