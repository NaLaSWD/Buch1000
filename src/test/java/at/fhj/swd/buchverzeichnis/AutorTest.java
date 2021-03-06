package at.fhj.swd.buchverzeichnis;

import at.fhj.swd.util.Transaction;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;


@org.junit.FixMethodOrder( org.junit.runners.MethodSorters.NAME_ASCENDING)
public class AutorTest {

    static VerlagRepository verlagRepository;
    static final int vid = 158;
    static final String name = "VerlAut";
    static final String strasse = "Heimweg 10";
    static final String ort = "Graz";
    static final int plz = 8010;
    static Autor autor;

    static EinzelbueroRepository einzelbueroRepository;
    static final int eid = 123;
    static final String eort = "Graz";
    static final String estrasse = "Strass 1";
    static final int eplz = 8010;

    static AutorRepository autorRepository;
    static final int aid = 567;
    static final String vorname = "Max";
    static final String nachname = "Mayer";
    static Date geb_datum;
    static final String vornameUpdate = "Martin";
    static Verlag verlag;
    static Einzelbuero einzelbuero;

    @BeforeClass
    public static void setup() {
        verlagRepository = new VerlagRepository();
        einzelbueroRepository = new EinzelbueroRepository();
        autorRepository = new AutorRepository();
        Transaction.begin();
        autorRepository.reset();
        einzelbueroRepository.reset();
        verlagRepository.reset();
        Transaction.commit();
    }

    @Test
    public void create () {
        Transaction.begin();
        verlag = verlagRepository.create(vid, name, strasse, ort, plz);
        einzelbuero = einzelbueroRepository.create(eid, eort, estrasse, eplz);
        autor = autorRepository.create(aid, vorname, nachname, geb_datum, verlag, einzelbuero);
        Transaction.commit();

        assertEquals(einzelbuero, autor.getEinzelbuero());
        assertTrue(einzelbuero.getAutor().equals(autor));
        assertEquals(verlag, autor.getVerlag());

    }

    @Test
    public void modify () {
        autor = autorRepository.find(aid);
        assertNotNull (autor);

        Transaction.begin();
        autor.setVorname(vornameUpdate);
        Transaction.commit();

        autor = null;
        autor = autorRepository.find(aid);
        assertEquals(vornameUpdate, autor.getVorname());
    }


    @Test
    public void findAutorByVerlag(){
        List<Autor> result = autorRepository.findAutorByVerlag(name);
        assertEquals(1, result.size());
    }

    @Test
    public void findEinzelbueroByVerlag(){
        String result = autorRepository.findEinzelbueroByVerlag(nachname);
        assertEquals(name, result);
    }

    @Test
    public void findEinzelbueroByAutor(){
        String result = autorRepository.findEinzelbueroByAutor(nachname);
        assertEquals(estrasse, result);
    }

    @Test
    public void findBuecherByAutor(){
        List<Buch> result = autorRepository.findBuecherByAutor(nachname);
        assertEquals(1, result.size());
    }


    @Test
    public void remove() {
        Autor autor = autorRepository.find(aid);
        assertNotNull (autor);

        Transaction.begin ();
        autorRepository.remove(autor);
        Transaction.commit();

        autor = autorRepository.find(aid);
        assertNull (autor);
    }

    @AfterClass
    public static void teardown() {
        Transaction.begin();
        autorRepository.reset();
        einzelbueroRepository.reset();
        verlagRepository.reset();
        Transaction.commit();
    }

}


