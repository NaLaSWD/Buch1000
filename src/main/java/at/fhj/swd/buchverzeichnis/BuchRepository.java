package at.fhj.swd.buchverzeichnis;

import at.fhj.swd.persistence.IRepository;
import at.fhj.swd.persistence.Persistence;
import at.fhj.swd.persistence.Repository;

import java.util.Date;

public class BuchRepository extends Repository<Buch> implements IRepository<Buch> {

    private final static String schema = "public";
    private final static String table = "buch";
    private final static String sequence = "buch_id_seq";

    public BuchRepository() {
        super(Buch.class);
    }

    public BuchRepository(String user, String password) {
        super(user, password, Buch.class);
    }

    public Buch create(int id, int isbn, Date erscheinungsjahr, String titel, Genre genre) {
        Buch buch = new Buch(id, isbn, erscheinungsjahr, titel, genre);
        entityManager.persist(buch);
        return buch;
    }

    public void reset() {
        Persistence.resetTable(schema, table);
        Persistence.resetSequence(schema, sequence);
    }
}
