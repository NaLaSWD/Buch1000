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

    private static VerlagRepository verlagRepository;
     static final int id = 158;
    private static final String name = "VerlAut";
    private static final String strasse = "Heimweg 10";
    private static final String ort = "Graz";
    private static final int plz = 8010;
    private static final String strasseUpdate = "Hausstrasse 20";
    private static List<Autor> autor = new ArrayList<>();

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
        verlagRepository.create(id, name, ort, strasse, plz);
        Transaction.commit();
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
