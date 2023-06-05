package entity;

import base.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Tweet extends BaseEntity<Long> {
    @Size(max = 280)
    private String message;
    @OneToMany(mappedBy = "tweet")
    private Set<Like> likeList;
    @OneToMany(mappedBy = "tweet")
    private Set<Comment> commentList;
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public Tweet(String message,Person person) {
        this.message = message;
        this.person=person;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "message='" + message + '\'' +
                ", person=" + person +
                "} " + super.toString();
    }
}
