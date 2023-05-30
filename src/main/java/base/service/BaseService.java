package base.service;

import base.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;

public interface BaseService <E extends BaseEntity<ID>,ID extends Serializable>{
void validate(E entity);
}
