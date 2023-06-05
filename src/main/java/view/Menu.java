package view;

import base.repository.util.HibernateUtil;
import entity.Comment;
import entity.Like;
import entity.Person;
import entity.Tweet;
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
        boolean usernameFlag=true;
        boolean passwordFlag=true;
        System.out.println("name:----------------------");
        String name = scanner.next();
        System.out.println("family:--------------------");
        String family = scanner.next();
        System.out.println("birthDate:-----------------");
        String birthdate = scanner.next();
        System.out.println("userName:------------------");
        String username = scanner.next();
        if (!username.equals(user.getUsername()))usernameFlag=false;
        System.out.println("password:------------------");
        String password = scanner.next();
        if(!password.equals(user.getPassword()))passwordFlag=false;
        System.out.println("age:-----------------------");
        int age = scanner.nextInt();

        Person person = new Person(name, family, birthdate, username, password, age, user.getTweetList());

        if (!personService.validate(person)) {
            showMenuEntrance();
        } else {
            personService.update(person);
            System.out.println("updated......");
            if (!usernameFlag || !passwordFlag){
                user=null;
                logIn();
            }
            user = person;
        }

    }

    public void logIn() {
        System.out.println("logIn:------------------------");
        System.out.println("username:---------------------");
        String userName = scanner.next();
        System.out.println("password:---------------------");
        String password = scanner.next();
        if (personService.findByUserName(userName).isPresent() &&
                personService.findByPassword(password).isPresent()) {
            user = personService.findByPassword(password).get();
            showHome();
        } else {
            System.out.println("username and password is inCorrect");
            showMenuEntrance();
        }
    }
    public void editProfile(){
        System.out.println("EDIT");
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
        Person person = new Person(name, family, birthdate, username, password, age, tweetList);

        if (!personService.validate(person)) {
            showMenuEntrance();
        } else {

            user = person;
        }
    }
    public void showProfile(){
        System.out.println(user);
        System.out.println("(1)Edit profile----(2)log out------");
        switch (scanner.nextInt()){
            case 1:
            break;
            case 2:
                user=null;
                showMenuEntrance();
                break;
        }
    }

    public void postTweet(Person person) {
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

    public void showHome() {
        System.out.println("=========Home=========");
        Set<Tweet> tweets = new HashSet<>(tweetService.loadAll());
        // tweets = tweetService.loadAll();

        if (tweets.size() == 0) {
            System.out.println("no body write tweets until now, do you want write tweet Y or N ?");
            if (scanner.next().equals("y")) postTweet(user);
            else if (scanner.next().equals("n")) System.exit(0);
        } else {
            for (Tweet tweet : tweets) {
                System.out.println(tweet.getMessage() + "  ===>  " + tweet.getPerson());
                System.out.println("---(1)like----(2)unlike----(3)ShowComment----(4)writeComment");
                System.out.println("---(5)nextTweet----(6)show profile");
                switch (scanner.nextInt()) {
                    case 1:
                        Set<Like> temporaryListLike = new HashSet<>(tweet.getLikeList());
                        Like like = new Like(user.getUsername(), tweet);
                        temporaryListLike.add(like);
                        likeService.save(like);
                        tweet.setLikeList(temporaryListLike);
                        tweetService.update(tweet);
                        System.out.println(temporaryListLike.size());
                        break;
                    case 2:
                        Set<Like> temporaryListUnLike = new HashSet<>(tweet.getLikeList());
                        for (Like unlike : temporaryListUnLike
                        ) {
                            if (unlike.getLikes().equals(user.getUsername())) {
                                temporaryListUnLike.remove(unlike);
                                likeService.remove(unlike);
                                break;
                            }
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
                        Set<Comment> comments = new HashSet<>(tweet.getCommentList());
                        comments.add(postComment(tweet));
                        tweet.setCommentList(comments);
                        tweetService.update(tweet);
                        break;
                    case 5:
                        //continue;
                        break;
                    case 6:
                        System.exit(0);
                }

            }
        }
    }
}


