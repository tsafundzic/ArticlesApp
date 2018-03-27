package com.example.cobe.articlesapp.ui.articles;


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

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticlesListFragment extends Fragment implements OnArticleClickListener {

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

    private void loadArticles() {
        List<Article> articles = DBHelper.getInstance().loadArticles();
        adapter.setArticles(articles);
    }

    private void setAdapter() {
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ArticleAdapter();
        adapter.setOnarticleClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    private void setUI(View view) {
        recyclerView = view.findViewById(R.id.rvArticleList);
    }

    @Override
    public void onArticleClick(int id) {
        startActivity(ArticleDetailsActivity.getLauchIntent(getActivity(), id));
    }
}
