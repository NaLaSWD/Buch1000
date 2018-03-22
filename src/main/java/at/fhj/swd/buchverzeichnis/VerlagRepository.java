package at.fhj.swd.buchverzeichnis;

import at.fhj.swd.persistence.IRepository;
import at.fhj.swd.persistence.Persistence;
import at.fhj.swd.persistence.Repository;

public class VerlagRepository extends Repository<Verlag> implements IRepository<Verlag> {

    private final static String schema = "public";
    private final static String table = "verlag";
    private final static String sequence = "verlag_id_seq";

    public VerlagRepository() {
        super(Verlag.class);
    }

    public VerlagRepository(String user, String password) {
        super(user, password, Verlag.class);
    }

    public Verlag create(int id, String name, String ort, String strasse, int plz) {
        Verlag verlag = new Verlag(id, name, ort, strasse, plz);
        entityManager.persist(verlag);
        return verlag;
    }

    public void reset() {
        Persistence.resetTable(schema, table);
        Persistence.resetSequence(schema, sequence);
    }

}

