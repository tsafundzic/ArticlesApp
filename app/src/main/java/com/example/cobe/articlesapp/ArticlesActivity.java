package com.example.cobe.articlesapp;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ArticlesActivity extends AppCompatActivity {

    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);

        setUI();
    }

    private void setUI() {
        floatingActionButton=findViewById(R.id.fabAddNewArticle);
    }
}
