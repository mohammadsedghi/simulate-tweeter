package service.Impl;

import base.repository.util.HibernateUtil;
import base.service.Impl.BaseServiceImpl;
import entity.Person;
import entity.Tweet;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.hibernate.Session;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import repository.Impl.TweetRepositoryImpl;
import service.TweetService;

import java.util.Set;

import static org.hibernate.query.sqm.tree.SqmNode.log;

public class TweetServiceImpl extends BaseServiceImpl<Tweet, Long> implements TweetService {
    Session session = HibernateUtil.getSessionFactory().openSession();
    TweetRepositoryImpl tweetRepository = new TweetRepositoryImpl(session);

    @Override
    public void validate(Tweet entity) {
        ValidatorFactory factory = Validation.byDefaultProvider()
                .configure()
                .messageInterpolator(new ParameterMessageInterpolator())
                .buildValidatorFactory();

        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Tweet>> violations = validator.validate(entity);
        if (violations.size() > 0) {
            for (ConstraintViolation<Tweet> violation : violations) {
                log.error(violation.getMessage());
            }
            factory.close();
        } else
            signUp(entity);
    }


    @Override
    public void signUp(Tweet tweet) {
        tweetRepository.save(tweet);
    }
}
