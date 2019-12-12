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
    public static final String TABLE_NAME = "db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();

        sb.append("CREATE TABLE " + TABLE_NAME + "( ");
        sb.append("_id INTEGER PRIMARY KEY AUTOINCREMENT,");
        sb.append("category INTEGER NOT NULL,");
        sb.append("todo TEXT NOT NULL");

//        sb.append("todo_Private TEXT ,");
//        sb.append("todo_Work TEXT ,");
//        sb.append("todo_Diary TEXT");
        sb.append(");");

        String sql = sb.toString();

        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        /**
         * テーブルを削除する
         */
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME + ";");

        // 新しくテーブルを作成する
        onCreate(db);

    }
}
