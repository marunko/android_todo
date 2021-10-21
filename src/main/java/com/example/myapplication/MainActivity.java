package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);    }

    public void sendMessage(View view){
        Intent intent = new Intent(this, MessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editMessage);

        String message = editText.getText().toString();

        intent.putExtra("message", message);
        startActivity(intent);
    }
    public void goToToDo(View view){
        Intent intent = new Intent(this, ToDoActivity.class);
        startActivity(intent);
    }
}