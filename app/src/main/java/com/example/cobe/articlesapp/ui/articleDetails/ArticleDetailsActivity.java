package com.example.cobe.articlesapp.ui.articleDetails;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.cobe.articlesapp.App;
import com.example.cobe.articlesapp.R;
import com.example.cobe.articlesapp.common.constants.ArticleType;
import com.example.cobe.articlesapp.database.DatabaseInterface;
import com.example.cobe.articlesapp.model.Article;
import com.example.cobe.articlesapp.ui.editArticle.EditArticleActivity;

public class ArticleDetailsActivity extends AppCompatActivity implements View.OnClickListener {


    private final DatabaseInterface database = App.getInstance().getDatabase();

    private TextView title;
    private TextView author;
    private TextView type;
    private TextView description;
    private int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_details);

        setUI();
        receiveArticleID();
        setText();

    }

    @Override
    protected void onResume() {
        setText();
        super.onResume();
    }

    private void setText() {
        Article article = database.getArticleById(id);

        if (article != null) {
            title.setText(article.getTitle());
            author.setText(String.format(getString(R.string.author_format), article.getAuthor()));
            type.setText(String.format(getString(R.string.type_format), article.getType().toString()));
            description.setText(article.getDescription());
        }
    }

    private void receiveArticleID() {
        Intent intent = getIntent();
        id = intent.getIntExtra("ID", 0);
    }

    private void setUI() {
        title = findViewById(R.id.tvDetailTitle);
        author = findViewById(R.id.tvDetailAuthor);
        type = findViewById(R.id.tvDetailType);
        description = findViewById(R.id.tvDetailDescription);
        View back = findViewById(R.id.backToHome);
        View editArticle = findViewById(R.id.EditArticle);

        back.setOnClickListener(this);
        editArticle.setOnClickListener(this);

    }

    public static Intent getLaunchIntent(Context from, int id) {
        Intent intent = new Intent(from, ArticleDetailsActivity.class);
        intent.putExtra("ID", id);
        return intent;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backToHome:
                onBackPressed();
                break;
            case R.id.EditArticle:
                startEditing();
                break;
        }
    }

    private void startEditing() {
        startActivity(EditArticleActivity.getLauchIntent(this, id));
    }
}
