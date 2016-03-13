package com.brgtu.smzks.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by yauheni on 12.03.16.
 */

@MappedSuperclass
public abstract class Identifire {

    @Id
    @GeneratedValue
    protected Long id;

    public Long getId() {
        return id;
    }
}
