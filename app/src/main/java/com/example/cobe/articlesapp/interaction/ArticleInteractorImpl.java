package com.example.cobe.articlesapp.interaction;

import com.example.cobe.articlesapp.App;
import com.example.cobe.articlesapp.database.DatabaseInterface;
import com.example.cobe.articlesapp.model.Article;

import java.util.List;

/**
 * Created by cobe on 10/04/2018.
 */

public class ArticleInteractorImpl implements ArticleInteractorInterface {

    private final DatabaseInterface database = App.getInstance().getDatabase();

    @Override
    public void addNewArticle(String author, String title, String description, String type) {
        List<Article> articles = database.getArticles();
        int id;

        if (articles.size() != 0) {
            id = articles.get(articles.size() - 1).getId() + 1;
        } else {
            id = 0;
        }

        Article article = new Article(id, author, title, description, type);
        database.addArticle(article);
    }

    @Override
    public void updateArticle(int id, String author, String title, String description, String type) {
        Article article = new Article(id, author, title, description, type);
        database.addArticle(article);
    }

    @Override
    public Article getArticle(int id) {
        return database.getArticleById(id);
    }
}
