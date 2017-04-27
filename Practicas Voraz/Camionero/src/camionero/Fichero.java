/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package camionero;

import java.io.*;
import java.util.ArrayDeque;

/**
 * Esta clase se utiliza para el tratamiento de los ficheros tanto de entrada como de
 * salida. En cuanto al de entrada comprueba que existe en la localización indicada y
 * si es así, valida su contenido de acuerdo a los criterios del problema.
 * @author danie
 */
public class Fichero {
    /** Distancia máxima en Km que se puederecorrer */
    private int N;
    /** Número total de gasolineras en el recorrido */ 
    private int G;
    /** Cola con la distancia en Km entre dos gasolineras adyacentes */
    private ArrayDeque<Integer>DG;
    /** Directorio desde donde se está ejecutando el programa */
    private final String currentDir =System.getProperty("user.dir");
    
    /**
     * Constructor de la clase.
     * Comprueba si se le ha pasado una ruta absoluta hacia el fichero de entrada.
     * Valida los datos contenidos en el fichero y de no superar la validación termina
     * la ejecución del programa.
     * Se incluye este proceso directamente en el constructor y no en un método
     * separado porque siempre que creemos este objeto será con este fín.
     * @paraminputFile El nombre del archivo de entrada
    */
    
    public Fichero(String inputFile){
        File f =new File(inputFile);
        
        if(f.isAbsolute()){
        // Se ha dado una ruta completa hasta el archivo.
            if(!f.exists())
               System.err.println("No se encuentra el archivo de entrada indicado: "+""+inputFile);
             System.exit(1);
        }else{
        // No se ha dado una ruta completa, comprobar si el archivo se encuentra en
        // el mismo directorio que el programa.
            if(f.exists())
                inputFile =currentDir +File.separator +inputFile;
            else{
                System.err.println("No se encuentra el archivo de entrada indicado: "+""+inputFile);
                System.exit(1);
            }
        }
        // Una vez se ha comprobado la existencia del archivo,
        // lo leemos y comprobamos que esté bien formado.
        File input;
        FileReader fr =null;
        BufferedReader br =null;
        try{
            input =new File(inputFile);
            fr = new FileReader(input);
            br = new BufferedReader(fr);
            // Primera línea contiene la distancia máxima que se puede recorrer
            String linea =br.readLine();
            this.N =Integer.parseInt(linea.trim());
            if(N <1)
                throw new IllegalArgumentException("La distancia mínima que hay que "+"recorrer debe ser superior a cero. En el archivo se indica "+"que es de "+N+" km.");
            
            // Segunda línea contiene el número total de gasolineras
            linea =br.readLine();
            this.G =Integer.parseInt(linea.trim());
            if(G <2)
                    throw new IllegalArgumentException("Debe haber un mínimo de dos "+"gasolineras. Se ha/n encontrado "+G);
            
            // Las siguientes G-1 líneas deben contener las distancias entre
            // gasolineras adyacentes, siendo estas entre1 y N Km
            this.DG =new ArrayDeque<Integer>(G-1);
            int distancia;
            
            while((linea =br.readLine())!=null){
                distancia =Integer.parseInt(linea.trim());
                if(distancia >N ||distancia <1)
                    throw new IllegalArgumentException("Se ha encontrado una distancia "+"con valor de "+distancia+". El máximo posible es de "+N+" "+"y el mínimo de 1.");
                DG.add(distancia);
            }
            if(DG.size()!=(G-1))
                throw new IndexOutOfBoundsException("Se han encontrado "+DG.size()+" "+"líneas indicando distancias entre gasolineras. Tiene que "+"haber "+(G-1)+" distancias.");
        }catch(IllegalArgumentException e){
            System.err.println("El fichero está mal formado.");
            System.err.println(e);
            System.exit(1);
        }catch(IndexOutOfBoundsException e){
            System.err.println("El fichero está mal formado. Posiblemente tenga líneas "+"de más referentes a las distancias entre gasolineras.\r\nSe "+"esperaban "+(G-1)+" líneas. Fichero: "+inputFile);
            System.err.println(e);
            System.exit(1);
        }catch(Exception e){
                System.err.println("Error. No se ha podido leer el fichero: "+inputFile);
                System.err.println(e);
        }finally{
            try{
                if(br !=null)
                    br.close();
                if(fr !=null)
                    br.close();
            }catch(Exception e2){
                System.err.println("Error. Ocurrió un error cerrando el fichero: "+inputFile);
                System.err.println(e2);
            }
        }
    }
        
        /**
         * Método que sirve para guardar una cadena de texto en el fichero indicado.
         * @paramoutputFile Nombre del fichero en el que guardar la cadena de texto.
         * @paraminforme La cadena de texto que se desea guardar.
        */
        
    public void saveOutput(String outputFile,String informe){
        File f =new File(outputFile);
        if(!f.isAbsolute()){
            // No se ha dado la ruta completa, asumimos que el informe debe guardarse
            // en el mismo directorio que la aplicación.
            outputFile =currentDir +File.separator +outputFile;
        }
        BufferedWriter writer =null;
        try{ 
            writer =new BufferedWriter(new FileWriter(outputFile));
            writer.write(informe);
            System.out.println("Fichero de salida guardado en: "+outputFile);
        }catch(IOException e){
            System.err.println("Ocurrió un error guardando el fichero: "+outputFile);
            System.err.println(e);
        }finally{
            try{
                if(writer !=null)
                    writer.close();
            }catch(IOException e2){
                e2.printStackTrace();
            }
        }
    }
    
    /**
     * Devuelve la distancia máxima que puede recorrerse sin repostar,
     * según lo indicado en el fichero.
     * @returnDistancia máxima que puede recorrerse sin repostar.
    */
    public int getMaxKm(){
            return this.N;
    }
    /**
     * Devuelve el número total de gasolineras que hay que considerar,
     * según lo indicado en el fichero.
     * @returnNúmero máximo de gasolineras.
    */
    public int getNumGasolineras(){ 
        return this.G;
    }
    
    /**
     * Devuelve una cola con las distancias entre dos gasolineras adyacentes,
     * según lo indicado en el fichero. La cabeza de la cola será la distancia entre la
     * primera gasolinera y la segunda, el segundo elemento de la cola la distancia
     *entre la segunda gasolinera y la tercera, y así sucesivamente.
     * @returnCola con las distancias entre dos gasolineras adyacentes.
    */
    public ArrayDeque<Integer> getDistancias(){
        return this.DG;
    }
       
}
