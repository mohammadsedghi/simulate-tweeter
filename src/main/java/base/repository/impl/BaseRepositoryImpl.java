package base.repository.impl;

import base.entity.BaseEntity;
import base.repository.BaseRepository;
import base.repository.util.HibernateUtil;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

public abstract class BaseRepositoryImpl <E extends BaseEntity<ID>,ID extends Serializable>
        implements BaseRepository<E,ID> {
    private Session session= HibernateUtil.getSessionFactory().openSession();
    @Override
    public E save(E entity) {
        session.getTransaction().begin();
        session.persist(entity);
        session.getTransaction().commit();
        return entity;
    }

    @Override
    public E update(E entity) {
        session.getTransaction().begin();
        session.merge(entity);
        session.getTransaction().commit();
        return entity;
    }

    @Override
    public void remove(E entity) {
        session.getTransaction().begin();
        session.remove(entity);
        session.getTransaction().commit();

    }

    @Override
    public List<E> load() {

       return session.createQuery("from "+getEntityClass().getSimpleName(),getEntityClass()).getResultList();
    }
    public abstract Class<E> getEntityClass();
}
