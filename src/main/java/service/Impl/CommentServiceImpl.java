package service.Impl;

import base.service.Impl.BaseServiceImpl;
import entity.Comment;
import org.hibernate.Session;
import service.CommentService;

public class CommentServiceImpl extends BaseServiceImpl<Comment,Long>
        implements CommentService {


    @Override
    public void validate(Comment entity) {

    }
}
