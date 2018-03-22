package at.fhj.swd.buchverzeichnis;

import at.fhj.swd.persistence.IRepository;
import at.fhj.swd.persistence.Persistence;
import at.fhj.swd.persistence.Repository;


public class EinzelbueroRepository extends Repository<Einzelbuero> implements IRepository<Einzelbuero> {

    private final static String schema = "public";
    private final static String table = "einzelbuero";
    private final static String sequence = "einzelbuero_id_seq";

    public EinzelbueroRepository() {
        super(Einzelbuero.class);
    }

    public EinzelbueroRepository(String user, String password) { super(user, password, Einzelbuero.class);
    }

    public Einzelbuero create(int id, String ort, String strasse, int plz) {
        Einzelbuero einzelbuero = new Einzelbuero(id, ort, strasse, plz);
        entityManager.persist(einzelbuero);
        return einzelbuero;
    }

    public void reset() {
        Persistence.resetTable(schema, table);
        Persistence.resetSequence(schema, sequence);
    }
}
