package at.fhj.swd.main;

import at.fhj.swd.persistence.IRepository;
import at.fhj.swd.persistence.Persistence;
import at.fhj.swd.persistence.Repository;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BuchRepository extends Repository<Buch> implements IRepository<Buch> {

    @Id
    private int id;

    private final static String schema = "public";
    private final static String table = "buch";
    private final static String sequence = "buch_id_seq";

    public BuchRepository() {
        super(Buch.class);
    }

    public BuchRepository(String user, String password, Class<Buch> entityClass) {
        super(user, password, entityClass);
    }

    public void reset() {
        Persistence.resetTable(schema, table);
        Persistence.resetSequence(schema, sequence);
    }

    public Buch create(int id, int isbn, int jahr, String titel) {
        Buch buch1 = new Buch(id, isbn, jahr, titel);
        entityManager.persist(buch1);
        return buch1;
    }
}