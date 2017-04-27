/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author danie
 */
public class Nodo {
    // profundidad del arbol
        public int profundidad;
        public int pedido[];
        public int solucion[];
        public List<Integer> pastelerosNoAsignados = new ArrayList<>();
        public int valorAcumulado;
        public final int nPasteleros;
        
        public Nodo (int profundidad, int nPasteleros, int[] pedido, List<Integer> pasteleros){
            this.profundidad = profundidad;
            this.pedido = pedido;
            this.pastelerosNoAsignados = pasteleros;
            this.nPasteleros = nPasteleros;
            this.solucion = new int[pedido.length];
        }
        
        public Nodo(Nodo n){
            this.profundidad = n.profundidad;
            this.pastelerosNoAsignados = new ArrayList<>(n.pastelerosNoAsignados);
            this.pedido = n.pedido;
            this.valorAcumulado = n.valorAcumulado;
            this.solucion = n.solucion.clone();
            this.nPasteleros = n.nPasteleros;
        }

}
