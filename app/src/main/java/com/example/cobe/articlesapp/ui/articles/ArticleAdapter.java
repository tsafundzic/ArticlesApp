package com.example.cobe.articlesapp.ui.articles;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cobe.articlesapp.R;
import com.example.cobe.articlesapp.model.Article;
import com.example.cobe.articlesapp.ui.listeners.OnArticleClickListener;
import com.example.cobe.articlesapp.ui.listeners.OnArticleLongClickListener;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by cobe on 27/03/2018.
 */

public class ArticleAdapter extends RecyclerView.Adapter<ArticleViewHolder> {

    private final List<Article> articles = new ArrayList<>();

    private OnArticleClickListener onArticleClickListener;
    private OnArticleLongClickListener onArticleLongClickListener;

    public void setOnArticleClickListener(OnArticleClickListener onArticleClickListener) {
        this.onArticleClickListener = onArticleClickListener;
    }

    public void setOnArticleLongClickListener(OnArticleLongClickListener onArticleLongClickListener) {
        this.onArticleLongClickListener = onArticleLongClickListener;
    }

    public void setArticles(List<Article> articles) {
        this.articles.clear();
        this.articles.addAll(articles);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_list_item, parent, false);
        return new ArticleViewHolder(view, onArticleClickListener, onArticleLongClickListener);
    }


    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        Article article = articles.get(position);
        if (article != null) {
            holder.setArticle(article);
        }
    }

    public void removeArticle(int id) {
        int articleIndex = -1;

        for (Article article : articles) {
            if (article.getId() == id) {
                articleIndex = articles.indexOf(article);
            }
        }

        if (articleIndex != -1) {
            articles.remove(articleIndex);
            notifyItemRemoved(articleIndex);
        }
    }
}
