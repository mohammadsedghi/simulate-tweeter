package repository.Impl;

import base.repository.impl.BaseRepositoryImpl;
import entity.Comment;
import repository.CommentRepository;

public class CommentRepositoryImpl extends BaseRepositoryImpl<Comment<Long>,Long>
        implements CommentRepository {
    @Override
    public String getQuery() {
        return "select * from comment";
    }
}
