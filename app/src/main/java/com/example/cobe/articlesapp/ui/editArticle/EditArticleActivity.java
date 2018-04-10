package com.example.cobe.articlesapp.ui.editArticle;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cobe.articlesapp.R;
import com.example.cobe.articlesapp.common.constants.ArticleType;
import com.example.cobe.articlesapp.interaction.ArticleInteractorImpl;
import com.example.cobe.articlesapp.interaction.ArticleInteractorInterface;
import com.example.cobe.articlesapp.presentation.EditArticleInterface;
import com.example.cobe.articlesapp.presentation.implementation.EditArticlePresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditArticleActivity extends AppCompatActivity implements EditArticleInterface.View {

    @BindView(R.id.etEditAuthor)
    EditText author;

    @BindView(R.id.etEditTitle)
    EditText title;

    @BindView(R.id.etEditDescription)
    EditText description;

    @BindView(R.id.spEditTypes)
    Spinner type;

    private static final String ID = "ID";

    private EditArticleInterface.Presenter presenter;

    public static Intent getLaunchIntent(Context from, int id) {
        Intent intent = new Intent(from, EditArticleActivity.class);
        intent.putExtra(ID, id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_article);

        ButterKnife.bind(this);

        injectDependencies();
        receiveArticleID();
    }

    private void injectDependencies() {
        ArticleInteractorInterface articleInteractor = new ArticleInteractorImpl();
        presenter = new EditArticlePresenterImpl(articleInteractor);
        presenter.setView(this);
    }

    private void receiveArticleID() {
        Intent intent = getIntent();
        presenter.getArticle(intent.getIntExtra(ID, 0));
    }

    @OnClick(R.id.saveChanges)
    public void updateData() {
        presenter.onUpdateTapped();
    }

    @OnClick(R.id.back)
    public void goBack() {
        finish();
    }


    @Override
    public void setAuthor(String authorText) {
        author.setText(authorText);
    }

    @Override
    public void setTitle(String titleText) {
        title.setText(titleText);
    }

    @Override
    public void setDescription(String descriptionText) {
        description.setText(descriptionText);
    }

    @Override
    public void setType(ArticleType typeText) {
        type.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, ArticleType.values()));
        type.setSelection(ArticleType.getTypeIndex(typeText));
    }

    @Override
    public void startUpdate(int articleID) {
        presenter.editArticle(articleID, author.getText().toString(), title.getText().toString(), description.getText().toString(), type.getItemAtPosition(type.getSelectedItemPosition()).toString());
        Toast.makeText(this, getString(R.string.updated_successuful), Toast.LENGTH_SHORT).show();
        finish();
    }
}
