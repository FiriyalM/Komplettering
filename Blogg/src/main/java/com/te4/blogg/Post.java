/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.te4.blogg;

/**
 *
 * @author Elev
 */
public class Post {
    public String title;
    public String content;
    public Post() {
    }

    public Post(String title, int likes, String content, String EmailUsername) {
        this.title = title;
        this.content = content;
     
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

  
}
