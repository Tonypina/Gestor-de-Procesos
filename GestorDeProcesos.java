import java.net.NetPermission;
import java.util.*;
import clases.*;
import clases.lista.*;

/**
 * Gestor de Procesos
 */
public class GestorDeProcesos {

    static final int QUANTUM = 2;
    static Memoria memoria;
    static Lista colaDeProcesos;

    public GestorDeProcesos() {
        memoria = new Memoria(1024);
        colaDeProcesos = new Lista();
    }

    public static void main(String[] args) {

        Proceso p1 = new Proceso( 1, "P1", 10, 15, 2 );
        Proceso p2 = new Proceso( 2, "P2", 10, 15, 2 );
        Proceso p3 = new Proceso( 3, "P3", 10, 15, 2 );
        Proceso p4 = new Proceso( 4, "P4", 10, 15, 2 );

        colaDeProcesos.insertar(p1);
        colaDeProcesos.insertar(p2);
        colaDeProcesos.insertar(p3);
        colaDeProcesos.insertar(p4);
    }

    public static void planificadorMedianoPlazo() {
        // Carga en memoria los procesos que quepan
        for ( int i = 0; i < colaDeProcesos.getLength(); i++ ) {
            if ( !memoria.cargar(colaDeProcesos.sacar()) ){
                break;
            }
        }
    }

    public static void planificador() {

        int tiempo = 0;

        //Para guardar el diagrama de gant
        String gant = new String();

        //el RoundRobin
        while (true){
            
            boolean bandera = true;

            for (int i = 0; i < memoria.getColaDeProcesosListos().getLength(); i++){
                
                System.out.print("\nNuestro diagrama de gant sería:"+gant+"\n");

                Proceso proceso_i = memoria.sacar();
                planificadorMedianoPlazo();

                if (proceso_i.get_tiempoLlegada() <= tiempo){
                    
                    if (proceso_i.get_tiempoLlegada() <= QUANTUM){
                        
                        if (proceso_i.get_rafaga() > 0){
                         
                            bandera = false;

                            if (proceso_i.getRafaga() > QUANTUM){ //Verificamos que el QUANTUM sea menor que el rafaga del proceso
                                
                                tiempo += QUANTUM;
                                proceso_i.set_rafaga(proceso_i.get_rafaga() - QUANTUM); //Le restamos lo que se ejecutó en este ciclo
                                proceso_i.set_tiempoLlegada(proceso_i.get_tiempoLlegada() + QUANTUM);

                                // Se vuelve a formar para competir por memoria
                                colaDeProcesos.insertar(proceso_i);
                                gant += ","+proceso_i.getNombre();

                            } else {

                                //Para terminar mi proceso
                                tiempo += proceso_i.get_rafaga();
                                //Tiempo terminado
                                proceso_i.setTiempoTerminado(tiempo - proceso_i.getTiempoLlegada());
                                //tiempo espera
                                proceso_i.setTiempoEspera(tiempo - proceso_i.getRafaga() - proceso_i.getTiempoLlegada());
                                proceso_i.set_rafaga(0);
                                gant +=","+proceso_i.getNombre();

                            }
                        }

                    } else if (proceso_i.get_tiempoLlegada() > QUANTUM){

                        colaDeProcesos.insertar(proceso_i);

                        //Si el proceso llega después que el QUANTUM, seguimos con los procesos que sí se puedan
                        for (int j = 0; j < memoria.getColaDeProcesosListos().getLength(); j++){
                            
                            Proceso proceso_j = memoria.sacar();
                            planificadorMedianoPlazo();

                            if (proceso_j.get_tiempoLlegada() < proceso_i.get_tiempoLlegada()){
                             
                                if (proceso_j.get_rafaga() > 0){
                             
                                    bandera = false;
                             
                                    if (proceso_j.get_rafaga() > QUANTUM){
                             
                                        tiempo += QUANTUM;
                                        proceso_j.set_rafaga(proceso_j.get_rafaga() - QUANTUM); //Le restamos lo que se ejecutó en este ciclo
                                        proceso_j.set_tiempoLlegada(proceso_j.get_tiempoLlegada() + QUANTUM);

                                        colaDeProcesos.insertar(proceso_j);
                                        gant += ","+proceso_j.getNombre();
                             
                                    } else {
                                        //Para terminar mi proceso
                                        tiempo += proceso_j.get_rafaga();
                                        //Tiempo terminado
                                        proceso_j.setTiempoTerminado(tiempo - proceso_j.getTiempoLlegada());
                                        //tiempo espera
                                        proceso_j.setTiempoEspera(tiempo - proceso_j.getRafaga() - proceso_j.getTiempoLlegada());
                                        proceso_j.set_rafaga(0);
                                        gant +=","+proceso_j.getNombre();
                                    }
                                }
                            }
                        }

                        if (proceso_i.get_rafaga() > 0){
                            bandera = false;
                            
                            if (proceso_i.get_rafaga() > QUANTUM){
                                tiempo += QUANTUM;
                                proceso_i.set_rafaga(proceso_i.get_rafaga() - QUANTUM); //Le restamos lo que se ejecutó en este ciclo
                                proceso_i.set_tiempoLlegada(proceso_i.get_tiempoLlegada() + QUANTUM);

                                colaDeProcesos.insertar(proceso_i);
                                gant += ","+proceso_i.getNombre();
                            
                            } else {
                                //Para terminar mi proceso
                                tiempo += proceso_i.get_rafaga();
                                //Tiempo terminado
                                proceso_i.setTiempoTerminado(tiempo - proceso_i.getTiempoLlegada());
                                //tiempo espera
                                proceso_i.setTiempoEspera(tiempo - proceso_i.getRafaga() - proceso_i.getTiempoLlegada());
                                proceso_i.set_rafaga(0);
                                gant +=","+proceso_i.getNombre();
                            }
                        }
                    }

                } else if(proceso_i.get_tiempoLlegada() > tiempo) { //si el tiempo en el que llega es mayor al que estamos, lo pasa, por ahora.
                    colaDeProcesos.insertar(proceso_i);
                    tiempo++;
                    i--;
                }  
            }
            
            if(bandera){
                break;
            }
        
        }
        
        System.out.println("\n\n\nNombre\tTiempoEjecutado\tTiempoEspera");
        
        int promtejec = 0,
            promtespera = 0;
        
        for (int i = 0; i < nombres.length; i++){
            System.out.print(""+nombres[i]+"\t"+tterminado[i]+"\t"+tiempoespera[i]+"\n");
            
            promtejec = promtejec + tterminado[i];
            promtespera = promtespera + tiempoespera[i];
        }

        System.out.print("\n\nPromedio ejec"+(float)promtejec/nombres.length);
        System.out.print("\nPromedio espera"+(float)promtespera/nombres.length);
        System.out.print("\nNuestro diagrama de gant sería:"+gant);
    }
}