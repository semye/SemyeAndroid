package com.semye.android.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by yesheng on 2015/4/27.
 */
public class DatabaseHelper extends SQLiteOpenHelper {


    private static final String TAG = "DataBaseHelper";

    private static final String DB_NAME = "semye.db";

    private static DatabaseHelper instance = null;

    private static int versions = 1;

    private DatabaseHelper(Context context) {
        super(context, DB_NAME, null, versions);
    }


    public static DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String module_table = "CREATE TABLE MODULE (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,moduleName TEXT )";
        String article_table = "CREATE TABLE ARTICLE (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,moduleId INTEGER NOT NULL,title TEXT,content TEXT,tag TEXT)";
        sqLiteDatabase.execSQL(module_table);
        sqLiteDatabase.execSQL(article_table);
        Log.d(TAG, "CREATE DATABASE SUCCESS!");
        String data1 = "INSERT INTO MODULE (moduleName) VALUES ('JAVA基础')";
        String data2 = "INSERT INTO MODULE (moduleName) VALUES ('数据库')";
        String data3 = "INSERT INTO MODULE (moduleName) VALUES ('Android基础')";
        String data4 = "INSERT INTO MODULE (moduleName) VALUES ('JAVAWEB基础')";
        String data5 = "INSERT INTO MODULE (moduleName) VALUES ('面试技巧')";
        String data6 = "INSERT INTO MODULE (moduleName) VALUES ('设计模式')";
        String data7 = "INSERT INTO MODULE (moduleName) VALUES ('IDE')";
        String data8 = "INSERT INTO MODULE (moduleName) VALUES ('HTML+CSS')";

        sqLiteDatabase.beginTransaction();
        sqLiteDatabase.execSQL(data1);
        sqLiteDatabase.execSQL(data2);
        sqLiteDatabase.execSQL(data3);
        sqLiteDatabase.execSQL(data4);
        sqLiteDatabase.execSQL(data5);
        sqLiteDatabase.execSQL(data6);
        sqLiteDatabase.execSQL(data7);
        sqLiteDatabase.execSQL(data8);
        sqLiteDatabase.setTransactionSuccessful();
        sqLiteDatabase.endTransaction();
        Log.d(TAG, "INSERT DATA SUCCESS!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {


    }
}
