package com.br.projetolp2.model;

import java.io.Serializable;

/**
 *
 * @author Victor
 */
public class User implements Serializable{

    private int user_id;
    private String username;
    private String password;
    private int permission;

    public User() {
        user_id=-1;
        username="";
    }

    public User(int user_id, String username, String password) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.permission = 0;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

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

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }
   
    
}
