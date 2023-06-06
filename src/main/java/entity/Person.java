package entity;

import base.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Person extends BaseEntity<Long> {
    private String name;
    private String family;
    private String birthdate;
    @Column(unique = true)
    private String username;
    private String password;
    @Positive(message = "age must be positive value")
    @NotNull(message = "age must be have value")
    @Max(value = 110, message = "age must be less than 110 value")
    @Min(value = 18, message = "age must be more than 18")
    private int age;

    @OneToMany(mappedBy = "person",cascade = CascadeType.ALL)
  private Set<Tweet> tweetList;

    public Person(String name, String family, String birthdate, int age) {
        this.name = name;
        this.family = family;
        this.birthdate = birthdate;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", family='" + family + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                "} " + super.toString();
    }
}
