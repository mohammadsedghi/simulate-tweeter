package entity;

import base.entity.BaseEntity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class Tweet   extends BaseEntity<Long> {
@Size(max = 280)
   private String message;
   @OneToMany
    private List<Like> likeList;
   @OneToMany
   private List<Comment> commentList;
   @ManyToOne
   private Person person;

}
