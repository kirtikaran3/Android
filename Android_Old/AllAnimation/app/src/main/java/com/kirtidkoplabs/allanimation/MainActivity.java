package com.kirtidkoplabs.allanimation;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public void clickIt(View view){

        ImageView see = (ImageView)(findViewById(R.id.seesaw));

        see.animate().translationXBy(1000f);

        see.animate().rotationBy(1800).setDuration(2000);

        ImageView joker = (ImageView)(findViewById(R.id.joker));
        joker.animate().alpha(1f).setDuration(2000);
        joker.animate().rotationBy(1800).setDuration(2000);
        joker.animate().scaleX(0.5f).scaleY(0.5f).setDuration(2000);
        //joker.animate().scaleX(1f).scaleY(1f).setDuration(2000);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //ImageView see1 = (ImageView)(findViewById(R.id.seesaw));
        //see1.animate().translationX(-1000f);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
