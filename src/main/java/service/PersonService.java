package service;

import base.service.BaseService;
import entity.Person;

public interface PersonService extends BaseService<Person,Long> {
    void signUp(Person person);
}