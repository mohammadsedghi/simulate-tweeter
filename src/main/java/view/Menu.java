package view;

import base.repository.util.HibernateUtil;
import entity.Comment;
import entity.Like;
import entity.Person;
import entity.Tweet;
import jakarta.persistence.NoResultException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
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

/**
 * in class design for kind of menu and written for request to  person and set value of object then use
 * other classes in another packages to save,edit,load,loadAll and remove something from database.
 * after main class this class is very important and have related between main and services .
 * this project using:
 * develop Enterprise Edition java language ----jdk 16.0.2..VM....
 * hibernate to connected database,
 * mockito and unit test for sign up and validate,
 * validation for evaluate properties,
 * maven for using  dependency
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Menu {
    final Session session = HibernateUtil.getSessionFactory().openSession();
    final PersonService personService = new PersonServiceImpl(new PersonRepositoryImpl(session));
    final TweetService tweetService = new TweetServiceImpl(new TweetRepositoryImpl(session));
    final LikeService likeService = new LikeServiceImpl(new LikeRepositoryImpl(session));
    final CommentService commentService = new CommentServiceImpl(new CommentRepositoryImpl(session));
    Set<Tweet> tweetList = new HashSet<>();

    Scanner scanner = new Scanner(System.in);
    public static Person user = new Person();

    public static Person getUser() {
        return user;
    }

    public void showMenuEntrance() {
        System.out.println("--------------------------Twitter--------------------------");
        System.out.println();
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
        tweet.setMessage(username + "joined  tweeter ");
        tweetList.add(tweet);
        Person person = new Person(name, family, birthdate, username, password, age);
        tweet.setPerson(person);
        if (!personService.validate(person)) {
            showMenuEntrance();
        } else {
            user = person;
        }

    }

    public void logIn() {
        System.out.println("=========LogIn=========");
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
        System.out.println("=========Edit=========");
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
        System.out.println("=========Profile=========");
        System.out.println("=====================================================================================================================================");
        System.out.println(user);
        System.out.println("=====================================================================================================================================");
        System.out.println("(1)Edit profile----(2)remove account----(3)log out------");
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

    public String checkTweetLength() {
        return "a".repeat(270);
    }

    public void postTweet() {
        System.out.println("please write your message");
        String message = scanner.next();
        Set<Tweet> tweets = new HashSet<>();
        Set<Like> likeList = new HashSet<>();
        Set<Comment> commentList = new HashSet<>();
        Tweet tweet = new Tweet(message, user);
        tweet.setLikeList(likeList);
        tweet.setCommentList(commentList);
        tweets.add(tweet);
        user.setTweetList(tweets);
        tweetService.save(tweet);
        personService.update(user);
        showHome();

    }

    public Comment postComment(Tweet tweet) {
        System.out.println("please write your comment");
        String message = scanner.next();
        Set<Like> likeList = new HashSet<>();
        Comment comment = new Comment(message, tweet);
        comment.setPerson(user);
        comment.setLikeList(likeList);
        commentService.save(comment);
        return comment;
    }

    public String editMessage() {
        System.out.println("please type your message");
        return scanner.next();

    }

    public void showHome() {
        while (true) {
            System.out.println("=========Home=========");
            Set<Tweet> tweets = new HashSet<>(tweetService.loadAll());
            if (tweets.size() == 0) {
                System.out.println("no body write tweets until now, do you want write tweet Y or N ?");
                if (scanner.next().equals("y")) postTweet();
                else if (scanner.next().equals("n")) System.exit(0);
            } else {
                for (Tweet tweet : tweets) {
                    boolean flagOfMenu = true;
                    while (flagOfMenu) {
                        System.out.println("************************************************************************************************************************************");
                        System.out.println("tweet message is:    " + tweet.getMessage());
                        System.out.println("and written with this person  ===>" + tweet.getPerson());
                        System.out.println("************************************************************************************************************************************");
                        System.out.print("like number is: " + tweet.getLikeList().size());
                        tweet.getLikeList().forEach(System.out::print);
                        System.out.println();
                        System.out.println("comment number is: " + tweet.getCommentList().size());
                        System.out.println("=====================================================================================================================================");
                        System.out.println("(1)like----(2)unlike----(3)ShowComment----(4)Like comment---(5)Unlike comment (6)writeComment---(7)Edit comment---(8)remove comment");
                        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("(9)write tweet---(10)Edit tweet--- (11)remove tweet---(12)search user---(13)show profile---(14)testTweetLength---(15)next tweet");
                        System.out.println("=====================================================================================================================================");
                        switch (scanner.nextInt()) {
                            case 1:
                                int flagLike = 0;

                                Set<Like> temporaryListLike = new HashSet<>(tweet.getLikeList());
                                for (Like like : temporaryListLike
                                ) {
                                    if (like.getPerson().getUsername().equals(user.getUsername())) {
                                        flagLike++;
                                    }
                                }
                                if (flagLike == 0) {
                                    Like like = new Like(user.getUsername(), tweet);
                                    like.setPerson(user);
                                    temporaryListLike.add(like);
                                    likeService.save(like);
                                    tweet.setLikeList(temporaryListLike);
                                    tweetService.update(tweet);
                                } else {
                                    System.out.println("liked before and can not liked again");
                                }
                                System.out.println("like number is: " + temporaryListLike.size());
                                break;
                            case 2:
                                boolean flagStatus = false;
                                Set<Like> temporaryListUnLike = new HashSet<>(tweet.getLikeList());
                                for (Like unlike : temporaryListUnLike
                                ) {
                                    if (unlike.getPerson().getUsername().equals(user.getUsername())) {
                                        temporaryListUnLike.remove(unlike);
                                        likeService.remove(unlike);
                                        break;
                                    } else flagStatus=true;
                                }
                                if (flagStatus)System.out.println("user not any liked in past");
                                tweet.setLikeList(temporaryListUnLike);
                                tweetService.update(tweet);
                                System.out.println("like number is: "+temporaryListUnLike.size());
                                break;

                            case 3:
                                System.out.println("comment: ");
                                if (tweet.getCommentList().size() == 0){
                                    System.out.println("no comments found for this tweet");
                                } else {
                                    for (Comment comment:tweet.getCommentList()
                                         ) {
                                        System.out.println("comment message is:  "+comment.getMessage());
                                        System.out.println("this comment is written with: "+comment.getPerson());
                                    }
                                }
                                break;

                            case 4:
                                int flag = 0;
                                Set<Comment> temporaryComments = new HashSet<>(tweet.getCommentList());
                                for (Comment comment : temporaryComments
                                ) {
                                    System.out.println(comment);
                                    System.out.println("like number of this comment is: " + comment.getLikeList().size());
                                    System.out.println("do you want like it? yes->y no->n");
                                    if (scanner.next().equals("y")) {
                                        Set<Like> temporaryCommentsLike = new HashSet<>(comment.getLikeList());
                                        for (Like temporaryLike : temporaryCommentsLike
                                        ) {
                                            if (temporaryLike.getPerson().getUsername().equals(user.getUsername())) {
                                                flag++;
                                            }
                                        }
                                        if (flag == 0) {
                                            Like likeOfComment = new Like(user.getUsername(), comment);
                                            likeOfComment.setPerson(user);
                                            temporaryCommentsLike.add(likeOfComment);
                                            likeService.save(likeOfComment);
                                            tweet.setLikeList(temporaryCommentsLike);
                                            commentService.update(comment);
                                            System.out.println("like number of this comment is: " + temporaryCommentsLike.size());
                                        } else System.out.println("you liked in past and can not liked again");

                                    } else System.out.println();
                                }
                                break;

                            case 5:
                                int flagUnlike = 0;
                                Set<Comment> temporaryCommentSet = new HashSet<>(tweet.getCommentList());
                                for (Comment comment : temporaryCommentSet
                                ) {
                                    System.out.println(comment);
                                    System.out.println("like number of this comment is: " + comment.getLikeList().size());
                                    System.out.println("do you want unlike it? yes->y no->n");
                                    if (scanner.next().equals("y")) {
                                        Like unlike = new Like();
                                        Set<Like> temporaryCommentsLike = new HashSet<>(comment.getLikeList());
                                        for (Like temporaryLike : temporaryCommentsLike
                                        ) {
                                            if (temporaryLike.getPerson().getUsername().equals(user.getUsername())) {
                                                flagUnlike++;
                                                unlike = temporaryLike;
                                            }
                                        }
                                        if (flagUnlike > 0) {
                                            temporaryCommentsLike.remove(unlike);
                                            likeService.remove(unlike);
                                            comment.setLikeList(temporaryCommentsLike);
                                            commentService.update(comment);
                                        } else System.out.println("you do not like before that unlike now");

                                        System.out.println("like number of this comment is: " + temporaryCommentsLike.size());
                                    } else System.out.println();
                                }
                                break;
                            case 6:
                                Set<Comment> comments = new HashSet<>(tweet.getCommentList());
                                comments.add(postComment(tweet));
                                tweet.setCommentList(comments);
                                tweetService.update(tweet);
                                break;
                            case 7:
                                Set<Comment> commentSetForEdit = new HashSet<>(tweet.getCommentList());
                                for (Comment comment : commentSetForEdit
                                ) {
                                    if (comment.getPerson().getUsername().equals(user.getUsername())) {
                                        System.out.println(comment);
                                        System.out.println("do you want edit it? yes->y or no->n :");
                                        if (scanner.next().equals("y")) {
                                            comment.setMessage(editMessage());
                                            commentService.update(comment);
                                            System.out.println("Comment Edited.....");
                                        }
                                    } else System.out.println("no have comment");
                                }
                                break;
                            case 8:
                                Set<Comment> commentSet = new HashSet<>(tweet.getCommentList());
                                for (Comment comment : commentSet
                                ) {
                                    if (comment.getPerson().getUsername().equals(user.getUsername())) {
                                        System.out.println(comment);
                                        System.out.println("do you want remove it? yes->y or no->n :");
                                        if (scanner.next().equals("y")) {
                                            commentService.remove(comment);
                                            System.out.println("comment removed.....");

                                        }
                                    }else System.out.println("you can not remove because you are not owner");
                                }

                                break;
                            case 9:
                                postTweet();
                                break;
                            case 10:
                                if (tweet.getPerson().getUsername().equals(user.getUsername())) {
                                    tweet.setMessage(editMessage());
                                    tweetService.update(tweet);
                                    System.out.println("tweet is Edited.....");
                                }
                                break;
                            case 11:
                                if (tweet.getPerson().getUsername().equals(user.getUsername())) {
                                    tweetService.remove(tweet);
                                    System.out.println("tweet is removed.....");
                                } else System.out.println("you can not remove this tweet because you are not owner");
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
                                break;
                            case 15:
                                System.out.println();
                                flagOfMenu = false;
                                break;
                        }
                    }
                }
            }
        }
    }
}


