/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

/**
 *
 * @author Pluce
 */
public class Security extends Secure.Security {
    
    static boolean authenticate(String username, String password) {
       if(username.equals("rome") && password.equals("rome"))
           return true;
       return false;
    }
}