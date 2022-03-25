package clases.lista;
import java.time.chrono.ThaiBuddhistChronology;

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

        this.ultimo.setSiguiente(nuevoProceso);
        nuevoProceso.setAnterior(this.ultimo);
        this.ultimo = nuevoProceso;
        length++;
    }

    public Proceso sacar() {
        
        if ( this.primero != null ) {
            Nodo temp = this.primero;
            
            if ( this.primero.getSiguiente() == null ) {
    
                this.primero = null;
                this.ultimo = null;
                this.length--;
    
                return temp.getProceso();
            }

            this.primero = this.primero.getSiguiente();
            this.primero.setAnterior( null );
            this.length--;

            return temp.getProceso();
        }

        return null;
    }

    // public Proceso peak( int index ) {
    //     this.cursor = this.primero;

    //     if ( index > this.length) {
    //         return null;
    //     }

    //     for ( int i = 0; i < this.length; i++ ) {
    //         if ( i == index ) {
    //             return this.cursor.getProceso();
    //         }
    //         this.cursor = this.cursor.getSiguiente();
    //     }

    //     return null;
    // }

    public Proceso peak() {
        return this.primero.getProceso();
    }

    public String listar() {

        this.cursor = this.primero;
        String cadena = ",";

        for ( int i = 0; i < this.length; i++ ) {
            cadena += this.cursor.getProceso().getNombre() + ", ";
            this.cursor = this.cursor.getSiguiente();
        }

        return cadena;
    }

    public int getLength() {
        return length;
    }
}
