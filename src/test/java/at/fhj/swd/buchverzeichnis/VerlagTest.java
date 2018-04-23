package at.fhj.swd.buchverzeichnis;

import at.fhj.swd.util.Transaction;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


@org.junit.FixMethodOrder( org.junit.runners.MethodSorters.NAME_ASCENDING)
public class VerlagTest {

    static VerlagRepository verlagRepository;
    static Verlag verlag;
    static final int id = 158;
    static final String name = "VerlAut";
    static final String strasse = "Heimweg 10";
    static final String ort = "Graz";
    static final int plz = 8010;
    static final String strasseUpdate = "Hausstrasse 20";

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
        verlag = verlagRepository.create(id, name, ort, strasse, plz);
        Transaction.commit();
        assertEquals(strasse, verlag.getStrasse());
    }

    @Test
    public void modify () {
        verlag = verlagRepository.find(id);
        assertNotNull (verlag);

        Transaction.begin ();
        verlag.setStrasse(strasseUpdate);
        Transaction.commit();

        verlag = null;
        verlag = verlagRepository.find(id);
        assertEquals(strasseUpdate, verlag.getStrasse());
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
