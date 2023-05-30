package service.Impl;

import base.service.Impl.BaseServiceImpl;
import entity.Like;
import org.hibernate.Session;
import service.LikeService;

public class LikeServiceImpl extends BaseServiceImpl<Like,Long>implements LikeService {

    @Override
    public void validate(Like entity) {

    }
}
