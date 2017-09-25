package com.kirtidkoplabs.connectgame;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int activePlayers = 0;
    boolean gameOn = true;
    int[] game={2,2,2,2,2,2,2,2,2};
    int [][] winningPos = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void dropIn(View view) {


        ImageView counter = (ImageView) view;

        // System.out.println("Tag is : "+ counter.getTag().toString());
        int tapped = Integer.parseInt(counter.getTag().toString());


        if (game[tapped] == 2 && gameOn) {
            game[tapped] = activePlayers;
            counter.setTranslationY(-1000f);
            if (activePlayers == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayers = 1;

            } else if (activePlayers == 1) {
                counter.setImageResource(R.drawable.red);
                activePlayers = 0;

            }

            counter.animate().translationY(0f).rotationBy(3600).setDuration(1000);
            for (int[] winningPosition : winningPos) {

                if (game[winningPosition[0]] == game[winningPosition[1]] && game[winningPosition[1]] == game[winningPosition[2]] &&
                        game[winningPosition[0]] != 2) {

                    gameOn = false;
                    String winner = "RED";

                    if ((game[winningPosition[0]]) == 0) {

                        winner = "    YELLOW  ";
                    }

                    LinearLayout layout = (LinearLayout) (findViewById(R.id.playAgain));

                    layout.setVisibility(View.VISIBLE);

                    TextView winnerMsg = (TextView) (findViewById(R.id.message));
                    winnerMsg.setText(winner + " Has WON ");

                }

            }
        }
    }

        public void Again(View view){

        gameOn = true;
        LinearLayout layout = (LinearLayout) (findViewById(R.id.playAgain));

        layout.setVisibility(View.INVISIBLE);
            activePlayers = 0;

            for(int i=0;i<game.length;i++) {
                game[i] = 2;
            }

            GridLayout gridLayout = (GridLayout)(findViewById(R.id.gridLayout));

            for(int i=0;i<gridLayout.getChildCount();i++){
                ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
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
