package clases.lista;
import clases.Proceso;

public class Lista {
    private Nodo primero;
    private Nodo ultimo;
    private Nodo cursor;
    private int length;

    public Lista() {
        this.primero = null;
        this.ultimo = null;
        this.length = 0;
    }

    public void insertar( Proceso proceso ) {
        Nodo nuevoProceso = new Nodo( proceso );
        
        if ( this.primero == null ) {
            
            this.primero = nuevoProceso;
            this.ultimo = nuevoProceso;
            this.length++;
            return;
        } 
        
        if ( this.primero.equals(this.ultimo) ) {

            this.primero.setSiguiente(nuevoProceso);
            nuevoProceso.setAnterior(this.primero);
            this.ultimo = nuevoProceso;
            this.length++;
            return;
        }

        Nodo temp = this.ultimo;
        temp.setSiguiente(nuevoProceso);
        temp.getAnterior().setSiguiente(temp);
        nuevoProceso.setAnterior(temp);
        length++;
    }

    public Proceso sacar() {
        
        if ( this.primero != null ) {
            Nodo temp = this.primero;

            this.primero = this.primero.getSiguiente();
            this.primero.setAnterior( null );
            this.length--;

            return temp.getProceso();
        }

        return null;
    }

    public Proceso peak( int index ) {
        this.cursor = this.primero;

        if ( index > this.length) {
            return null;
        }

        for ( int i = 0; i < this.length; i++ ) {
            if ( i == index ) {
                return this.cursor.getProceso();
            }
            this.cursor = this.cursor.getSiguiente();
        }

        return null;
    }

    public void listar() {

        Nodo temp = this.primero;

        while ( temp != null ) {
            System.out.print( temp.getProceso().getNombre() + ", " );
            temp = temp.getSiguiente();
        }
    }

    public int getLength() {
        return length;
    }
}
