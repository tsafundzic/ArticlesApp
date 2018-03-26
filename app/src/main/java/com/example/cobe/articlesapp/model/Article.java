package com.example.cobe.articlesapp.model;

/**
 * Created by cobe on 26/03/2018.
 */

public class Article {
    private static int id;
    private String author;
    private String title;
    private String description;
    private String type;

    public Article(String author, String title, String description, String type) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.type = type;
    }

}
