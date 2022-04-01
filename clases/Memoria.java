package clases;
import clases.lista.*;

/**
 * Clase Memoria
 */
public class Memoria {
    private Lista colaDeProcesosListos;     
    private long tamano;                    

    /**
     * Constructor.
     * @param tamano Recibe el tamaño con el que se inicializará la memoria.
     */
    public Memoria( long tamano ) {
        this.colaDeProcesosListos = new Lista();
        this.tamano = tamano;
    }

    /**
     * Método getColaDeProcesosListos.
     * @brief Getter del atributo de tipo Lista colaDeProcesosListos.
     * @return Regresa la lista de procesos cargados en memoria.
     */
    public Lista getColaDeProcesosListos() {
        return this.colaDeProcesosListos;
    }

    /**
     * Getter del atributo tamano
     * @return Regresa el tamaño disponible en memoria.
     */
    public long getTamano() {
        return this.tamano;
    }

    /**
     * Método cargar.
     * @brief Carga un proceso en la lista de memoria.
     * @param proceso Recibe el proceso que se cargará en la memoria.
     * @return Regresa un valor de confirmación. Si se insertó = true, si no = false.
     */
    public boolean cargar( Proceso proceso ) {
        
        if ( this.tamano > proceso.getTamano() ) {          
            this.colaDeProcesosListos.insertar(proceso);    
            this.tamano -= proceso.getTamano();             
            
            return true;                                    
        }
        return false;                                       
    }

    /**
     * Método sacar.
     * @brief Saca el primer proceso formado en la lista de procesos listos para cargar a CPU.
     * @return Regresa el proceso que sale de memoria.
     */
    public Proceso sacar() {
 
        if ( this.colaDeProcesosListos.getLength() > 0 ) {          
            Proceso proceso = this.colaDeProcesosListos.sacar();    
            this.tamano += proceso.getTamano();                     

            return proceso;                                         
        }
        return null;                                                
    }
}