package at.fhj.swd.test;

import at.fhj.swd.main.Buch;
import at.fhj.swd.main.BuchRepository;
import at.fhj.swd.util.Transaction;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


@org.junit.FixMethodOrder( org.junit.runners.MethodSorters.NAME_ASCENDING)
public class BuchTest {

        static BuchRepository buchRepository;
        static final int id = 158;
        static final int isbn = 5656;
        static final int jahr = 2017;
        static final String titel = "DB_Buch";
        static final int isbnUpdate = 7575;

        @BeforeClass
        public static void setup() {

            buchRepository = new BuchRepository();
            Transaction.begin();
            buchRepository.reset();
            Transaction.commit();
        }

        @Test
        public void create () {
            Transaction.begin();
            buchRepository.create(id, isbn, jahr, titel);
            Transaction.commit();
        }

        @Test public void getFindTitleByIdQuery () {
            String result = buchRepository.findTitleByTitel(id);
            assertEquals(titel, result);
        }

        @Test
        public void modify () {
            Buch buch = buchRepository.find(id);
            assertNotNull (buch);
            Transaction.begin ();

            buch.setIsbn(isbnUpdate);
            Transaction.commit();

            buch = buchRepository.find(id);
            assertEquals(isbnUpdate, (int) buch.getIsbn());
        }

        @Test
        public void remove () {
            Buch buch = buchRepository.find(id);
            assertNotNull (buch);
            Transaction.begin ();

            buchRepository.remove( buch );
            Transaction.commit();
            buch = buchRepository.find(id);
            assertNull (buch);
        }

        @AfterClass
        public static void teardown() {
            Transaction.begin();
            buchRepository.reset();
            Transaction.commit();
        }

}


