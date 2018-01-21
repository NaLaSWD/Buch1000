package at.fhj.swd.main;

import at.fhj.swd.persistence.IRepository;
import at.fhj.swd.persistence.Persistence;
import at.fhj.swd.persistence.Repository;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name="GenreRepository.findGenreById", query="SELECT g.genre FROM Genre g WHERE g.id = :id"),
        @NamedQuery(name="GenreRepository.findByBuch", query="SELECT g FROM Genre g JOIN  Buch b WHERE b.titel = :titel"),
})

public class GenreRepository extends Repository<Genre> implements IRepository<Genre> {

    @Id
    private int id;
    private final static String schema = "public";
    private final static String table = "genre";
    private final static String sequence = "genre_id_seq";

    public GenreRepository() {
        super(Genre.class);
    }

    public GenreRepository(String user, String password, Class<Genre> entityClass) {
        super(user, password, entityClass);
    }

    public void reset() {
        Persistence.resetTable(schema, table);
        Persistence.resetSequence(schema, sequence);
    }

    public Genre create(int id, String genre, List<Buch> buch) {
        Genre thriller = new Genre(id, genre, buch);
        entityManager.persist(thriller);
        return thriller;
    }
    public String findGenreById(int id) {
        TypedQuery<String> query = entityManager.createNamedQuery("GenreRepository.findGenreById", String.class);
        query.setParameter ("id", id);
        return query.getSingleResult();
    }
}
