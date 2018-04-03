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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditArticleActivity extends AppCompatActivity {

    private final DatabaseInterface database = App.getInstance().getDatabase();

    private Article article;
    int id;

    @BindView(R.id.etEditAuthor)
    EditText author;
    @BindView(R.id.etEditTitle)
    EditText title;
    @BindView(R.id.etEditDescription)
    EditText description;
    @BindView(R.id.spEditTypes)
    Spinner type;
    @BindView(R.id.back)
    View back;
    @BindView(R.id.saveChanges)
    View saveChanges;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_article);

        ButterKnife.bind(this);

        receiveArticleID();
        showData();
    }

    @OnClick(R.id.back)
    public void goBack() {
        onBackPressed();
    }

    @OnClick(R.id.saveChanges)
    public void updateData() {
        String selectedType = type.getItemAtPosition(type.getSelectedItemPosition()).toString();
        Article article = new Article(id, author.getText().toString(), title.getText().toString(), description.getText().toString(), selectedType);
        database.addArticle(article);

        Toast.makeText(this, getString(R.string.updated_successuful), Toast.LENGTH_SHORT).show();
        finish();
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

    public static Intent getLaunchIntent(Context from, int id) {
        Intent intent = new Intent(from, EditArticleActivity.class);
        intent.putExtra("ID", id);
        return intent;
    }
}
