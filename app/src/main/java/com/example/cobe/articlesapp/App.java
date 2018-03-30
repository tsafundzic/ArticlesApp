package com.example.cobe.articlesapp;

import android.app.Application;

import com.example.cobe.articlesapp.database.DatabaseHelper;
import com.example.cobe.articlesapp.database.DatabaseInterface;

import io.realm.Realm;

/**
 * Created by cobe on 30/03/2018.
 */

public class App extends Application {

    private static App instance;

    private DatabaseInterface database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Realm.init(this);
        setDataBase();
    }

    private void setDataBase() {
        database = new DatabaseHelper(Realm.getDefaultInstance());
    }

    public static App getInstance() {
        return instance;
    }

    public DatabaseInterface getDatabase() {
        return database;
    }
}
