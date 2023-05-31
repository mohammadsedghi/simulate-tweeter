package repository.Impl;

import base.repository.impl.BaseRepositoryImpl;
import entity.Person;
import org.hibernate.Session;
import repository.PersonRepository;

public class PersonRepositoryImpl extends BaseRepositoryImpl<Person, Long>
        implements PersonRepository {
    private final Session session;
    public PersonRepositoryImpl(Session session) {

        super(session);
        this.session=session;
    }

    @Override
    public Class<Person> getEnitytyClass() {
        return Person.class;
    }


    @Override
    public Session getSession() {
        return session;
    }
}
