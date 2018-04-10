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

        Article article = new Article(getNewArticleId(articles), author, title, description, type);
        database.addArticle(article);
    }

    private int getNewArticleId(List<Article> articles) {
        int size = articles.size();

        return size != 0 ? (articles.get(size - 1).getId() + 1) : 0;
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

    @Override
    public List<Article> getArticles() {
        return database.getArticles();
    }

    @Override
    public void deleteArticle(int id) {
        database.deleteArticle(id);
    }
}
