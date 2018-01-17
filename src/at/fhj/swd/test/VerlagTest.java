package at.fhj.swd.test;

import at.fhj.swd.main.Verlag;
import at.fhj.swd.main.VerlagRepository;
import at.fhj.swd.util.Transaction;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

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




/*
package at.fhj.swd.test;

import at.fhj.swd.main.GenreRepository;
import at.fhj.swd.main.Verlag;
import at.fhj.swd.main.VerlagRepository;
import at.fhj.swd.util.Transaction;
import org.junit.AfterClass;
import org.junit.Test;

import static org.junit.Assert.*;

@org.junit.FixMethodOrder( org.junit.runners.MethodSorters.NAME_ASCENDING)
public class VerlagTest {

    private static VerlagRepository verlagRepository;
    private static int id = 765596;
    private static String name = "Verlag Austria";
    private static String ort = "Graz";
    private static String strasse = "Heimweg 10";
    private static int plz = 8010;
    private static String strasseUpdate = "Hausweg 20";

    @Test
    public void create () {
        verlagRepository = new VerlagRepository();
        Transaction.begin();
        verlagRepository.reset();
        Transaction.commit();
    }

    @Test
    public void modify () {
        Verlag verlAut = verlagRepository.find(id);
        assertNotNull (verlAut);
        Transaction.begin ();

        verlAut.setStrasse(strasseUpdate);
        Transaction.commit();

        verlAut = verlagRepository.find(id);
        assertEquals(strasseUpdate, (String) verlAut.getStrasse());
    }

    @Test
    public void remove () {
        Verlag verlAut = verlagRepository.find(id);
        assertNotNull (verlAut);
        Transaction.begin ();

        verlagRepository.remove( verlAut );
        Transaction.commit();
        verlAut = verlagRepository.find(id);
        assertNull (verlAut);
    }

    @AfterClass
    public static void teardown() {
        Transaction.begin();
        verlagRepository.reset();
        Transaction.commit();
    }
}


*/
