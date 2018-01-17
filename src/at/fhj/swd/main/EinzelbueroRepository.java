package at.fhj.swd.main;

import at.fhj.swd.persistence.IRepository;
import at.fhj.swd.persistence.Persistence;
import at.fhj.swd.persistence.Repository;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EinzelbueroRepository extends Repository<Einzelbuero> implements IRepository<Einzelbuero> {

    @Id
    private int id;
    private final static String schema = "public";
    private final static String table = "einzelbuero";
    private final static String sequence = "einzelbuero_id_seq";

    public EinzelbueroRepository() {
        super(Einzelbuero.class);
    }

    public EinzelbueroRepository(String user, String password, Class<Einzelbuero> entityClass) {
        super(user, password, entityClass);
    }

    public void reset() {
        Persistence.resetTable(schema, table);
        Persistence.resetSequence(schema, sequence);
    }

    public Einzelbuero create(int id, String ort, String strasse, int plz) {
        Einzelbuero einzelbuero1 = new Einzelbuero(id, ort, strasse, plz);
        entityManager.persist(einzelbuero1);
        return einzelbuero1;
    }
}
