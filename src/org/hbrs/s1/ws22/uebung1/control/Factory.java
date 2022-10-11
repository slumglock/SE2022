package org.hbrs.s1.ws22.uebung1.control;
// lglock2s 9032124
    /*
    *   Anwendung des Factory Method Design Pattern
    */
public class Factory {

    public static Translator createGermanTranslator() {
        return new GermanTranslator();
    }

    public void create(){

    }
}
