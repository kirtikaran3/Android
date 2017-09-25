/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Switch;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseUser;


public class MainActivity extends AppCompatActivity {

  public void getStarted(View view) {

      Switch userTypeSwitch = (Switch) findViewById(R.id.userTypeSwitch);

      Log.i("Switch value", String.valueOf(userTypeSwitch.isChecked()));

      String userType = "rider";

      if (userTypeSwitch.isChecked()) {

          userType = "driver";

      }

      ParseUser.getCurrentUser().put("riderOrDriver", userType);

      Log.i("Info", "Redirecting as " + userType);

  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

      getSupportActionBar().hide();

      if (ParseUser.getCurrentUser() == null) {

          ParseAnonymousUtils.logIn(new LogInCallback() {
              @Override
              public void done(ParseUser user, ParseException e) {

                  if (e == null) {

                      Log.i("Info", "Anonymous login successful");

                  } else {

                      Log.i("Info", "Anonymous login failed");

                  }


              }
          });

      } else {

          if (ParseUser.getCurrentUser().get("riderOrDriver") != null) {

              Log.i("Info", "Redirecting as " + ParseUser.getCurrentUser().get("riderOrDriver"));

          }

      }


    ParseAnalytics.trackAppOpenedInBackground(getIntent());
  }

}
