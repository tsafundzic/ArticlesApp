package com.example.cobe.articlesapp.ui.articleDetails;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.cobe.articlesapp.R;
import com.example.cobe.articlesapp.common.constants.ArticleType;
import com.example.cobe.articlesapp.interaction.ArticleInteractorImpl;
import com.example.cobe.articlesapp.interaction.ArticleInteractorInterface;
import com.example.cobe.articlesapp.presentation.ArticleDetailsInterface;
import com.example.cobe.articlesapp.presentation.implementation.ArticleDetailsPresenterImpl;
import com.example.cobe.articlesapp.ui.editArticle.EditArticleActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ArticleDetailsActivity extends AppCompatActivity implements ArticleDetailsInterface.View {

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

        injectDependencies();
        receiveArticleID();
    }

    @Override
    protected void onResume() {
        presenter.refreshData();
        super.onResume();
    }

    private void injectDependencies() {
        ArticleInteractorInterface articleInteractor = new ArticleInteractorImpl();
        presenter = new ArticleDetailsPresenterImpl(articleInteractor);
        presenter.setView(this);
    }

    private void receiveArticleID() {
        Intent intent = getIntent();
        presenter.getArticle(intent.getIntExtra(ID, 0));
    }

    @OnClick(R.id.EditArticle)
    public void startEditing() {
        presenter.onEditTapped();
    }

    @OnClick(R.id.backToHome)
    public void goBack() {
        finish();
    }


    @Override
    public void showAuthor(String authorText) {
        author.setText(String.format(getString(R.string.author_format), authorText));
    }

    @Override
    public void showTitle(String titleText) {
        title.setText(titleText);
    }

    @Override
    public void showDescription(String descriptionText) {
        description.setText(descriptionText);
    }

    @Override
    public void showType(ArticleType typeText) {
        type.setText(String.format(getString(R.string.type_format), typeText));
    }

    @Override
    public void startEdit(int articleId) {
        startActivity(EditArticleActivity.getLaunchIntent(this, articleId));
    }
}
