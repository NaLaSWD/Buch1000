package at.fhj.swd.buchverzeichnis;

import at.fhj.swd.persistence.IRepository;
import at.fhj.swd.persistence.Persistence;
import at.fhj.swd.persistence.Repository;

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

    public Genre create(int id, String genre) {
        Genre thriller = new Genre(id, genre);
        entityManager.persist(thriller);
        return thriller;
    }

    public void reset() {
        Persistence.resetTable(schema, table);
        Persistence.resetSequence(schema, sequence);
    }

}
