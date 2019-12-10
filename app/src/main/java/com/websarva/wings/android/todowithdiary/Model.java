package com.websarva.wings.android.todowithdiary;

/**
 * Created by hanadariko on 2019/12/10.
 */

public class Model {
    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    private  int _id;
    private String todo;
}
