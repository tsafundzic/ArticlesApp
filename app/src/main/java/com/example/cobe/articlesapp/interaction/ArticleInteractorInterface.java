package com.example.cobe.articlesapp.interaction;

import com.example.cobe.articlesapp.model.Article;

/**
 * Created by cobe on 10/04/2018.
 */

public interface ArticleInteractorInterface {

    void addNewArticle(String author, String title, String description, String type);

    void updateArticle(int id, String author, String title, String description, String type);

    Article getArticle(int id);

}
