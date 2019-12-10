package com.websarva.wings.android.todowithdiary;

/**
 * Created by hanadariko on 2019/12/10.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 */
public class TodoAdapter {

    private SQLiteDatabase db;
    static private DatabaseHelper helper;

    // コンストラクタ
    public TodoAdapter(Context context){
        helper = new DatabaseHelper(context);
        db = helper.getWritableDatabase();
    }

    // リストを取得
    public Cursor getAllList(){
        return db.query(DatabaseHelper.TABLE_NAME, null, null, null, null, null, null);
    }

    // 追加
    public void insert(String memo){
        ContentValues values = new ContentValues();
        values.put("memo", memo);
        db.insertOrThrow(DatabaseHelper.TABLE_NAME, null, values);
    }

}