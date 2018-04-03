package com.example.cobe.articlesapp.database;

import com.example.cobe.articlesapp.model.Article;

import java.util.List;

/**
 * Created by cobe on 30/03/2018.
 */

public interface DatabaseInterface {

    List<Article> getArticles();

    Article getArticleById(int id);

    void deleteArticle(int id);

    void addArticle(Article article);
}
