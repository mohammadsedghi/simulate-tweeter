package entity;

import base.entity.BaseEntity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class Like <ID extends Serializable> extends BaseEntity<ID> {
    @ManyToOne
    private Tweet<ID> tweet;
}
