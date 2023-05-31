package service.Impl;

import base.repository.BaseRepository;
import base.service.Impl.BaseServiceImpl;
import entity.Comment;
import org.hibernate.Session;
import repository.CommentRepository;
import service.CommentService;

public class CommentServiceImpl extends BaseServiceImpl<Comment,CommentRepository,Long >
        implements CommentService {


    public CommentServiceImpl(CommentRepository repository) {
        super(repository);
    }
}
