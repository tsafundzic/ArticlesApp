package com.example.cobe.articlesapp.common;

import com.example.cobe.articlesapp.model.Article;

import java.util.List;

import io.realm.Realm;

/**
 * Created by cobe on 27/03/2018.
 */

public class DBHelper {
    private Realm realm;

    private static DBHelper instance = new DBHelper(Realm.getDefaultInstance());

    private DBHelper(Realm realm) {
        this.realm = realm;
    }

    public static DBHelper getInstance() {
        return instance;
    }

    public void addArticle(Article article) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(article);
        realm.commitTransaction();
    }

    public List<Article> loadArticles() {
        return realm.copyFromRealm(realm.where(Article.class).findAll());
    }

    public Realm getRealm() {
        return realm;
    }

    public boolean isRealmEmpty() {
        return realm.isEmpty();
    }

    public Article returnArticleBasedOnID(int id) {
        return realm.where(Article.class).equalTo("id", id).findFirst();
    }

    public void deleteArticle(int id) {
        realm.beginTransaction();
        Article article = realm.where(Article.class).equalTo("id", id).findFirst();
        if (article != null) {
            article.deleteFromRealm();
        } else
            realm.commitTransaction();
    }

}
