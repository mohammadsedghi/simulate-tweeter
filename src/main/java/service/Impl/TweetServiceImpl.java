package service.Impl;


import base.service.Impl.BaseServiceImpl;
import entity.Tweet;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import repository.TweetRepository;
import service.TweetService;

import java.util.Set;

import static org.hibernate.query.sqm.tree.SqmNode.log;

public class TweetServiceImpl extends BaseServiceImpl<Tweet, TweetRepository, Long> implements TweetService {
   private TweetRepository tweetRepository ;

    public TweetServiceImpl(TweetRepository repository) {
        super(repository);
        this.tweetRepository=repository;
    }


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
            tweetRepository.save(entity);
    }

}
