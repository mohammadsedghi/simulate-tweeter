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
    private  String likes;
    @ManyToOne
    @JoinColumn(name = "tweet_id")
    private Tweet tweet;
}
