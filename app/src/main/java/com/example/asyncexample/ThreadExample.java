package com.example.asyncexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

public class ThreadExample extends AppCompatActivity {

    int nCounter;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            TextView mInfoTextView = (TextView) findViewById(R.id.textViewInfo);
            mInfoTextView.setText("Сегодня ворон было " + nCounter++ + " штук");
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_example);


    }
    public void onClick(View view) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                long endTime = System.currentTimeMillis() + 20 * 1000;
                while(System.currentTimeMillis() < endTime) {
                    synchronized (this) {
                        try {
                            wait(endTime-System.currentTimeMillis());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                // так нельзя
              //  TextView mInfoTextView = (TextView) findViewById(R.id.textViewInfo);
              //  mInfoTextView.setText("Сегодня ворон было " + nCounter++ + " штук");
                handler.sendEmptyMessage(0);
            }

        };
        Thread thread = new Thread(runnable);
        thread.start();

}
}



  /*  new Thread(new Runnable() {
        @Override
        public void run() {
            //do time consuming operations here
        }
    }).start();*/

/*
    private Runnable doInBackgroundProcessing = new Runnable() {
        @Override
        public void run() {
            backgroundThreadProcessing();
        }
    }

    private void backgroundThreadProcessing() {
        //трудоемкие операции
    }

    private void mainProcessing() {
        Thread thread = new Thread(null, doInBackgroundProcessing, "Background");
    }
*/