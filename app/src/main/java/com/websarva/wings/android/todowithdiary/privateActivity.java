package com.websarva.wings.android.todowithdiary;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class privateActivity extends AppCompatActivity {


    private TodoAdapter todo;
    private List<Model> list = new ArrayList<Model>();
    private ListView lv_private;

    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //新規登録画面:遷移
        Button bt_newPrivate = findViewById(R.id.bt_newPrivate);
        bt_newPrivate.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(privateActivity.this, registerPrivateActivity.class);
                intent.putExtra("id", "");
                startActivity(intent);
            }
        });


        todo = new TodoAdapter(this);
        Cursor c = todo.getAllList();
        if (c.moveToFirst()) {
            do {
                Model item = new Model();
                item.set_id(c.getInt(c.getColumnIndex("_id")));
                item.setTodo(c.getString(c.getColumnIndex("todo")));
                list.add(item);
            } while (c.moveToNext());
        }


        //ListView取得
        lv_private = findViewById(R.id.lv_private);

        TodoListAdapter rowAdapater = new TodoListAdapter(this, 0, list);

        lv_private.setAdapter(rowAdapater);

    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//
//        // メニューボタンを追加
//        MenuItem item = menu.add("NEW");
//        item.setIcon(android.R.drawable.ic_input_add);
//        // メニューのイベント定義
//        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//
//                // インテントのインスタンス生成
//                Intent intent = new Intent(privateActivity.this, registerPrivateActivity.class);
//                // 次画面のアクティビティ起動
//                startActivity(intent);
//
//                return false;
//            }
//        });
//        // 画面に表示する処理
//        MenuItemCompat.setShowAsAction(item,
//                MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
//
//        return true;
//    }


//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.bt_registerPrivate) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }


    @Override
    protected void onRestart() {

        Log.d("activity", "onRestart");

        list.clear();
        Cursor c = todo.getAllList();
        if (c.moveToFirst()) {
            do {
                Model item = new Model();
                item.set_id(c.getInt(c.getColumnIndex("_id")));
                item.setTodo(c.getString(c.getColumnIndex("todo")));
                list.add(item);
            } while (c.moveToNext());
        }

        // 紐付け
        lv_private = (ListView) findViewById(R.id.lv_private);

        // ArrayAdapterへ設定
        TodoListAdapter rowAdapater = new TodoListAdapter(this, 0, list);

        // リストビューへ設定
        lv_private.setAdapter(rowAdapater);

        super.onRestart();
    }


    //戻る処理
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    //戻るボタン処理
    public void onBackButtonClick(View view) {
        finish();
    }

}


