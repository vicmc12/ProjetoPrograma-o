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
public class Bid implements Serializable{
    private int id_user;
    private List<Game> games;
    private int id_bid;
    private int id_post;
    private String status;

    public Bid(int id_user, List<Game> games, int id_bid, int id_post) {
        this.id_user = id_user;
        this.games = games;
        this.id_bid = id_bid;
        this.id_post = id_post;
        status = "Pendente";
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Bid() {
        status = "Pendente";
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public int getId_bid() {
        return id_bid;
    }

    public void setId_bid(int id_bid) {
        this.id_bid = id_bid;
    }

    public int getId_post() {
        return id_post;
    }

    public void setId_post(int id_post) {
        this.id_post = id_post;
    }

}
