package service.Impl;


import base.service.Impl.BaseServiceImpl;
import entity.Person;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import repository.PersonRepository;
import service.PersonService;

import java.util.Optional;
import java.util.Set;

import static org.hibernate.query.sqm.tree.SqmNode.log;

public class PersonServiceImpl extends BaseServiceImpl<Person, PersonRepository, Long> implements PersonService {

    PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository repository) {
        super(repository);
        this.personRepository=repository;
    }


    @Override
    public boolean validate(Person entity) {
        ValidatorFactory factory = Validation.byDefaultProvider()
                .configure()
                .messageInterpolator(new ParameterMessageInterpolator())
                .buildValidatorFactory();

        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Person>> violations = validator.validate(entity);
        if (violations.size() > 0) {
            for (ConstraintViolation<Person> violation : violations) {
                log.error(violation.getMessage());
            }
            factory.close();
            return false;
        } else
        {
            signUp(entity);
        return true;
        }

    }

    @Override
    public   Optional<Person> findByUserName(String username) {
        return personRepository.findByUserName(username);
    }

    @Override
    public Optional<Person> findByPassword(String password) {
        return personRepository.findByPassword(password);
    }

    @Override
    public void signUp(Person person) {
        personRepository.getSession().getTransaction().begin();
        personRepository.save(person);
        personRepository.getSession().getTransaction().commit();
    }


}
