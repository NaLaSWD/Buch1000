package at.fhj.swd.buchverzeichnis;

import at.fhj.swd.util.Transaction;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.omg.CORBA.TRANSACTION_MODE;

import java.nio.file.attribute.AclEntry;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@org.junit.FixMethodOrder( org.junit.runners.MethodSorters.NAME_ASCENDING)
public class AutorBuchTest {

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
    static Verlag verlag;
    static Einzelbuero einzelbuero;

    static GenreRepository genreRepository;
    static int gid = 97;
    static String genreBezeichnung = "Thriller";
    static Buch buch;


    static BuchRepository buchRepository;
    static final int bid = 158;
    static final int isbn = 5656;
    static Date erscheinungsjahr ;
    static final String titel = "DB_Buch";
    static Genre genre;

    final int bid2 = 160;
    final int isbn2 = 8888;
    final String titel2 = "Java 9";


    @BeforeClass
    public static void setup() {
        verlagRepository = new VerlagRepository();
        einzelbueroRepository = new EinzelbueroRepository();
        autorRepository = new AutorRepository();
        genreRepository = new GenreRepository();
        buchRepository = new BuchRepository();
        Transaction.begin();
        autorRepository.reset();
        verlagRepository.reset();
        einzelbueroRepository.reset();
        buchRepository.reset();
        genreRepository.reset();
        Transaction.commit();
    }

    @Test
    public void createAutor() {
        Transaction.begin();
        verlag = verlagRepository.create(vid, name, strasse, ort, plz);
        einzelbuero = einzelbueroRepository.create(eid, eort, estrasse, eplz);
        autor = autorRepository.create(aid, vorname, nachname, geb_datum, verlag, einzelbuero);
        genre = genreRepository.create(gid, genreBezeichnung);
        buch = buchRepository.create(bid, isbn, erscheinungsjahr, titel, genre);
        Transaction.commit();
        buch = buchRepository.find(bid);
        autor = autorRepository.find(aid);
        Transaction.begin();
        buch.addAutor(autor);
        Transaction.commit();
    }

    @Test
    public void modify() {

        Transaction.begin();
        Buch newbuch = buchRepository.create(bid2, isbn2, erscheinungsjahr, titel2, genre);
        Transaction.commit();

        Transaction.begin();
        autor.addBuch(newbuch);
        Transaction.commit();
    }

    @Test
    public void remove() {

        Einzelbuero einzelbuero = einzelbueroRepository.find(eid);
        Verlag verlag = verlagRepository.find(vid);
        Autor autor = autorRepository.find(aid);
        Buch buch = buchRepository.find(bid);
        Buch buch2 = buchRepository.find(bid2);
        Genre genre = genreRepository.find(gid);

        assertNotNull(einzelbuero);
        assertNotNull(verlag);
        assertNotNull(autor);
        assertNotNull(buch);
        assertNotNull(buch2);
        assertNotNull(genre);


        Transaction.begin ();

        einzelbueroRepository.remove(einzelbuero);
        verlagRepository.remove(verlag);
        autorRepository.remove(autor);
        buchRepository.remove(buch);
        buchRepository.remove(buch2);
        genreRepository.remove(genre);

        Transaction.commit();
        einzelbuero =  einzelbueroRepository.find(eid);
        verlag = verlagRepository.find(vid);
        autor = autorRepository.find(aid);
        buch = buchRepository.find(bid);
        buch2 = buchRepository.find(bid2);
        genre = genreRepository.find(gid);

        assertNull (einzelbuero);
        assertNull (verlag);
        assertNull (autor);
        assertNull (buch);
        assertNull(buch2);
        assertNull (genre);

    }

    @AfterClass
    public static void teardown() {
        Transaction.begin();
        /*einzelbueroRepository.reset();
        verlagRepository.reset();
        autorRepository.reset();
        buchRepository.reset();
        genreRepository.reset();*/

        autorRepository.reset();
        buchRepository.reset();
        genreRepository.reset();
        verlagRepository.reset();
        einzelbueroRepository.reset();

        Transaction.commit();
    }
}
