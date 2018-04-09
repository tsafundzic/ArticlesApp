package com.example.cobe.articlesapp.ui.articles;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.cobe.articlesapp.R;
import com.example.cobe.articlesapp.model.Article;
import com.example.cobe.articlesapp.listeners.OnArticleClickListener;
import com.example.cobe.articlesapp.listeners.OnArticleLongClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cobe on 27/03/2018.
 */

public class ArticleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    private OnArticleClickListener onArticleClickListener;
    private OnArticleLongClickListener onArticleLongClickListener;
    private int id;

    @BindView(R.id.tvArticleAuthor)
    TextView author;
    @BindView(R.id.tvArticleTitle)
    TextView title;

    ArticleViewHolder(View itemView, OnArticleClickListener onArticleClickListener, OnArticleLongClickListener onArticleLongClickListener) {
        super(itemView);
        this.onArticleClickListener = onArticleClickListener;
        this.onArticleLongClickListener = onArticleLongClickListener;

        ButterKnife.bind(this, itemView);
    }

    @Override
    public void onClick(View view) {
        onArticleClickListener.onArticleClick(id);
    }

    @Override
    public boolean onLongClick(View view) {
        onArticleLongClickListener.onArticleLongClick(id);
        return true;
    }

    public void setArticle(Article article) {
        this.id = article.getId();

        title.setText(article.getTitle());
        author.setText(article.getAuthor());

        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }
}
