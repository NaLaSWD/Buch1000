package at.fhj.swd.test;

import at.fhj.swd.main.Verlag;
import at.fhj.swd.main.VerlagRepository;
import at.fhj.swd.util.Transaction;
import org.junit.AfterClass;
import org.junit.Test;

import static org.junit.Assert.*;

@org.junit.FixMethodOrder( org.junit.runners.MethodSorters.NAME_ASCENDING)
public class VerlagTest {

    private VerlagRepository verlagRepository;
    private int id = 765596;
    private String name = "Verlag Austria";
    private String ort = "Graz";
    private String strasse = "Heimweg 10";
    private int plz = 8010;
    private String strasseUpdate = "Hausweg 20";

    @Test
    public void create () {
        Transaction.begin();
        verlagRepository.create(id, name, ort, name, plz);
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
    }

}


