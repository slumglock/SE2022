package org.hbrs.s1.ws22.uebung1.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.hbrs.s1.ws22.uebung1.*;
import org.hbrs.s1.ws22.uebung1.control.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GermanTranslatorTest {
    private GermanTranslator translator = null;

    @BeforeEach
    void setup() {
        translator = (GermanTranslator) Factory.createGermanTranslator();
    }

    @AfterEach
    void teardown() {translator = null;}

    @Test
    void testTranslateNumber() {
        //Negativtests
        assertEquals("Übersetzung der Zahl -123 nicht möglich (1.0)", translator.translateNumber(-123), "Fehler bei translateNumber");
        assertEquals("Übersetzung der Zahl 12000 nicht möglich (1.0)", translator.translateNumber(12000), "Fehler bei translateNumber");
        assertEquals("Übersetzung der Zahl 0 nicht möglich (1.0)", translator.translateNumber(0), "Fehler bei translateNumber");

        //Positivtests
        assertEquals("fuenf", translator.translateNumber(5), "Fehler bei translateNumber");


    }



}