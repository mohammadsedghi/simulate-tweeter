package repository.Impl;

import base.repository.impl.BaseRepositoryImpl;
import entity.Like;
import repository.LikeRepository;

public class LikeRepositoryImpl extends BaseRepositoryImpl<Like<Long>,Long>
        implements LikeRepository {
    @Override
    public String getQuery() {
        return "select * from like";
    }
}
