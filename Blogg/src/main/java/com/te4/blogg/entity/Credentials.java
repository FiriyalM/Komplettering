/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.te4.blogg.entity;

/**
 *
 * @author Elev
 */
public class Credentials {
    private String username;
    private String password;
    private int id;

    public Credentials(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public Credentials(String username, String password, int id) {
        this.username = username;
        this.password = password;
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
