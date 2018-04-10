package com.example.cobe.articlesapp.presentation.implementation;

import com.example.cobe.articlesapp.interaction.ArticleInteractorInterface;
import com.example.cobe.articlesapp.model.Article;
import com.example.cobe.articlesapp.presentation.ArticleDetailsInterface;

/**
 * Created by cobe on 09/04/2018.
 */

public class ArticleDetailsPresenterImpl implements ArticleDetailsInterface.Presenter {

    private ArticleDetailsInterface.View view;
    private final ArticleInteractorInterface articleInteractor;
    private int articleId;

    public ArticleDetailsPresenterImpl(ArticleInteractorInterface articleInteractor) {
        this.articleInteractor = articleInteractor;
    }

    @Override
    public void setView(ArticleDetailsInterface.View view) {
        this.view = view;
    }

    @Override
    public void getArticle(int id) {
        this.articleId = id;

        showData();
    }

    private void showData() {
        Article article = articleInteractor.getArticle(articleId);
        if (article != null) {
            view.showAuthor(article.getAuthor());
            view.showTitle(article.getTitle());
            view.showDescription(article.getDescription());
            view.showType(article.getType());
        }
    }

    @Override
    public void onEditTapped() {
        view.startEdit(articleId);
    }

    @Override
    public void refreshData() {
        showData();
    }
}
