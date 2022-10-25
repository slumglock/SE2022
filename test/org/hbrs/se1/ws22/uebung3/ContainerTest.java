package org.hbrs.se1.ws22.uebung3;

import org.hbrs.se1.ws22.uebung3.control.ConcreteMember;
import org.hbrs.se1.ws22.uebung3.control.Container;
import org.hbrs.se1.ws22.uebung3.control.ContainerException;
import org.hbrs.se1.ws22.uebung3.control.Member;
import org.hbrs.se1.ws22.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws22.uebung3.view.MemberView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ContainerTest {
    private Member x = null;
    private Member y = null;
    private Member z = null;
    private Member m = null;
    private Container con = null;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setup() {

        x = new ConcreteMember(1);
        y = new ConcreteMember(2);
        z = new ConcreteMember(3);
        m = new ConcreteMember(3);
        con = Container.getInstance();

        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void teardown() {

        x = null;
        y = null;
        z = null;
        m = null;
        Container.deleteInstance();

        System.setOut(originalOut);

    }

    @Test
    void testContainer() throws ContainerException {

        // Test von size() und addMember()
        assertEquals(con.size(), 0, "Fehler neuer Container sollte leer (0) sein");

        con.addMember(x);
        con.addMember(y);
        con.addMember(z);
        assertEquals(con.size(), 3, "Size sollte nach Hinzufügen 3 sein");

        //Test deleteMember() und size()
        con.deleteMember(2);
        con.deleteMember(3);
        assertEquals(con.size(), 1, "Size sollte nach löschen von 2 Membern 1 sein.");


        assertEquals(con.deleteMember(4), "Kein Member mit der ID (4) ist in der Liste enthalten.", "Sollte nicht möglich sein");

    }
    @Test
        // Test von Duplikat
    void testDuplikat() throws ContainerException {
        con.addMember(z);
        assertThrows(ContainerException.class, () -> con.addMember(m), "Member mit ID 3 bereits vorhanden.");

    }

    @Test
        // Test von dump aus MemberView
    void testDump() throws ContainerException {

        con.addMember(x);
        con.addMember(y);
        con.addMember(z);

        MemberView.dump(con.getCurrentList());
        assertEquals("Alle IDs der aktuell gespeicherten Obejkte im Container:\nMember (ID =1)\nMember (ID =2)\nMember (ID =3)\n", outContent.toString(), "Ausgabe dump Methode ist falsch");

    }
    @Test
    void testSecondContainer() {
        Container secondCon = Container.getInstance();
        assertEquals(secondCon, con, "Es konnte ein zweiter Container erstellt werden.");
    }


    @Test
    void testStoreAndLoad() {
        try {
            con.addMember(x);
            con.addMember(y);
            con.addMember(z);
            assertEquals(3, con.size(), "Size() sollte hier 3 sein!");
            con.store();
            List<Member> control = new ArrayList<Member>();
            control = con.getCurrentList();
            Container.deleteInstance();
            Container con2 = Container.getInstance();
//            con2.load();
//            assertEquals(control, con2.getCurrentList(),"Bitte funktioniere!");
        } catch (ContainerException | PersistenceException e) {
            e.printStackTrace();
        }
    }
}
