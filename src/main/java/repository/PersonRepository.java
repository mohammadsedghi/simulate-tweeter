package repository;

import base.repository.BaseRepository;
import base.repository.impl.BaseRepositoryImpl;
import entity.Person;

import java.util.Optional;

public interface PersonRepository extends BaseRepository<Person,Long> {
    Optional<Person>  findByUserName(String username);
    Optional<Person> findByPassword(String password);
}
