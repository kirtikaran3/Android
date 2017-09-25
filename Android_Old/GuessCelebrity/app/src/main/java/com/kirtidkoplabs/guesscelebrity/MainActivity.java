package com.kirtidkoplabs.guesscelebrity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    public class Downloader extends AsyncTask<String,Void,String> {


        @Override
        protected String doInBackground(String... params) {

            URL url;
            String input ="";

            try {

                url = new URL(params[0]);
                HttpURLConnection connect = (HttpURLConnection) url.openConnection();
                InputStream in = connect.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data =  reader.read();

                while(data!=-1){

                    char current =  (char) data;
                    input += current;
                    data = reader.read();

                }
                return input;

            }
                catch (Exception e) {

                    e.printStackTrace();
                }

            return null;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Downloader download = new Downloader();
        String input = null;

        try {
            input = download.execute("http://www.getImageblahblah.com").get();
        }
        catch (InterruptedException e) {

            e.printStackTrace();

        }
        catch (ExecutionException e) {

            e.printStackTrace();
        }

    }
}
