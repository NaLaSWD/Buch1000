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

@org.junit.FixMethodOrder( org.junit.runners.MethodSorters.NAME_ASCENDING)
public class AutorBuchTest {

    /*static BuchRepository buchRepository;
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
    static Buch buchIsbn2;*/

    /*static Autor SimonBeckett;
    static AutorRepository autorRepository;
    static Verlag verlag;
    static VerlagRepository verlagRepository;
    static Einzelbuero einzelbuero;
    static EinzelbueroRepository einzelbueroRepository;*/

    static VerlagRepository verlagRepository;
    static final int vid = 158;
    static final String name = "VerlAut";
    static final String strasse = "Heimweg 10";
    static final String ort = "Graz";
    static final int plz = 8010;
    static final String strasseUpdate = "Hausstrasse 20";
    static List<Autor> autoren = new ArrayList<>();
    static Autor autor;

    static EinzelbueroRepository einzelbueroRepository;
    static final int eid = 123;
    static final String eort = "Graz";
    static final String estrasse = "Strass 1";
    static final int eplz = 8010;
    static final int eplzUpdate = 8020 ;
    static List<Autor> eautor = new ArrayList<>();

    static AutorRepository autorRepository;
    static final int aid = 567;
    static final String vorname = "Max";
    static final String nachname = "Mayer";
    static Date geb_datum;
    static final String vornameUpdate = "Martin";
    static List<Buch> buecher = new ArrayList<>();
    static Verlag verlag;
    static Einzelbuero einzelbuero;

    static GenreRepository genreRepository;static int gid = 97;
    static String genreDescription = "Thriller";
    static String genreUpdate = "Romane";
    //static List<Buch> buch = new ArrayList<>();
    static Buch buch;


    static BuchRepository buchRepository;
    static final int bid = 158;
    static final int isbn = 5656;
    static Date erscheinungsjahr ;
    static final String titel = "DB_Buch";
    static final int isbnUpdate = 7575;
    static Genre genre;
    //static List<Autor> autoren = new ArrayList<>();


    @BeforeClass
    public static void setup() {
        genreRepository = new GenreRepository();
        verlagRepository = new VerlagRepository();
        einzelbueroRepository = new EinzelbueroRepository();
        autorRepository = new AutorRepository();
        buchRepository = new BuchRepository();
        Transaction.begin();
        autorRepository.reset();
        buchRepository.reset();
        genreRepository.reset();
        verlagRepository.reset();
        einzelbueroRepository.reset();
        Transaction.commit();
    }

    @Test
    public void createAutor() {
        Transaction.begin();
        verlag = verlagRepository.create(vid, name, strasse, ort, plz);
        einzelbuero = einzelbueroRepository.create(eid, eort, estrasse, eplz);
        autor = autorRepository.create(aid, vorname, nachname, geb_datum, verlag, einzelbuero);
        genre = genreRepository.create(gid, genreDescription);
        buch = buchRepository.create(bid, isbn, erscheinungsjahr, titel, genre);
        Transaction.commit();
        buch = null;
        autor = null;
        buch = buchRepository.find(bid);
        autor = autorRepository.find(aid);
        Transaction.begin();
        buch.addAutor(autor);
        Transaction.commit();
    }

    @Test
    public void modify() {
        //TODO
    }

    @Test
    public void remove() {
        //TODO
    }

    @AfterClass
    public static void teardown() {
        Transaction.begin();
        autorRepository.reset();
        buchRepository.reset();
        genreRepository.reset();
        verlagRepository.reset();
        einzelbueroRepository.reset();
        Transaction.commit();
    }
}