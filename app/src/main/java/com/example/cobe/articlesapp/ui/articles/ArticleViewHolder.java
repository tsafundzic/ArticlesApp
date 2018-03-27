package com.example.cobe.articlesapp.ui.articles;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.cobe.articlesapp.R;
import com.example.cobe.articlesapp.common.DBHelper;
import com.example.cobe.articlesapp.model.Article;
import com.example.cobe.articlesapp.ui.listeners.OnArticleClickListener;

import java.util.List;

/**
 * Created by cobe on 27/03/2018.
 */

public class ArticleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView author;
    TextView title;
    private OnArticleClickListener onArticleClickListener;
    private List<Article> articles = DBHelper.getInstance().loadArticles();

    public ArticleViewHolder(View itemView, OnArticleClickListener onArticleClickListener) {
        super(itemView);
        author = itemView.findViewById(R.id.tvArticleAuthor);
        title = itemView.findViewById(R.id.tvArticleTitle);
        this.onArticleClickListener = onArticleClickListener;
        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        onArticleClickListener.onArticleClick(articles.get(getAdapterPosition()).getId());
    }
}
