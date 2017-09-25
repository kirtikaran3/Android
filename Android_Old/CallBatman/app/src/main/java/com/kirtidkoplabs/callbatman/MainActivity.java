package com.kirtidkoplabs.callbatman;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CountDownTimer count;
    SeekBar seek;
    TextView text;
    MediaPlayer mplay;
    boolean staus=false;
    Button button;
    public void update(int secondsLeft){

        Log.i("Timer","update loop");
        int minutes = (int) secondsLeft/60;
        int seconds =  secondsLeft-minutes *60;
        text = (TextView)(findViewById(R.id.textView2));
        text.setText(Integer.toString(minutes)+":"+Integer.toString(seconds));
    }

    public void callBatman(View view){

        if(staus==false) {
            staus = true;
            seek.setEnabled(false);
            button.setText("Stop Him");
            Log.i("Status: ", "Start Timer");

           count = new CountDownTimer(seek.getProgress() * 1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                    Log.i("Timer", "CountDownLoop");
                    update((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {

                    ImageView image = (ImageView)(findViewById(R.id.Batman));
                    image.animate().alpha(1f).setDuration(3000);

                    Toast.makeText(getApplicationContext(), "Batman is Coming", Toast.LENGTH_LONG).show();
                    Log.i("Status: ", "Batman is Coming");
                    mplay.start();


                }
            }.start();
        }
        else {

            text.setText("0:30");
            seek.setProgress(30);
            count.cancel();
            button.setText("CALL BATMAN");
            seek.setEnabled(true);
            staus=false;
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

         seek =  (SeekBar)(findViewById(R.id.seekBar));
        text = (TextView)(findViewById(R.id.textView2));
       // text.setText(Integer.toString(minutes)+":"+Integer.toString(seconds));
        button = (Button)(findViewById(R.id.button));
        seek.setMax(6000);
        seek.setProgress(30);

        mplay = MediaPlayer.create(this,R.raw.batman);

        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                Log.i("Timer","on create");
                    update(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

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
