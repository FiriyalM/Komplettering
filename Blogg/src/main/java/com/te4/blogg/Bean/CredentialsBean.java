/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.te4.blogg.Bean;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.te4.blogg.ConnectionFactory;
import com.te4.blogg.entity.Credentials;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Base64;
import javax.ejb.Stateless;

/**
 *
 * @author Elev
 */

@Stateless

public class CredentialsBean {
     public Credentials creatCredentials(String basicAuth){
        basicAuth = basicAuth.substring(6).trim();
        byte[] bytes = Base64.getDecoder().decode(basicAuth);
        basicAuth = new String(bytes);
        int colon = basicAuth.indexOf(":");
        String username = basicAuth.substring(0, colon);
        String password = basicAuth.substring(colon+1);
        return new Credentials(username, password);
    }
    /**
     * This method controlls if the right username is connected with the right password
     * It also makes the password into a 64 bytes.
     * This makes the password kinda safe
     * @param credentials
     * @return the verified hashed password
     */
    public boolean cheackCredentials(Credentials credentials){
         try(Connection connection = ConnectionFactory.getConnection()){
             Statement stmt= connection.createStatement();
             String sql = String.format("SELECT * FROM user WHERE Username='%s'", credentials.getUsername());
             ResultSet data = stmt.executeQuery(sql);
             if(data.next()){
                String hashedPassword = data.getString("Password");
                BCrypt.Result result = BCrypt.verifyer().verify(credentials.getPassword().toCharArray(), hashedPassword);
                return result.verified;
            }else{
                return false;
            }
        }catch(Exception e){
            return false;
        }
    }
    //This is where everything saves and it sends a message back if there is a error with the password and username
    public int saveCredentials(Credentials credentials){
        try(Connection connection = ConnectionFactory.getConnection()){
        String hashedString = BCrypt.withDefaults().hashToString(12, credentials.getPassword().toCharArray());
            Statement stmt= connection.createStatement();
            String sql = String.format("INSERT INTO user(Username, Password) VALUES('%s','%s')", credentials.getUsername(), hashedString);
            return stmt.executeUpdate(sql);
        }catch(Exception e){
            System.out.println("Error CredentialsBean.saveCredentials: " + e.getMessage());
            return 0;
        }
    }

}
