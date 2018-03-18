package at.fhj.swd.buchverzeichnis.main;

import at.fhj.swd.buchverzeichnis.persistence.IRepository;
import at.fhj.swd.buchverzeichnis.persistence.Persistence;
import at.fhj.swd.buchverzeichnis.persistence.Repository;

import java.util.Date;

public class BuchRepository extends Repository<Buch> implements IRepository<Buch> {

    private final static String schema = "public";
    private final static String table = "buch";
    private final static String sequence = "buch_id_seq";

    public BuchRepository() {
        super(Buch.class);
    }

    public BuchRepository(String user, String password, Class<Buch> entityClass) {
        super(user, password, entityClass);
    }

    public Buch create(int id, int isbn, Date erscheinungsjahr, String titel) {
        Buch buch = new Buch(id, isbn, erscheinungsjahr, titel);
        entityManager.persist(buch);
        return buch;
    }

    public void reset() {
        Persistence.resetTable(schema, table);
        Persistence.resetSequence(schema, sequence);
    }
}
