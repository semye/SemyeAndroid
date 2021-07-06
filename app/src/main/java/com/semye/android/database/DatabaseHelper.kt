package com.semye.android.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

/**
 * Created by yesheng on 2021/7/6.
 */
class DatabaseHelper private constructor(context: Context) :
    SQLiteOpenHelper(context, DB_NAME, null, versions) {

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        val module_table =
            "CREATE TABLE MODULE (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,moduleName TEXT )"
        val article_table =
            "CREATE TABLE ARTICLE (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,moduleId INTEGER NOT NULL,title TEXT,content TEXT,tag TEXT)"
        sqLiteDatabase.execSQL(module_table)
        sqLiteDatabase.execSQL(article_table)
        Log.d(TAG, "CREATE DATABASE SUCCESS!")
        val data1 = "INSERT INTO MODULE (moduleName) VALUES ('JAVA基础')"
        val data2 = "INSERT INTO MODULE (moduleName) VALUES ('数据库')"
        val data3 = "INSERT INTO MODULE (moduleName) VALUES ('Android基础')"
        val data4 = "INSERT INTO MODULE (moduleName) VALUES ('JAVAWEB基础')"
        val data5 = "INSERT INTO MODULE (moduleName) VALUES ('面试技巧')"
        val data6 = "INSERT INTO MODULE (moduleName) VALUES ('设计模式')"
        val data7 = "INSERT INTO MODULE (moduleName) VALUES ('IDE')"
        val data8 = "INSERT INTO MODULE (moduleName) VALUES ('HTML+CSS')"
        sqLiteDatabase.beginTransaction()
        sqLiteDatabase.execSQL(data1)
        sqLiteDatabase.execSQL(data2)
        sqLiteDatabase.execSQL(data3)
        sqLiteDatabase.execSQL(data4)
        sqLiteDatabase.execSQL(data5)
        sqLiteDatabase.execSQL(data6)
        sqLiteDatabase.execSQL(data7)
        sqLiteDatabase.execSQL(data8)
        sqLiteDatabase.setTransactionSuccessful()
        sqLiteDatabase.endTransaction()
        Log.d(TAG, "INSERT DATA SUCCESS!")
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}

    companion object {
        private const val DB_NAME = "semye.db"
        private const val versions = 1
        private const val TAG = "DataBaseHelper"


        private var instance: DatabaseHelper? = null
        fun getInstance(context: Context): DatabaseHelper? {
            if (instance == null) {
                instance = DatabaseHelper(context)
            }
            return instance
        }
    }
}