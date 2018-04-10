package com.example.cobe.articlesapp.presentation;


import com.example.cobe.articlesapp.common.constants.ArticleType;

/**
 * Created by cobe on 10/04/2018.
 */

public interface EditArticleInterface {

    interface View {

        void setAuthor(String authorText);

        void setTitle(String titleText);

        void setDescription(String descriptionText);

        void setType(ArticleType typeText);

        void startUpdate(int articleId);
    }

    interface Presenter extends BasePresenter<View> {

        void editArticle(int id, String author, String title, String description, String type);

        void onUpdateTapped();

        void getArticle(int id);
    }
}
