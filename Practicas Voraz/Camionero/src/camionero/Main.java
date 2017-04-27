/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package camionero;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase principal del programa.
 * Valida los argumentos de la línea de comandos que se usan al ejecutar el programa.
 * Utiliza los objetos de las clases Fichero y Entrega para procesar los ficheros 
 * ejecutar el algoritmo correspondiente, respectivamente.
 * @author danie
 */
public class Main {
   
    /** Ruta completa del fichero de entrada */
    private String inputFile;
    /** Ruta completa del fichero de salida */
    private String outputFile;
    /** Distancia en Km que se puede recorrer sin repostar */
    private int N;/** Número total de gasolineras del problema a resolver */
    private int G;/** Cola (FIFO) que contendrá las distancias en Km entre dos gasolineras adyacentes */
    private ArrayDeque<Integer>DG;
    /** Objeto que permitirá trabajar con los ficheros */
    private Fichero fichero;
    /** Objeto que contiene la lógica del algoritmo utilizado para resolver el problema */
    private Camionero entrega;/** Indica si debemos mostrar la traza de la ejecución del algoritmo */
    public static boolean DEBUG =false; 
    
    /**
     * Constructor de la clase. Utiliza el objeto <fichero> para recuperar,
     * desde el archivo de entrada indicado, los diferentes valores de las variables
     * necesariaspara resolver el problema (N, G y las distancias). A continuación se
     * pasan estos valores al objeto <entrega> donde serán tratados según el algoritmo
     * seleccionado para resolver le problema, y se recibirá la solución* correspondiente.
     * @paramficheros Lista que contiene el nombre de los archivos de entrada y de salida, en este orden.
     * @paramtrazar Booleano que indica si se debe trazar la ejecución del algoritmo.
    */
    
    public Main(List<String> ficheros,boolean trazar){
        this.DEBUG =trazar;
        this.inputFile = ficheros.get(0);
        this.outputFile =(ficheros.size()==2)?ficheros.get(1):null;
        fichero =new Fichero(inputFile);
        this.N =fichero.getMaxKm();
        this.G =fichero.getNumGasolineras();
        this.DG =fichero.getDistancias();
        entrega = new Camionero(DG,N,G);
        String informe =entrega.getOutput();
        System.out.println(informe);
        if(outputFile !=null){
            fichero.saveOutput(outputFile,informe);
        }
    }
    
    /**
     * Finaliza la ejecución del programa con el mensaje indicado.
     * @parammensaje El mensaje a mostrar.
     * @paramstatus 0: sin error. 1: con error.
     * @paramhelp Boolean. ¿Debe mostrarse la ayuda?
    */
    private static void die(String mensaje,int status,boolean help){
        System.out.println(mensaje);
        if(help) mostrarAyuda();
        System.exit(status);
    }
    
    /**
     * Método que imprime en pantalla información sobre cómo ejecutar el programa
     * mediante y los parámetros que éste acepta.
    */
    private static void mostrarAyuda(){
        System.out.println();
        System.out.println("java Express [-t] [-h] [entrada] [salida]");
        System.out.println("java -jar Express.jar [-t] [-h] [entrada] [salida]");
        System.out.println();System.out.println(" -h          Muestra esta ayuda.");
        System.out.println(" -t          Nuestra la traza del algoritmo. Si se "+"especifica un fichero de");
        System.out.println("             salida también se guardará en dicho fichero.");
        System.out.println(" entrada     Ruta hasta el archivo que contiene los datos "+"del problema. Puede");
        System.out.println("             ser una ruta absoluta, "+"o indicar únicamente el nombre del archivo,");
        System.out.println("             en cuyo caso éste se buscará en el directorio "+"de trabajo actual.");
        System.out.println(" salida    Opcional. Ruta hasta el archivo en el que se "+"guardarán los");
        System.out.println("             resultados obtenidos. Se puede especificar una"+"ruta absoluta, o");
        System.out.println("             sólo el nombre del archivo, "+"en cuyo caso éste se creará en el");
        System.out.println("directorio de trabajo actual.");
        
    }
    
    /**
     * Lanzador del programa.* Valida los argumentos que se pasan mediante la línea de comandos, e invoca al
     * objeto principal de la aplicación (<Express>) de la forma adecuada en función de* esos parámetros.
     * @paramargs Los argumentos de la línea de comandos.
    */
    
    public static void main (String[]args){
        boolean trazar =false;
        List<String>ficheros =new ArrayList<String>();
        
        if(args.length >4&&args.length <1){
            die("Error. No se han introducido argumentos válidos.",1,true);
        }
        
        for(String arg :args){
            if(arg.startsWith("-")&&!(arg.equals("-t")||arg.equals("-h"))){
                die("Argumento no válido: "+arg,1,true);
            }else if(arg.equals("-t")){
                trazar =true;
            }else if(arg.equals("-h")){
                die("Muestra en pantalla una solución al problema de Mensajería Urgente.",0,true);
            }else{
                ficheros.add(arg);
            }
        }
        
        if(ficheros.isEmpty())
            die("Error. No se ha especificado el fichero de entrada.",1,true);
        else{
            for(int i=0;i<10;i++)
                new Main(ficheros,trazar);
        }
    }
}
