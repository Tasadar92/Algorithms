import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author danie
 */
public class Actividad implements Comparable<Object>{
	
	private int ini;
	private int fin;
	private int id;

	public Actividad(int id,int init, int end){
		if ( init > end )
			System.out.println("Tiempo final ha de ser mayor que tiempo inicial");
		this.ini = init;
		this.fin = end;
                this.id = id;
	}
	
	public boolean enRango( int date ){
		return ( date > ini && date < fin );
	}
	
	public int compareTo(Object o) {
		Actividad a = (Actividad) o;

        if ( a.getFin() < getFin() )
        	return 1;
        if ( a.getFin() == getFin() )
        	return 0;
        else
        	return -1;
        
    }

	public int getIni() {
		return ini;
	}

	public void setIni(int init) {
		this.ini = init;
	}

	public int getFin() {
		return fin;
	}

	public void setFin(int end) {
		this.fin = end;
	}
	
	public int getLength(){
		return fin - ini;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString(){
		return "Inicio: " + ini + ", End: " + fin + ", Id: " + id;
	}
	
        public boolean factible( Actividad [] other ){
		boolean solapado = false;
		
		try{
                    for(int i=0;i<other.length;i++){
			// is overlapped if the other init or end is inside my range
			if( enRango( other[i].getIni()) || enRango( other[i].getFin() ) )
				solapado = true;
                    }
		}catch( Exception e ) {
			// nulls or other
		}
		
		return solapado;
	}
}
