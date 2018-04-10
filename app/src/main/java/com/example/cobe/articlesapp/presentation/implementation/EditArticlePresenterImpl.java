package com.example.cobe.articlesapp.presentation.implementation;

import com.example.cobe.articlesapp.interaction.ArticleInteractorInterface;
import com.example.cobe.articlesapp.model.Article;
import com.example.cobe.articlesapp.presentation.EditArticleInterface;

/**
 * Created by cobe on 10/04/2018.
 */

public class EditArticlePresenterImpl implements EditArticleInterface.Presenter {

    private final ArticleInteractorInterface articleInteractor;
    private EditArticleInterface.View view;

    private int articleId;

    public EditArticlePresenterImpl(ArticleInteractorInterface articleInteractor) {
        this.articleInteractor = articleInteractor;
    }

    @Override
    public void setView(EditArticleInterface.View view) {
        this.view = view;
    }

    @Override
    public void getArticle(int id) {
        this.articleId = id;

        showData();
    }

    private void showData() {
        Article article = articleInteractor.getArticle(articleId);

        view.setAuthor(article.getAuthor());
        view.setTitle(article.getTitle());
        view.setDescription(article.getDescription());
        view.setType(article.getType());

    }

    @Override
    public void editArticle(int id, String author, String title, String description, String type) {
        articleInteractor.updateArticle(id, author, title, description, type);
    }

    @Override
    public void onUpdateTapped() {
        view.startUpdate(articleId);
    }
}
