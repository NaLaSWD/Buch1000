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
import static org.junit.Assert.assertTrue;

public class AutorBuchTest {

    static BuchRepository buchRepository;
    static final int bid = 158;
    static final int bid2 = 565;
    static final int isbn = 5656;
    static final int isbn2 = 2920;
    static Date erscheinungsjahr ;
    static Date erscheinungsjahr2;
    static final String titel = "DB_Buch";
    static final String titel2 = "Datenbanken";
    static Genre genre;
    static List<Autor> autor = new ArrayList<>();

    static Buch buchIsbn;
    static Buch buchIsbn2;

    static Autor SimonBeckett;
    static AutorRepository autorRepository;
    static Verlag verlag;
    static VerlagRepository verlagRepository;
    static Einzelbuero einzelbuero;
    static EinzelbueroRepository einzelbueroRepository;


    @BeforeClass
    public static void setup() {
        buchRepository = new BuchRepository();
        Transaction.begin();
        buchRepository.reset();
        Transaction.commit();
    }


    public Autor buildAutor(int id, String vorname, String nachname, Date geb_datum, Verlag verlag, Einzelbuero einzelbuero) {

        SimonBeckett = autorRepository.create(id, vorname, nachname, geb_datum, verlag, einzelbuero);

        return SimonBeckett;
    }


    public Autor buildAutor() {
        verlag = verlagRepository.create(123, "AUT-Verlag", "Graz", "Verlagstrasse 1", 8010);
        einzelbuero = einzelbueroRepository.create(456, "Graz", "E-Bueroweg 1", 8010);

        return buildAutor(789, "Simon", "Becket", null, verlag, einzelbuero);
    }


    @Test
    public void createAutor() {
        Transaction.begin();

        buchIsbn = buchRepository.create(bid, isbn, erscheinungsjahr, titel, genre);
        buchIsbn2 = buchRepository.create(bid2, isbn2, erscheinungsjahr2, titel2, genre);

        SimonBeckett = buildAutor();

        // join Autor and Buch
        SimonBeckett.addBuch(buchIsbn);

        Transaction.commit();

        // test bidirectional connections
        assertTrue(SimonBeckett.getBuch().contains(buchIsbn));
        assertTrue(buchIsbn.getAutor().contains(SimonBeckett));
    }

    @Test
    public void modify() {
        Transaction.begin();

        SimonBeckett.addBuch(buchIsbn);
        Transaction.commit();

        SimonBeckett = autorRepository.find(bid);
        assertNotNull(SimonBeckett);

        buchIsbn = buchRepository.find(bid);
        buchIsbn2 = buchRepository.find(bid2);

        assertNotNull(buchIsbn);
        assertNotNull(buchIsbn2);
        assertTrue(buchIsbn.getAutor().contains(SimonBeckett));
        assertTrue(buchIsbn2.getAutor().contains(SimonBeckett));
        assertTrue(SimonBeckett.getBuch().contains(buchIsbn));
        assertTrue(SimonBeckett.getBuch().contains(buchIsbn2));
    }

    @Test
    public void remove() {
        Buch buch = buchRepository.find(bid);
        Autor autor = autorRepository.find(bid);

        assertNotNull(buch);
        assertNotNull(autor);

        Transaction.begin();

        buchRepository.remove(buch);
        autorRepository.remove(autor);

        Transaction.commit();

        buch = buchRepository.find(bid);
        autor = autorRepository.find(bid);

        assertNull(buch);
        assertNull(autor);
    }

    @AfterClass
    public static void teardown() {
        Transaction.begin();
        autorRepository.reset();
        buchRepository.reset();
        Transaction.commit();
    }
}
