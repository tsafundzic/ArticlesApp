package com.example.cobe.articlesapp.ui.editArticle;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cobe.articlesapp.App;
import com.example.cobe.articlesapp.R;
import com.example.cobe.articlesapp.common.constants.ArticleType;
import com.example.cobe.articlesapp.database.DatabaseInterface;
import com.example.cobe.articlesapp.model.Article;

public class EditArticleActivity extends AppCompatActivity implements View.OnClickListener {

    private final DatabaseInterface database = App.getInstance().getDatabase();

    private EditText author;
    private EditText title;
    private EditText description;
    private Spinner type;
    private Article article;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_article);

        setUI();
        receiveArticleID();
        showData();
    }

    private void setUI() {
        author = findViewById(R.id.etEditAuthor);
        title = findViewById(R.id.etEditTitle);
        description = findViewById(R.id.etEditDescription);
        type = findViewById(R.id.spEditTypes);
        View save = findViewById(R.id.saveChanges);
        View back = findViewById(R.id.back);

        save.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    private void receiveArticleID() {
        Intent intent = getIntent();
        id = intent.getIntExtra("ID", 0);
        article = database.getArticleById(id);
    }

    private void showData() {
        author.setText(article.getAuthor());
        title.setText(article.getTitle());
        description.setText(article.getDescription());
        type.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, ArticleType.values()));
        type.setSelection(ArticleType.getTypeIndex(article.getType()));
    }

    public static Intent getLauchIntent(Context from, int id) {
        Intent intent = new Intent(from, EditArticleActivity.class);
        intent.putExtra("ID", id);
        return intent;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.saveChanges:
                updateData();
                Toast.makeText(this, getString(R.string.updated_successuful), Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.back:
                finish();
                break;
        }
    }

    private void updateData() {
        String selectedType = type.getItemAtPosition(type.getSelectedItemPosition()).toString();
        Article article = new Article(id, author.getText().toString(), title.getText().toString(), description.getText().toString(), selectedType);
        database.addArticle(article);
    }
}
