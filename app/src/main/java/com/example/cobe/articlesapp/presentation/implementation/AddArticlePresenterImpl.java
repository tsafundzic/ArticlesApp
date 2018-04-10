package com.example.cobe.articlesapp.presentation.implementation;

import com.example.cobe.articlesapp.common.ValidationUtils;
import com.example.cobe.articlesapp.interaction.ArticleInteractorInterface;
import com.example.cobe.articlesapp.model.Article;
import com.example.cobe.articlesapp.presentation.AddArticleInterface;


/**
 * Created by cobe on 09/04/2018.
 */

public class AddArticlePresenterImpl implements AddArticleInterface.Presenter {

    private AddArticleInterface.View view;
    private final ArticleInteractorInterface articleInteractor;

    private final Article article = new Article();

    public AddArticlePresenterImpl(ArticleInteractorInterface articleInteractor) {
        this.articleInteractor = articleInteractor;
    }

    @Override
    public void setView(AddArticleInterface.View view) {
        this.view = view;
    }

    public void onAuthorNameChanged(String authoName) {

        if (authoName.length() < 10) {
            view.setAuthorError();
        }else {
            //remove error
        }
    }

    @Override
    public void onAddTapped(String author, String title, String description, String type) {
        if (ValidationUtils.isEmpty(author)) {
            view.setAuthorError();
        } else if (ValidationUtils.isEmpty(title)) {
            view.setTitleError();
        } else if (ValidationUtils.isEmpty(description)) {
            view.setDescriptionError();
        } else {
            addNewArticle(author, title, description, type);
        }
    }

    private void addNewArticle(String author, String title, String description, String type) {
        articleInteractor.addNewArticle(author, title, description, type);
        view.onArticleAdded();
    }
}
