package com.websarva.wings.android.todowithdiary;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import static com.websarva.wings.android.todowithdiary.DatabaseHelper.TABLE_NAME;

public class privateActivity extends AppCompatActivity {


    private TodoAdapter todo;
    private List<Model> list = new ArrayList<Model>();
    private ListView lv_private;
    private int category;
    private TodoListAdapter rowAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private);

        category = getIntent().getIntExtra("category", 0);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //新規登録画面:遷移
        Button bt_newPrivate = findViewById(R.id.bt_newPrivate);

        bt_newPrivate.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(privateActivity.this, registerPrivateActivity.class);
                intent.putExtra("id", "");
                intent.putExtra("category", category);
                //startActivity(intent);
                startActivityForResult(intent, 100);
            }
        });


        todo = new TodoAdapter(this);
        Cursor c = todo.getAllList(category);


        if (c.moveToFirst()) {
            do {
                Model item = new Model();
                item.set_id(c.getInt(c.getColumnIndex("_id")));
                item.setTodo(c.getString(c.getColumnIndex("todo")));
                item.setCategory(c.getInt(c.getColumnIndex("category")));
                list.add(item);
            } while (c.moveToNext());
        }


        //ListView取得
        lv_private = findViewById(R.id.lv_private);

        rowAdapter = new TodoListAdapter(this, 0, list);


        lv_private.setAdapter(rowAdapter);


        //全削除ボタン
        Button all_delete_btn = findViewById(R.id.all_delete_btn);
        all_delete_btn.setOnClickListener(new ListItemClickListener());


        //長押し削除
        lv_private.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                final Model model = (Model) parent.getItemAtPosition(position);

                AlertDialog.Builder builder = new AlertDialog.Builder(privateActivity.this);
                builder.setMessage("削除しますか？").setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        DatabaseHelper helper = new DatabaseHelper(privateActivity.this);
                        SQLiteDatabase db = helper.getWritableDatabase();

                        try {
                            String sqlPartDelete = "DELETE FROM " + TABLE_NAME + " WHERE _id = ?";

                            SQLiteStatement stmt = db.compileStatement(sqlPartDelete);

                            stmt.bindLong(1, model.get_id());

                            stmt.executeUpdateDelete();
                        } finally {
                            db.close();
                        }


                        privateActivity.this.loadPrivateListView();
                        // update ListView
                        privateActivity.this.rowAdapter.notifyDataSetChanged();
                        Toast.makeText(privateActivity.this, "削除しました", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("キャンセル", null).setCancelable(true);
                // show dialog
                builder.show();
                return false;
            }
        });
    }

    private class ListItemClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            DeleteDialogFragment dialogFragment = new DeleteDialogFragment(privateActivity.this);
            dialogFragment.show(getSupportFragmentManager(), "DeleteDialogFragment");

        }
    }

    @Override
    protected void onRestart() {

        Log.d("activity", "onRestart");

        loadPrivateListView();

        // 紐付け
        lv_private = (ListView) findViewById(R.id.lv_private);

        // ArrayAdapterへ設定
        TodoListAdapter rowAdapater = new TodoListAdapter(this, 0, list);

        // リストビューへ設定
        lv_private.setAdapter(rowAdapater);

        super.onRestart();
    }

    private void loadPrivateListView() {
        list.clear();
        Cursor c = todo.getAllList(category);
        if (c.moveToFirst()) {
            do {
                Model item = new Model();
                item.set_id(c.getInt(c.getColumnIndex("_id")));
                item.setTodo(c.getString(c.getColumnIndex("todo")));
                list.add(item);
            } while (c.moveToNext());
        }
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

    @SuppressLint("ValidFragment")
    public static class DeleteDialogFragment extends DialogFragment {
        privateActivity privateActivity;

        public DeleteDialogFragment(privateActivity privateActivity) {
            this.privateActivity = privateActivity;
        }

        public Dialog onCreateDialog(Bundle savedInstanceState) {

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            builder.setTitle(R.string.dialog_title);

            builder.setMessage(R.string.dialog_message);

            builder.setPositiveButton(R.string.dialog_btn_ok, new DeleteDialogFragment.DialogButtonClickListener());

            builder.setNegativeButton(R.string.dialog_btn_ng, new DeleteDialogFragment.DialogButtonClickListener());

            AlertDialog dialog = builder.create();
            return dialog;
        }

        private class DialogButtonClickListener implements DialogInterface.OnClickListener {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                String msg = "";

                //全削除dialog分岐
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        msg = getString(R.string.dialog_ok_toast);
                        privateActivity.list.clear();

                        DatabaseHelper helper = new DatabaseHelper(privateActivity);
                        SQLiteDatabase db = helper.getWritableDatabase();
                        try {
                            String sqlDelete = "DELETE FROM " + TABLE_NAME + " WHERE category=" + privateActivity.category +";";
                            SQLiteStatement stmt = db.compileStatement(sqlDelete);
                            stmt.executeUpdateDelete();
                        } finally {
                            db.close();
                        }
                        privateActivity.loadPrivateListView();
                        privateActivity.rowAdapter.notifyDataSetChanged();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        msg = getString(R.string.dialog_ng_toast);
                }
                privateActivity.loadPrivateListView();
                Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == Activity.RESULT_OK) {
            loadPrivateListView();

        }
    }
}


