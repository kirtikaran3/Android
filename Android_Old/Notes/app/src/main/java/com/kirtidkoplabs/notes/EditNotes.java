package com.kirtidkoplabs.notes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import java.util.HashSet;

import static com.kirtidkoplabs.notes.MainActivity.notes;
import static com.kirtidkoplabs.notes.MainActivity.set;

public class EditNotes extends AppCompatActivity implements TextWatcher {

    int noteID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_notes);
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        EditText editText = (EditText)findViewById(R.id.editText);
        Intent i = getIntent();

        noteID = i.getIntExtra("Info",-1);

        if(noteID != -1){

            editText.setText(notes.get(noteID));
            editText.addTextChangedListener(this);

        }


    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        notes.set(noteID,String.valueOf(charSequence));
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
        MainActivity.set.addAll(MainActivity.notes);
        sharedPreferences.edit().remove("notes").apply();
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
