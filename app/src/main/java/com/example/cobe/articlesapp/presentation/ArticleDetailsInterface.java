package com.example.cobe.articlesapp.presentation;


import com.example.cobe.articlesapp.common.constants.ArticleType;

/**
 * Created by cobe on 09/04/2018.
 */

public interface ArticleDetailsInterface {

    interface View {

        void showAuthor(String authorText);

        void showTitle(String titleText);

        void showDescription(String descriptionText);

        void showType(ArticleType typeText);

        void startEdit(int articleId);
    }

    interface Presenter extends BasePresenter<View> {

        void getArticle(int id);

        void onEditTapped();

        void refreshData();
    }
}
