package at.fhj.swd.buchverzeichnis.main;

import at.fhj.swd.buchverzeichnis.persistence.IRepository;
import at.fhj.swd.buchverzeichnis.persistence.Repository;
import at.fhj.swd.buchverzeichnis.util.Persistence;

import java.util.Date;

public class AutorRepository extends Repository<Autor> implements IRepository<Autor> {

    private final static String schema = "public";
    private final static String table = "autor";
    private final static String sequence = "autor_id_seq";

    public AutorRepository() {
        super(Autor.class);
    }

    public AutorRepository(String user, String password, Class<Autor> entityClass) {
        super(user, password, entityClass);
    }

    public Autor create( int id, String vorname, String nachname, Date geb_datum) {
        Autor autor = new Autor(id, vorname, nachname, geb_datum);
        entityManager.persist(autor);
        return autor;
    }

    public void reset() {
        Persistence.resetTable(schema, table);
        Persistence.resetSequence(schema, sequence);
    }

}