package com.example.asyncexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ButtonClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.Asynctask:
                intent = new Intent(this, AsyncTaskExample.class);
                startActivity(intent);
                break;
            case R.id.thread:
                intent = new Intent(this, ThreadExample.class);
                startActivity(intent);
                break;
            default: return;
        }
    }

}