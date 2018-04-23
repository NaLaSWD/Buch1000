package at.fhj.swd.buchverzeichnis;

import at.fhj.swd.util.Transaction;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.TypedQuery;
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

    /*//Einelbuero Test
    public Einzelbuero findEinzelbueroOrtByAutorID(String name){
        TypedQuery<Einzelbuero> query = entityManager.createNamedQuery("Einzelbuero.findEinzelbueroOrtByAutorID", Einzelbuero.class);
        query.setParameter("Einzelbuero", name);
        return query.getSingleResult();
    }*/

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

