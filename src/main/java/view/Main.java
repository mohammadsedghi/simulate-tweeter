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

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Session session= HibernateUtil.getSessionFactory().openSession();
        PersonRepository personRepository=new PersonRepositoryImpl(session);
        TweetRepository tweetRepository=new TweetRepositoryImpl(session);
        LikeRepository likeRepository=new LikeRepositoryImpl(session);
        CommentRepository commentRepository=new CommentRepositoryImpl(session);
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
        Person person=new Person("ali","sedghi","1993","mohammdad","12345",tweetList);
        tweet.setLikeList(likeList);
        tweet.setCommentList(commentList);
       tweet.setPerson(person);
       comment.setTweet(tweet);
        tweetList.add(tweet);
        personRepository.save(person);
        tweetRepository.save(tweet);
        likeRepository.save(like);
        commentRepository.save(comment);

    }
}