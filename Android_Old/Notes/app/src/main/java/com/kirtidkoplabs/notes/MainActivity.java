package com.kirtidkoplabs.notes;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    static ArrayList<String> notes =  new ArrayList<>();
    static ArrayAdapter arrayAdapter;
    static Set<String> set;
    static int noteID;
    String s;

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

        ListView listView =  (ListView)findViewById(R.id.listId);
        notes.add("Example Notes");
        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,notes);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {



            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {

                Intent i = new Intent(getApplicationContext(),EditNotes.class);
                i.putExtra("NoteID",position);
                startActivity(i);
                arrayAdapter.notifyDataSetChanged();

                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Are you sure")
                        .setMessage("Do You Want to delete it")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                notes.remove(position);
                            }
                            SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences("com.kirtidkoplabs.notes", Context.MODE_PRIVATE);


                        })

                        .setNegativeButton("No",null)
                        .show();

                return true;
            }
        });
        SharedPreferences sharedPreferences = this.getSharedPreferences("com.kirtidkoplabs.notes", Context.MODE_PRIVATE);

        set = sharedPreferences.getStringSet("Notes",null);

              if(set != null){

                    notes.clear();
                    notes.addAll(set);

                }
                else {


                    set.add("Notes to be Added");
                    set =  new HashSet<>();
                    set.addAll(notes);
                    //sharedPreferences.edit().putStringSet("Notes",set).apply();

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
        if (id == R.id.add) {
            notes.add("");
            return true;
        }
        notes.set(noteID,String.valueOf(s));
        MainActivity.arrayAdapter.notifyDataSetChanged();
        set.clear();
        set.addAll(notes);

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.kirtidkoplabs.notes", Context.MODE_PRIVATE);

        set = sharedPreferences.getStringSet("Notes",null);

        if(MainActivity.set != null){

            notes.clear();
            notes.addAll(set);

        }
        else {

            notes.clear();
            MainActivity.set.add("Notes to be Added");
            set =  new HashSet<String>();
            set.addAll(notes);
            sharedPreferences.edit().putStringSet("Notes",MainActivity.set).apply();

        }
        Intent i  = new Intent(getApplicationContext(),EditNotes.class);
        i.putExtra("NotesID",notes.size()-1);
        startActivity(i);

        return super.onOptionsItemSelected(item);
    }
}
