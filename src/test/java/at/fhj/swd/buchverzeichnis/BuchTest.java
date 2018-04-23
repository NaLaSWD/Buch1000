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
public class BuchTest {

    static BuchRepository buchRepository;
    static GenreRepository genreRepository;
    static List<Buch> buecher = new ArrayList<>();

    static Genre genre;
    static int gid = 97;
    static String genreBezeichnung = "Lehrbuch";

    static Buch buch;
    static final int bid = 158;
    static final int isbn = 5656;
    static Date erscheinungsjahr = new Date(20100822);
    static final String titel = "DB_Buch";
    static final int isbnUpdate = 7575;

    static Buch buch2;
    static final int bid2 = 987;
    static final int isbn2 = 3434;
    static Date erscheinungsjahr2 = new Date(20120404);
    static final String titel2 = "SQL beginner";
    static final String titel2Update = "SQL advanced";

    @BeforeClass
    public static void setup() {

        genreRepository = new GenreRepository();
        buchRepository = new BuchRepository();
        Transaction.begin();
        buchRepository.reset();
        genreRepository.reset();
        Transaction.commit();
    }

    @Test
    public void create () {
        Transaction.begin();
        genre = genreRepository.create(gid, genreBezeichnung);
        buch = buchRepository.create(bid, isbn, erscheinungsjahr, titel, genre);
        buch2 = buchRepository.create(bid2, isbn2, erscheinungsjahr2, titel2, genre);
        buecher.add(buch);
        buecher.add(buch2);
        Transaction.commit();
        assertEquals(genre, buch.getGenre());
        assertTrue(genre.getBuecher().equals(buecher));
        assertEquals(2, buecher.size());
    }

    @Test
    public void modify () {
        buch = buchRepository.find(bid);
        assertNotNull(buch);
        buch2 = buchRepository.find(bid2);
        assertNotNull(buch2);

        Transaction.begin ();
        buch.setIsbn(isbnUpdate);
        buch2.setTitel(titel2Update);
        Transaction.commit();

        buch = null;
        buch = buchRepository.find(bid);
        assertEquals(isbnUpdate, buch.getIsbn());

        buch2 = null;
        buch2 = buchRepository.find(bid2);
        assertEquals(titel2Update, buch2.getTitel());
    }

    @Test
    public void findGenreBezeichnungByBuchTitel(){
        String result = genreRepository.findGenreBezeichnung(titel);
        String result2 = genreRepository.findGenreBezeichnung(titel2);
        assertEquals(genreBezeichnung, result);
        assertEquals(genreBezeichnung, result2);
    }

    @Test
    public void findBuecherByGenre(){
        List<Buch> result = genreRepository.findBuecherByGenre(genreBezeichnung);
        assertTrue(buecher.containsAll(result));
    }

    /*@Test
    public void findBuchTitelByErscheinungsjahr(){
        String result = buchRepository.findBuchTitelByErscheinungsjahr(erscheinungsjahr);
        assertEquals(titel, result);
    }

    @Test
    public void findBuchIdByGenre(){
        List<Buch> result = buchRepository.findBuchIdByGenre(genreBezeichnung);

        for (Object i: result) {
            int test = buecher.iterator().next().getIsbn();
            assertEquals(buecher.iterator().next().getIsbn(), (int)i);
        }
    }*/

    @Test
    public void remove () {
        buch = buchRepository.find(bid);
        assertNotNull (buch);

        buch2 = buchRepository.find(bid2);
        assertNotNull(buch2);
        Transaction.begin ();

        buchRepository.remove( buch );
        buchRepository.remove(buch2);
        Transaction.commit();
        buch = buchRepository.find(bid);
        buch2 = buchRepository.find(bid2);
        assertNull (buch);
        assertNull (buch2);
    }

    @AfterClass
    public static void teardown() {
        Transaction.begin();
        buchRepository.reset();
        genreRepository.reset();
        Transaction.commit();
    }

}