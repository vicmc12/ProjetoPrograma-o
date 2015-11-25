package com.br.projetolp2.controller;

import com.br.projetolp2.model.User;
import com.br.projetolp2.model.dao.UserDAO;
import javax.servlet.http.Cookie;

/**
 *
 * @author 1147106
 */
public class UserManager {
    private static User user = null;
    /**
     * 
     * @param username USERNAME
     * @param password PASSWORD
     * @return -1 for "User not found" -2 for "Wrong Password" 1 for "User authorized"
     */
    public static int authorize(String username, String password){
        int auth = -1;
        UserDAO dao = new UserDAO();
        user = dao.readByName(username);
        if(user.getUser_id() == -1){
            System.out.println("Error: User not found");
            auth = -1;
        } else if(!user.getPassword().equals(password)){
            System.out.println("Error: Wrong passsword");
            auth = -2;
        } else {
            auth = 1;
            
        }
        return auth;
    }
    
    public static int insert(User user, String pwd2){
        UserDAO dao = new UserDAO();
        
        if( dao.readByName(user.getUsername()).getUser_id() != -1 ){
            System.out.println("Error: username already used");
            return -3;
        }    
        if(user.getPassword().equals(pwd2)){
            boolean inserted = dao.insert(user);
            if(inserted) {
                System.out.println("User "+user.getUsername()+" inserted successfully" );
                return 1;
            }
            else {
                System.out.println("Error: user not inserted");
                return -5;
            }
        } else {
            System.out.println("Error: Password doesn't match");
            return -4;
        }
        
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        UserManager.user = user;
    }
    
}
