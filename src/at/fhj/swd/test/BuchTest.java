package at.fhj.swd.test;

import at.fhj.swd.main.BuchRepository;
import at.fhj.swd.util.Transaction;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/*import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;*/


@org.junit.FixMethodOrder( org.junit.runners.MethodSorters.NAME_ASCENDING)
public class BuchTest {

        /*static EntityManagerFactory factory;
        static EntityManager manager;
        static EntityTransaction transaction;*/
        static final String persistenceUnitName = "BuchverzeichnisDb";

        static BuchRepository buchRepository;
        static final int id = 158;
        static final int isbn = 5656;
        static final int jahr = 2017;
        static final String titel = "DB_Buch";
        static final int isbnUpdate = 7575;

        @BeforeClass
        public static void setup() {

            buchRepository = new BuchRepository();
            /*factory = Persistence.createEntityManagerFactory(persistenceUnitName);
            assertNotNull(factory);

            manager = factory.createEntityManager();
            assertNotNull(manager);

            transaction = manager.getTransaction();
            assertNotNull(transaction);*/
        }


        @Test
        public void create () {
            Transaction.begin();
            buchRepository.create(id, isbn, jahr, titel);
            Transaction.commit();
            /*transaction.begin ();
            Buch buch = new Buch (id, isbn, jahr, titel);
            System.out.println("test: " + buch);

            assertNotNull (buch);
            manager.persist(buch);
            transaction.commit();*/
        }

   /*  @Test
    public void testCreateBuch(){
        BuchEntity buch1 = new BuchEntity();
        buch1.setIsbn(5656);
        buch1.setJahr(2018);
        buch1.setTitel("HalloBuch");

     Dataaccess.getInstance().getManager().getTransaction().begin();
        Dataaccess.getInstance().getManager().persist(buch1);
        Dataaccess.getInstance().getManager().getTransaction().commit();
        transaction.begin();
        manager.persist(buch1);
        transaction.commit();
    }*/

       /* @Test
        public void modify () {
            Buch buch = manager.find (Buch.class, id);
            assertNotNull (buch);
            transaction.begin ();

            buch.setIsbn(isbnUpdate);
            transaction.commit();
            teardown();
            setup();

            buch = manager.find(Buch.class, id);
            assertEquals(isbn + isbnUpdate, (int) buch.getIsbn());
        }

        @Test
        public void remove () {
            Buch buch = manager.find (Buch.class, id);
            assertNotNull (buch);
            transaction.begin ();
            manager.remove ( buch );
            transaction.commit();
            buch = manager.find(Buch.class, id);
            assertNull (buch);
        }

*/
        @AfterClass
        public static void teardown() {
            Transaction.begin();
            buchRepository.reset();
            Transaction.commit();
        }

}


