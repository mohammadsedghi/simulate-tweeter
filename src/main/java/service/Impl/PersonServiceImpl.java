package service.Impl;

import base.repository.util.HibernateUtil;
import base.service.Impl.BaseServiceImpl;
import entity.Person;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.hibernate.Session;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import repository.Impl.PersonRepositoryImpl;
import service.PersonService;

import java.util.Set;

import static org.hibernate.query.sqm.tree.SqmNode.log;

public class PersonServiceImpl extends BaseServiceImpl<Person, Long> implements PersonService {
    Session session = HibernateUtil.getSessionFactory().openSession();
    PersonRepositoryImpl personRepository = new PersonRepositoryImpl(session);

    @Override
    public void validate(Person entity) {
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
        } else
            signUp(entity);

    }

    @Override
    public void signUp(Person person) {
        personRepository.save(person);
    }


}
