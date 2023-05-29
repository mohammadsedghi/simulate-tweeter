package base.service.Impl;

import base.entity.BaseEntity;
import base.service.BaseService;

import java.io.Serializable;

public class BaseServiceImpl<E extends BaseEntity<ID>,ID extends Serializable>
        implements BaseService<E,ID> {
}
