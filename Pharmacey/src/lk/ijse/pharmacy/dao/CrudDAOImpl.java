package lk.ijse.pharmacy.dao;



import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;

public  abstract class CrudDAOImpl<T,ID> implements CrudDAO<T,ID> {

    protected EntityManager entityManager;
    private Class<T> entity;

    public CrudDAOImpl() {
        entity = (Class<T>) (((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    @Override
    public void save(T entity) throws Exception {
        entityManager.persist(entity);
    }

    @Override
    public void update(T entity) throws Exception {
        entityManager.persist(entity);
    }

    @Override
    public void delete(ID id) throws Exception {
        T t = entityManager.find(entity, id);
        entityManager.remove(t);
    }

    @Override
    public T find(ID id) throws Exception {
        return entityManager.find(entity, id);
    }

    @Override
    public ArrayList<T> getAll() throws Exception {
        return (ArrayList<T>) entityManager.createQuery("FROM "+ entity.getName()).getResultList();
    }


    @Override
    public void setEntityManager(EntityManager entityManager) {

        this.entityManager=entityManager;
    }
}
