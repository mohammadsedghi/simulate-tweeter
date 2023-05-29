package repository.Impl;

import base.repository.impl.BaseRepositoryImpl;
import entity.Tweet;
import org.hibernate.Session;
import repository.TweetRepository;

public class TweetRepositoryImpl extends BaseRepositoryImpl<Tweet,Long>
        implements TweetRepository {

    public TweetRepositoryImpl(Session session) {
        super(session);
    }

    @Override
    public Class<Tweet> getEnitytyClass() {
        return Tweet.class;
    }


}
