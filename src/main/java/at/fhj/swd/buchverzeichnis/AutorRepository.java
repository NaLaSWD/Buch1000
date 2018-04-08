package at.fhj.swd.buchverzeichnis;

import at.fhj.swd.persistence.IRepository;
import at.fhj.swd.persistence.Persistence;
import at.fhj.swd.persistence.Repository;

import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

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

    public Autor create(int id, String vorname, String nachname, Date geb_datum, Verlag verlag, Einzelbuero einzelbuero) {
        Autor autor = new Autor(id, vorname, nachname, geb_datum, verlag, einzelbuero);
        entityManager.persist(autor);
        return autor;
    }

    public void reset() {
        Persistence.resetTable(schema, table);
        Persistence.resetSequence(schema, sequence);
    }

    public Autor findAutorByVerlag(String name){
        TypedQuery<Autor> query = entityManager.createNamedQuery("Autor.findAutorByVerlag", Autor.class);
        query.setParameter("Verlag", name);
        return query.getSingleResult();
    }

    public String findEinzelbueroByVerlag(String name){
        TypedQuery<String> query = entityManager.createNamedQuery("Autor.findEinzelbueroByVerlag", String.class);
        query.setParameter("Verlag", name);
        return query.getSingleResult();
    }


}