package at.fhj.swd.buchverzeichnis.main;

import at.fhj.swd.buchverzeichnis.persistence.IRepository;
import at.fhj.swd.buchverzeichnis.persistence.Persistence;
import at.fhj.swd.buchverzeichnis.persistence.Repository;

import javax.persistence.*;
import java.util.List;

public class VerlagRepository extends Repository<Verlag> implements IRepository<Verlag> {

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

    public Verlag create(int id, String name, String ort, String strasse, int plz, List<Autor> autor) {
        Verlag verlag = new Verlag(id, name, ort, strasse, plz);
        entityManager.persist(verlag);
        return verlag;
    }

    public String findOrtById(int id) {
        TypedQuery<String> query = entityManager.createNamedQuery("VerlagRepository.findOrtById", String.class);
        query.setParameter ("id", id);
        return query.getSingleResult();
    }
}

