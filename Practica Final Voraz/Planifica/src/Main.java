/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author daniel
 */



public class Main {
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
  
        Actividad [] actividades = null;    //Array donde se guardan las actividades
        Actividad [] result = null;      //Array donde se guardan las actividades compatibles

        int numProgramas = 0;
        
        Scanner teclado = new Scanner(System.in);                   //Leemos el numero de actividades
        String direccion;  
        if (args.length != 0) {
              direccion = args[0];
        }
        else{
          direccion = "";
        }

        if (direccion.equals("")){                                              //Si el usuario no introduce ruta, se le pedira que meta los datos manualmente   
            System.out.print("Introduzca numero de tareas: ");
            numProgramas = teclado.nextInt();
            actividades = new Actividad [numProgramas];
            result = new Actividad [numProgramas];

            for(int i = 0; i < actividades.length; i++){
                System.out.print("Introduzca inicio actividad " + i +" : ");
                int inicio = teclado.nextInt();
                System.out.print("Introduzca final actividad " + i +" : ");
                int fin = teclado.nextInt();
                actividades[i] = new Actividad (i,inicio,fin);
            }
        }
        else{
            File archivo = new File(direccion);
            if (archivo.exists()){                                                      //Si la ruta existe, se leeran los datos
                String cadena;
                FileReader f = new FileReader(archivo);
                BufferedReader b = new BufferedReader(f);
                boolean primeraLinea = true;
                int i = 0;
                while((cadena = b.readLine())!=null) {
                    if (primeraLinea){                                                  //Si la linea leida es la primera, guardar ese numero como numero de actividades
                        numProgramas = Integer.parseInt(cadena);
                        actividades = new Actividad [numProgramas];
                        result = new Actividad [numProgramas];
                        primeraLinea = false;
                    }
                    else{
                        int inicioArc= Integer.parseInt(cadena.split(" ")[0]);          //Si la linea leida no es la primera, partir cadena en dos partes: la primera parte sera el tiempo de inicio de la activdad y la segunda el tiempo de fin
                        int finArc= Integer.parseInt(cadena.split(" ")[1]);

                        actividades[i] = new Actividad (i,inicioArc,finArc);
                        i++;
                    }
                }

                b.close();


            }
            else{
                System.out.println("No existe tal archivo");
                System.exit(0);
            }

        }
        
        //COMIENZO DEL ALGORITMO
        Arrays.sort(actividades);
        
        int sig = 0;
        double totalTiempo;
        double tiempoInicio;
 
        // Inicializa con el tiempo actual
        tiempoInicio = System.nanoTime();

	for(Actividad actividad:actividades){                                           
            if ( result.length == 0){
		result[sig] = actividad;
                sig++;
            }else 
                if (!actividad.factible(result)){ //Si la siguiente actividad tiene un inicio mayor que el fin de la anterior, es una actividad copatible
                    result[sig] = actividad;
                    sig++;
            }          
        }
        
     
        //FIN DEL ALGORITMO

        String archivoGuardar;        //Si ha introducido por consola dos o mas archivos, el segundo se escoge como fichero de salida.                                           
        if (args.length==0 || args.length==1) {                                 
            archivoGuardar ="";
        }
        else{
            archivoGuardar = args[1];

        }
        
        if (archivoGuardar.equals("")){                                         //Si el usuario no introduce ruta, se le muestra por pantalla
            System.out.print("Solucion:");
            for(int j = 0; j < result.length ; j++){                         //Imprimir conjunto de cardinalidad maxima
                if(result[j]!= null){
                    System.out.print(result[j].getId() +",");
                } 
            }
            /*float time_start, time_end;
            time_start = System.currentTimeMillis();
            //ReallyHeavyTask(); // llamamos a la tarea
            time_end = System.currentTimeMillis();
            System.out.println("the task has taken "+ ( time_end - time_start ) +" milliseconds");*/
            
            // Resta el tiempo de inicio al actual resultando el tiempo usado
            totalTiempo = (System.nanoTime() - tiempoInicio)/1000000;
 
            System.out.println("Tiempo demorado:\t" + totalTiempo + " milisegundos.");
        }
        else{       
            File archivo = new File(archivoGuardar);                            //Creamos el archivo y escribimos en el
            if (archivo.exists()) {
                System.out.println("El archivo de destino ya existe");
            }
            else{
                try{
                    PrintWriter escribirTexto = new PrintWriter(archivoGuardar, "UTF-8");
                     for(int j = 0; j < result.length ; j++){                              //Imprimir conjunto de cardinalidad maxima
                        if(result[j]!= null){
                                escribirTexto.print(result[j].getId() +",");
                        } 
                    }
                    escribirTexto.close();
                }
                catch (IOException e) {
                    System.out.println("error");
                }
            }
        }
    }
}
    

