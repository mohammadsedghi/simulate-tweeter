package entity;

import base.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Tweet extends BaseEntity<Long> {
    @Size(max = 280)
    private String message;
    @OneToMany(mappedBy = "tweet")
    private List<Like> likeList;
    @OneToMany(mappedBy = "tweet")
    private List<Comment> commentList;
    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

}
