package org.hbrs.s1.ws22.uebung1.control;

public class Factory {

    public Translator createTranslator() {
        return new GermanTranslator();
    }

    public void create(){

    }
}
