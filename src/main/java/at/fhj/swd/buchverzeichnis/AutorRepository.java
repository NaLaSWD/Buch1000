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

    public List<Autor> findAutorByVerlag(String verlagname){
        TypedQuery<Autor> query = entityManager.createNamedQuery("Autor.findAutorByVerlag", Autor.class);
        query.setParameter("Verlag", verlagname);
        return query.getResultList();
    }

    public String findEinzelbueroByVerlag(String nachname){
        TypedQuery<String> query = entityManager.createNamedQuery("Autor.findVerlagByAutor", String.class);
        query.setParameter("Nachname", nachname);
        return query.getSingleResult();
    }

    public String findEinzelbueroByAutor(String nachname){
        TypedQuery<String> query = entityManager.createNamedQuery("Autor.findEinzelbueroByAutor", String.class);
        query.setParameter("Nachname", nachname);
        return query.getSingleResult();
    }

    public List<Buch> findBuecherByAutor(String nachname){
        TypedQuery<Buch> query = entityManager.createNamedQuery("Autor.findBuecherByAutor", Buch.class);
        query.setParameter("Nachname", nachname);
        return query.getResultList();
    }
}