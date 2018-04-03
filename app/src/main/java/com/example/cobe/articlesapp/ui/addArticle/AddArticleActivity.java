package com.example.cobe.articlesapp.ui.addArticle;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.cobe.articlesapp.App;
import com.example.cobe.articlesapp.R;
import com.example.cobe.articlesapp.common.ValidationUtils;
import com.example.cobe.articlesapp.common.constants.ArticleType;
import com.example.cobe.articlesapp.database.DatabaseInterface;
import com.example.cobe.articlesapp.model.Article;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddArticleActivity extends AppCompatActivity {

    private final DatabaseInterface database = App.getInstance().getDatabase();

    @BindView(R.id.etAuthor)
    EditText author;
    @BindView(R.id.etTitle)
    EditText title;
    @BindView(R.id.etDescription)
    EditText description;
    @BindView(R.id.spTypes)
    Spinner type;
    @BindView(R.id.backToDetails)
    View back;
    @BindView(R.id.saveNewArticle)
    View saveNewArticle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_article);
        ButterKnife.bind(this);

        setUI();
    }

    private void setUI() {
        type.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, ArticleType.values()));
    }

    public static Intent getLaunchIntent(Context from) {
        return new Intent(from, AddArticleActivity.class);
    }

    @OnClick(R.id.backToDetails)
    public void goBack() {
        onBackPressed();
    }

    @OnClick(R.id.saveNewArticle)
    public void saveNewArticle() {
        checkIfInputsAreOK();
    }

    private void checkIfInputsAreOK() {
        if (ValidationUtils.isEmpty(author.getText().toString())) {
            author.setError(getString(R.string.wrong_input));
        } else if (ValidationUtils.isEmpty(title.getText().toString())) {
            title.setError(getString(R.string.wrong_input));
        } else if (ValidationUtils.isEmpty(description.getText().toString())) {
            description.setError(getString(R.string.wrong_input));
        } else {
            addNewArticle();
        }
    }

    private void addNewArticle() {
        String selectedType = type.getItemAtPosition(type.getSelectedItemPosition()).toString();
        List<Article> articles = database.getArticles();
        int id;
        if (articles.size() != 0) {
            id = articles.get(articles.size() - 1).getId() + 1;
        } else {
            id = 0;
        }

        Article article = new Article(id, author.getText().toString(), title.getText().toString(), description.getText().toString(), selectedType);
        database.addArticle(article);
        finish();
    }
}
