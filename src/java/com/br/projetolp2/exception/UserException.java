/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.projetolp2.exception;

/**
 *
 * @author 31377467
 */
public class UserException extends RuntimeException{
    public static String usernotfound = "UserException: nao achado";

    public UserException(String msg) {
        
        super(msg);
    }
    
    
}
