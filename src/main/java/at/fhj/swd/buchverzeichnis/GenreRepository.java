package at.fhj.swd.buchverzeichnis;

import at.fhj.swd.persistence.IRepository;
import at.fhj.swd.persistence.Persistence;
import at.fhj.swd.persistence.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

public class GenreRepository extends Repository<Genre> implements IRepository<Genre> {

    private final static String schema = "public";
    private final static String table = "genre";
    private final static String sequence = "genre_id_seq";

    public GenreRepository() {
        super(Genre.class);
    }

    public GenreRepository(String user, String password) {
        super(user, password, Genre.class);
    }

    public Genre create(int id, String bezeichnung) {
        Genre thriller = new Genre(id, bezeichnung);
        entityManager.persist(thriller);
        return thriller;
    }

    public void reset() {
        Persistence.resetTable(schema, table);
        Persistence.resetSequence(schema, sequence);
    }

    public String findGenreBezeichnung(String titel){
        TypedQuery<String> query = entityManager.createNamedQuery("Genre.findGenreBezeichnungByBuchTitel", String.class);
        query.setParameter("Buchtitel", titel);
        return query.getSingleResult();
    }

    public List<Buch> findBuecherByGenre(String bezeichnung){
        TypedQuery<Buch> query = entityManager.createNamedQuery("Genre.findBuchTitelByGenre", Buch.class);
        query.setParameter("Genre", bezeichnung);
        return query.getResultList();
    }

}
