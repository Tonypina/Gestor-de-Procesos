
import lista.*;
import procesos.*;

public class GestorDeProcesos {

    public static void main(String[] args) {
        // System.out.println("Hola");

        Proceso p1 = new Proceso( 1, "P1", 10, 15, 3, 2 );
        Proceso p2 = new Proceso( 2, "P2", 10, 15, 3, 3 );
        Proceso p3 = new Proceso( 3, "P3", 10, 15, 3, 1 );
        Proceso p4 = new Proceso( 4, "P4", 10, 15, 3, 4 );

        Lista colaDeProcesos = new Lista();
        

        
        colaDeProcesos.insertar(p1);
        colaDeProcesos.insertar(p2);
        colaDeProcesos.insertar(p3);
        colaDeProcesos.insertar(p4);

        colaDeProcesos.listar();
            //Aquí están los datos de cada proceso, como verán cree unos nuevos jaja
        String nombres[]={"P1","P2","P3","P4"};
        int tllegada[]={0,1,2,3};
        int trafaga[]={10,4,5,3};
        int quantum=2;
        
        
        planificador(nombres,tllegada,trafaga,quantum);
        

        
    }

public static void planificador(String nombres[], int tllegadao[],int trafagao[], int quantum){ 
        int tiempo=0;
        //Para guardar mi diagrama de gant
        String gant=new String();
        
          
        int tiempoespera[]= new int[nombres.length];
        int tterminado[]=new int [nombres.length];
        
        //Guardamos los tiempos orginales y los asignamos en otros para poderlos trabajar
        int tllegada[]=new int[tllegadao.length];
        int trafaga[]=new int[trafagao.length];
        
        for (int i = 0; i < tllegadao.length; i++) { 
            tllegada[i] = tllegadao[i]; 
            trafaga[i] = trafagao[i]; 
        }

  
        while (true) { 
            boolean bandera=true;
            for (int i=0; i<nombres.length ;i++) { 
                System.out.print("\nNuestro diagrama de gant sería:"+gant+"\n");
                
                //Nos fijamos si el tiempo de llegada del proceso en el que estamos está dentro del tiempo en general
                if(tllegada[i]<=tiempo) { 
                    if (tllegada[i]<=quantum) { //Si el proceso llega al tiempo en el que vamos
                        if(trafaga[i]>0) { //Para ver si ejecutamo esto y si se nos llegó a colar un trafaga que sea 0
                            bandera=false;
                            if (trafaga[i]>quantum) { //Verificamos que el cuantum sea menor que el rafaga del proceso
  
                                tiempo=tiempo+quantum; //Vamos sumando el tiempo con el quantum ejecutado
                                trafaga[i]=trafaga[i]-quantum; //Le restamos lo que se ejecutó en este ciclo
                                tllegada[i]=tllegada[i]+quantum; //Creamos un nuevo tiempo de llegada para el siguiente ciclo
                                      
                                gant += ","+nombres[i];
                            } 
                            else { 
  
                                //Para terminar mi proceso
                                tiempo=tiempo+trafaga[i];
                                //Tiempo terminado
                                tterminado[i]=tiempo-tllegadao[i];
                                //tiempo espera
                                tiempoespera[i]=tiempo-trafagao[i]-tllegadao[i];
                                trafaga[i]=0;
                                gant +=","+nombres[i];
                            } 
                        } 
                    } 
                    else if (tllegada[i]>quantum) { //El tiempo de llegada 
  
                        //Si el proceso llega después que el quantum, seguimos con los procesos que sí se puedan
                        for (int j=0; j<nombres.length;j++){

                            if (tllegada[j]<tllegada[i]) { 
                                if (trafaga[j]>0) { 
                                    bandera=false; 
                                    if (trafaga[j]>quantum) { 
                                        tiempo=tiempo+quantum;
                                        trafaga[i]=trafaga[i]-quantum; //Le restamos lo que se ejecutó en este ciclo
                                        tllegada[i]=tllegada[i]+quantum;
                                        gant += ","+nombres[i];
                                    } 
                                    else { 
                                        //Para terminar mi proceso
                                        tiempo=tiempo+trafaga[j];
                                        //Tiempo terminado
                                        tterminado[j]=tiempo-tllegadao[j];
                                        //tiempo espera
                                        tiempoespera[j]=tiempo-trafagao[j]-tllegadao[j];
                                        trafaga[j]=0;
                                        gant +=","+nombres[j];
                                    } 
                                } 
                            } 
                        } 
  
                        //ejecutamos todo lo anterior, entonces, sí mi 
                        if (trafaga[i]>0) { 
                            bandera=false;
  
                            if (trafaga[i]>quantum) { 
                                tiempo=tiempo+quantum;
                                trafaga[i]=trafaga[i]-quantum; //Le restamos lo que se ejecutó en este ciclo
                                tllegada[i]=tllegada[i]+quantum;
                                gant += ","+nombres[i];
                            } 
                            else { 
                               //Para terminar mi proceso
                                 tiempo=tiempo+trafaga[i];
                                 //Tiempo terminado
                                 tterminado[i]=tiempo-tllegadao[i];
                                 //tiempo espera
                                 tiempoespera[i]=tiempo-trafagao[i]-tllegadao[i];
                                 trafaga[i]=0;
                                 gant +=","+nombres[i];
                            } 
                        } 
                    } 
                } 
  
                else if (tllegada[i]>tiempo) { //si el tiempo en el que llega es mayor al que estamos, lo pasa, por ahora.
                    tiempo++;
                    i--;
                } 
            } 
            if (bandera) { 
                break; 
            } 
        } 
  
        System.out.println("\n\n\nNombre\tTiempoEjecutado\tTiempoEspera"); 
        int promtejec=0,promtespera=0;
        for (int i=0;i<nombres.length;i++){
            System.out.print(""+nombres[i]+"\t"+tterminado[i]+"\t"+tiempoespera[i]+"\n");
            promtejec=promtejec+tterminado[i];
            promtespera=promtespera+tiempoespera[i];
        }
        
        System.out.print("\n\nPromedio ejec"+(float)promtejec/nombres.length);
        System.out.print("\nPromedio espera"+(float)promtespera/nombres.length);
        System.out.print("\nNuestro diagrama de gant sería:"+gant);
    } 
public static void memoria() {
        //Hola huevos
}
}