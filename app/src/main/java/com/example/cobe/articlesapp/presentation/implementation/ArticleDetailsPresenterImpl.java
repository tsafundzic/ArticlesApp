package com.example.cobe.articlesapp.presentation.implementation;

import com.example.cobe.articlesapp.presentation.ArticleDetailsInterface;

/**
 * Created by cobe on 09/04/2018.
 */

public class ArticleDetailsPresenterImpl implements ArticleDetailsInterface.Presenter {

    private ArticleDetailsInterface.View view;

    public ArticleDetailsPresenterImpl(){

    }

    @Override
    public void setView(ArticleDetailsInterface.View view) {
        this.view = view;
    }
}
