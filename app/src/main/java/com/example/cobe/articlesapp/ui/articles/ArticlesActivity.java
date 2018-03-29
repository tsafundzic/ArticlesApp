package com.example.cobe.articlesapp.ui.articles;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.example.cobe.articlesapp.R;
import com.example.cobe.articlesapp.common.DBHelper;
import com.example.cobe.articlesapp.ui.addArticle.AddArticleActivity;

import io.realm.Realm;

public class ArticlesActivity extends AppCompatActivity implements View.OnClickListener {

    FloatingActionButton floatingActionButton;
    FrameLayout articleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);

        checkIfDatabaseIsEmpty();
        setUI();
    }

    @Override
    protected void onResume() {
        checkIfDatabaseIsEmpty();
        super.onResume();
    }

    private void checkIfDatabaseIsEmpty() {
        Realm.init(getApplicationContext());

        if (DBHelper.getInstance().isRealmEmpty()) {
            Fragment fragment = new ListIsEmptyFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.articleList, fragment);
            fragmentTransaction.commit();
        } else {
            Fragment fragment = new ArticlesListFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.articleList, fragment);
            fragmentTransaction.commit();
        }
    }

    private void setUI() {
        floatingActionButton = findViewById(R.id.fabAddNewArticle);
        floatingActionButton.setOnClickListener(this);
        articleList = findViewById(R.id.articleList);
    }

    @Override
    public void onClick(View view) {
        startActivity(AddArticleActivity.getLaunchIntent(this));
    }
}
