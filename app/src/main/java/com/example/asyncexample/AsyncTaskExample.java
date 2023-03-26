package com.example.asyncexample;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

public class AsyncTaskExample extends AppCompatActivity {

            private TextView mInfoTextView;

            private Button mStartButton;
            private ProgressBar mHorizontalProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_example);

        mInfoTextView = (TextView) findViewById(R.id.textInfo);
        mStartButton = (Button) findViewById(R.id.buttonStart);
        mHorizontalProgressBar = findViewById(R.id.progressBar);

    }

    public void onClick(View v) {
        CurierTask curierTask = new CurierTask();
        curierTask.execute();
    }


    class CurierTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected void onPreExecute() {
            mInfoTextView.setText("Курьер зашел в Ваш дом");
            mStartButton.setVisibility(View.INVISIBLE);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            mInfoTextView.setText("Этаж " + values[0]);
            mHorizontalProgressBar.setProgress(values[0]);

        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                int numberOfFloors = 14;
                int counter = 0;
                for (int i = 0; i < 14; i++) {
                    getFloor(counter);
                    publishProgress(++counter);
                }
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            mInfoTextView.setText("Звонок в дверь. Заберите Вашу пиццу");
            mStartButton.setVisibility(View.VISIBLE);
            mHorizontalProgressBar.setProgress(0);
        }

        private void getFloor(int counter) throws InterruptedException {
            TimeUnit.SECONDS.sleep(1);
        }


    }
}







