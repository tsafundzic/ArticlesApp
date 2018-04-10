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

import com.example.cobe.articlesapp.R;
import com.example.cobe.articlesapp.common.DialogUtils;
import com.example.cobe.articlesapp.interaction.ArticleInteractorImpl;
import com.example.cobe.articlesapp.interaction.ArticleInteractorInterface;
import com.example.cobe.articlesapp.model.Article;
import com.example.cobe.articlesapp.presentation.ArticlesListInterface;
import com.example.cobe.articlesapp.presentation.implementation.ArticleListPresenterImpl;
import com.example.cobe.articlesapp.ui.articleDetails.ArticleDetailsActivity;
import com.example.cobe.articlesapp.listeners.OnArticleClickListener;
import com.example.cobe.articlesapp.listeners.OnArticleLongClickListener;
import com.example.cobe.articlesapp.listeners.OnDeleteArticleListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticlesListFragment extends Fragment implements OnArticleClickListener, OnArticleLongClickListener, OnDeleteArticleListener, ArticlesListInterface.View {

    private final ArticleAdapter adapter = new ArticleAdapter();

    @BindView(R.id.articles)
    RecyclerView recyclerView;

    @BindView(R.id.emptyStateView)
    TextView emptyStateView;

    private ArticlesListInterface.Presenter presenter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_articles_list, container, false);

        ButterKnife.bind(this, view);

        injectDependencies();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setAdapter();
        presenter.getArticles();
    }

    private void injectDependencies() {
        ArticleInteractorInterface articleInteractor = new ArticleInteractorImpl();
        presenter = new ArticleListPresenterImpl(articleInteractor);
        presenter.setView(this);
    }

    @Override
    public void onResume() {
        presenter.refreshData();
        super.onResume();
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
        presenter.onItemLongClick(id);
    }

    @Override
    public void deleteArticle(int id) {
        presenter.deleteSelectedArticle(id);
        adapter.removeArticle(id);
    }

    @Override
    public void setList(List<Article> articles) {
        adapter.setArticles(articles);
        adapter.notifyDataSetChanged();

        recyclerView.setVisibility(View.VISIBLE);
        emptyStateView.setVisibility(View.GONE);
    }

    @Override
    public void setEmptyList(List<Article> articles) {
        adapter.setArticles(articles);
        adapter.notifyDataSetChanged();

        recyclerView.setVisibility(View.GONE);
        emptyStateView.setVisibility(View.VISIBLE);
    }

    @Override
    public void startItemDelete(Article article) {
        DialogUtils.showDeleteArticleDialog(getActivity(), article, this);
    }
}
