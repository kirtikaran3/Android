package com.kirtidkoplabs.downloadimages;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public void GuessIT(View view){

        ImageView rdj =  (ImageView)(findViewById(R.id.rdjImage));
        rdj.animate().alpha(1f);
        Log.i("OUTPUT:","RDJ");


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
