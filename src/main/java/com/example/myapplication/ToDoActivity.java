package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ToDoActivity extends AppCompatActivity {
    private ArrayAdapter<String> adapter;
    private List<String> list = new ArrayList<>(Arrays.asList("Do one", new String("Do two"), "Do tree"));
    private ArrayList<String> selected = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice,
                list);

        ListView listView = (ListView)findViewById(R.id.toDoList);
        listView.setAdapter(adapter);

        EditText editText = (EditText) findViewById(R.id.insertToDoList);

        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if(keyEvent.getAction() == KeyEvent.ACTION_DOWN){
                    if (keyCode == KeyEvent.KEYCODE_ENTER){
                        // Inserting
                        list.add(0, editText.getText().toString());
                        adapter.notifyDataSetChanged();
//Empty input
                        editText.setText("");
                        closeKeyboard();
                        return true;
                    }
                }

                return false;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = adapter.getItem(position);
                if(listView.isItemChecked(position)){
                    selected.add(item);
                }
                else{
                    selected.remove(item);
                }

            }
        });


    }
    private void closeKeyboard()
    {
        // this will give us the view
        // which is currently focus
        // in this layout
        View view = this.getCurrentFocus();

        // if nothing is currently
        // focus then this will protect
        // the app from crash
        if (view != null) {

            // now assign the system
            // service to InputMethodManager
            InputMethodManager manager
                    = (InputMethodManager)
                    getSystemService(
                            Context.INPUT_METHOD_SERVICE);
            manager
                    .hideSoftInputFromWindow(
                            view.getWindowToken(), 0);
        }
    }

    public void remove(View view){

        int i =0;
        for(String item : selected){
            adapter.remove(selected.get(i));
            i++;
        }
        ListView listView = (ListView)findViewById(R.id.toDoList);
        listView.clearChoices();

        selected.clear();
        adapter.notifyDataSetChanged();
    }

}