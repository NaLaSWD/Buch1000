package at.fhj.swd.buchverzeichnis.test;

import at.fhj.swd.buchverzeichnis.main.Autor;
import at.fhj.swd.buchverzeichnis.main.VerlagRepository;
import at.fhj.swd.buchverzeichnis.main.Verlag;
import at.fhj.swd.buchverzeichnis.util.Transaction;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


@org.junit.FixMethodOrder( org.junit.runners.MethodSorters.NAME_ASCENDING)
public class VerlagTest {

    static VerlagRepository verlagRepository;
    static final int id = 158;
    static final String name = "verlAut";
    static final String ort = "Graz";
    static final String strasse = "Heimweg 10";
    static final int plz = 8010;
    static final String strasseUpdate = "Hausstrasse 20";
    static List<Autor> autor;

    @BeforeClass
    public static void setup() {

        verlagRepository = new VerlagRepository();
        Transaction.begin();
        verlagRepository.reset();
        Transaction.commit();
    }


    @Test
    public void create () {
        Transaction.begin();
        verlagRepository.create(id, name, ort, strasse, plz, autor);
        Transaction.commit();
    }

    @Test public void getFindOrtByIdQuery () {
        String result = verlagRepository.findOrtById(id);
        assertEquals(ort, result);
    }

    @Test
    public void modify () {
        Verlag verlag = verlagRepository.find(id);
        assertNotNull (verlag);
        Transaction.begin ();

        verlag.setStrasse(strasseUpdate);
        Transaction.commit();

        verlag = verlagRepository.find(id);
        assertEquals(strasseUpdate, (String) verlag.getStrasse());
    }

    @Test
    public void remove () {
        Verlag verlag = verlagRepository.find(id);
        assertNotNull (verlag);
        Transaction.begin ();

        verlagRepository.remove( verlag );
        Transaction.commit();
        verlag = verlagRepository.find(id);
        assertNull (verlag);
    }

    @AfterClass
    public static void teardown() {
        Transaction.begin();
        verlagRepository.reset();
        Transaction.commit();
    }

}
