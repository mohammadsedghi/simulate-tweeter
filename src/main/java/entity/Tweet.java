package entity;

import base.BaseEntity;

import java.io.Serializable;

public class Tweet <ID extends Serializable>  extends BaseEntity<ID> {
    String message;

}
