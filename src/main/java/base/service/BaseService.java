package base.service;

import base.entity.BaseEntity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface BaseService <E extends BaseEntity<ID>,ID extends Serializable>{

    E save(E entity);
    E update(E entity);
    void remove(E entity);
   Collection<E> loadAll();
    Optional<E> findById(ID id);
}
