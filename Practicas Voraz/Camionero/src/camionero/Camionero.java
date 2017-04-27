/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package camionero;

import java.util.ArrayDeque;
 /**
     * Esta clase contiene el algoritmo que se ha de seguir para resolver el problema.
     * Se utiliza una Cola como estructura de datos para almacenar las distancias entre
     * gasolineras (ArrayDeque<>), porque imagino que recuperar un elemento  de un array (la* 
     * alternativa) no tiene un coste constante, si no que dependerá del número de
     * elementos en la matriz, y podría incidir negativamente en el tiempo de ejecución del algoritmo.
     * En cambio, recuperar la cabeza de una cola, que es lo que haremos en este caso,
     * tiene un coste temporal constante O(1) sea cual sea el tamaño de esa cola.*
     * @author danie
     */
    
public class Camionero {
    /** Cola en la que se irán insertando los índices de las gasolineras visitadas */
    private ArrayDeque<Integer>solucion =new ArrayDeque<Integer>();
    /** Texto que se irá rellenando, si es necesario, con las diferentes decisiones que
     *  ha tomado el algoritmo durante la ejecución */
    private StringBuilder traza =new StringBuilder();
    /** Salto de línea independiente del SO en el que se ejecute el programa */
    private final String eol =System.getProperty("line.separator");
    /** Se usa para saber el tiempo que tarda la ejecución del algoritmo,
     * y es incluído en la traza */
    private double ms;
    
    /*** Constructor de la clase.
     * Directamente, contiene el algoritmo en sí mismo, no es necesario utilizar otro
     * método ya que siempre que creemos un objeto <Entrega> será con este fín.
     * @paramDG Cola con las distancias entre gasolineras adyacentes (de G(N) a G(N+1))
     * @paramN Distancia máxima en Km que se puede recorrer sin repostar
     * @paramG Número total de gasolineras en el recorrido
    */
    
    public Camionero(ArrayDeque<Integer>DG,int N,int G){ 
        this.ms =System.nanoTime();
        int km =0;
        
        // La gasolinera 0 (el punto de partida), se mostrará en la traza de una forma
        // diferente al resto. La añadimos aquí para no "ensuciar" demasiado el bucle
        // principal del algoritmo.
        if(Main.DEBUG)
            makeTraza(0,km,N,DG.peek(),false,"Salida desde gasolinera G0");
        
        // Contendrá dinámicamente la distancia entre la gasolinera en la que estamos y
        // la siguiente.
        int dist;
        
        for(int i =0;i <G-1;i++){
            dist =DG.poll();
            
            if((km +dist)>N){
               if(Main.DEBUG &&i !=0)
                   makeTraza(i,km,N,dist,true,"");
               solucion.addLast(i);km =0;
            }else if(Main.DEBUG &&i !=0){
                makeTraza(i,km,N,dist,false,"");
            }
            km +=dist;
        }
        
        // La última gasolinera no se contempla en el algoritmo (no tiene sentido
        // puesto que no hay más distancia que recorrer). Si tenemos que mostrar la
        // traza hay que añadir este paso aparte.
        
        if(Main.DEBUG)
            makeTraza(G-1,km,N,0,false,"Llegada a destino, "+"gasolinera G"+(G-1));
        
        // Calculamos el tiempo total que ha tardado en ejecutarse el algoritmo
        this.ms =(System.nanoTime()-this.ms)/1000000;
        System.out.println(ms +" ms con "+G+" gasolineras.");
    }
    
    /**
     * Devuelve la solución calculada por el algoritmo en forma de una cadena de texto
     * que contiene los índices de las gasolineras en las que se ha parado.
     * Si se debe mostrar la traza, también la devuelve en la misma cadena de texto.
     * @returnLa solución del problema, incluyendo la traza si es necesario
    */
    
    public String getOutput(){
        String out ="";
        if(solucion.isEmpty()){
            out ="Sin paradas, viaje directo :)";
        }else{
            while(!solucion.isEmpty()){
             out +=solucion.removeFirst()+" ";
        }
        if(Main.DEBUG){
            out +=eol+eol+"***** TRAZA DE EJECUCIÓN: *****"+eol+traza;
            out +="***** TIEMPO: ("+ms+" ms) *****";
        }
        return out;  
    }
    
    /**
     * Método auxiliar que sirve para no "ensuciar" mucho el código del algoritmo si
     * necesitamos generar la traza de su ejecución. Genera un texto con la parte de la
     * traza correspondiente a la gasolinera que se estátratando cuando es invocado.
     * @parami Índice de la gasolinera que se está tratando
     * @paramkm Km recorridos hasta el momento
     * @paramN Km máximos que se pueden recorrer sin repostar
     * @paramdistancia Distancia hasta la próxima gasolinera
     * @paramrepostar Booleano que indica si se debe repostar en la gasolinera que se esta tratando o no
     * @paramoptText Texto alternativo a mostrar en el encabezado de esta parte de la traza.
    */
        
    private void makeTraza(int i,int km,int N,int distancia,boolean repostar,String optText){
            
        if(optText !="")
           traza.append(optText+eol);
        else
           traza.append("Paso por gasolinera G"+i+eol);
        traza.append("    Km recorridos: "+km+" de "+N+eol);
        traza.append("    Siguiente gasolinera en "+distancia+" km"+eol);
        traza.append("    ¿Repostar?: "+(repostar?"Sí":"No")+eol);
    }
}
