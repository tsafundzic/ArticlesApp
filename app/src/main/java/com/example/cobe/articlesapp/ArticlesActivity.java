package com.example.cobe.articlesapp;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.cobe.articlesapp.ui.addArticle.AddArticleActivity;

public class ArticlesActivity extends AppCompatActivity implements View.OnClickListener {

    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);

        setUI();
    }

    private void setUI() {
        floatingActionButton = findViewById(R.id.fabAddNewArticle);
        floatingActionButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        startActivity(AddArticleActivity.getLaunchIntent(this));
    }
}
