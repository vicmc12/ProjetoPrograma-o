/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.projetolp2.model.dao;

import com.br.projetolp2.model.Game;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Victor
 */

//Perguntar se precisa fazer alguma coisa diferente já que esta classe é uma herança de items
public class GameDAO implements GenericDAO<Game>{

     private Connection conn;

    public GameDAO() {
        //1. Realizar a conexão
        conn = ConnectionDB.getInstance();
    }
    
    @Override
    public boolean insert(Game game) {
        boolean resp = false;    
        String sql = "INSERT INTO game(nome,type_game, description, genre, releasedate) VALUES(?,?,?,?,?)";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, game.getName());
            ps.setString(2, game.getType());
            ps.setString(3, game.getDescription());
            ps.setString(4, game.getGenre());
            java.sql.Date datesql = new java.sql.Date(game.getRelreaseDate().getTime());
            ps.setDate(5,datesql);
            
            
            int resposta = ps.executeUpdate();
            if(resposta == 0){
                System.out.println("Error: game not inserted");
            } else {
                System.out.println("Game inserted succefully");
                resp = true;
            }
            
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resp;
    }

    @Override
    public List<Game> read() {
        List<Game> games = new ArrayList<>();
        //2. Criar o preparedStatement
        String sql = "SELECT * FROM game";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            
            //3. Executa a query
            ResultSet rs = ps.executeQuery();
            
            //4. Mostrar os resultados
            while(rs.next()){
                Game game = new Game();
                game.setId_game(rs.getInt("id_game") );
                game.setDescription(rs.getString("description") );
                game.setGenre(rs.getString("genre"));
                game.setRelreaseDate(rs.getDate("releasedate"));
                game.setType(rs.getString("type_game"));
                games.add(game);
            }
            
            //5. Fechar tudo
            ps.close();
            rs.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return games;
    }
    
    
    @Override
    public boolean update(Game game) {
        boolean resp = false;
        String sql = "UPDATE game SET nome=?,type_game=?, description=?, genre=?, releasedate=? WHERE id_game=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, game.getName());
            ps.setString(2, game.getType());
            ps.setString(3, game.getDescription());
            ps.setString(4, game.getGenre());
            ps.setInt(6, game.getId_game());
            java.sql.Date datesql = new java.sql.Date(game.getRelreaseDate().getTime());
            ps.setDate(5,datesql);
            int resposta = ps.executeUpdate();
            if(resposta == 0){
                System.out.println("Error: game not updated");
            } else {
                System.out.println("Game updated successfully");
                resp = true;
            }
            
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resp;
    }

    @Override
    public boolean delete(Game game) {
        boolean resp = false;
        String sql = "DELETE FROM game WHERE id_game=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, game.getId_game());
            int resposta = ps.executeUpdate();
            if(resposta == 0){
                System.out.println("Error: game not removed");
            } else {
                System.out.println("Game removed successfully");
                resp = true;
            }
            
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resp;
    }
    
}
