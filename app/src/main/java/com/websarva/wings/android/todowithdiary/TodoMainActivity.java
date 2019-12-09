package com.websarva.wings.android.todowithdiary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TodoMainActivity extends AppCompatActivity {
    Intent intent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_main);


        //private
        Button btnPrivate = (Button)findViewById(R.id._btPrivate);
        btnPrivate.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                Intent intent = new Intent(TodoMainActivity.this, privateActivity.class);
                startActivity(intent);
            }
        });

        //work
        Button btnWork = (Button)findViewById(R.id._btWork);
        btnWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TodoMainActivity.this, workActivity.class);
                startActivity(intent);
            }
        });

        //diary
        Button btnDiary = (Button)findViewById(R.id._btDiary);
        btnDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TodoMainActivity.this, diaryActivity.class);
                startActivity(intent);
            }
        });
    }


}
