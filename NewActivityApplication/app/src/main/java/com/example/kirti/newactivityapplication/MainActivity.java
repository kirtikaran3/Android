package com.example.kirti.newactivityapplication;

import android.content.Intent;
import android.renderscript.Int2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void redirectNew(View view){

        Intent intent = new Intent(getApplicationContext(),NewMapsActivity.class);
        startActivity(intent);

    }

}
