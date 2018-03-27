package com.example.cobe.articlesapp.ui.editArticle;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cobe.articlesapp.R;
import com.example.cobe.articlesapp.common.DBHelper;
import com.example.cobe.articlesapp.model.Article;

public class EditArticleActivity extends AppCompatActivity implements View.OnClickListener {

    EditText author;
    EditText title;
    EditText description;
    Spinner type;
    Button save;
    ImageView back;
    Article article;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_article);

        setUI();
        recieverArticleID();
        setTextToEditext();
    }

    private void setUI() {
        author = findViewById(R.id.etEditAuthor);
        title = findViewById(R.id.etEditTitle);
        description = findViewById(R.id.etEditDescription);
        type = findViewById(R.id.spEditTypes);
        save = findViewById(R.id.btnEditSave);
        back = findViewById(R.id.ivEditBack);

        save.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    private void recieverArticleID() {
        Intent intent = getIntent();
        id = intent.getIntExtra("ID", 0);
        article = DBHelper.getInstance().returnArticleBasedOnID(id);
    }

    private void setTextToEditext() {
        author.setText(article.getAuthor());
        title.setText(article.getTitle());
        description.setText(article.getDescription());
        for (int i = 0; i < type.getCount(); i++) {
            if (type.getItemAtPosition(i).equals(article.getType())) {
                type.setSelection(i);
            }
        }
    }

    public static Intent getLauchIntent(Context from, int id) {
        Intent intent = new Intent(from, EditArticleActivity.class);
        intent.putExtra("ID", id);
        return intent;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnEditSave:
                updateData();
                Toast.makeText(this, getString(R.string.updated_successuful), Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.ivEditBack:
                onBackPressed();
                break;
        }
    }

    private void updateData() {
        String selectedtype = type.getItemAtPosition(type.getSelectedItemPosition()).toString();
        Article article = new Article(id, author.getText().toString(), title.getText().toString(), description.getText().toString(), selectedtype);
        DBHelper.getInstance().addArticle(article);
    }
}
