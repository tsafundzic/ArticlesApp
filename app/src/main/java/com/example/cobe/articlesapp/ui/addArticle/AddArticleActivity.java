package com.example.cobe.articlesapp.ui.addArticle;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.cobe.articlesapp.R;
import com.example.cobe.articlesapp.database.DatabaseHelper;
import com.example.cobe.articlesapp.common.ValidationUtils;
import com.example.cobe.articlesapp.model.Article;

import java.util.List;

import io.realm.Realm;

public class AddArticleActivity extends AppCompatActivity implements View.OnClickListener {

    EditText author;
    EditText title;
    EditText description;
    Spinner type;
    Button save;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_article);
        setUI();

        Realm.init(getApplicationContext());
    }

    private void setUI() {
        author = findViewById(R.id.etAuthor);
        title = findViewById(R.id.etTitle);
        description = findViewById(R.id.etDescription);
        type = findViewById(R.id.spTypes);
        save = findViewById(R.id.btnSave);
        back = findViewById(R.id.ivBack);
        back.setOnClickListener(this);
        save.setOnClickListener(this);
    }

    public static Intent getLaunchIntent(Context from) {
        return new Intent(from, AddArticleActivity.class);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                onBackPressed();
                break;
            case R.id.btnSave:
                checkIfInputsAreOK();
                break;
        }
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
        String selectedtype = type.getItemAtPosition(type.getSelectedItemPosition()).toString();
        List<Article> articles = DatabaseHelper.getInstance().loadArticles();
        int id;
        if (articles.size() != 0) {
            id = articles.get(articles.size() - 1).getId() + 1;
        } else {
            id = 0;
        }
        Article article = new Article(id, author.getText().toString(), title.getText().toString(), description.getText().toString(), selectedtype);
        DatabaseHelper.getInstance().addArticle(article);
        finish();
    }
}
