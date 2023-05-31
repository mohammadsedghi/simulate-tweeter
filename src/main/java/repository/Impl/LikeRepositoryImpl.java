package repository.Impl;

import base.repository.impl.BaseRepositoryImpl;
import entity.Like;
import org.hibernate.Session;
import repository.LikeRepository;

public class LikeRepositoryImpl extends BaseRepositoryImpl<Like,Long>
        implements LikeRepository {
    private final Session session;
    public LikeRepositoryImpl(Session session) {

        super(session);
        this.session=session;
    }

    @Override
    public Class<Like> getEnitytyClass() {
        return Like.class;
    }


    @Override
    public Session getSession() {
        return session;
    }
}
