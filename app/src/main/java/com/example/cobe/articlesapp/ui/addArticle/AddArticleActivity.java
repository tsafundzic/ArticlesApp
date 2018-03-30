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

public class AddArticleActivity extends AppCompatActivity implements View.OnClickListener {

    private final DatabaseInterface database = App.getInstance().getDatabase();

    private EditText author;
    private EditText title;
    private EditText description;
    private Spinner type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_article);
        setUI();
    }

    private void setUI() {
        author = findViewById(R.id.etAuthor);
        title = findViewById(R.id.etTitle);
        description = findViewById(R.id.etDescription);
        type = findViewById(R.id.spTypes);
        type.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, ArticleType.values()));
        View save = findViewById(R.id.saveNewArticle);
        View back = findViewById(R.id.backToDetails);
        back.setOnClickListener(this);
        save.setOnClickListener(this);
    }

    public static Intent getLaunchIntent(Context from) {
        return new Intent(from, AddArticleActivity.class);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backToDetails:
                onBackPressed();
                break;
            case R.id.saveNewArticle:
                checkIfInputsAreOK();
                break;
        }
    }

    private void checkIfInputsAreOK() {
        if (ValidationUtils.isEmpty(author.getText().toString()) || ValidationUtils.isEmpty(title.getText().toString()) || ValidationUtils.isEmpty(description.getText().toString())) {
            author.setError(getString(R.string.wrong_input));
            title.setError(getString(R.string.wrong_input));
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
