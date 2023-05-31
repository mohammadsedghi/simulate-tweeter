package repository.Impl;

import base.repository.impl.BaseRepositoryImpl;
import entity.Tweet;
import org.hibernate.Session;
import repository.TweetRepository;

public class TweetRepositoryImpl extends BaseRepositoryImpl<Tweet,Long>
        implements TweetRepository {
private final Session session;
    public TweetRepositoryImpl(Session session) {

        super(session);
        this.session=session;
    }

    @Override
    public Class<Tweet> getEnitytyClass() {
        return Tweet.class;
    }


    @Override
    public Session getSession() {
        return session;
    }
}
