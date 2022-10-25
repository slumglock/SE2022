package org.hbrs.se1.ws22.uebung3.control;

import org.hbrs.se1.ws22.uebung3.control.ContainerException;
import org.hbrs.se1.ws22.uebung3.control.Member;

import java.util.ArrayList;
import java.util.List;

public class Container {

    public List<org.hbrs.se1.ws22.uebung3.control.Member> aList = new ArrayList<>();

    // Versucht Member hinzuzufuegen, falls ID bereits vorhanden wird ContainerException geworfen
    public void addMember(org.hbrs.se1.ws22.uebung3.control.Member member) throws org.hbrs.se1.ws22.uebung3.control.ContainerException {
        if (contains(member)){
            org.hbrs.se1.ws22.uebung3.control.ContainerException exception = new ContainerException();
            exception.setExceptionID(member.getID());
            throw exception;
        }
        aList.add(member);
    }


    // cotains() Methode um zu Ueberpruefen ob eine Member ID bereits vergeben ist
    public boolean contains(org.hbrs.se1.ws22.uebung3.control.Member member){
        Integer id = member.getID();
        for(org.hbrs.se1.ws22.uebung3.control.Member x : aList) {
            if(member.getID().equals(x.getID())){
                return true;
            }
        }
        return false;
    }

    // Liefert String ob Member mit ID erfolgreich geloescht wurde
    // oder ob kein Member mit der uebergebenen ID existiert
    public String deleteMember(Integer id){
        org.hbrs.se1.ws22.uebung3.control.Member x = getMember(id);
        if (x == null){
            return "Kein Member mit der ID (" + id + ") ist in der Liste enthalten.";
        } else {
            aList.remove(x);
            return "Der Member mit der ID (" + id + ") wurde erfolgreich geloescht.";
        }

    }
    //Liefert das Member Objekt welches zu der uebergeben ID gehoert
    private org.hbrs.se1.ws22.uebung3.control.Member getMember(Integer id){
        for(org.hbrs.se1.ws22.uebung3.control.Member x:aList){
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