/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author danie
 */
public class Beneficio {
    private float beneficio;
    private float peso;
    private float bxp;
   
    public Beneficio(float beneficio,float peso){
        this.beneficio=beneficio;
        this.peso=peso;
        this.bxp=(beneficio/peso);          
    }

    public Beneficio() {

    }
    
    public float getBeneficio(){
        return beneficio;
    }
    
    public void setBeneficio(float beneficio){
        this.beneficio=beneficio;
    }
    
    public float getPeso(){
        return peso;
    }
    
    public void setPeso(float peso){
        this.peso=peso;
    }
    
    public float getBxp(){
        return bxp;
    }
}
