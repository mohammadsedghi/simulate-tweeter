package service;

import base.service.BaseService;
import entity.Person;
import entity.Tweet;

public interface TweetService extends BaseService<Tweet,Long> {
    void signUp(Tweet tweet);
    void validate(Tweet tweet );
}
