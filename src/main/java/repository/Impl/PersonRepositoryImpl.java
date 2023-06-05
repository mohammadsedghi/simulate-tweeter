package repository.Impl;

import base.repository.impl.BaseRepositoryImpl;
import entity.Person;
import entity.Tweet;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import repository.PersonRepository;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Person> findByUserName(String username) {
        //session.getTransaction().begin();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Person> cr = cb.createQuery(Person.class);
        Root<Person> root = cr.from(Person.class);
        cr.select(root);
        cr.where(cb.equal(root.get("username"),username));
        TypedQuery<Person> query = session.createQuery(cr);
        Person results = query.getSingleResult();
        return Optional.ofNullable(results);
    }

    @Override
    public Optional<Person> findByPassword(String password) {
        //session.getTransaction().begin();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Person> cr = cb.createQuery(Person.class);
        Root<Person> root = cr.from(Person.class);
        cr.select(root);

        cr.where(cb.equal(root.get("password"),password));
        TypedQuery<Person> query = session.createQuery(cr);
        Person results = query.getSingleResult();
        return Optional.ofNullable(results);
//        Query query=session.createQuery("from User where username=:username and password=:password");
//        query.setParameter("username", "test");
//        query.setParameter("password", "test1234");
//        User user=(User)query.uniqueResult();
    }
}
