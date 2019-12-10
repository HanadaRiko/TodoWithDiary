package com.websarva.wings.android.todowithdiary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by hanadariko on 2019/12/10.
 */

public class TodoListAdapter extends ArrayAdapter<Model> {
    private LayoutInflater layoutinflater;

    // コンストラクタ
    public TodoListAdapter(Context context, int textViewResourceId, List<Model> objects){
        super(context, textViewResourceId, objects);
        layoutinflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 指定行のデータを取得
        Model detail = (Model) getItem(position);

        // nullの場合のみ再作成
        if(null == convertView){
            convertView = layoutinflater.inflate(R.layout.row, null);
        }

        // 行のデータを項目へ設定
        TextView text1 = (TextView)convertView.findViewById(R.id.textView);
        text1.setText(String.valueOf(detail.get_id()));

        TextView text2 = (TextView)convertView.findViewById(R.id.textView2);
        text2.setText(detail.getTodo());

        // 返却
        return convertView;
    }

}