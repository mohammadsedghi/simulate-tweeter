package entity;

import base.entity.BaseEntity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class Comment<ID extends Serializable> extends BaseEntity<ID> {
    @OneToMany
    private Tweet<ID> tweet;
}
