/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Switch;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;


public class MainActivity extends AppCompatActivity {

  public void getStarted(View view){

    Switch switchUser = (Switch) findViewById(R.id.switchUser);
    Log.i("Status",String.valueOf(switchUser.isChecked()));

    String userType = "Rider";

    if(switchUser.isChecked()){

      userType = "Driver";
    }
    else{

      userType =  "Rider";
    }

    ParseUser.getCurrentUser().put("RiderOrDriver",userType);

    Log.i("Status: ","Switching to "+ userType);

  }


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

   //getSupportActionBar().hide();

    setTitle("Uber User");
    if(ParseUser.getCurrentUser()==null){

      ParseAnonymousUtils.logIn(new LogInCallback() {
        @Override
        public void done(ParseUser user, ParseException e) {

          if(e==null){

            Log.i("Status: ","Logged In Successful");
          }

          else{

            Log.i("Status: ","Logged In Failed");
          }
        }
      });

    }
    else{


      if(ParseUser.getCurrentUser().get("RiderOrDriver")!=null){

        Log.i("Status: ","Redirecting As"+ParseUser.getCurrentUser().get("RiderOrDriver"));

      }
    }



    ParseAnalytics.trackAppOpenedInBackground(getIntent());
  }

}




