package repository.Impl;

import base.repository.impl.BaseRepositoryImpl;
import entity.Person;
import org.hibernate.Session;
import repository.PersonRepository;

public class PersonRepositoryImpl extends BaseRepositoryImpl<Person, Long>
        implements PersonRepository {

    public PersonRepositoryImpl(Session session) {
        super(session);
    }

    @Override
    public Class<Person> getEnitytyClass() {
        return Person.class;
    }


}
