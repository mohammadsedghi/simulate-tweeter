package service.Impl;

import base.repository.BaseRepository;
import base.service.Impl.BaseServiceImpl;
import entity.Like;
import org.hibernate.Session;
import repository.LikeRepository;
import service.LikeService;

public class LikeServiceImpl extends BaseServiceImpl<Like, LikeRepository,Long>implements LikeService {


    public LikeServiceImpl(LikeRepository repository) {
        super(repository);
    }
}
