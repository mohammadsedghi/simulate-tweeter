package repository.Impl;

import base.repository.impl.BaseRepositoryImpl;
import entity.Comment;
import org.hibernate.Session;
import repository.CommentRepository;

public class CommentRepositoryImpl extends BaseRepositoryImpl<Comment,Long>
        implements CommentRepository {
    public CommentRepositoryImpl(Session session) {
        super(session);
    }

    @Override
    public Class<Comment> getEnitytyClass() {
        return Comment.class;
    }


}
