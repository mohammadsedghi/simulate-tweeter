package repository.Impl;

import base.repository.impl.BaseRepositoryImpl;
import entity.Person;
import repository.PersonRepository;

public class PersonRepositoryImpl extends BaseRepositoryImpl<Person<Long>, Long>
        implements PersonRepository {

    @Override
    public String getQuery() {
        return "select * from person" ;
    }
}
