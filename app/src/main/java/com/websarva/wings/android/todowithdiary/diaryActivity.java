package com.websarva.wings.android.todowithdiary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class diaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
    }

    //戻るボタン処理
    public void onBackButtonClick(View view){
        finish();
    }
}
