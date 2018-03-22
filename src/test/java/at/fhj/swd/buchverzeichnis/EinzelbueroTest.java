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
public class EinzelbueroTest {

    static EinzelbueroRepository einzelbueroRepository;
    static final int id = 123;
    static final String ort = "Graz";
    static final String strasse = "Strass 1";
    static final int plz = 8010;
    static final int plzUpdate = 8020 ;
    static List<Autor> autor = new ArrayList<>();

    @BeforeClass
    public static void setup() {
        einzelbueroRepository = new EinzelbueroRepository();
        Transaction.begin();
        einzelbueroRepository.reset();
        Transaction.commit();
    }

    @Test
    public void create () {
        Transaction.begin();
        einzelbueroRepository.create(id, ort, strasse, plz);
        Transaction.commit();
    }

    @Test
    public void modify () {
        Einzelbuero einzelbuero = einzelbueroRepository.find(id);
        assertNotNull (einzelbuero);
        Transaction.begin ();

        einzelbuero.setPlz(plzUpdate);
        Transaction.commit();

        einzelbuero = einzelbueroRepository.find(id);
        assertEquals(plzUpdate, (int) einzelbuero.getPlz());
    }

    @Test
    public void remove () {
        Einzelbuero einzelbuero = einzelbueroRepository.find(id);
        assertNotNull (einzelbuero);
        Transaction.begin ();
        einzelbueroRepository.remove( einzelbuero );
        Transaction.commit();
        einzelbuero = einzelbueroRepository.find(id);
        assertNull (einzelbuero);
    }

    @AfterClass
    public static void teardown() {
        Transaction.begin();
        einzelbueroRepository.reset();
        Transaction.commit();
    }

}


