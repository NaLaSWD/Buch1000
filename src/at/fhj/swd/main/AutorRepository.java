package at.fhj.swd.main;

import at.fhj.swd.persistence.IRepository;
import at.fhj.swd.persistence.Persistence;
import at.fhj.swd.persistence.Repository;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name="AutorRepository.findFirstnameById", query="SELECT a.vorname FROM Autor a WHERE a.id = :id"),
        @NamedQuery(name="AutorRepository.findByVerlag", query="SELECT a FROM Autor a JOIN  Verlag v WHERE v.name = :name")
})

public class AutorRepository extends Repository<Autor> implements IRepository<Autor> {

    @Id
    private int id;

    private final static String schema = "public";
    private final static String table = "autor";
    private final static String sequence = "autor_id_seq";

    public AutorRepository() {
        super(Autor.class);
    }

    public AutorRepository(String user, String password, Class<Autor> entityClass) {
        super(user, password, entityClass);
    }

    public void reset() {
        Persistence.resetTable(schema, table);
        Persistence.resetSequence(schema, sequence);
    }

    public Autor create(int id, String vorname, String nachname, String geb_datum, List<Buch> buch, List<Verlag> verlag, Einzelbuero einzelbuero) {
        Autor autor1 = new Autor(id, vorname, nachname, geb_datum, buch, verlag, einzelbuero);
        entityManager.persist(autor1);
        return autor1;
    }

    public String findFirstnameById(int id) {
        TypedQuery<String> query = entityManager.createNamedQuery("AutorRepository.findFirstnameById", String.class);
        query.setParameter ("id", id);
        return query.getSingleResult();
    }
}