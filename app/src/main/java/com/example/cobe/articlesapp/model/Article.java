package com.example.cobe.articlesapp.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by cobe on 26/03/2018.
 */

public class Article extends RealmObject{

    @PrimaryKey
    private int id;
    private String author;
    private String title;
    private String description;
    private String type;

    public Article(){

    }

    public Article(int id, String author, String title, String description, String type) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.description = description;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
