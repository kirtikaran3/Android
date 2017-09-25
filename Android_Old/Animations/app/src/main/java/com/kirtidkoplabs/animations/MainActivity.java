package com.kirtidkoplabs.animations;

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
    public void fade(View view){

        ImageView jokerIn = (ImageView)(findViewById(R.id.joker));

       // ImageView batmanIn = (ImageView)(findViewById(R.id.batman));

        //jokerIn.animate().alpha(0.0f).setDuration(2000);
        jokerIn.animate().translationYBy(2000f);


        //batmanIn.animate().alpha(1f).setDuration(2000);

    }

        public void newChange (View view){

        ImageView batman = (ImageView) (findViewById(R.id.batman));

        //batman.animate().alpha(0.0f).setDuration(2000);

        ImageView jokerIn = (ImageView) (findViewById(R.id.joker));
        batman.animate().rotation(180f);

        //jokerIn.animate().alpha(1.0f);

        //jokerIn.animate().alpha(1f).setDuration(2000);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
