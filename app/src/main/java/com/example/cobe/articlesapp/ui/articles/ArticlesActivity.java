package com.example.cobe.articlesapp.ui.articles;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.cobe.articlesapp.App;
import com.example.cobe.articlesapp.R;
import com.example.cobe.articlesapp.database.DatabaseInterface;
import com.example.cobe.articlesapp.ui.addArticle.AddArticleActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

public class ArticlesActivity extends AppCompatActivity  {

    @BindView(R.id.fabAddNewArticle)
    View floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);

        ButterKnife.bind(this);

        checkIfDatabaseIsEmpty();
    }

    @Override
    protected void onResume() {
        checkIfDatabaseIsEmpty();
        super.onResume();
    }

    public void checkIfDatabaseIsEmpty() {
        Fragment fragment = new ArticlesListFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.articleList, fragment);
        fragmentTransaction.commit();

    }

    @OnClick(R.id.fabAddNewArticle)
    public void startAddNewArticle() {
        startActivity(AddArticleActivity.getLaunchIntent(this));
    }
}
