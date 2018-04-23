package at.fhj.swd.buchverzeichnis;

import at.fhj.swd.persistence.IRepository;
import at.fhj.swd.persistence.Persistence;
import at.fhj.swd.persistence.Repository;

import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

public class BuchRepository extends Repository<Buch> implements IRepository<Buch> {

    private final static String schema = "public";
    private final static String table = "buch";
    private final static String sequence = "buch_id_seq";

    public BuchRepository() {
        super(Buch.class);
    }

    public BuchRepository(String user, String password) {
        super(user, password, Buch.class);
    }

    public Buch create(int id, int isbn, Date erscheinungsjahr, String titel, Genre genre) {
        Buch buch = new Buch(id, isbn, erscheinungsjahr, titel, genre);
        entityManager.persist(buch);
        return buch;
    }

    public void reset() {
        Persistence.resetTable(schema, table);
        Persistence.resetSequence(schema, sequence);
    }

    /*public String findBuchTitelByErscheinungsjahr(Date erscheinungsjahr){
        TypedQuery<String> query = entityManager.createNamedQuery("Buch.findBuchTitelByErscheinungsjahr", String.class);
        query.setParameter("Erscheinungsjahr", erscheinungsjahr);
        return query.getSingleResult();
    }*/

    public List<Buch> findBuchIdByGenre(String bezeichnung){
        TypedQuery<Buch> query = entityManager.createNamedQuery("Buch.findBuchIdByGenre", Buch.class);
        query.setParameter("GenreName", bezeichnung);
        return query.getResultList();
    }

}