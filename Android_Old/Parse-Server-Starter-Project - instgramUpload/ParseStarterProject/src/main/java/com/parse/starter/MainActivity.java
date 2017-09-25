/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;
import android.*;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

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
import com.parse.SignUpCallback;

import java.io.IOException;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {

    TextView changeSign;
    Boolean signStatus=true;

    EditText password;


    public void showuserlist(){

        Intent i  = new Intent(getApplicationContext(),UserListActivity.class);
        startActivity(i);
    }


    @Override
    public boolean onKey(View view, int keyCode, KeyEvent event) {

        if(keyCode == event.KEYCODE_ENTER){

            signupButton(view);

        }

        return false;
    }

    @Override
    public void onClick(View view) {

        Button signUpButton = (Button) findViewById(R.id.signup);

        if (view.getId() == R.id.textViewlogIn) {

            if (signStatus) {

                signStatus= false;
                signUpButton.setText("LogIN");
                changeSign.setText("Or, SignUP");
                Log.i("Log Status: ","Signing IN");

            }

            else{

                signStatus = true;
                signUpButton.setText("SignUP");
                changeSign.setText("Or, LogIN");
                Log.i("Log Status: ","Loggin In");
               // showuserlist();
            }

        }
        else if(view.getId()==R.id.relativeLay || view.getId()==R.id.logoView){

            InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }

    }


    public void signupButton(View view){

        EditText username =  (EditText)findViewById(R.id.editusername);

            if(username.getText().toString().matches("") || password.getText().toString().matches("")){

                Toast.makeText(this, "Please Enter UserName and password to SIGNUP", Toast.LENGTH_LONG).show();

            }
            else {
                if (signStatus) {

                    ParseUser user = new ParseUser();
                    user.setUsername(username.getText().toString());
                    user.setPassword(password.getText().toString());

                    user.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {

                            if (e == null) {

                                Toast.makeText(MainActivity.this, "SignUP Successfull Congrats", Toast.LENGTH_SHORT).show();
                                Log.i("Status: ", "Successfully signedUP");
                                showuserlist();
                            } else {

                                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                Log.i("Status: ", "Failed To signUP");
                                Log.i("Error: ", e.getMessage());
                            }
                        }
                    });
                }
                else{

                    ParseUser.logInInBackground(username.getText().toString(), password.getText().toString(), new LogInCallback() {
                        @Override
                        public void done(ParseUser user, ParseException e) {

                            if(user !=null){

                                Log.i("Status: ","Succesfull");
                                Toast.makeText(MainActivity.this, "Successfully LogedIn", Toast.LENGTH_SHORT).show();
                                showuserlist();
                            }
                            else{

                                Log.i("Status: ","Failed to Log");
                                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }
            }



    }



  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

      setTitle("Instagram");

      changeSign  = (TextView) findViewById(R.id.textViewlogIn);
        changeSign.setOnClickListener(this);


      RelativeLayout relativeLayout = (RelativeLayout)findViewById(R.id.relativeLay);
      ImageView logo =  (ImageView)findViewById(R.id.logoView);

      relativeLayout.setOnClickListener(this);
      logo.setOnClickListener(this);


      password =  (EditText)findViewById(R.id.editpassword);
      password.setOnKeyListener(this);

      if(ParseUser.getCurrentUser() != null){

          showuserlist();

      }

    ParseAnalytics.trackAppOpenedInBackground(getIntent());

  }


}




