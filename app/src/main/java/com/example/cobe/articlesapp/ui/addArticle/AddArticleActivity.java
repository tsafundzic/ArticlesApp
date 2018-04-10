package com.example.cobe.articlesapp.ui.addArticle;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.cobe.articlesapp.R;
import com.example.cobe.articlesapp.common.constants.ArticleType;
import com.example.cobe.articlesapp.interaction.ArticleInteractorImpl;
import com.example.cobe.articlesapp.interaction.ArticleInteractorInterface;
import com.example.cobe.articlesapp.presentation.AddArticleInterface;
import com.example.cobe.articlesapp.presentation.implementation.AddArticlePresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class AddArticleActivity extends AppCompatActivity implements AddArticleInterface.View {

    @BindView(R.id.etAuthor)
    EditText author;

    @BindView(R.id.etTitle)
    EditText title;

    @BindView(R.id.etDescription)
    EditText description;

    @BindView(R.id.spTypes)
    Spinner type;

    private AddArticleInterface.Presenter presenter;

    public static Intent getLaunchIntent(Context from) {
        return new Intent(from, AddArticleActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_article);
        ButterKnife.bind(this);

        injectDependencies();
        setUI();
    }

    private void injectDependencies() {
        ArticleInteractorInterface articleInteractor = new ArticleInteractorImpl();
        presenter = new AddArticlePresenterImpl(articleInteractor);
        presenter.setView(this);
    }

    private void setUI() {
        type.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, ArticleType.values()));
    }

    @OnClick(R.id.saveNewArticle)
    public void saveNewArticle() {
        presenter.onAddTapped(author.getText().toString(), title.getText().toString(), description.getText().toString(), type.getItemAtPosition(type.getSelectedItemPosition()).toString());
    }

    @OnTextChanged(R.id.etAuthor)
    void onAuthorNameChanged(String authoName) {
//        presenter.onAuthorNameChanged(authoName);
    }

    @Override
    public void setAuthorError() {
        author.setError(getString(R.string.wrong_input));
    }

    @Override
    public void setTitleError() {
        title.setError(getString(R.string.wrong_input));
    }

    @Override
    public void setDescriptionError() {
        description.setError(getString(R.string.wrong_input));
    }

    @Override
    public void onArticleAdded() {
        finish();
    }

    @OnClick(R.id.backToDetails)
    public void goBack() {
        finish();
    }
}
