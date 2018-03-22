package at.fhj.swd.persistence;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public abstract class Repository<T> {

    protected EntityManager entityManager;

    protected Class<T> entityClass = null;

    public Repository(Class<T> entityClass) {
        this.entityClass = entityClass;
        entityManager = Persistence.connect();
    }

    public Repository(String user, String password, Class<T> entityClass) {
        this.entityClass = entityClass;
        entityManager = Persistence.connect(user, password);
    }

    public T find(int id) {
        return entityManager.find(entityClass, id);
    }

    public List<T> findAll() {
        TypedQuery<T> query = entityManager.createQuery(
                "SELECT entity FROM "
                        + entityClass.getTypeName() + " entity"
                        + " ORDER BY entity.id"
                , entityClass);
        return query.getResultList();
    }

    public void remove(Object obj) {
        entityManager.remove(obj);
    }
}
