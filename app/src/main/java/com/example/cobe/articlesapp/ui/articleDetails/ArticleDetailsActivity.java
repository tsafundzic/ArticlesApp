package com.example.cobe.articlesapp.ui.articleDetails;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.cobe.articlesapp.App;
import com.example.cobe.articlesapp.R;
import com.example.cobe.articlesapp.database.DatabaseInterface;
import com.example.cobe.articlesapp.model.Article;
import com.example.cobe.articlesapp.presentation.ArticleDetailsInterface;
import com.example.cobe.articlesapp.presentation.implementation.ArticleDetailsPresenterImpl;
import com.example.cobe.articlesapp.ui.editArticle.EditArticleActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ArticleDetailsActivity extends AppCompatActivity implements ArticleDetailsInterface.View {

    private final DatabaseInterface database = App.getInstance().getDatabase();
    private int id;

    @BindView(R.id.tvDetailTitle)
    TextView title;

    @BindView(R.id.tvDetailAuthor)
    TextView author;

    @BindView(R.id.tvDetailType)
    TextView type;

    @BindView(R.id.tvDetailDescription)
    TextView description;

    private ArticleDetailsInterface.Presenter presenter;

    private static final String ID = "ID";

    public static Intent getLaunchIntent(Context from, int id) {
        Intent intent = new Intent(from, ArticleDetailsActivity.class);
        intent.putExtra(ID, id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_details);

        ButterKnife.bind(this);

        presenter = new ArticleDetailsPresenterImpl();
        presenter.setView(this);
        receiveArticleID();
        setText();
    }

    @Override
    protected void onResume() {
        setText();
        super.onResume();
    }

    @OnClick(R.id.EditArticle)
    public void startEditing() {
        startActivity(EditArticleActivity.getLaunchIntent(this, id));
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
        id = intent.getIntExtra(ID, 0);
    }

    @OnClick(R.id.backToHome)
    public void goBack() {
        onBackPressed();
    }

}
