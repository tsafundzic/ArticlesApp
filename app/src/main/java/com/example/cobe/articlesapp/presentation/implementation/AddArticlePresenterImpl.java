package com.example.cobe.articlesapp.presentation.implementation;

import com.example.cobe.articlesapp.App;
import com.example.cobe.articlesapp.common.ValidationUtils;
import com.example.cobe.articlesapp.database.DatabaseInterface;
import com.example.cobe.articlesapp.model.Article;
import com.example.cobe.articlesapp.presentation.AddArticleInterface;

import java.util.List;

/**
 * Created by cobe on 09/04/2018.
 */

public class AddArticlePresenterImpl implements AddArticleInterface.Presenter {

    private AddArticleInterface.View view;
    private final DatabaseInterface database = App.getInstance().getDatabase();

    public AddArticlePresenterImpl() {

    }

    @Override
    public void setView(AddArticleInterface.View view) {
        this.view = view;
    }

    @Override
    public void onArticleAdd(String author, String title, String description, String type) {
        if (ValidationUtils.isEmpty(author)) {
            view.setAuthorError();
        } else if (ValidationUtils.isEmpty(title)) {
            view.setTitleError();
        } else if (ValidationUtils.isEmpty(description)) {
            view.setDescriptionError();
        } else {
            List<Article> articles = database.getArticles();
            int id;
            if (articles.size() != 0) {
                id = articles.get(articles.size() - 1).getId() + 1;
            } else {
                id = 0;
            }
            Article article = new Article(id, author, title, description, type);
            database.addArticle(article);
            view.finished();
        }
    }
}
