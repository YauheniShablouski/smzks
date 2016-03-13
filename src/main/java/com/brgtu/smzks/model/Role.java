package com.brgtu.smzks.model;

import javax.persistence.*;

/**
 * Created by yauheni on 12.03.16.
 */

@Entity
@Table(name = "ROLE")
public class Role extends Identifire{

    private String role;

    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
