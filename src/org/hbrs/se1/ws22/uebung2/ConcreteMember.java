package org.hbrs.se1.ws22.uebung2;


import org.hbrs.se1.ws22.uebung2.Member;

public class ConcreteMember  implements Member {

    private Integer id;

    //Erstellt Objekte von Member während der Laufzeit mit übergebener ID
    public defMember(Integer id) {this.id = id;}

    //Methoden aus dem Interface überschreiben

    @Override
    public Integer getID() {return id;}

    @Override
    public String toString(){

        return "Member (ID =" + id + ")\n";
    }
}
