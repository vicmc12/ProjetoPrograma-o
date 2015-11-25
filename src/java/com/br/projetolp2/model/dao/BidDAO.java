/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.projetolp2.model.dao;

import com.br.projetolp2.model.Bid;
import com.br.projetolp2.model.Game;
import com.br.projetolp2.model.Post;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Victor
 */
public class BidDAO implements GenericDAO<Bid> {

    private Connection conn;

    public BidDAO() {
        //1. Realizar a conexão
        conn = ConnectionDB.getInstance();
    }
    
    @Override
    public boolean insert(Bid bid) {
        boolean resp = false;    
        String sql = "INSERT INTO bid(id_user, id_post, status) VALUES(?,?,?)";
        String sql2 = "INSERT INTO game_list_bid(id_bid, id_game) VALUES(?,?)";
        try (
        PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
    ) {
        statement.setInt(1, bid.getId_user());
        statement.setInt(2,bid.getId_post());
        statement.setString(3,bid.getStatus());
        int resposta = statement.executeUpdate();

        if (resposta == 0) {
            throw new SQLException("Creating user failed, no rows affected.");
        }

        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                bid.setId_bid(generatedKeys.getInt(1));
            }
            else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        PreparedStatement statement2 = conn.prepareStatement(sql2);
          for (int i = 0; i < bid.getGames().size(); i++) {
           statement2.setInt(1, bid.getId_bid());
           statement2.setInt(2, bid.getGames().get(i).getId_game());
           int resposta2 = statement2.executeUpdate();
           }  
          
        
    }catch(Exception ex){
        ex.printStackTrace();
    }

       return resp;
    }

    @Override
    public List<Bid> read() {
        List<Bid> bids = new ArrayList<>();
        //2. Criar o preparedStatement
        String sql = "SELECT * FROM bid";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            PreparedStatement psGame;
            PreparedStatement psGames;
            
            //3. Executa a query
            ResultSet rs = ps.executeQuery();
            ResultSet rsGame;
            ResultSet rsGames;
            
            
            //4. Mostrar os resultados
            while(rs.next()){
                Bid bid = new Bid();
                bid.setId_bid(rs.getInt("id_bid") );
                String sqlGames = "SELECT game_list_bid.id_game from game_list_bid where game_list_bid.id_bid = "+(rs.getInt("id_bid"));
                
                psGames = conn.prepareStatement(sqlGames);
                rsGames = psGames.executeQuery();
                
                
                /**LOGICA PARA ADICIONAR LISTA DE JOGOS **/
                List<Game> games = new ArrayList<>();
                while(rsGames.next()){
                    String sqlGame = "SELECT * from game where game.id_game = "+(rsGames.getInt("id_game"));
                    psGame = conn.prepareStatement(sqlGame);
                    rsGame = psGame.executeQuery();
                    while(rsGame.next()){
                    Game game = new Game();
                    game.setId_game(rsGame.getInt("id_game") );
                    game.setDescription(rsGame.getString("description") );
                    game.setGenre(rsGame.getString("genre"));
                    game.setName(rsGame.getString("nome"));
                    game.setRelreaseDate(rsGame.getDate("releasedate"));
                    game.setType(rsGame.getString("type_game"));
                    games.add(game);
                    }
                    psGame.close();
                    rsGame.close();
                    
                }
                
                /** FIM DA LOGICA DE ADICIONAR LISTA DE JOGOS **/
                
                
                bid.setId_user(rs.getInt("id_user"));
                bid.setGames(games);
                bid.setId_post(rs.getInt("id_post"));
                bid.setStatus(rs.getString("status"));
                
                bids.add(bid);
                psGames.close();
                rsGames.close();
            }
            
            //5. Fechar tudo
            ps.close();
            rs.close();
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return bids;
    }
    @Override
    public boolean update(Bid bid) {
        boolean resp = false;    
        String sql = "UPDATE bid set status=? WHERE id_bid=?";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, bid.getStatus());
            ps.setInt(2, bid.getId_bid());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resp;
    }


    public boolean deleteBidById(int bidID) {
        boolean resp = false;
        String sql = "DELETE FROM bid WHERE id_bid=?";
        String sqlDeleteGames = "DELETE from game_list_bid where game_list_bid.id_bid = ?";
        try {
            PreparedStatement psDelete = conn.prepareStatement(sqlDeleteGames);
            psDelete.setInt(1, bidID);
            int resportaDelete = psDelete.executeUpdate();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, bidID);
            int resposta = ps.executeUpdate();
            
            if(resposta == 0){
                System.out.println("Error: post not removed");
            } else {
                System.out.println("Post removed successfully");
                resp = true;
            }
            
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resp;
    }

    @Override
    public boolean delete(Bid t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

}
