package entity;

import base.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment extends BaseEntity<Long> {
    public Comment(String message, Tweet tweet) {
        this.message = message;
        this.tweet = tweet;
    }

    public Comment(String message, Set<Like> likeList) {
        this.message = message;
        this.likeList = likeList;
    }

    private String message;
    @ManyToOne
    @JoinColumn(name = "tweet_id")
    private Tweet tweet;
    @OneToMany(mappedBy = "comment", cascade = CascadeType.REMOVE)
    private Set<Like> likeList;
}
