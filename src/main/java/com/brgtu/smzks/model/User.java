package com.brgtu.smzks.model;

import javax.persistence.*;
import java.util.List;
import java.util.List;

/**
 * Created by yauheni on 12.03.16.
 */

@Entity
@Table(name = "USER")
public class User {


    @Id
    private String username;
    private String password;
    private Boolean enabled;
    private String name;


    @OneToMany(mappedBy = "user")
    private List<Role> roles;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
