
package org.hbrs.se1.ws22.uebung4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Klasse zur Darstellung der Eingabe (laut MVC Pattern: View)
 * Ubernimmt zusätzlich die Navigtion zwischen dem Modell und der Ausgabe
 * (laut MVC Pattern: Controller)
 * @author saschaalda
 *
 */
public class EingabeDialog {
	
	// Diese Methode realisiert die Eingabe
	public void startEingabe() {
	
		String strInput = null;
		
		// Ausgabe eines Texts zur Begruessung
		System.out.println("Prio-Tool V2.0");
		System.out.println("c/o Sascha Alda in 2018\n");
		
		// Initialisierung des Eingabe-View
		BufferedReader input = new BufferedReader( new InputStreamReader(System.in ) );
		
		// Kontinuierliche Abfrage der Eingabe ueber While-Schleife.
		// Dann Abfrage ueber switch/case oder (hier:) if-Schleifen
		// Optimierung: Command Pattern (Kapitel 6)
		while ( true ) {

			try {		
				System.out.print("> ");
				strInput = input.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			// Extrahiert ein Array aus der Eingabe
			String[] strings = strInput.split(" ");	

			// 	Falls 'help' eingegeben wurde, werden alle Befehle ausgedruckt
			if ( strings[0].equals("help") ) {
				System.out.println("Folgende Befehle stehen zur Verfuegung: help, dump....");
			}
		
			if ( strings[0].equals("dump") ) {
				this.startAusgabe();
			}
			
			if ( strings[0].equals("load") ) {
				Container.getInstance().load();
			}
			
			if ( strings[0].equals("store") ) {
				try {
					Container.getInstance().store();
				} catch (ContainerException e) {
					e.printStackTrace();
				}
			}
					 
			if (  strings[0].equals("enter")  ) {
				
				MyConsole console = new MyConsole();
				// Ausgabe einer Eingabeaufforderung
				System.out.println("Geben sie die Grunddaten der User Story ein:");
				
				// Eingabe der ID:
				int id = console.readLineInt("ID der User Story: ");
					
				// Abfrage der einzelnen Werte der User Story:
				String titel = console.readLine("Titel der User Story: ");
				
				// Eingabe des Aufwands:
				int aufwand = console.readLineInt("Aufwand der User Story: ");
					
				// Eingabe des Risikos:
				int risk = console.readLineInt("Risiko der User Story: ");
					
				// Eingabe des Mehrwerts:
				int mehrwert = console.readLineInt("Mehrwert der User Story: ");
				
				// Eingabe der Strafe:
				int strafe = console.readLineInt("Strafe der User Story: ");
				
				// Berechnung der Priorisierung
				double prio =  ( (mehrwert + strafe ) / ( aufwand + risk ) );
				System.out.println("User Story mit ID: " + id + " hat die Prio: " + prio);
				
				// Neues Objekt vom Typ UserStory
				UserStory us = new UserStory( id, titel, mehrwert, strafe, aufwand, risk , prio );
				
				// Hinzufügen
				try {
					Container.getInstance().addUserStory( us ); 
				} catch (ContainerException e) {
					// TODO Auto-generated catch block
					System.out.println("Fehler beim Abspeichern der User Story!");
				}  

			} 
		} // Ende der Schleife
	}

	private void startAusgabe() {
		AusgabeDialog dialog = new AusgabeDialog( Container.getInstance().getCurrentList() );
		dialog.dump(); 
		
		
	}



}
