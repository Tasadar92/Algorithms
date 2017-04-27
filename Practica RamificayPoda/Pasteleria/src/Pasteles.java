/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.*;
import java.io.*;
/**
 *
 * @author danie
 */
public class Pasteles {
    // MENSAJES PARA EL USUARIO
    private static final String uso = "Puedes pasar al programa dos argumentos, el archivo origen y el archivo de salida "
                                        + "\nEl archivo origen es obligatorio, si no especificas salida se imprimirá por consola"
                                        + "\nEjemplo: Pasteleria.jar Origen.txt Destino.txt";
    
    private static final String inv_args = "\nArgumentos erróneos o insuficientes, no se ejecutará el algoritmo."
                                        + "\nPara recibir instrucciones de uso sobre el programa, llámelo sin parámetros.";
    
    private static final String info_entrada_salida = "\n"
                                        + "  - Entrada: %s"
                                        + "\n  - Salida: %s";
    
    private static boolean escribirconsola;
    
    private static int cotaActual;
    
    private static int n, m;
    private static int[][] matriz = null;
    private static int[] pedido = null;
    private static int[] solucion = null;
    
    private static PriorityQueue<Nodo> cola;
    
    public static int[] vectorMaximos;
    
    public static int cotaInferior;
    
    public static Nodo nodosolucion;
    
    public static long t0,t1,t2,t3,t4;
    
    public static void main(String[] args) {
        
        // Si no pasan argumentos
        if(args.length==0){
            // Les decimos que lo han hecho mal y salimos
            System.out.println(uso);
            System.out.println(inv_args);
            System.exit(1);
        }
        
        // Si pasan demasiados argumentos
        if(args.length>2){
            // Lo mismo, mal mal mal y nos vamos
            System.out.println(inv_args);
            System.exit(1);
        }
        
        // Si sólo nos pasan un argumento
        if(args.length == 1){
            // Asumimos que es la entrada y la salida la consola
            escribirconsola = true;
            System.out.println(String.format(info_entrada_salida, args[0], "Por Consola"));

        // Si nos pasan dos, el primero es la entrada y el segundo la salida
        } else {
            escribirconsola = false;
            System.out.println(String.format(info_entrada_salida, args[0], args[1]));

        }
    
}}
