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

import io.realm.Realm;

public class ArticlesActivity extends AppCompatActivity implements View.OnClickListener {

    private final DatabaseInterface database = App.getInstance().getDatabase();

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

    public void checkIfDatabaseIsEmpty() {
        Realm.init(getApplicationContext());

        if (database.isEmpty()) {
            Fragment fragment = new ListIsEmptyFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.articleList, fragment);
            fragmentTransaction.commit();
        } else {
            Fragment fragment = new ArticlesListFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction(); // TODO: 30/03/2018  promijeni u jedan fragment koji ima empty state view!
            fragmentTransaction.replace(R.id.articleList, fragment);
            fragmentTransaction.commit();
        }
    }

    private void setUI() {
        View floatingActionButton = findViewById(R.id.fabAddNewArticle);
        floatingActionButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        startActivity(AddArticleActivity.getLaunchIntent(this));
    }
}
