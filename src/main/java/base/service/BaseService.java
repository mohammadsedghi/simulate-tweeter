package base.service;

import base.entity.BaseEntity;
import base.repository.BaseRepository;

import java.io.Serializable;
import java.util.List;

public interface BaseService <E extends BaseEntity<ID>,ID extends Serializable>{

    E save(E entity);
    E update(E entity);
    void remove(E entity);
    List<E> load();

}
