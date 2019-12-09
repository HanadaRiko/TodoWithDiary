package com.websarva.wings.android.todowithdiary;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class privateActivity extends AppCompatActivity {


    private ListView lv_private;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        //新規登録画面:遷移
        Button bt_newPrivate = (Button) findViewById(R.id.bt_newPrivate);
        bt_newPrivate.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(privateActivity.this, registerPrivateActivity.class);
                intent.putExtra("id", "");
                startActivity(intent);
            }
        });

        //ListView取得
        lv_private = (ListView) findViewById(R.id.lv_private);

        adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1);

        // ListViewの初期表示
        adapter.add("掃除");
        adapter.add("買い物");

        lv_private.setAdapter(adapter);

        //addStringData();

         //ListViewアイテムを選択した場合の動作
        lv_private.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // 選択したListViewアイテムをトースト表示する
                ListView list = (ListView) parent;
                String selectedItem = (String) list.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), selectedItem,
                        Toast.LENGTH_LONG).show();
            }
        });

    }

//    private void addStringData() {
//        EditText edit = findViewById(R.id.registerPrivateTodo);
//        adapter.add(edit.getText().toString());
//    }


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


