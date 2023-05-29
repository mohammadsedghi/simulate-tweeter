package entity;

import base.entity.BaseEntity;
import jakarta.persistence.OneToMany;
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
public class Person extends BaseEntity<Long> {
    private String name;
    private String family;
    private String birthdate;
    private String username;
    private String password;
   // @OneToMany
  //  private List<Tweet<ID>> tweetList;

}
