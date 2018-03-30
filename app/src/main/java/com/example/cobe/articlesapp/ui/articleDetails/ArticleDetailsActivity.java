package com.example.cobe.articlesapp.ui.articleDetails;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cobe.articlesapp.R;
import com.example.cobe.articlesapp.database.DatabaseHelper;
import com.example.cobe.articlesapp.model.Article;
import com.example.cobe.articlesapp.ui.editArticle.EditArticleActivity;

public class ArticleDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    Article article;
    TextView title;
    TextView author;
    TextView type;
    TextView description;
    ImageView back;
    ImageView editArticle;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_details);

        setUI();
        recieverArticleID();
        setText();

    }

    @Override
    protected void onResume() {
        setText();
        super.onResume();
    }

    private void setText() {
        title.setText(article.getTitle());
        author.setText(String.format(getString(R.string.author_format), article.getAuthor()));
        type.setText(String.format(getString(R.string.type_format), article.getType()));
        description.setText(article.getDescription());
    }

    private void recieverArticleID() {
        Intent intent = getIntent();
        id = intent.getIntExtra("ID", 0);
        article = DatabaseHelper.getInstance().returnArticleBasedOnID(id);
    }

    private void setUI() {
        title = findViewById(R.id.tvDetailTitle);
        author = findViewById(R.id.tvDetailAuthor);
        type = findViewById(R.id.tvDetailType);
        description = findViewById(R.id.tvDetailDescription);
        back = findViewById(R.id.ivDetailBack);
        editArticle = findViewById(R.id.ivDetailEditArticle);

        back.setOnClickListener(this);
        editArticle.setOnClickListener(this);

    }

    public static Intent getLauchIntent(Context from, int id) {
        Intent intent = new Intent(from, ArticleDetailsActivity.class);
        intent.putExtra("ID", id);
        return intent;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ivDetailBack:
                onBackPressed();
                break;
            case R.id.ivDetailEditArticle:
                startEditing();
                break;
        }
    }

    private void startEditing() {
        startActivity(EditArticleActivity.getLauchIntent(getApplicationContext(), id));
    }
}
