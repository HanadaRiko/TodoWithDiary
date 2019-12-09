package com.websarva.wings.android.todowithdiary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hanadariko on 2019/12/09.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String  DATABASE_NAME = "db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();

        sb.append("CREATE TABLE db(");
        sb.append("todo TEXT PRIMARY KEY");
        sb.append(");");

        String sql = sb.toString();

        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        /**
         * テーブルを削除する
         */
        db.execSQL("DROP TABLE IF EXISTS db");

        // 新しくテーブルを作成する
        onCreate(db);

    }
}