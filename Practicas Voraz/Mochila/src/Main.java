/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.*;
/**
 *
 * @author danie
 */
public class Main {
    public static void main(String[] args){
        int n;
        Scanner sc = new Scanner(System.in);
        System.out.println("Insertar el numero de elementos existentes:");
        n= sc.nextInt();
        
        float solucion[] = new float[n];
        
        Beneficio objetos[] = new Beneficio[n];
        llenar(objetos);//ingresamos los valores
        ordena(objetos);//ordena segun la funcion seleccion
        mostrar(objetos);//muestra los elementos ordenados
        objetivo(solucion,objetos);//resuelve el problema
        mostrar(solucion,objetos);//muestra la solucion
    }
    
    public static void llenar(Beneficio vector[]){
        int p;
        int b;
        for(int i=0;i<vector.length;i++){
            Scanner sc = new Scanner(System.in);
            System.out.println("Insertar el peso del objeto: ");
            p=sc.nextInt();
            Scanner sc1 = new Scanner(System.in);
            System.out.println("Insertar el beneficio del objeto: ");
            b=sc1.nextInt();
            
            vector[i] = new Beneficio(b,p);
                       
        }
    }
    
    
    public static void ordena(Beneficio vector[]){
        int i,j,k;
        double menor;
        Beneficio aux = new Beneficio();
        aux = vector[0];
        for(i=0;i<vector.length;i++){
            menor=vector[i].getBxp();
            k=i;
            
            for(j=i+1;j<vector.length;j++){
                if (vector[j].getBxp() < menor){
                    menor=vector[j].getBxp();
                    aux=vector[j];
                    k=j;
                }
                aux=vector[k];
                vector[k]=vector[i];
                vector[i]=aux;
                
            }
            
        }
    }
    
    public static void mostrar(Beneficio[] aux){
        String beneficio="beneficio: ", peso="peso: ",valor="valor por unidad: ";
        for(int i=0; i<aux.length;i++){
            beneficio+=aux[i].getBeneficio() + " , ";
            peso+=aux[i].getPeso() + " , ";
            valor+=aux[i].getBeneficio()/aux[i].getPeso() + " , ";
        }
        System.out.println(peso + beneficio + valor);
    }
    
    public static void objetivo(float solucion[], Beneficio objetos[]){
        float n;
        float peso_act=0;
        int i=solucion.length-1;
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el tamaño de la mochila");
        n=sc.nextInt();
        
        while(peso_act<n || i>=0){
            System.out.println("Peso Actual: " + peso_act + " Peso objeto: " + i + ": "+ objetos[i].getPeso());
            if((peso_act + objetos[i].getPeso())<n){
                solucion[i]=1;
                peso_act+=objetos[i].getPeso();
                System.out.println("Peso Actual: " + peso_act  + " Porcentaje del objeto: " + i + ": "+ solucion[i]*100);
            }else{
                 solucion[i]=(n-peso_act)/objetos[i].getPeso();
                 peso_act+=((n-peso_act)/objetos[i].getPeso())*objetos[i].getPeso();
                 System.out.println("Peso Actual: " + peso_act + " Porcentaje del objeto: " + i + ": "+ solucion[i]*100);
            }
        i--;
        }
    }
    
    public static void mostrar(float aux[],Beneficio b[]){
        String solucion="solucion";
        float suma=0;
        for(int i=0;i<aux.length;i++){
            solucion+=aux[i]+"______";
            suma+=aux[i]*b[i].getBeneficio();
        }
        System.out.println(solucion + "Beneficion total: "  + suma );
    }
    
}