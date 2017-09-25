package com.kirtidkoplabs.audio;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import static com.kirtidkoplabs.audio.R.id.seekBar;
import static com.kirtidkoplabs.audio.R.id.toolbar;

public class MainActivity extends AppCompatActivity {

    AudioManager manager;
    MediaPlayer mediaplay;
    public void PlayIt(View view){


        mediaplay.start();
    }

    public void PauseIt (View view){

        //MediaPlayer mplay = MediaPlayer.create(this,R.raw.audio);
        mediaplay.pause();

    }
    public void StopIt(View view){


        mediaplay.stop();
        create();


    }
    public void create(){

        mediaplay = MediaPlayer.create(this,R.raw.audio);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        manager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVol = manager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currVol = manager.getStreamVolume(AudioManager.STREAM_MUSIC);

        mediaplay = MediaPlayer.create(this,R.raw.audio);
        SeekBar seek = (SeekBar) (findViewById(seekBar));
        seek.setMax(maxVol);
        seek.setProgress(currVol);


        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                Log.i("Progress :", Integer.toString(progress));
                manager.setStreamVolume(manager.STREAM_MUSIC,progress,0);

                if (progress>10){

                    Toast.makeText(getApplicationContext(),"Hazardious Level",Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        }

        );

        final SeekBar scrub = (SeekBar)(findViewById(R.id.Scrubber));
        scrub.setMax(mediaplay.getDuration());
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
            }

        } ,0,1000);

                scrub.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                                mediaplay.seekTo(progress);

                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });

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
