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
public class GenreTest {

    static GenreRepository genreRepository;
    static Genre thriller;
    static int id = 97;
    static String bezeichnung = "Thriller";
    static String bezeichnungUpdate = "Romane";

    @BeforeClass
    public static void setup(){
        genreRepository = new GenreRepository();
        Transaction.begin();
        genreRepository.reset();
        Transaction.commit();
    }

    @Test
    public void create(){
        Transaction.begin();
        Genre newGenre = genreRepository.create(id, bezeichnung);
        Transaction.commit();
        assertEquals(id, newGenre.getId());
    }

    @Test
    public void modify(){
        thriller = genreRepository.find(id);
        assertNotNull (thriller);

        Transaction.begin();
        thriller.setBezeichnung(bezeichnungUpdate);
        Transaction.commit();

        thriller = null;
        thriller = genreRepository.find(id);
        assertEquals(bezeichnungUpdate, thriller.getBezeichnung());
    }

    @Test
    public void remove () {
        thriller = genreRepository.find(id);
        assertNotNull (thriller);

        Transaction.begin ();
        genreRepository.remove( thriller );
        Transaction.commit();

        thriller = genreRepository.find(id);
        assertNull (thriller);
    }

    @AfterClass
    public static void teardown(){
        Transaction.begin();
        genreRepository.reset();
        Transaction.commit();
    }

}
