import java.util.*;
import clases.*;
import clases.lista.*;

/**
 * Gestor de Procesos
 */
public class GestorDeProcesos {

    static final int QUANTUM = 2;
    static int tiempo = 0;
    static Memoria memoria = new Memoria(1024);
    static Lista colaDeProcesos = new Lista();

    // public static void main(String[] args) {

    //     // System.out.println("Menu");
    //     // Scanner sc = new Scanner(System.in);

    //     // String nombre = sc.nextLine();

    //     Proceso p1 = new Proceso( 1, "P1", 1, 10, 0 );
    //     Proceso p2 = new Proceso( 2, "P2", 1, 4, 1 );
    //     Proceso p3 = new Proceso( 3, "P3", 1, 5, 2 );
    //     Proceso p4 = new Proceso( 4, "P4", 1, 3, 3 );

    //     colaDeProcesos.insertar(p1);
    //     colaDeProcesos.insertar(p2);
    //     colaDeProcesos.insertar(p3);
    //     colaDeProcesos.insertar(p4);

    //     System.out.println(colaDeProcesos.getLength());

    //     System.out.println("La cola de procesos empieza como: " + colaDeProcesos.listar());


    //     planificadorMedianoPlazo();
    //     planificador();

    //     // Imprimir tiempos
    // }

    public static void main(String[] args) {
        System.out.println(colaDeProcesos.getLength());
        
        inicio();
        lectura();
        System.out.println("La cola de procesos empieza como: " + colaDeProcesos.listar());
        planificadorMedianoPlazo();
        planificador();

        // Imprimir tiempos
    }
    public static void lectura(){
        
        imprimir();
        
        Scanner entrada = new Scanner (System.in);
        System.out.print("Cuántos procesos desea crear?\n");

        int n=entrada.nextInt();
        
        Proceso proceso[] = new Proceso[n];
        int id=1;
        for (int i =0;i<n;i++){
            System.out.print("Deme en nombre del proceso " + id +"\n");
            String nombre=entrada.next();
            
            System.out.print("Deme el tiempo de rafaga del proceso " + nombre +"\n");
            int trafaga=entrada.nextInt();
            
            System.out.print("Deme el tiempo de llegada del proceso " + nombre +"\n");
            int tllegada=entrada.nextInt();
            
            proceso[i]=new Proceso(id, nombre,1 ,trafaga,tllegada);
            
            colaDeProcesos.insertar(proceso[i]);
            
            id++;
            System.out.print("\n\n\n");
            
        }
        System.out.print("\033[H\033[2J");
        System.out.flush();
      
    }
    
    public static void inicio(){
        imprimir();
        Scanner teclado = new Scanner (System.in);
        String seguir;
        System.out.print("Integrantes: \n \tColón Palacios Emmanuel \n \tPiña Rossette Marco Antonio \n \tRoldán Sánchez Alexis \n \tValencia Mancera Erick Samuel \n Presione una tecla para continuar...");
        try
        {
            seguir = teclado.nextLine();
        }
        catch(Exception e)
        {}
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void imprimir(){
        System.out.print("=======================================\n");
        System.out.print("Roun Robin \t Equpo 2 \n");
        System.out.print("=======================================\n");
    }

    public static void planificadorMedianoPlazo() {
        // Carga en memoria los procesos que quepan
        // for ( int i = 0; i < colaDeProcesos.getLength(); i++ ) {
        //     System.out.println("Cargando en memoria: " + colaDeProcesos.peak().getNombre());
        //     if ( colaDeProcesos.peak() != null && colaDeProcesos.peak().getTamano() < memoria.getTamano() ) {
        //         if ( colaDeProcesos.peak().getTiempoLlegada() <= tiempo ) {
        //             memoria.cargar(colaDeProcesos.sacar());
        //             // System.out.println("Se actualizo la memoria: " + memoria.getColaDeProcesosListos().listar());
        //         }
        //     } else { return; }
        // }

        for ( int i = 0; i < colaDeProcesos.getLength(); i++ ) {
            if ( colaDeProcesos.peak(i) != null && colaDeProcesos.peak(i).getTamano() < memoria.getTamano() ) {
                if ( colaDeProcesos.peak(i).getTiempoLlegada() <= tiempo ) {
                    System.out.println("Cargando en memoria: " + colaDeProcesos.peak(i).getNombre());
                    memoria.cargar(colaDeProcesos.sacar());
                    System.out.println("Se actualizo la memoria: " + memoria.getColaDeProcesosListos().listar());
                } else { 
                    colaDeProcesos.insertar(colaDeProcesos.sacar());
                    // memoria.cargar(colaDeProcesos.sacar());
                    // return;
                }
            } else { return; }
        }
    }

    public static void planificador() {


        //Para guardar el diagrama de gant
        String gant = new String();

        //el RoundRobin
        while (true){
            
            boolean bandera = true;

            for (int i = 0; i < memoria.getColaDeProcesosListos().getLength(); i++){
                
                System.out.print("\nNuestro diagrama de gant sería:"+gant+"\n");

                Proceso proceso_i = memoria.getColaDeProcesosListos().peak();
                System.out.println("Proceso a cargar en CPU: " + proceso_i.getNombre());

                // if (proceso_i.get_tiempoLlegada() <= tiempo){
                    
                    if (proceso_i.get_tiempoLlegada() <= QUANTUM){
                        
                        if (proceso_i.get_rafaga() > 0){
                         
                            bandera = false;

                            if (proceso_i.getRafaga() > QUANTUM){ //Verificamos que el QUANTUM sea menor que el rafaga del proceso
                                
                                proceso_i = memoria.sacar();
                                System.out.println("Se actualizo la memoria: " + memoria.getColaDeProcesosListos().listar());
                                
                                tiempo += QUANTUM;
                                proceso_i.set_rafaga(proceso_i.get_rafaga() - QUANTUM); //Le restamos lo que se ejecutó en este ciclo
                                proceso_i.set_tiempoLlegada(proceso_i.get_tiempoLlegada() + QUANTUM);
                                
                                colaDeProcesos.insertar(proceso_i);
                                System.out.println(colaDeProcesos.listar()); 
                                planificadorMedianoPlazo();

                                // Se vuelve a formar para competir por memoria
                                gant += ","+proceso_i.getNombre();

                            } else {

                                proceso_i = memoria.sacar();
                                
                                //Para terminar mi proceso
                                tiempo += proceso_i.get_rafaga();
                                //Tiempo terminado
                                proceso_i.setTiempoTerminado(tiempo - proceso_i.getTiempoLlegada());
                                //tiempo espera
                                proceso_i.setTiempoEspera(tiempo - proceso_i.getRafaga() - proceso_i.getTiempoLlegada());
                                proceso_i.set_rafaga(0);

                                planificadorMedianoPlazo();
                                gant +=","+proceso_i.getNombre();

                            }
                        }

                    } else if (proceso_i.get_tiempoLlegada() > QUANTUM){

                        //Si el proceso llega después que el QUANTUM, seguimos con los procesos que sí se puedan
                        for (int j = 0; j < memoria.getColaDeProcesosListos().getLength(); j++){
                            
                            Proceso proceso_j = memoria.getColaDeProcesosListos().peak();
                            
                            if (proceso_j.get_tiempoLlegada() < proceso_i.get_tiempoLlegada()){
                                
                                if (proceso_j.get_rafaga() > 0){
                                    
                                    bandera = false;
                                    
                                    if (proceso_j.get_rafaga() > QUANTUM){
                                        
                                        proceso_j = memoria.sacar();
                                        
                                        tiempo += QUANTUM;
                                        proceso_j.set_rafaga(proceso_j.get_rafaga() - QUANTUM); //Le restamos lo que se ejecutó en este ciclo
                                        proceso_j.set_tiempoLlegada(proceso_j.get_tiempoLlegada() + QUANTUM);
                                        
                                        colaDeProcesos.insertar(proceso_j);
                                        
                                        planificadorMedianoPlazo();
                                        gant += ","+proceso_j.getNombre();
                             
                                    } else {
                                        proceso_j = memoria.sacar();
                                        
                                        //Para terminar mi proceso
                                        tiempo += proceso_j.get_rafaga();
                                        //Tiempo terminado
                                        proceso_j.setTiempoTerminado(tiempo - proceso_j.getTiempoLlegada());
                                        //tiempo espera
                                        proceso_j.setTiempoEspera(tiempo - proceso_j.getRafaga() - proceso_j.getTiempoLlegada());
                                        proceso_j.set_rafaga(0);
                                
                                        planificadorMedianoPlazo();
                                        gant +=","+proceso_j.getNombre();
                                    }
                                }
                            }
                        }

                        if (proceso_i.get_rafaga() > 0){
                            bandera = false;
                            
                            if (proceso_i.get_rafaga() > QUANTUM){

                                proceso_i = memoria.sacar();
                                
                                tiempo += QUANTUM;
                                proceso_i.set_rafaga(proceso_i.get_rafaga() - QUANTUM); //Le restamos lo que se ejecutó en este ciclo
                                proceso_i.set_tiempoLlegada(proceso_i.get_tiempoLlegada() + QUANTUM);
                                
                                colaDeProcesos.insertar(proceso_i);
                                planificadorMedianoPlazo();
                                
                                gant += ","+proceso_i.getNombre();
                            
                            } else {

                                proceso_i = memoria.sacar();
                                
                                //Para terminar mi proceso
                                tiempo += proceso_i.get_rafaga();
                                //Tiempo terminado
                                proceso_i.setTiempoTerminado(tiempo - proceso_i.getTiempoLlegada());
                                //tiempo espera
                                proceso_i.setTiempoEspera(tiempo - proceso_i.getRafaga() - proceso_i.getTiempoLlegada());
                                proceso_i.set_rafaga(0);
                                planificadorMedianoPlazo();
                                
                                gant +=","+proceso_i.getNombre();
                            }
                        }
                    }

                // } else if(proceso_i.get_tiempoLlegada() > tiempo) { //si el tiempo en el que llega es mayor al que estamos, lo pasa, por ahora.
                //     colaDeProcesos.insertar(proceso_i);
                //     tiempo++;
                //     i--;
                // }  
            }
            
            if(bandera){
                break;
            }
        
        }
        
        // System.out.println("\n\n\nNombre\tTiempoEjecutado\tTiempoEspera");
        
        // int promtejec = 0,
        //     promtespera = 0;
        
        // for (int i = 0; i < nombres.length; i++){
        //     System.out.print(""+nombres[i]+"\t"+tterminado[i]+"\t"+tiempoespera[i]+"\n");
            
        //     promtejec = promtejec + tterminado[i];
        //     promtespera = promtespera + tiempoespera[i];
        // }

        // System.out.print("\n\nPromedio ejec"+(float)promtejec/nombres.length);
        // System.out.print("\nPromedio espera"+(float)promtespera/nombres.length);
        // System.out.print("\nNuestro diagrama de gant sería:"+gant);
    }
}