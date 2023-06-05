package base.service.Impl;

import base.entity.BaseEntity;
import base.repository.BaseRepository;
import base.service.BaseService;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class BaseServiceImpl<E extends BaseEntity<ID>, REPOSITORY extends BaseRepository<E, ID>, ID extends Serializable>
        implements BaseService<E, ID> {
    protected REPOSITORY repository;

    public BaseServiceImpl(REPOSITORY repository) {
        this.repository = repository;
    }

    @Override
    public E save(E entity) {
        repository.getSession().getTransaction().begin();
        repository.save(entity);
        repository.getSession().getTransaction().commit();
        return entity;
    }

    @Override
    public E update(E entity) {
        repository.getSession().getTransaction().begin();
        repository.update(entity);
        repository.getSession().getTransaction().commit();
        return entity;
    }

    @Override
    public void remove(E entity) {
        repository.getSession().getTransaction().begin();
        repository.remove(entity);
        repository.getSession().getTransaction().commit();

    }

    @Override
    public Collection<E> loadAll() {
        return repository.load();
    }

    @Override
    public Optional<E> findById(ID id) {
        return repository.findById(id);
    }
}
