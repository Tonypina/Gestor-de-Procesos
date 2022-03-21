import java.util.*;
import lista.*;
import procesos.*;

public class GestorDeProcesos {

    public static void main(String[] args) {
        // System.out.println("Hola");

        Proceso p1 = new Proceso( 1, "P1", 10, 15, 3, 2 );
        Proceso p2 = new Proceso( 2, "P2", 10, 15, 3, 2 );
        Proceso p3 = new Proceso( 3, "P3", 10, 15, 3, 2 );
        Proceso p4 = new Proceso( 4, "P4", 10, 15, 3, 2 );

        Lista colaDeProcesos = new Lista();

        colaDeProcesos.insertar(p1);
        colaDeProcesos.insertar(p2);
        colaDeProcesos.insertar(p3);
        colaDeProcesos.insertar(p4);

        colaDeProcesos.listar();
    }

    public static void planificador() {
        
    }

    public static void memoria() {
        
    }
}