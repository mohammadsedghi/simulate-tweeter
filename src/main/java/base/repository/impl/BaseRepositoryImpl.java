package base.repository.impl;

import base.entity.BaseEntity;
import base.repository.BaseRepository;

import java.io.Serializable;
import java.util.List;

public class BaseRepositoryImpl <E extends BaseEntity<ID>,ID extends Serializable>
        implements BaseRepository<E,ID> {
    @Override
    public E save(E entity) {
        return null;
    }

    @Override
    public E update(E entity) {
        return null;
    }

    @Override
    public void remove(E entity) {

    }

    @Override
    public List<E> load() {
        return null;
    }
}
