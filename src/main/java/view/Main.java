package view;

import base.repository.util.HibernateUtil;
import entity.Comment;
import entity.Like;
import entity.Person;
import entity.Tweet;
import org.hibernate.Session;
import repository.CommentRepository;
import repository.Impl.CommentRepositoryImpl;
import repository.Impl.LikeRepositoryImpl;
import repository.Impl.PersonRepositoryImpl;
import repository.Impl.TweetRepositoryImpl;
import repository.LikeRepository;
import repository.PersonRepository;
import repository.TweetRepository;
import service.CommentService;
import service.Impl.CommentServiceImpl;
import service.Impl.LikeServiceImpl;
import service.Impl.PersonServiceImpl;
import service.Impl.TweetServiceImpl;
import service.LikeService;
import service.PersonService;
import service.TweetService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Session session= HibernateUtil.getSessionFactory().openSession();
        PersonService personService=new PersonServiceImpl(new PersonRepositoryImpl(session));
        TweetService tweetService=new TweetServiceImpl(new TweetRepositoryImpl(session));
        LikeService likeService=new LikeServiceImpl(new LikeRepositoryImpl(session));
        CommentService commentService=new CommentServiceImpl(new CommentRepositoryImpl(session));
        Tweet tweet=new Tweet();
        Like like=new Like();
        like.setLikes("likee");
        Comment comment=new Comment();
        tweet.setMessage("aaaaaa");
        List<Tweet> tweetList=new ArrayList<>();
        List<Like> likeList=new ArrayList<>();
        List<Comment> commentList=new ArrayList<>();
        like.setTweet(tweet);
        comment.setMessage("commenting");
        likeList.add(like);
        commentList.add(comment);
        Person person=new Person("ali","sedghi","1993","mohammdad","12345",25,tweetList);
        tweet.setLikeList(likeList);
        tweet.setCommentList(commentList);
       tweet.setPerson(person);
       comment.setTweet(tweet);
        tweetList.add(tweet);
        personService.save(person);
        tweetService.save(tweet);
        likeService.save(like);
        commentService.save(comment);

    }
}