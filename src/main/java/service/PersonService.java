package service;

import base.service.BaseService;
import entity.Person;

import java.util.Optional;

public interface PersonService extends BaseService<Person,Long> {
    void signUp(Person person);
    boolean validate(Person person);
    Optional<Person> findByUserName(String username);
    Optional<Person> findByPassword(String password);

}