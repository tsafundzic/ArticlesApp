package com.example.cobe.articlesapp.ui.articles;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cobe.articlesapp.App;
import com.example.cobe.articlesapp.R;
import com.example.cobe.articlesapp.common.DialogUtils;
import com.example.cobe.articlesapp.database.DatabaseInterface;
import com.example.cobe.articlesapp.model.Article;
import com.example.cobe.articlesapp.ui.articleDetails.ArticleDetailsActivity;
import com.example.cobe.articlesapp.ui.listeners.OnArticleClickListener;
import com.example.cobe.articlesapp.ui.listeners.OnArticleLongClickListener;
import com.example.cobe.articlesapp.ui.listeners.OnDeleteArticleListener;

import java.util.List;

import io.realm.Realm;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticlesListFragment extends Fragment implements OnArticleClickListener, OnArticleLongClickListener, OnDeleteArticleListener {

    private final ArticleAdapter adapter = new ArticleAdapter();
    private final DatabaseInterface database = App.getInstance().getDatabase();

    private RecyclerView recyclerView;
    private TextView emptyStateView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_articles_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUI(view);
        setAdapter();
        loadArticles();
    }

    private void setUI(View view) {
        recyclerView = view.findViewById(R.id.articles);
        emptyStateView = view.findViewById(R.id.emptyStateView);
    }

    @Override
    public void onResume() {
        loadArticles();
        super.onResume();
    }

    private void loadArticles() {
        List<Article> articles = database.getArticles();
        adapter.setArticles(articles);
        adapter.notifyDataSetChanged();
        if (articles.size() == 0) {
            recyclerView.setVisibility(View.GONE);
            emptyStateView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyStateView.setVisibility(View.GONE);
        }
    }

    private void setAdapter() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        adapter.setOnArticleClickListener(this);
        adapter.setOnArticleLongClickListener(this);
    }

    @Override
    public void onArticleClick(int id) {
        startActivity(ArticleDetailsActivity.getLaunchIntent(getActivity(), id));
    }

    @Override
    public void onArticleLongClick(int id) {
        DialogUtils.showDeleteArticleDialog(getActivity(), database.getArticleById(id), this);
    }

    @Override
    public void deleteArticle(int id) {
        database.deleteArticle(id);
        adapter.removeArticle(id);
    }
}
