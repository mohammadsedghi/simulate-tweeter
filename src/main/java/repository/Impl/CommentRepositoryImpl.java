package repository.Impl;

import base.repository.impl.BaseRepositoryImpl;
import entity.Comment;
import org.hibernate.Session;
import repository.CommentRepository;

public class CommentRepositoryImpl extends BaseRepositoryImpl<Comment,Long>
        implements CommentRepository {
    private final Session session;
    public CommentRepositoryImpl(Session session) {

        super(session);
        this.session=session;
    }

    @Override
    public Class<Comment> getEnitytyClass() {
        return Comment.class;
    }


    @Override
    public Session getSession() {
        return session;
    }
}
