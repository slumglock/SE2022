package org.hbrs.s1.ws22.uebung1.view;
// lglock2s 9032124
import org.hbrs.s1.ws22.uebung1.control.*;

public class Client {

		/*
		 * Methode zur Ausgabe einer Zahl auf der Console (auch bezeichnet als CLI, Terminal)
		 */
		public void display( int aNumber ){
			// In dieser Methode soll die Methode translateNumber
			// mit dem übergegebenen Wert der Variable aNumber
			// aufgerufen werden.
			//
			// Strenge Implementierung gegen das Interface Translator gewuenscht!


			// Factory wird Verwendet - Referenz auf einen Translator wird
			// von Client bezogen
			Translator translator = Factory.createGermanTranslator();

			System.out.println("Das Ergebnis der Berechnung: " +
					"[das Ergebnis an dieser Stelle]"  );

		}
}





