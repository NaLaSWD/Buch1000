package at.fhj.swd.main;

import at.fhj.swd.persistence.IRepository;
import at.fhj.swd.persistence.Persistence;
import at.fhj.swd.persistence.Repository;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
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

    public Genre create(int id, String genre) {
        Genre thriller = new Genre(id, genre);
        entityManager.persist(thriller);
        return thriller;
    }
}
