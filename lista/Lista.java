package lista;
import procesos.Proceso;

public class Lista {
    private Nodo primero;
    private Nodo ultimo;

    public Lista() {
        this.primero = null;
        this.ultimo = null;
    }

    public void insertar( Proceso proceso ) {
        Nodo nuevoProceso = new Nodo( proceso );
        
        if ( this.primero == null ) {
            
            this.primero = nuevoProceso;
            this.ultimo = nuevoProceso;

        } else {

            Nodo temp = this.primero;
            
            nuevoProceso.setSiguiente( temp );
            temp.setAnterior( nuevoProceso );
            this.primero = nuevoProceso;
            this.ultimo = temp;
        }
    }

    public Proceso sacar( Proceso proceso ) {
        
        if ( this.primero != null && this.primero.getProceso().equals(proceso) ) {
            Nodo temp = this.primero;

            this.primero = this.primero.getSiguiente();
            this.primero.setAnterior( null );

            return temp.getProceso();
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
}
