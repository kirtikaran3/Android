package com.kirtidkoplabs.jsondemo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            DownloadTask download = new DownloadTask();

            download.execute("http://api.openweathermap.org/data/2.5/forecast/city?id=524901&APPID=3e43af3e0bd5ad0104299a8e11d17cce");



    }

    public class DownloadTask extends AsyncTask<String,Void,String> {


        @Override
        protected String doInBackground(String... params) {

            String result="";
            URL url;
            HttpURLConnection connect;
            InputStreamReader reader;
            InputStream inURL;
            try {
               url = new URL(params[0]);

                connect =  (HttpURLConnection) url.openConnection();
                inURL = connect.getInputStream();
                 reader = new InputStreamReader(inURL);

                int data = reader.read();

                while( data != -1 ){

                    char current = (char) data;
                    result += current;
                    data = reader.read();

                }

            return result;

            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {

                JSONObject jObj = new JSONObject(result);
                String jWeather = jObj.getString("list");
                Log.i("URL RESULT: ", jWeather);

                JSONArray arr = new JSONArray(jWeather);

                for(int i=0;i<arr.length();i++){

                    JSONObject jsonObj = arr.getJSONObject(i);
                    Log.i("JSONPART:  ",jsonObj.getString("main"));


                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

           //Log.i("URL RESULT: ", result);
        }


   }

}
