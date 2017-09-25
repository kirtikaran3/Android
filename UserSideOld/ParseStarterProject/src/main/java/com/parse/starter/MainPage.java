package com.parse.starter;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainPage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mainPage();
    }



    public void mainPage(){

        final ArrayList<String> members = new ArrayList<String>();

        ListView listView = (ListView)findViewById(R.id.listViewID);
        members.add("Offer 1");
        members.add("Offer 2");
        members.add("Offer 3");

        members.add("Ads 1");
        members.add("Ads 2");

        members.add("Reviews 1");
        members.add("Reviews 2");

        members.add("Recommendation 1");
        members.add("Recommendation 2");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,members);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Log.i("Member No: ",members.get(i));

                if(members.get(i)=="Offer 1"){
                    Log.i("Status : " ,"Redirecting it");
                    Toast.makeText(getApplicationContext(),"Redirect it to Offer 1",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),NewsFeedActivity.class);
                    startActivity(intent);

                }

                else if(members.get(i)=="Offer 2"){
                    Log.i("Status : " ,"Redirecting it");
                    Toast.makeText(getApplicationContext(),"Redirect it to offer 2",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),NewsFeedActivity.class);
                    startActivity(intent);

                }

                else if(members.get(i)=="Offer 3"){
                    Log.i("Status : " ,"Redirecting it");
                    Toast.makeText(getApplicationContext(),"Redirect it to offer 3",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),NewsFeedActivity.class);
                    startActivity(intent);

                }

                else if(members.get(i)=="Ads 1"){
                    Log.i("Status : " ,"Redirecting it");
                    Toast.makeText(getApplicationContext(),"Redirect it to Ads 1",Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(),NewsFeedActivity.class);
                    startActivity(intent);

                }
                else if(members.get(i)=="Ads 2"){
                    Log.i("Status : " ,"Redirecting it");
                    Toast.makeText(getApplicationContext(),"Redirect it to Ads 2",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),NewsFeedActivity.class);
                    startActivity(intent);

                }
                else if(members.get(i)=="Reviews 1"){
                    Log.i("Status : " ,"Redirecting it");
                    Toast.makeText(getApplicationContext(),"Redirect it to Review 1",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),NewsFeedActivity.class);
                    startActivity(intent);

                }
                else if(members.get(i)=="Reviews 2"){
                    Log.i("Status : " ,"Redirecting it");
                    Toast.makeText(getApplicationContext(),"Redirect it to Reviews 2",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),NewsFeedActivity.class);
                    startActivity(intent);

                }

                else if(members.get(i)=="Recommendation 1"){
                    Log.i("Status : " ,"Redirecting it");
                    Toast.makeText(getApplicationContext(),"Redirect it to Recommendation 1",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),NewsFeedActivity.class);
                    startActivity(intent);

                }
                else if(members.get(i)=="Recommendation 2"){
                    Log.i("Status : " ,"Redirecting it");
                    Toast.makeText(getApplicationContext(),"Redirect it to Recommendation 2",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),NewsFeedActivity.class);
                    startActivity(intent);

                }


            }
        });

    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_page, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
