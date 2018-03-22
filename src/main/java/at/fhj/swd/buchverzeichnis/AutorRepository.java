package at.fhj.swd.buchverzeichnis;

import at.fhj.swd.persistence.IRepository;
import at.fhj.swd.persistence.Persistence;
import at.fhj.swd.persistence.Repository;

import java.util.Date;

public class AutorRepository extends Repository<Autor> implements IRepository<Autor> {

    private final static String schema = "public";
    private final static String table = "autor";
    private final static String sequence = "autor_id_seq";

    public AutorRepository() {
        super(Autor.class);
    }

    public AutorRepository(String user, String password) {
        super(user, password, Autor.class);
    }

    public Autor create(int id, String vorname, String nachname, Date geb_datum) {
        Autor autor = new Autor(id, vorname, nachname, geb_datum);
        entityManager.persist(autor);
        return autor;
    }

    public void reset() {
        Persistence.resetTable(schema, table);
        Persistence.resetSequence(schema, sequence);
    }

}