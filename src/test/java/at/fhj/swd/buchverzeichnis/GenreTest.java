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
    static int id = 97;
    static String bezeichnung = "Thriller";
    static String genreUpdate = "Romane";
    static List<Buch> buch = new ArrayList<>();

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
        genreRepository.create(id, bezeichnung);
        Transaction.commit();
    }

    @Test
    public void modify(){
        Genre thriller = genreRepository.find(id);
        assertNotNull (thriller);
        Transaction.begin();

        thriller.setBezeichnung(genreUpdate);
        Transaction.commit();

        thriller = genreRepository.find(id);
        assertEquals(genreUpdate, (String) thriller.getBezeichnung());
    }

    @Test
    public void remove () {
        Genre thriller = genreRepository.find(id);
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
