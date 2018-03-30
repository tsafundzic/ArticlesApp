package com.example.cobe.articlesapp.common;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.example.cobe.articlesapp.R;
import com.example.cobe.articlesapp.model.Article;
import com.example.cobe.articlesapp.ui.listeners.OnDeleteArticleListener;

/**
 * Created by cobe on 30/03/2018.
 */

public class DialogUtils {

    public static void showDeleteArticleDialog(Context from, Article article, final OnDeleteArticleListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(from);
        builder.setMessage(from.getString(R.string.delete_article_dialog_are_you_sure_message, article.getTitle()))
                .setCancelable(false)
                .setPositiveButton(from.getString(R.string.general_positive_button), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        listener.deleteArticle(id);
                    }
                })
                .setNegativeButton(from.getString(R.string.general_negative_button), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
