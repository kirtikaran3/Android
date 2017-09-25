package com.kirtidkoplabs.sqltest;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

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

        try {

            SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("User",MODE_PRIVATE,null);
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS users(name VARCHAR,age INT(3))");

            SQLiteDatabase newDatabase =  this.openOrCreateDatabase("User",MODE_PRIVATE,null);
            newDatabase.execSQL("CREATE TABLE IF NOT EXISTS users(name,age INT(3))");


            /*sqLiteDatabase.execSQL("INSERT INTO users(name,age)VALUES('KIRTI',25)");
            sqLiteDatabase.execSQL("INSERT INTO users(name,age)VALUES('SACHIN',40)");
            sqLiteDatabase.execSQL("INSERT INTO users(name,age)VALUES('RAHUL',42)");
            sqLiteDatabase.execSQL("INSERT INTO users(name,age)VALUES('KARAN',20)");
            sqLiteDatabase.execSQL("INSERT INTO users(name,age)VALUES('ROBERT',22)");
            sqLiteDatabase.execSQL("INSERT INTO users(name,age)VALUES('JENNIFER',25)");
            sqLiteDatabase.execSQL("INSERT INTO users(name,age)VALUES('METHEW',42)");*/

            /*Search for all the entry
            //Cursor c =  sqLiteDatabase.rawQuery("SELECT * FROM users",null);

            //Search for particular query
            //Cursor c =  sqLiteDatabase.rawQuery("SELECT * FROM users WHERE age < 30",null);

            //Search for query for more than one parameter
            //Cursor c =  sqLiteDatabase.rawQuery("SELECT * FROM users WHERE age < 30 AND name = 'ROBERT' ",null);

            //Search for string with i and i% for start with
            //Cursor c =  sqLiteDatabase.rawQuery("SELECT * FROM users WHERE name LIKE '%i%'",null);

            //Search for query but only for a result
            Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM users WHERE name LIKE '%i%' LIMIT 1",null);*/

            //sqLiteDatabase.execSQL("DELETE FROM users WHERE name='KIRTI'");
            sqLiteDatabase.execSQL("UPDATE users SET age=2 WHERE name ='ROBERT'");
            Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM users",null);

            int nameIndex =  c.getColumnIndex("name");
            int ageIndex =  c.getColumnIndex("age");

            c.moveToNext();

            while( c != null){

                Log.i("Name",c.getString(nameIndex));
                Log.i("Age",Integer.toString(c.getInt(ageIndex)));

                c.moveToNext();

            }


        }
        catch (Exception e){

            e.printStackTrace();

        }

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
