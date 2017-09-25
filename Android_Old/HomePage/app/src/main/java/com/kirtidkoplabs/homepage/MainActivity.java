package com.kirtidkoplabs.homepage;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void LogIN(View view) {

        EditText name = (EditText) (findViewById(R.id.editName));
        EditText password = (EditText) (findViewById(R.id.editPassword));

        String inName = name.getText().toString();
        String message = password.getText().toString();
        Log.i("Name :", name.getText().toString());
        Log.i("Password :", password.getText().toString());

        if ((inName.equals("Kirti")) && (message.equals("1234"))) {
            view.setVisibility(View.GONE);
            ImageView home = (ImageView) (findViewById(R.id.into));
            home.animate().alpha(1f).setDuration(2000);
            Toast.makeText(getApplicationContext(), "Successfully LogIn", Toast.LENGTH_LONG).show();

        }
        else{
            Toast.makeText(getApplicationContext(), "Incorrect Combination", Toast.LENGTH_LONG).show();
        }
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
