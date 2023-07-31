package com.semye.android.module.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase

/**
 * Created by yesheng on 2015/4/27.
 */
object DatabaseOperator {

    private lateinit var mDatabaseHelper: DatabaseHelper

    fun init(context: Context) {
        mDatabaseHelper = DatabaseHelper(context)
    }

    // 数据库对象
    private var mSqLiteDatabase: SQLiteDatabase? = null

    val writableDatabase: SQLiteDatabase
        get() {
            if (mSqLiteDatabase == null) {
                mSqLiteDatabase = mDatabaseHelper.writableDatabase
            }
            return mSqLiteDatabase!!
        }

    val readableDatabase: SQLiteDatabase
        get() {
            if (mSqLiteDatabase == null) {
                mSqLiteDatabase = mDatabaseHelper.readableDatabase
            }
            return mSqLiteDatabase!!
        }

    /**
     * 打开数据库，返回数据库对象
     */
    fun openDatabase() {
        try {
            if (mSqLiteDatabase == null || mSqLiteDatabase?.isOpen != true) {
                mSqLiteDatabase = mDatabaseHelper.writableDatabase
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 关闭数据库
     */
    fun closeDatabase() {
        if (mSqLiteDatabase?.isOpen == true) {
            mSqLiteDatabase?.close()
            mSqLiteDatabase = null
        }
    }

    /**
     * 判断数据库是否关闭
     *
     * @return
     */
    fun ifOpen(): Boolean {
        var flag = true
        if (mSqLiteDatabase == null || mSqLiteDatabase?.isOpen != true) {
            flag = false
        }
        return flag
    }

    /**
     * 执行自定义的SQL语句
     *
     * @param strSql 要执行的SQL语句
     */
    fun executeSQL(strSql: String) {
        mSqLiteDatabase?.execSQL(strSql)
    }
}