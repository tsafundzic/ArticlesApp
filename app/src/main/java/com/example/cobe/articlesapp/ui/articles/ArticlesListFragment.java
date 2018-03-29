package com.example.cobe.articlesapp.ui.articles;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cobe.articlesapp.R;
import com.example.cobe.articlesapp.common.DBHelper;
import com.example.cobe.articlesapp.model.Article;
import com.example.cobe.articlesapp.ui.listeners.OnArticleClickListener;
import com.example.cobe.articlesapp.ui.articleDetails.ArticleDetailsActivity;
import com.example.cobe.articlesapp.ui.listeners.OnArticleLongClickListener;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticlesListFragment extends Fragment implements OnArticleClickListener, OnArticleLongClickListener {

    RecyclerView recyclerView;
    ArticleAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

    public ArticlesListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_articles_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUI(view);
        setAdapter();
        loadArticles();
    }

    @Override
    public void onResume() {
        loadArticles();
        super.onResume();
    }

    private void loadArticles() {
        List<Article> articles = DBHelper.getInstance().loadArticles();
        adapter.setArticles(articles);
        adapter.notifyDataSetChanged();
        if (articles.size() == 0) {
            ((ArticlesActivity)getActivity()).checkIfDatabaseIsEmpty();
        }
    }

    private void setAdapter() {
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ArticleAdapter();
        adapter.setOnArticleClickListener(this);
        adapter.setOnArticleLongClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    private void setUI(View view) {
        recyclerView = view.findViewById(R.id.rvArticleList);
    }

    @Override
    public void onArticleClick(int id) {
        startActivity(ArticleDetailsActivity.getLauchIntent(getActivity(), id));
    }

    @Override
    public void onArticleLongClick(int id) {
        startDialog(id);
    }

    public void startDialog(final int articleIdForRemove) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(getString(R.string.are_you_sure))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DBHelper.getInstance().deleteArticle(articleIdForRemove);
                        loadArticles();
                    }
                })
                .setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
