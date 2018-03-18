package at.fhj.swd.buchverzeichnis.main;

import at.fhj.swd.buchverzeichnis.persistence.IRepository;
import at.fhj.swd.buchverzeichnis.persistence.Persistence;
import at.fhj.swd.buchverzeichnis.persistence.Repository;


public class EinzelbueroRepository extends Repository<Einzelbuero> implements IRepository<Einzelbuero> {

    private final static String schema = "public";
    private final static String table = "einzelbuero";
    private final static String sequence = "einzelbuero_id_seq";

    public EinzelbueroRepository() {
        super(Einzelbuero.class);
    }

    public EinzelbueroRepository(String user, String password,  Class<Einzelbuero> entityClass) { super(user, password, entityClass);
    }

    public Einzelbuero create(int id, String ort, String strasse, int plz, Autor autor) {
        Einzelbuero einzelbuero = new Einzelbuero(id, ort, strasse, plz);
        entityManager.persist(einzelbuero);
        return einzelbuero;
    }

    public void reset() {
        Persistence.resetTable(schema, table);
        Persistence.resetSequence(schema, sequence);
    }
}
