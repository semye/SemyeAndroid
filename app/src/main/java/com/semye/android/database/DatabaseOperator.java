package com.semye.android.database;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by yesheng on 2015/4/27.
 */
public class DatabaseOperator {

    public static DatabaseOperator instance = null;

    // 数据库对象
    private SQLiteDatabase mSqLiteDatabase = null;

    // 由SQLiteOpenHelper继承过来
    private static DatabaseHelper mDatabaseHelper = null;


    private DatabaseOperator() {
    }

    public static DatabaseOperator getInstance() {
        if (instance == null) {
            instance = new DatabaseOperator();
        }
        return instance;
    }

    public SQLiteDatabase getWritableDatabase() {
        if (mSqLiteDatabase == null) {
            mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();
        }
        return mSqLiteDatabase;
    }

    public SQLiteDatabase getReadableDatabase() {
        if (mSqLiteDatabase == null) {
            mSqLiteDatabase = mDatabaseHelper.getReadableDatabase();
        }
        return mSqLiteDatabase;
    }


    /**
     * 打开数据库，返回数据库对象
     */
    public void openDatabase() {
        try {
            if (mSqLiteDatabase == null || !mSqLiteDatabase.isOpen()) {
//                mDatabaseHelper = DatabaseHelper.getInstance(BaseApplication.getInstance().getApplicationContext());
                mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭数据库
     */
    public void closeDatabase() {
        if (mSqLiteDatabase.isOpen()) {
            mSqLiteDatabase.close();
            mSqLiteDatabase = null;
            mDatabaseHelper.close();
            mDatabaseHelper = null;

        }
    }

    /**
     * 判断数据库是否关闭
     *
     * @return
     */
    public boolean ifOpen() {
        boolean flag = true;
        if (mSqLiteDatabase == null || !mSqLiteDatabase.isOpen()) {
            flag = false;
        }
        return flag;
    }

    /**
     * 执行自定义的SQL语句
     *
     * @param strSql 要执行的SQL语句
     */
    public void executeSQL(String strSql) {
        if (mSqLiteDatabase != null) {
            mSqLiteDatabase.execSQL(strSql);
        }
    }


}
