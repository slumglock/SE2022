package org.hbrs.se1.ws22.uebung4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyConsole {
	private BufferedReader input = null;

	
	public MyConsole(){
		input = new BufferedReader( new InputStreamReader(System.in ) );
	}
	
	public String readLine(String prompt){
		String strInput = null;
		
		System.out.print( prompt );
		try {
			
			strInput = input.readLine();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return strInput;
	}
	
	public String readLine() {
		return this.readLine("> ");
	}
	
	public int readLineInt(String prompt){
		String strInput = null;
		System.out.print(prompt);
		
		// Eingabe des Wertes
		try {
			strInput = input.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Umwandlung nach Integer --> int
		int id = 0;
		try {
			id = new Integer( strInput ).intValue();
		} catch (NumberFormatException e){
			System.out.println("Das ist keine Zahl!");
			return this.readLineInt(prompt);
		}
		return id;
	}
		

}
