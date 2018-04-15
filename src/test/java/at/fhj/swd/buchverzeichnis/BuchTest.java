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

    static GenreRepository genreRepository;static int gid = 97;
    //static String genreDescription = "Thriller";
    static String genreBezeichnung = "Thriller";
    static List<Buch> buecher = new ArrayList<>();


    static BuchRepository buchRepository;
    static Buch buch;
    static final int bid = 158;
    static final int isbn = 5656;
    static Date erscheinungsjahr = new Date(20100822);
    static final String titel = "DB_Buch";
    static final int isbnUpdate = 7575;
    static Genre genre;
    static List<Autor> autor = new ArrayList<>();

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
        buecher.add(buch);
        Transaction.commit();
        assertEquals(genre, buch.getGenre());
        assertTrue(genre.getBuecher().equals(buecher));
    }

    @Test
    public void modify () {
        buch = buchRepository.find(bid);
        assertNotNull(buch);
        Transaction.begin ();

        buch.setIsbn(isbnUpdate);
        Transaction.commit();

        buch = buchRepository.find(bid);
        assertEquals(isbnUpdate, (int) buch.getIsbn());
    }

    @Test
    public void findGenreBezeichnungByBuchTitel(){
        String result = genreRepository.findGenreBezeichnung(titel);
        assertEquals(genreBezeichnung, result);
    }

    @Test
    public void findBuecherByGenre(){
        List<Buch> result = genreRepository.findBuecherByGenre(genreBezeichnung);
        assertEquals(buecher, result);
    }


    //Buch-Query Tests
    @Test
    public void findBuchTitelByErscheinungsjahr(){
        String result = buchRepository.findBuchTitelByErscheinungsjahr(erscheinungsjahr);
        assertEquals(titel, result);
    }

    @Test
    public void findBuchIdByGenre(){
        Integer result = buchRepository.findBuchIdByGenre(genreBezeichnung);
        assertEquals(buecher.get(0).getId(), (int)result);
    }



    @Test
    public void remove () {
        buch = buchRepository.find(bid);
        assertNotNull (buch);
        Transaction.begin ();

        buchRepository.remove( buch );
        Transaction.commit();
        buch = buchRepository.find(bid);
        assertNull (buch);
    }

    @AfterClass
    public static void teardown() {
        Transaction.begin();
        buchRepository.reset();
        genreRepository.reset();
        Transaction.commit();
    }

}