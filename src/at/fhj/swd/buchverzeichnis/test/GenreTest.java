package at.fhj.swd.buchverzeichnis.test;

import at.fhj.swd.buchverzeichnis.main.Genre;
import at.fhj.swd.buchverzeichnis.main.GenreRepository;
import at.fhj.swd.buchverzeichnis.main.Buch;
import at.fhj.swd.buchverzeichnis.util.Transaction;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@org.junit.FixMethodOrder( org.junit.runners.MethodSorters.NAME_ASCENDING)
public class GenreTest {

    private static GenreRepository genreRepository;
    private static int id = 97;
    private static String genre = "Thriller";
    private static String genreUpdate = "Romane";
    private List<Buch> buch;

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
        genreRepository.create(id, genre);
        Transaction.commit();
    }

    /*@Test public void getFindGenreByIdQuery () {
        String result = genreRepository.findGenreById(id);
        assertEquals(genre, result);
    }*/

    @Test
    public void modify(){
        Genre thriller = genreRepository.find(id);
        assertNotNull (thriller);
        Transaction.begin();

        thriller.setGenre(genreUpdate);
        Transaction.commit();

        thriller = genreRepository.find(id);
        assertEquals(genreUpdate, (String) thriller.getGenre());
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