package at.fhj.swd.buchverzeichnis.test;

import at.fhj.swd.buchverzeichnis.main.*;
import at.fhj.swd.buchverzeichnis.util.Transaction;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;


@org.junit.FixMethodOrder( org.junit.runners.MethodSorters.NAME_ASCENDING)
public class AutorTest {

    static AutorRepository autorRepository;
    static final int id = 567;
    static final String vorname = "Max";
    static final String nachname = "Mayer";
    static Date geb_datum;
    static final String vornameUpdate = "Martin";
    static List<Buch> buecher;
    static List<Verlag> verlag;
    static Einzelbuero einzelbuero;

    @BeforeClass
    public static void setup() {

        autorRepository = new AutorRepository();
        Transaction.begin();
        autorRepository.reset();
        Transaction.commit();
    }

    @Test
    public void create () {
        Transaction.begin();
        autorRepository.create(id, vorname, nachname, geb_datum);
        Transaction.commit();
    }

    /*@Test public void getFindFirstnameByIdQuery () {
        String result = autorRepository.findFirstnameById(id);
        assertEquals(vorname, result);
    }*/

    @Test
    public void modify () {
        Autor autor = autorRepository.find(id);
        assertNotNull (autor);
        Transaction.begin ();

        autor.setVorname(vornameUpdate);
        Transaction.commit();

        autor = autorRepository.find(id);
        assertEquals(vornameUpdate, (String) autor.getVorname());
    }

    @Test
    public void remove () {
        Autor autor = autorRepository.find(id);
        assertNotNull (autor);
        Transaction.begin ();
        autorRepository.remove(autor);
        Transaction.commit();
        autor = autorRepository.find(id);
        assertNull (autor);
    }

    @AfterClass
    public static void teardown() {
        Transaction.begin();
        autorRepository.reset();
        Transaction.commit();
    }

}


