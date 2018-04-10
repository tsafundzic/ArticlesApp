package com.example.cobe.articlesapp.presentation.implementation;

import com.example.cobe.articlesapp.interaction.ArticleInteractorInterface;
import com.example.cobe.articlesapp.model.Article;
import com.example.cobe.articlesapp.presentation.ArticlesListInterface;

import java.util.List;

/**
 * Created by cobe on 10/04/2018.
 */

public class ArticleListPresenterImpl implements ArticlesListInterface.Presenter {

    private ArticlesListInterface.View view;
    private final ArticleInteractorInterface articleInteractor;

    public ArticleListPresenterImpl(ArticleInteractorInterface articleInteractor) {
        this.articleInteractor = articleInteractor;
    }

    @Override
    public void getArticles() {
        showData();
    }

    private void showData() {
        List<Article> articles = articleInteractor.getArticles();
        if (articles != null) {
            if (articles.size() != 0) {
                view.setList(articles);
            } else {
                view.setEmptyList(articles);
            }
        }
    }

    @Override
    public void onItemLongClick(int id) {
        view.startItemDelete(articleInteractor.getArticle(id));
    }

    @Override
    public void refreshData() {
        showData();
    }

    @Override
    public void deleteSelectedArticle(int id) {
        articleInteractor.deleteArticle(id);
    }

    @Override
    public void setView(ArticlesListInterface.View view) {
        this.view = view;
    }
}
