package com.kirtidkoplabs.imagedownload;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    ImageView image;

    public void DownloadIT(View view){

        Downloader imgdwn = new Downloader();
        Bitmap mybit;

        try {
            mybit =  imgdwn.execute("https://lh6.ggpht.com/_vxF0kmn9hnjqgrnTdv5b6uOw7bgCAVeVJsUWI_9ZDfk6Zm5ZJ7FmOOlyWAglgwiDg=h1264").get();
            image.setImageBitmap(mybit);
        }
        catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image =  (ImageView)(findViewById(R.id.imageView2));

    }

    public class Downloader extends AsyncTask<String,Void,Bitmap>{


        @Override
        protected Bitmap doInBackground(String... params) {



            try {

                URL url = null;
                url = new URL(params[0]);
                HttpURLConnection connection = null;
                connection = (HttpURLConnection) url.openConnection();
                InputStream inputstream =  connection.getInputStream();
                Bitmap mybit = BitmapFactory.decodeStream(inputstream);
                return mybit;
            }
            catch (MalformedURLException e) {

                e.printStackTrace();
            }

            catch (IOException e) {

                e.printStackTrace();

            }
            return null;
        }


    }
 }
