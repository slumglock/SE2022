package org.hbrs.se1.ws22.uebung3.control;


import org.hbrs.se1.ws22.uebung3.control.Member;

public class ConcreteMember  implements Member {

    private Integer id;

    //Erstellt Objekte von Member während der Laufzeit mit übergebener ID
    public ConcreteMember(Integer id) {this.id = id;}

    //Methoden aus dem Interface überschreiben

    @Override
    public Integer getID() {return id;}

    @Override
    public String toString(){

        return "Member (ID =" + id + ")\n";
    }
}