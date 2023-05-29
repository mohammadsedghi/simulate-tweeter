package base.repository;

import base.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;

public interface BaseRepository<E extends BaseEntity<ID>,ID extends Serializable> {
    E save(E entity);
    E update(E entity);
    void remove(E entity);
    List<E> load();
}
