package com.example.cobe.articlesapp.presentation;

/**
 * Created by cobe on 09/04/2018.
 */

public interface AddArticleInterface {

    interface View {

        void setAuthorError();

        void setTitleError();

        void setDescriptionError();

        void finished();

    }

    interface Presenter extends BasePresenter<View> {

        void onArticleAdd(String author, String title, String description, String type);
    }
}
