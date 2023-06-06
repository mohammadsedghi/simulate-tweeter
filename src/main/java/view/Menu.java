package view;

import base.repository.util.HibernateUtil;
import entity.Comment;
import entity.Like;
import entity.Person;
import entity.Tweet;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import repository.Impl.CommentRepositoryImpl;
import repository.Impl.LikeRepositoryImpl;
import repository.Impl.PersonRepositoryImpl;
import repository.Impl.TweetRepositoryImpl;
import service.CommentService;
import service.Impl.CommentServiceImpl;
import service.Impl.LikeServiceImpl;
import service.Impl.PersonServiceImpl;
import service.Impl.TweetServiceImpl;
import service.LikeService;
import service.PersonService;
import service.TweetService;

import java.util.*;

public class Menu {
    Session session = HibernateUtil.getSessionFactory().openSession();
    PersonService personService = new PersonServiceImpl(new PersonRepositoryImpl(session));
    TweetService tweetService = new TweetServiceImpl(new TweetRepositoryImpl(session));
    LikeService likeService = new LikeServiceImpl(new LikeRepositoryImpl(session));
    CommentService commentService = new CommentServiceImpl(new CommentRepositoryImpl(session));
    Set<Tweet> tweetList = new HashSet<>();
    Set<Like> likeList = new HashSet<>();
    Set<Comment> commentList = new HashSet<>();
    Scanner scanner = new Scanner(System.in);
    public static Person user = new Person();

    public static Person getUser() {
        return user;
    }

    public void showMenuEntrance() {
        System.out.println("----------------Twitter--------------");
        System.out.println("-----(1)signUp-----------(2)logIn-----------(3)exit------");
        switch (scanner.nextInt()) {
            case 1:
                signUp();
                logIn();
                break;
            case 2:
                logIn();
                break;
            case 3:
                System.exit(0);
        }
    }

    public void signUp() {
        System.out.println("name:----------------------");
        String name = scanner.next();
        System.out.println("family:--------------------");
        String family = scanner.next();
        System.out.println("birthDate:-----------------");
        String birthdate = scanner.next();
        System.out.println("userName:------------------");
        String username = scanner.next();
        System.out.println("password:------------------");
        String password = scanner.next();
        System.out.println("age:-----------------------");
        int age = scanner.nextInt();
        Tweet tweet = new Tweet();
        tweetList.add(tweet);
        Person person = new Person(name, family, birthdate, username, password, age, tweetList);

        if (!personService.validate(person)) {
            showMenuEntrance();
        } else {
            user = person;
        }

    }

    public void logIn() {
        System.out.println("logIn:------------------------");
        System.out.println("username:---------------------");
        String userName = scanner.next();
        System.out.println("password:---------------------");
        String password = scanner.next();
        try {
            if (personService.findByUserName(userName).isPresent() &&
                    personService.findByPassword(password).isPresent()) {
                user = personService.findByPassword(password).get();
                showHome();
            }
        } catch (NoResultException e) {
            System.out.println("username and password is inCorrect");
            showMenuEntrance();
        }
    }

    public void editProfile() {
        System.out.println("EDIT");
        System.out.println("name:----------------------");
        String name = scanner.next();
        System.out.println("family:--------------------");
        String family = scanner.next();
        System.out.println("birthDate:-----------------");
        String birthdate = scanner.next();
        System.out.println("age:-----------------------");
        int age = scanner.nextInt();
        user.setName(name);
        user.setFamily(family);
        user.setBirthdate(birthdate);
        user.setAge(age);
        if (!personService.validate(user)) {
            showMenuEntrance();
        } else {
            personService.update(user);
            System.out.println("updated.......");

        }
    }

    public void showProfile() {
        System.out.println(user);
        System.out.println("(1)Edit profile----(2)remove account---(3)log out------");
        switch (scanner.nextInt()) {
            case 1:
                editProfile();
                break;
            case 2:
                personService.remove(user);
                System.out.println("this account is removed....");
                user = null;
                showMenuEntrance();
                break;
            case 3:
                user = null;
                showMenuEntrance();
                break;
        }
    }
    public String checkTweetLength(){
        return "a".repeat(270);
    }

    public void postTweet() {
        System.out.println("please write your message");
        String message = scanner.next();
        Set<Tweet> tweets = new HashSet<>();
        Tweet tweet = new Tweet(message, user);
        tweets.add(tweet);
        user.setTweetList(tweets);
        tweetService.save(tweet);
        personService.update(user);
        showHome();

    }

    public Comment postComment(Tweet tweet) {
        System.out.println("please write your comment");
        String message = scanner.next();
        Comment comment = new Comment(message, tweet);
        commentService.save(comment);
        return comment;
    }
    public String editTweets(){
        System.out.println("please type your message");
      return scanner.next();

    }

    public void showHome() {
        System.out.println("=========Home=========");
        Set<Tweet> tweets = new HashSet<>(tweetService.loadAll());
        // tweets = tweetService.loadAll();

        if (tweets.size() == 0) {
            System.out.println("no body write tweets until now, do you want write tweet Y or N ?");
            if (scanner.next().equals("y")) postTweet();
            else if (scanner.next().equals("n")) System.exit(0);
        } else {
            for (Tweet tweet : tweets) {
                System.out.println(tweet.getMessage() + "  ===>  " + tweet.getPerson());
                System.out.println("like number is: " + new HashSet<>(tweet.getLikeList()).size());
                System.out.println("================================================================================");
                System.out.println("(1)like----(2)unlike----(3)ShowComment----(4)Like comment---(5)Unlike comment (6)writeComment---(7)Edit comment---(8)remove comment");
                System.out.println("----------------------------------------------------------------------------------");
                System.out.println("(9)write tweet---(10)Edit tweet--- (11)remove tweet---(12)search user---(13)show profile---(14)testTweetLength");
                System.out.println("================================================================================");
                switch (scanner.nextInt()) {
                    case 1:
                        Set<Like> temporaryListLike = new HashSet<>(tweet.getLikeList());
                        Like like = new Like(user.getUsername(), tweet);
                        temporaryListLike.add(like);
                        likeService.save(like);
                        tweet.setLikeList(temporaryListLike);
                        tweetService.update(tweet);
                        System.out.println("like number is: " + temporaryListLike.size());
                        break;
                    case 2:
                        Set<Like> temporaryListUnLike = new HashSet<>(tweet.getLikeList());
                        for (Like unlike : temporaryListUnLike
                        ) {
                            if (unlike.getLikes().equals(user.getUsername())) {
                                temporaryListUnLike.remove(unlike);
                                likeService.remove(unlike);
                                break;
                            } else System.out.println("user not any liked in past");
                        }
                        tweet.setLikeList(temporaryListUnLike);
                        tweetService.update(tweet);
                        System.out.println(temporaryListUnLike.size());
                        break;

                    case 3:
                        System.out.println("comment: ===>");
                        if (tweet.getCommentList().size() == 0) System.out.println("no comments found for this tweet");
                        else tweet.getCommentList().forEach(System.out::println);
                        break;

                    case 4:

                        break;
                    case 5:
                        break;
                    case 6:
                        Set<Comment> comments = new HashSet<>(tweet.getCommentList());
                        comments.add(postComment(tweet));
                        tweet.setCommentList(comments);
                        tweetService.update(tweet);
                        break;
                    case 7:
                        break;
                    case 8:
                        Set<Comment> commentSet = new HashSet<>(tweet.getCommentList());
                        for (Comment comment : commentSet
                        ) {
                            if (comment.getTweet().getPerson().getUsername().equals(user.getUsername())) {
                                System.out.println(comment);
                                System.out.println("do you want remove it? yes->y or no->n :");
                                if (scanner.next() .equals("y")) {
                                    commentService.remove(comment);
                                    System.out.println("removed.....");

                                }
                            }
                        }

                        break;
                    case 9:
                        postTweet();
                        break;
                    case 10:
                        if (tweet.getPerson().getUsername().equals(user.getUsername())) {
                           tweet.setMessage(editTweets());
                            tweetService.update(tweet);
                            System.out.println("tweet is Edited.....");
                        }
                        break;
                    case 11:
                        if (tweet.getPerson().getUsername().equals(user.getUsername())) {
                            tweetService.remove(tweet);
                            System.out.println("tweet is removed.....");
                        }
                        break;
                    case 12:
                        System.out.println("please type username");
                        try {
                            personService.findByUserName(scanner.next()).ifPresent(System.out::println);
                        } catch (NoResultException e) {
                            System.out.println("person with this username not found");
                        }
                        break;
                    case 13:
                        showProfile();
                        break;
                    case 14:
                        String message = checkTweetLength();
                        Set<Tweet> tweetSet = new HashSet<>();
                        Tweet validTweet = new Tweet(message, user);
                        tweetSet.add(validTweet);
                        user.setTweetList(tweetSet);
                        tweetService.validate(validTweet);
                        personService.update(user);
                }

            }
        }
    }
}


