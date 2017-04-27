/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
/**
 *
 * @author danie
 */
public class Tilling {
    	public static void main(String args[])
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Introduzca el orden del tablero :");
		int k=0,x=0,y=0;
		try
		{
			k=Integer.parseInt(br.readLine());
			System.out.print("Introducir la posicion del hueco en blanco\n row: ");
			x=Integer.parseInt(br.readLine());
			System.out.print("\n col: ");
			y=Integer.parseInt(br.readLine());
		}
		catch (Exception e1)
		{
		}

		Board b= new Board(k,x,y);

		System.out.println("\nTablero inicial:");
		b.showBoard();
		int p=b.getPattern(k,x,y);
		b.fill_pattern(p,k-1,0,0,x,y);

		System.out.println("Tablero final: ");
		b.showBoard();
	}    
}
