package at.fhj.swd.buchverzeichnis;

import at.fhj.swd.util.Transaction;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@org.junit.FixMethodOrder( org.junit.runners.MethodSorters.NAME_ASCENDING)
public class BuchTest {

    static GenreRepository genreRepository;static int gid = 97;
    static String genreDescription = "Thriller";
    static String genreUpdate = "Romane";
    static List<Buch> buch = new ArrayList<>();


    static BuchRepository buchRepository;
    static final int bid = 158;
    static final int isbn = 5656;
    static Date erscheinungsjahr ;
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
            genre = genreRepository.create(gid, genreDescription);
            buchRepository.create(bid, isbn, erscheinungsjahr, titel, genre);
            Transaction.commit();
        }

        @Test
        public void modify () {
            Buch buch = buchRepository.find(bid);
            assertNotNull(buch);
            Transaction.begin ();

            buch.setIsbn(isbnUpdate);
            Transaction.commit();

            buch = buchRepository.find(bid);
            assertEquals(isbnUpdate, (int) buch.getIsbn());
        }

        @Test
        public void remove () {
            Buch buch = buchRepository.find(bid);
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


