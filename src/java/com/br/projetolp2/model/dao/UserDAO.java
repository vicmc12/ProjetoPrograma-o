package com.br.projetolp2.model.dao;

import com.br.projetolp2.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements GenericDAO<User>{
    private Connection conn;

    public UserDAO() {
        conn = ConnectionDB.getInstance();
    }

    @Override
    public boolean insert(User user) {
         boolean resp = false;    
        String sql = "INSERT INTO user_t(username,password) VALUES(?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            int resposta = ps.executeUpdate();
            if(resposta == 0){
                System.out.println("Erro ao inserir o Usuário");
            } else {
                System.out.println("Usuário inserido com sucesso!");
                resp = true;
            }
            
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resp;
        
    }

    @Override
    public List<User> read() {
        List<User> users = new ArrayList<>();
        //2. Criar o preparedStatement
        String sql = "SELECT * FROM user_t";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            
            //3. Executa a query
            ResultSet rs = ps.executeQuery();
            
            //4. Mostrar os resultados
            while(rs.next()){
                int id = rs.getInt("id_user");
                String username = rs.getString("username");
                String password = rs.getString("password");
                User u = new User(id, username, password);
                users.add(u);
            }
            
            //5. Fechar tudo
            ps.close();
            rs.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return users;
        
    }
    public User readByName(String username) {
        User user = new User();
        //2. Criar o preparedStatement
        String sql = "SELECT * FROM user_t WHERE username=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            
            //3. Executa a query
            ResultSet rs = ps.executeQuery();
            
            //4. Mostrar os resultados
            while(rs.next()){
                user.setUser_id(rs.getInt("id_user"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
            }
            
            //5. Fechar tudo
            ps.close();
            rs.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
        
    }


    @Override
    public boolean update(User user) {
        boolean resp = false;
        String sql = "UPDATE user_t SET username=?,password=? WHERE id_user=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getUser_id());
            int resposta = ps.executeUpdate();
            if(resposta == 0){
                System.out.println("Erro ao atualizar o usuário");
            } else {
                System.out.println("Usuário atualizado com sucesso!");
                resp = true;
            }
            
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resp;
        
    }

    @Override
    public boolean delete(User user) {
        
         boolean resp = false;
        String sql = "DELETE FROM user_t WHERE user_id=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getUser_id());
            int resposta = ps.executeUpdate();
            if(resposta == 0){
                System.out.println("Erro ao remover o Usuário");
            } else {
                System.out.println("Usuário removido com sucesso!");
                resp = true;
            }
            
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resp;
    
    }

    public User readById(int userid) {
        User user = new User();
        //2. Criar o preparedStatement
        String sql = "SELECT * FROM user_t WHERE id_user=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userid);
            
            //3. Executa a query
            ResultSet rs = ps.executeQuery();
            
            //4. Mostrar os resultados
            while(rs.next()){
                user.setUser_id(rs.getInt("id_user"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
            }
            
            //5. Fechar tudo
            ps.close();
            rs.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }
    
}