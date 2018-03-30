package com.example.cobe.articlesapp.database;

import com.example.cobe.articlesapp.model.Article;

import java.util.List;

import io.realm.Realm;

/**
 * Created by cobe on 27/03/2018.
 */

public class DatabaseHelper implements DatabaseInterface {

    private final Realm realm;

    public DatabaseHelper(Realm realm) {
        this.realm = realm;
    }

    @Override
    public void addArticle(Article article) {
        if (article != null) {
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(article);
            realm.commitTransaction();
        }
    }

    @Override
    public void deleteArticle(int id) {
        Article article = getArticleById(id);

        if (article != null) {
            realm.beginTransaction();
            article.deleteFromRealm();
            realm.commitTransaction();
        }
    }

    @Override
    public boolean isEmpty() {
        return realm.isEmpty();
    }

    @Override
    public List<Article> getArticles() {
        return realm.copyFromRealm(realm.where(Article.class).findAll());
    }

    @Override
    public Article getArticleById(int id) {
        Article article = realm.where(Article.class).equalTo("id", id).findFirst();

        if (article != null) {
            return realm.copyFromRealm(article);
        } else {
            return null;
        }
    }
}
