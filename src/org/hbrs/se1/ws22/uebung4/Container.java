package org.hbrs.se1.ws22.uebung4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;



/**
 * Klasse zum Management sowie zur Eingabe unnd Ausgabe von User Stories.
 * (Laut MVC Pattern: Controller)
 * Die Anwendung wird über dies Klasse auch gestartet (main-Methode hier vorhanden)
 * 
 */

public class Container {
	
	// Interne ArrayList zur Abspeicherung der Objekte
	private List<UserStory> liste = null; 
	
	// Statische Klassen-Variable, um die Referenz
	// auf das einzige Container-Objekt abzuspeichern
	private static Container instance = new Container();
	
	public static synchronized Container getInstance() {
	if (instance == null) {
		instance = new Container();
	}
		return instance;
	}
	
	/*
	 * Ueberschreiben des Konstruktors. 
	 * 
	 */
	private Container(){
		liste = new ArrayList<UserStory>();
	}
	

	/**
	 * Methode zum Einfuegen eines Story
	 * @param r
	 * @throws ContainerException
	 */
	public void addUserStory ( UserStory r ) throws ContainerException {		
		if ( contains(r) == true ) {
			ContainerException ex = new ContainerException("ID bereits vorhanden!");
			throw ex;
		}
		liste.add(r);
		
	} 
	

	private boolean contains(UserStory r) {
		int ID = r.getId();
		for ( UserStory rec : liste) {
			if ( rec.getId() == ID ) {
				return true;
			}
		}
		return false;
	}
	
	public int getAnzahl(){
		return liste.size();
	}
	

	public List<UserStory> getCurrentList() {
		return this.liste;
	}
	
	/*
	 * Methode zur Auslieferung der UserStory-Objekte.
	 * Es werden keine Referenzen auf die Entity selber übergeben,
	 * sondern nur DTO
	 * 
	 */
	public List<UserStoryDTO> getCurrentListOfUserStoriesAsDTO() {
		List<UserStoryDTO> newListe = new ArrayList<UserStoryDTO>();
		
		for ( UserStory us : this.liste ) {
			UserStoryDTO dto = new UserStoryDTO();
			dto.setTitel( us.getTitel()  ); 
			// Weitere Attribute ausgelassen
			newListe.add(dto);
		}
		return newListe;

	}
	

	/*
	 * Interne Methode zur Ermittlung einer Person
	 * 
	 */
	private UserStory getUserStory(int id) {
		for ( UserStory rec : liste) {
			if (id == rec.getId() ){
				return rec;
			}
		}
		return null;
	}

	public void load() {
		this.liste = PersistenceManager.load();
		
	}

	public void store() throws ContainerException {
		PersistenceManager.store( this.liste );
		
	}



}
