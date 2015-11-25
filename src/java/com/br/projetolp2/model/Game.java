/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.projetolp2.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Victor
 */
public class Game implements Serializable{
private int id_game;
private String genre;
private Date relreaseDate;
private String name; 
private String type;
private String description;
    public Game(String genre, Date relreaseDate, String name, String type, String description) {
        this.name = name;
        this.type = type; 
        this.description = description;
        this.genre = genre;
        this.relreaseDate = relreaseDate;
    }

    public Game() {
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Date getRelreaseDate() {
        return relreaseDate;
    }

    public void setRelreaseDate(Date relreaseDate) {
        this.relreaseDate = relreaseDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId_game() {
        return id_game;
    }

    public void setId_game(int id_game) {
        this.id_game = id_game;
    }

    @Override
    public String toString() {
        return "Game{" + "id_game=" + id_game + ", genre=" + genre + ", relreaseDate=" + relreaseDate + ", name=" + name + ", type=" + type + ", description=" + description + '}';
    }



    
    
}
