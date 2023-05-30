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
public class Comment extends BaseEntity<Long> {
    private String message;
    @ManyToOne
@JoinColumn(name = "tweet_id",nullable = false)
    private Tweet tweet;
}
