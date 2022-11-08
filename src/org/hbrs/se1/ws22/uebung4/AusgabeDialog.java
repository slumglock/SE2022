package org.hbrs.se1.ws22.uebung4;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class AusgabeDialog {
	
	List<UserStory> liste = null;
	
	public AusgabeDialog(List<UserStory> currentList) {
		this.liste = currentList;
	}

	// Diese Methode realisiert die Ausgabe
	public void dump(){
		// Falls Liste leer, dann kurze Ausgabe und raus.
		if ( liste.isEmpty() ) {
			System.out.println("Keine User Stories vorhanden");
			return;
		}
		
		// hier koennen verschiedene dump-Varianten ausgefuehrt werden.
		// Bitte entsprechend auskommentieren bzw. umaendern.
		this.dumpLambdaWithFilterAndMapAndSort();
		// this.dumpForEach();
		
	}
	
	private void dumpIterator() {
		
		// Hier sortiert Herr P. die Liste mit einem eigenen Sortieralgorithmus
		// Dafür muss UserStory das Comparable-Interface implementieren
		
		// Sortierung hier über die Methode sort der Klasse Collections
		Collections.sort(  this.liste );
		
		// Ausgabe ueber einen Iterartor (Ausgabe kann auch optimiert werden ;-)):
		Iterator<UserStory> i = liste.iterator();
		while (  i.hasNext() ) {
			UserStory p = i.next();
			System.out.println("ID: " + p.getId() );
			System.out.println("Titel: " + p.getTitel());
			System.out.println("Prio: " + p.getPrio());
			System.out.println("Aufwand: " + p.getAufwand());
			System.out.println("Risiko: " + p.getRisk());
			System.out.println("Mehrwert: " + p.getMehrwert());
			System.out.println("Risiko: " + p.getRisk());
			System.out.println("\n");
		}
		System.out.println("Aktuelle Anzahl User Stories: " + liste.size() );
	}
	
	/*
	 * Ausgabe ueber eine for-each-Methode.
	 * Hier: imperative Ausgabe der Liste: strenge Sequenzbildung!
	 */
	private void dumpForEach() {
		for ( UserStory us : liste ) {
			System.out.println( us.toString() );
		}
	}
	
	/*
	 * Ausgabe hier über die foreach-Methode der Klasse List
	 * Ubergabe einer anonymen internen Klasse, die von einem 
	 * Functional Interface sich ableitet. Deklarative Programmierung:
	 * Nur Angabe, WAS zu berechnen ist, jedoch bleibt das WIE 
	 * der Methode forEach selber ueberlassen! Immense Performance-Vorteile
	 * moeglich!!
	 * 
	 */
	private void dumpFunctionInterface() {
		liste.forEach( new Consumer<UserStory>() {
			@Override
			public void accept(UserStory us) {
				System.out.println( us.toString() );	
			}
		}  );
	}
	
	/*
	 * Ausgabe nun verfeinfacht ueber einen Lambda-Ausdruck, der die
	 * interne Klasse ersetzt. Vorteil: vereinfachte und intuitive
	 * Programmierung.
	 */
	private void dumpLambdaInFunctionalInterface() {
		// Einfache Ausgabe ueber Methode der Liste (Filter und Map hier nicht moeglich)
		liste.forEach( ( UserStory us )  ->  { System.out.println( us.toString() ) ; } ); // Reduce
		
		// oder, per Type Inference:
		liste.forEach( us  -> System.out.println( us.toString() ) ); // Reduce
		
	}
	
	/*
	 * Ausgabe nun ueber einen Stream (siehe Kapitel 4, Abschnitt 5). Dadurch erweiterte
	 * Verarbeitungsschritte moeglich durch Method Chaining. Vorteile: vereinfachtes
	 * Entwicklungsmodell, Lazy-Eigenschaft moeglich, gute Performance moeglich!
	 */
	private void dumpLambdaInStream() {	
		// Einfache Ausgabe ueber Stream (ohne Filter und Map)
		liste.stream().forEach( us  -> System.out.println( us.toString() ) ); // Reduce
	}
	
	/*
	 * Ausgabe mit zusaetzlichem Filter. Optimierung: Uebergabe eines int-Werts in 
	 * der Methode
	 */
	private void dumpLambdaWithFilter() {	
		// Ausgabe von UserStories mit Aufwaenden groeßer 4
		liste.stream().filter( us -> us.getRisk() > 4)   // Filter
			          .forEach( us  -> System.out.println( us.toString() ) ); // Reduce
	}
	
	/*
	 * Hier Ausgabe ueber Stream, der das Filter-Map-Reduce Pattern
	 * voellstaendig implementiert. Durch das Map (Projektion) werden nur Titel ausgegeben.
	 */
	private void dumpLambdaWithFilterAndMap() {
		// Ausgabe von UserStories mit Aufwaenden groeßer 4, nur Titel werden ausgegeben
		liste.stream() 
			      .filter( element -> element.getAufwand() > 4 )         // Filter  
			      .map( element -> element.getTitel() )                  // Map (Projektion)
			      .forEach( element -> System.out.println( element ) );  // Reduce (oder: System.out::println)
	}
	
	/*
	 * Hier Ausgabe ueber parallele Streams, so dass die Werte aus der Liste
	 * parallel abgearbeitet werden. Bei großen Listen erhebliche Performance-Vorteile!!
	 */
	private void dumpLambdaWithFilterAndMapParallel() {
		// Ausgabe von UserStories mit Aufwaenden groeßer 4, nur Titel werden ausgegeben
		liste.parallelStream() 
			      .filter( element -> element.getAufwand() > 4 )         // Filter  
			      .map( element -> element.getTitel() )                  // Map (Projektion)
			      .forEach( element -> System.out.println( element ) );  // Reduce (oder: System.out::println)
	}
	
	/*
	 * Ausgabe und zusaetliche Sortierung ueber den Stream.
	 * Parallele Ausgabe sollte nicht durchgefuehrt werden, da die
	 * Methode sorted zustandsbehaftet ist, was die Sortierung verfaelschen koennte!! 
	 */
	private void dumpLambdaWithFilterAndMapAndSort() {
		liste.stream() 
	        .filter( element -> element.getAufwand() > 4 )                         // Filter  
		    .sorted( (i1, i2) ->  Double.compare( i1.getPrio() , i2.getPrio()  ) ) // Map (Sortierung)
		    .forEach( element -> System.out.println( element ) );                  // Reduce (forEach)
	}
	
	/*
	 * Zusaetliche Sortierung ueber den Stream sowie Rueckgabe als Liste
	 * Parallele Ausgabe sollte nicht durchgefuehrt werden, da die
	 * Methode sorted zustandsbehaftet ist, was die Sortierung verfaelschen koennte!! 
	 */
	private List<UserStory> dumpLambdaWithFilterAndMapAndCollect() {
		return liste.stream() 
	        .filter( element -> element.getAufwand() > 4 )                         // Filter  
		    .sorted( (i1, i2) ->  Double.compare( i1.getPrio() , i2.getPrio()  ) ) // Map (Sortierung)
		    .collect( Collectors.toList()  );                                      // Reduce (Collect in Liste)
	}



}
