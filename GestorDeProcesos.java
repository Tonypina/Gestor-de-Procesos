import java.util.*;
import clases.*;
import clases.lista.*;

/**
 * Gestor de Procesos
 */
public class GestorDeProcesos {

    static final int QUANTUM = 2;
    static int tiempo = 0;
    static String gant = new String();

    static Memoria memoria = new Memoria(1024);
    static Lista colaDeProcesos = new Lista();
    static Lista ProcFin = new Lista();

    /**
     * Main de prueba.
     */
     public static void main(String[] args) {

        Proceso p1 = new Proceso( 1, "A", 250, 4, 1 );
        Proceso p2 = new Proceso( 2, "B", 400, 8, 2 );
        Proceso p3 = new Proceso( 3, "C", 200, 2, 3 );
        Proceso p4 = new Proceso( 4, "D", 400, 4, 5 );
        Proceso p5 = new Proceso( 5, "E", 100, 5, 8 );
        Proceso p6 = new Proceso( 6, "F", 600, 1, 22 );

        colaDeProcesos.insertar(p1);
        colaDeProcesos.insertar(p2);
        colaDeProcesos.insertar(p3);
        colaDeProcesos.insertar(p4);
        colaDeProcesos.insertar(p5);
        colaDeProcesos.insertar(p6);

        System.out.println("La cola de procesos empieza como: " + colaDeProcesos.listar());

        colaDeProcesos = colaDeProcesos.merge_sort();
        System.out.println("Después de ordenar la lista: " + colaDeProcesos.listar());
        tiempo = colaDeProcesos.peak().getTiempoLlegada();

        planificadorMedianoPlazo();
        planificadorCortoPlazo();

        System.out.println("El diagrama de gant final es: " + gant);
        
        promedios();
    }

    /**
     * Main real.
     */
    /*
    public static void main(String[] args) {
         System.out.println(colaDeProcesos.getLength());
        
        inicio();
        lectura();
        System.out.println("La cola de procesos empieza como: " + colaDeProcesos.listar());
        int tamano = colaDeProcesos.getLength();
        

        // Para el correcto funcionamiento del algoritmo, al inicio debemos
        // ordenar los procesos por su tiempo de llegada.
        colaDeProcesos = colaDeProcesos.merge_sort();
        System.out.println("Después de ordenar la lista: " + colaDeProcesos.listar());

        planificadorMedianoPlazo();
        planificadorCortoPlazo();

        System.out.println("El diagrama de gant final es: " + gant);
        
        promedios(tamano);
     }

    */

    public static void lectura(){
        
        imprimir();
        
        int n;

        Scanner entrada = new Scanner(System.in);
        System.out.print("Cuántos procesos desea crear?\n");

        n = entrada.nextInt();
        
        Proceso proceso[] = new Proceso[n];
        int id=1;
        for (int i =0;i<n;i++){
            System.out.print("Deme en nombre del proceso " + id +"\n");
            String nombre=entrada.next();
            
            System.out.print("Deme el tiempo de rafaga del proceso " + nombre +"\n");
            int trafaga=entrada.nextInt();
            
            System.out.print("Deme el tiempo de llegada del proceso " + nombre +"\n");
            int tllegada=entrada.nextInt();
            
            System.out.print("Deme el tamaño del proceso " + nombre +"\n");
            int tamano= entrada.nextInt();

            proceso[i]=new Proceso(id, nombre, tamano ,trafaga,tllegada);
            
            colaDeProcesos.insertar(proceso[i]);
            
            id++;
            System.out.print("\n\n\n");
            
        }
        System.out.print("\033[H\033[2J");
        System.out.flush();

        entrada.close();
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

        // teclado.close();
    }
    /**
    
     */
    public static void imprimir(){
        System.out.print("=======================================\n");
        System.out.print("Roun Robin \t Equpo 2 \n");
        System.out.print("=======================================\n");
    }

    /**
     * Planificador de memoria.
     */
    public static void planificadorMedianoPlazo() {

        int tamanoDeLaCola = colaDeProcesos.getLength();
        
        for ( int i = 0; i < tamanoDeLaCola ; i++ ) {

            if ( colaDeProcesos.peak() != null && colaDeProcesos.peak().getTamano() < memoria.getTamano() ) { 

                if ( colaDeProcesos.peak().getTiempoLlegada() <= tiempo ) {    
                    
                    System.out.println("Cargando en memoria: " + colaDeProcesos.peak().getNombre());
                    memoria.cargar(colaDeProcesos.sacar());
                    System.out.println("Se actualizo la memoria: " + memoria.getColaDeProcesosListos().listar());
                    System.out.println("El tamaño actual de la memoria es: " + memoria.getTamano());
                    
                } else {

                    colaDeProcesos.insertar(colaDeProcesos.sacar());
                    System.out.println("Se actualizo la cola de procesos: " + colaDeProcesos.listar());
                }
            
            } else { return; } 
        }
    }
    /**
     * función planificadorCortoPlazo
     * @brief Realiza el proceso de Round Robin, usando mi cola de procesos.
     
     */
    public static void planificadorCortoPlazo() {

        //el RoundRobin
        while (true){
            
            boolean bandera = true;

            for (int i = 0; i < memoria.getColaDeProcesosListos().getLength(); i++){
                System.out.println("Tiempo actual: " + tiempo);

                System.out.print("\nNuestro diagrama de gant seria:"+gant+"\n");

                Proceso proceso_i = memoria.getColaDeProcesosListos().peak();
                System.out.println("Proceso a cargar en CPU: " + proceso_i.getNombre());
                    
                    if (proceso_i.get_tiempoLlegada() <= QUANTUM){
                        
                        if (proceso_i.get_rafaga() > 0){
                         
                            bandera = false;

                            if (proceso_i.getRafaga() > QUANTUM){
                                
                                proceso_i = memoria.sacar();
                                System.out.println("Se actualizo la memoria: " + memoria.getColaDeProcesosListos().listar());
                                
                                tiempo += QUANTUM;
                                proceso_i.set_rafaga(proceso_i.get_rafaga() - QUANTUM); 
                                proceso_i.set_tiempoLlegada(proceso_i.get_tiempoLlegada() + QUANTUM);
                                
                                colaDeProcesos.insertar(proceso_i);
                                System.out.println("La cola de procesos es: " + colaDeProcesos.listar()); 
                                planificadorMedianoPlazo();
                                
                                proceso_i.setTiempoEjecutado(QUANTUM);
                            
                                gant += ","+proceso_i.getNombre();
                                
                            } else {
                                
                                proceso_i.setTiempoCarga(tiempo); 
                                
                                proceso_i = memoria.sacar();
                                System.out.println("Se actualizo la memoria: " + memoria.getColaDeProcesosListos().listar());
                                
                                tiempo += proceso_i.get_rafaga();
                             
                                /////////////////////////
                                proceso_i.setTiempoSalida(tiempo);
                                
                                System.out.print("Tiempos"+proceso_i.getNombre()+":");
                                System.out.print("\tTiempo ejec: "+proceso_i.getTiempoEjecutado()+ "\n");
                                
                                System.out.println("\nTiempo salida proceso: "+ proceso_i.getTiempoSalida()+ "\n");
                                System.out.print("\tTiempo de carga: "+proceso_i.getTiempoCarga()+"\n");
                                ProcFin.insertar(proceso_i);
                                
                                proceso_i.setTiempoTerminado(tiempo - proceso_i.getTiempoLlegada());
                                
                                proceso_i.setTiempoEspera(tiempo - proceso_i.getRafaga() - proceso_i.getTiempoLlegada());
                            
                                proceso_i.set_rafaga(0);

                                planificadorMedianoPlazo();
                                gant +=","+proceso_i.getNombre();

                            }
                        }

                    } else if (proceso_i.get_tiempoLlegada() > QUANTUM){
                        
                        for (int j = 0; j < memoria.getColaDeProcesosListos().getLength(); j++){
                            
                            Proceso proceso_j = memoria.getColaDeProcesosListos().peak();
                            
                            if (proceso_j.get_tiempoLlegada() < proceso_i.get_tiempoLlegada()){
                                
                                if (proceso_j.get_rafaga() > 0){
                                    
                                    bandera = false;
                                    
                                    if (proceso_j.get_rafaga() > QUANTUM){
                                        
                                        proceso_j.setTiempoCarga(tiempo);
                                        
                                        proceso_j = memoria.sacar();
                                        
                                        tiempo += QUANTUM;
                                        proceso_j.set_rafaga(proceso_j.get_rafaga() - QUANTUM);
                                        proceso_j.set_tiempoLlegada(proceso_j.get_tiempoLlegada() + QUANTUM);
                                        
                                        colaDeProcesos.insertar(proceso_j);
                                        
                                        planificadorMedianoPlazo();
                                        gant += ","+proceso_j.getNombre();
                                        
                                        
                                        proceso_j.setTiempoEjecutado(QUANTUM);
                                        
                                        
                                    } else {
                                        
                                        
                                        proceso_j = memoria.sacar();
                                        tiempo += proceso_j.get_rafaga();
                                        
                                        proceso_j.setTiempoSalida(tiempo);
                                        System.out.print("Tiempos"+proceso_i.getNombre()+":");
                                        System.out.print("\tTiempo ejec: "+proceso_j.getTiempoEjecutado()+"\n");
                                        
                                        System.out.print("\tTiempo de carga: "+proceso_j.getTiempoCarga()+"\n");
                                        System.out.println("\nTiempo salida proceso: "+ proceso_j.getTiempoSalida()+ "\n");
                                        ProcFin.insertar(proceso_j);
                                        
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
                                proceso_i.set_rafaga(proceso_i.get_rafaga() - QUANTUM); 
                                proceso_i.set_tiempoLlegada(proceso_i.get_tiempoLlegada() + QUANTUM);
                                
                                
                                colaDeProcesos.insertar(proceso_i);
                                planificadorMedianoPlazo();
                                
                                gant += ","+proceso_i.getNombre();
                                
                                proceso_i.setTiempoEjecutado(QUANTUM);
                                
                                
                            } else {
                                proceso_i.setTiempoCarga(tiempo);
                                
                                
                                proceso_i = memoria.sacar();
                                
                                
                                tiempo += proceso_i.get_rafaga();
                                
                                proceso_i.setTiempoSalida(tiempo);
                                System.out.print("Tiempos"+proceso_i.getNombre()+":");
                                System.out.print("\tTiempo ejec: "+proceso_i.getTiempoEjecutado());
                                
                                System.out.print("\tTiempo de carga: "+proceso_i.getTiempoCarga()+"\n");
                                System.out.println("\nTiempo salida proceso: "+ proceso_i.getTiempoSalida()+ "\n");
                                
                                ProcFin.insertar(proceso_i);
                                
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
            }
            
            if(bandera){
                break;
            }
        
        }
    }
    /**
     * función promedios
     * @brief Realiza el proceso de obtener los promedios despues de que se obtuvieron los tiempos necesarios en el planificador de corto plaza.
     
     */
    public static void promedios(){

        float tresp = 0,
              tesp = 0,
              tejec = 0,
              carga,
              ejec,
              salida,
              tllegada;

        System.out.println("La cola con los tiempos finales es: "+ProcFin.listar());

        ProcFin = ProcFin.merge_sort();
        ProcFin.setCursor( ProcFin.getPrimero() );
        
        for(int i = 0; i < ProcFin.getLength(); i++){

            carga = ProcFin.getCursor().getProceso().getTiempoCarga();
            ejec = ProcFin.getCursor().getProceso().getTiempoEjecutado();
            salida = ProcFin.getCursor().getProceso().getTiempoSalida();
            tllegada = ProcFin.getCursor().getProceso().getTiempoLlegada();
            
            System.out.print("\nProceso: " + ProcFin.getCursor().getProceso().getNombre());
            System.out.print("\n \t Tiempo de carga: "+ carga + "\n \t Tiempo de ejec: "+ ejec +"\n \t Tiempo de salida: "+salida+"\n \t Tiempo de llegada: "+tllegada);
            
            tesp += (carga - tllegada - ejec);
            tejec += (salida-tllegada);
            tresp += (carga - tllegada);

            ProcFin.setCursor(ProcFin.getCursor().getSiguiente());    
        }

        tresp = (tresp / ProcFin.getLength());
        tejec = (tejec/ProcFin.getLength());
        tesp = (tesp/ProcFin.getLength());
        
        System.out.print("\n El tiempo promedio de espera es: " + tesp + "\n El tiempo de ejecución promedio es: "+tejec+"\n El tiempo respuesta es: "+tresp+"\n");

    }
}