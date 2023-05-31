package base.repository.impl;

import base.entity.BaseEntity;
import base.repository.BaseRepository;
import base.repository.util.HibernateUtil;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

public abstract class BaseRepositoryImpl <E extends BaseEntity<ID>,ID extends Serializable>
        implements BaseRepository<E,ID> {
    private final Session session;

    public BaseRepositoryImpl(Session session) {
        this.session = session;
    }

    @Override
    public E save(E entity) {
        session.persist(entity);
        return entity;
    }

    @Override
    public E update(E entity) {
        session.merge(entity);
        return entity;
    }

    @Override
    public void remove(E entity) {
        session.remove(entity);

    }

    @Override
    public List<E> load() {

       return session.createQuery("from"+getEnitytyClass().getSimpleName(),getEnitytyClass()).getResultList();
    }
    public abstract Class<E> getEnitytyClass();
}
