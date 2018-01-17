package at.fhj.swd.main;

import at.fhj.swd.persistence.IRepository;
import at.fhj.swd.persistence.Persistence;
import at.fhj.swd.persistence.Repository;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AutorRepository extends Repository<Autor> implements IRepository<Autor> {

    @Id
    private int id;

    private final static String schema = "public";
    private final static String table = "autor";
    private final static String sequence = "autor_id_seq";

    public AutorRepository() {
        super(Autor.class);
    }

    public AutorRepository(String user, String password, Class<Autor> entityClass) {
        super(user, password, entityClass);
    }

    public void reset() {
        Persistence.resetTable(schema, table);
        Persistence.resetSequence(schema, sequence);
    }

    public Autor create(int id, String vorname, String nachname, String geb_datum) {
        Autor autor1 = new Autor(id, vorname, nachname, geb_datum);
        entityManager.persist(autor1);
        return autor1;
    }
}