package com.flutter.hybrid.androidinterview.task;

import android.os.AsyncTask;

public class AynckTaskDemo {

    public void simple() {
        AsyncTask asyncTask = new AsyncTask<String,Integer,String>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(String... strings) {
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
            }
        };
        asyncTask.execute("parameter");
    }

}
