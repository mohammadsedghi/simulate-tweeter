package repository.Impl;

import base.repository.impl.BaseRepositoryImpl;
import entity.Like;
import entity.Tweet;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import repository.LikeRepository;

import java.util.List;

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
