package entity;

import base.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "likes")
public class Like  extends BaseEntity<Long> {
    public Like(String likes, Tweet tweet) {
        this.likes = likes;
        this.tweet = tweet;
    }

    public Like(String likes, Comment comment) {
        this.likes = likes;
        this.comment = comment;
    }

    private  String likes;
    @ManyToOne
    @JoinColumn(name = "tweet_id")
    private Tweet tweet;



    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

}
