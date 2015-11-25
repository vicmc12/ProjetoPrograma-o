/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.projetolp2.model.dao;


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
public class PostDAO implements GenericDAO<Post>{

    private Connection conn;

    public PostDAO() {
        //1. Realizar a conexão
        conn = ConnectionDB.getInstance();
    }
    
    @Override
    public boolean insert(Post post) {
        boolean resp = false;    
        String sql = "INSERT INTO post(id_user, description, status) VALUES(?,?,?)";
        String sql2 = "INSERT INTO game_list_post(id_post, id_game) VALUES(?,?)";
        try (
        PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
    ) {
        statement.setInt(1, post.getUser_id());
        statement.setString(2, post.getDescription());
        statement.setString(3, post.getStatus());
        int resposta = statement.executeUpdate();

        if (resposta == 0) {
            throw new SQLException("Creating user failed, no rows affected.");
        }

        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                post.setId_post(generatedKeys.getInt(1));
            }
            else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        PreparedStatement statement2 = conn.prepareStatement(sql2);
            System.out.println("GENERATED KEY "+post.getId_post());
          for (int i = 0; i < post.getGames().size(); i++) {
           statement2.setInt(1, post.getId_post());
           statement2.setInt(2, post.getGames().get(i).getId_game());   
           int resposta2 = statement2.executeUpdate();
           }  
          
        
    }catch(Exception ex){
        ex.printStackTrace();
    }

       return resp;
    }

    @Override
    public List<Post> read() {
        List<Post> posts = new ArrayList<>();
        //2. Criar o preparedStatement
        String sql = "SELECT * FROM post";

                        System.out.println("aki4");

        
        try {
            System.out.println("aki6");
            PreparedStatement ps = conn.prepareStatement(sql);
            PreparedStatement psGame;
            PreparedStatement psGames;
            
            //3. Executa a query
            ResultSet rs = ps.executeQuery();
            ResultSet rsGame;
            ResultSet rsGames;
            
            
            //4. Mostrar os resultados
            while(rs.next()){
                System.out.println("aki7");
                Post post = new Post();
                post.setId_post(rs.getInt("id_post") );
                String sqlGames = "SELECT game_list_post.id_game from game_list_post where game_list_post.id_post = "+(rs.getInt("id_post"));
                
                psGames = conn.prepareStatement(sqlGames);
                rsGames = psGames.executeQuery();
                
                
                /**LOGICA PARA ADICIONAR LISTA DE JOGOS **/
                List<Game> games = new ArrayList<>();
                while(rsGames.next()){
                    System.out.println("aki8");
                    String sqlGame = "SELECT * from game where game.id_game = "+(rsGames.getInt("id_game"));
                    System.out.println((rsGames.getInt("id_game")));
                    psGame = conn.prepareStatement(sqlGame);
                    rsGame = psGame.executeQuery();
                    while(rsGame.next()){
                        System.out.println("ola");
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
                
                
                post.setDescription(rs.getString("description"));
                post.setId_post(rs.getInt("id_post"));
                System.out.println(games.size());
                post.setGames(games);
                post.setUser_id(rs.getInt("id_user"));
                post.setStatus(rs.getString("status"));
                
                posts.add(post);
                psGames.close();
                rsGames.close();
            }
            
            //5. Fechar tudo
            ps.close();
            rs.close();
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return posts;
    }
    
    
    @Override
    public boolean update(Post post) {
         boolean resp = false;    
        String sql = "UPDATE post set status=? WHERE id_post=?";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, post.getStatus());
            ps.setInt(2, post.getId_post());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resp;
    }

    @Override
    public boolean delete(Post post) {
        boolean resp = false;
        String sql = "DELETE FROM post WHERE id_post=?";
        String sqlDeleteGames = "DELETE * from game_list_post where game_list_post.id_post = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, post.getId_post());
            int resposta = ps.executeUpdate();
            PreparedStatement psDelete = conn.prepareStatement(sqlDeleteGames);
            psDelete.setInt(1, post.getId_post());
            int resportaDelete = psDelete.executeUpdate();
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

    public boolean deletePostById(int postID) {
        boolean resp = false;
        String sql = "DELETE FROM post WHERE id_post=?";
        String sqlDeleteGames = "DELETE from game_list_post where game_list_post.id_post = ?";
        String sqlModifyBid = "UPDATE bid set id_post=-1, status='Post deletado' WHERE id_post=?";
        
        try {
             PreparedStatement psModifyBid = conn.prepareStatement(sqlModifyBid);
            psModifyBid.setInt(1, postID);
            int resportaModifyBid = psModifyBid.executeUpdate();
            
            PreparedStatement psDelete = conn.prepareStatement(sqlDeleteGames);
            psDelete.setInt(1, postID);
            int resportaDelete = psDelete.executeUpdate();
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, postID);
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
    
}
