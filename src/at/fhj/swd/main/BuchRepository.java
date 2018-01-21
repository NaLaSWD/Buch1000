package at.fhj.swd.main;

import at.fhj.swd.persistence.IRepository;
import at.fhj.swd.persistence.Persistence;
import at.fhj.swd.persistence.Repository;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name="BuchRepository.findTitleByTitel", query="SELECT b.titel FROM Buch b WHERE b.id = :titel"),
        @NamedQuery(name="BuchRepository.findByGenre", query="SELECT b FROM Buch b WHERE b.genre = :genre"),
        @NamedQuery(name="BuchRepository.findByAutor", query="SELECT b FROM Buch b JOIN Autor a WHERE a.id = :id"),
})

public class BuchRepository extends Repository<Buch> implements IRepository<Buch> {

    @Id
    private int id;

    private final static String schema = "public";
    private final static String table = "buch";
    private final static String sequence = "buch_id_seq";

    public BuchRepository() {
        super(Buch.class);
    }

    public BuchRepository(String user, String password, Class<Buch> entityClass) {
        super(user, password, entityClass);
    }

    public void reset() {
        Persistence.resetTable(schema, table);
        Persistence.resetSequence(schema, sequence);
    }

    public Buch create(int id, int isbn, int jahr, String titel, Genre genre, List<Autor> autor) {
        Buch buch1 = new Buch(id, isbn, jahr, titel, genre, autor);
        entityManager.persist(buch1);
        return buch1;
    }

    public String findTitleByTitel(int id) {
        TypedQuery<String> query = entityManager.createNamedQuery("BuchRepository.findTitleByTitel", String.class);
        query.setParameter ("titel", id);
        return query.getSingleResult();
    }
}
