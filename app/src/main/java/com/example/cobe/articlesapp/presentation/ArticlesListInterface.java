package com.example.cobe.articlesapp.presentation;

import com.example.cobe.articlesapp.model.Article;

import java.util.List;

/**
 * Created by cobe on 10/04/2018.
 */

public interface ArticlesListInterface {

    interface View {

        void setList(List<Article> articles);

        void setEmptyList(List<Article> articles);

        void startItemDelete(Article article);

    }

    interface Presenter extends BasePresenter<View> {

        void getArticles();

        void onItemLongClick(int id);

        void refreshData();

        void deleteSelectedArticle(int id);
    }
}
