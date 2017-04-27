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
public class ComparaNodos implements Comparator{
    public int compare(Object o1, Object o2) {
        
        // La cola devolvera los Nodos de mayor valor Acumulado a menor
        return ((Nodo) o2).valorAcumulado -((Nodo) o1).valorAcumulado;
    }
    
}
