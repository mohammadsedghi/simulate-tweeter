package base.service.Impl;

import base.entity.BaseEntity;
import base.service.BaseService;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

public class BaseServiceImpl<E extends BaseEntity<ID>,ID extends Serializable>
        implements BaseService<E,ID> {

Session session;

    public BaseServiceImpl(Session session) {
        this.session = session;
    }


}
