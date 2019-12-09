package com.websarva.wings.android.todowithdiary;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.UUID;

public class registerPrivateActivity extends AppCompatActivity {
    //保存ボタンフィールド
    Button bt_registerPrivate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_private);

        Intent intent = this.getIntent();

        //戻るボタン処理
        Button backButton = (Button) findViewById(R.id.bt_onBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //保存せず一覧へ
                finish();
            }
        });

        //保存ボタン取得
        bt_registerPrivate = (Button) findViewById(R.id.bt_registerPrivate);
        //保存処理
        bt_registerPrivate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //入力内容取得
                EditText etNote = (EditText) findViewById(R.id.registerPrivateTodo);
                String note = etNote.getText().toString();


                DatabaseHelper helper = new DatabaseHelper(registerPrivateActivity.this);
                SQLiteDatabase db = helper.getWritableDatabase();

                try {
                    //インサート文字列
                    String sqlInsert = "INSERT INTO db (todo) VALUES (?)";

                    SQLiteStatement stmt;

                    stmt = db.compileStatement(sqlInsert);
                    stmt.bindString(1, note);

                    stmt.executeInsert();
                } finally {
                    db.close();
                    finish();
                }



                etNote.setText("");

                //保存後戻る処理
                Intent intent = new Intent(registerPrivateActivity.this, privateActivity.class);
                startActivity(intent);
            }
        });

    }



}
