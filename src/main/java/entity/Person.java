package entity;

import base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class Person<ID extends Serializable> extends BaseEntity<ID> {
    protected String name;
    protected String family;
    protected String birthdate;
    protected String username;
    protected String password;
}
