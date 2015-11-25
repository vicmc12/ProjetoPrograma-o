/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.projetolp2.model;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Victor
 */
public class Post implements Serializable{
   private int id_post;
   private int user_id;
   private String description;
   List<Game> games;
   private String status;

    

    public Post(int user_id, String description,  List<Game> games) {
        this.user_id = user_id;
        this.description = description;
        this.games = games;
        status = "Ativo";
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }   
    public Post() {
        status = "Ativo";
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public int getId_post() {
        return id_post;
    }

    public void setId_post(int id_post) {
        this.id_post = id_post;
    }

    
   

}
