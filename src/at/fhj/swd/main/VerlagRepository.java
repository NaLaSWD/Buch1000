package at.fhj.swd.main;

import at.fhj.swd.persistence.IRepository;
import at.fhj.swd.persistence.Persistence;
import at.fhj.swd.persistence.Repository;
import javax.persistence.Id;

public class VerlagRepository extends Repository<Verlag> implements IRepository<Verlag> {

    @Id
    private int id;
    private final static String schema = "public";
    private final static String table = "verlag";
    private final static String sequence = "verlag_id_seq";

    public VerlagRepository() {
        super(Verlag.class);
    }

    public VerlagRepository(String user, String password, Class<Verlag> entityClass) {
        super(user, password, entityClass);
    }

    public void reset() {
        Persistence.resetTable(schema, table);
        Persistence.resetSequence(schema, sequence);
    }

    public Verlag create(int id, String name, String ort, String strasse, int plz) {
        Verlag verlAut = new Verlag(id, name, ort, strasse, plz);
        entityManager.persist(verlAut);
        return verlAut;
    }
}

