package org.hbrs.se1.ws22.uebung3.control;

import org.hbrs.se1.ws22.uebung3.control.ContainerException;
import org.hbrs.se1.ws22.uebung3.control.Member;
import org.hbrs.se1.ws22.uebung3.persistence.PersistenceException;

import java.util.ArrayList;
import java.util.List;

public class Container {

    // CR1: Nur 1 Container Objekt pro Laufzeit - Singleton Pattern
    // Konstruktor Private um Zugriff und Objektbildung von Außen zu unterbinden

    private static Container instance; //instance = null

    private Container() {}

    public static synchronized Container getInstance(){
        if(instance == null) {
            instance = new Container();
        }
        return instance;
    }

    public static void deleteInstance(){

        instance = null;
    }

    //Uebung3 CR2:

    public void store() throws PersistenceException {

    }

    public void load() throws PersistenceException {

    }
    //Uebung 3 CR3:

    public List<Member> getCurrentList(){
        return aList;
    }



    public List<Member> aList = new ArrayList<>();

    // Versucht Member hinzuzufuegen, falls ID bereits vorhanden wird ContainerException geworfen
    public void addMember(Member member) throws ContainerException {
        if (contains(member)){
            ContainerException exception = new ContainerException();
            exception.setExceptionID(member.getID());
            throw exception;
        }
        aList.add(member);
    }


    // cotains() Methode um zu Ueberpruefen ob eine Member ID bereits vergeben ist
    public boolean contains(Member member){
        Integer id = member.getID();
        for(Member x : aList) {
            if(member.getID().equals(x.getID())){
                return true;
            }
        }
        return false;
    }

    // Liefert String ob Member mit ID erfolgreich geloescht wurde
    // oder ob kein Member mit der uebergebenen ID existiert
    public String deleteMember(Integer id){
        Member x = getMember(id);
        if (x == null){
            return "Kein Member mit der ID (" + id + ") ist in der Liste enthalten.";
        } else {
            aList.remove(x);
            return "Der Member mit der ID (" + id + ") wurde erfolgreich geloescht.";
        }

    }
    //Liefert das Member Objekt welches zu der uebergeben ID gehoert
    private Member getMember(Integer id){
        for(Member x:aList){
            if(id.equals(x.getID())){
                return x;
            }
        }
        return null;
    }

    //Printet alle Member der aktuellen Liste
    public void dump(){
        System.out.print("Alle IDs der aktuell gespeicherten Objekte im Container:\n");
        for(Member x:aList){
            System.out.print(x.toString());
        }
    }
    // size() methode von Arraylist
    public int size(){

        return aList.size();
    }
}