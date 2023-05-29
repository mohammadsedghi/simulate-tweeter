package repository.Impl;

import base.repository.impl.BaseRepositoryImpl;
import entity.Tweet;
import repository.TweetRepository;

public class TweetRepositoryImpl extends BaseRepositoryImpl<Tweet<Long>,Long>
        implements TweetRepository {

    @Override
    public String getQuery() {
        return "select * from tweet";
    }
}
