package entity;

import base.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Person extends BaseEntity<Long> {
    private String name;
    private String family;
    private String birthdate;
    private String username;
    private String password;
    @OneToMany(mappedBy = "person")
   private List<Tweet> tweetList;


}
