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
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {

    Boolean check = true;
    TextView changeViewClick;
    ParseUser parseUser = new ParseUser();
    EditText username;
    EditText password;

    public void MainPageSwitch(){

        Intent intent = new Intent(this,MainPage.class);
        startActivity(intent);

    }


    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.changeView){

            Button signupButton = (Button) findViewById(R.id.button2);

            if(check){

                check = false;
                signupButton.setText("Login");
                changeViewClick.setText("Or, SignUP");

            }

            else{

                check = true;
                signupButton.setText("SignUP");
                changeViewClick.setText("Or, Login");

            }

        } else if ( v.getId()==R.id.constraintLayoutID || v.getId()==R.id.imageView){


            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);

        }


    }

  public void signUP(View view){


        if(check){

             username = (EditText)findViewById(R.id.editText);
             password = (EditText)findViewById(R.id.editPassword);

            if(username.getText().toString() !="" && password.getText().toString()!=""){

                parseUser.setUsername(username.getText().toString());
                parseUser.setPassword(password.getText().toString());


                parseUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {

                        if(e==null){

                            Log.i("SignUP Status : ","SignUP Successful");
                            Toast.makeText(getApplicationContext(),"SignUP successful",Toast.LENGTH_SHORT).show();
                            MainPageSwitch();

                        }
                        else{

                            Log.i("SignUP Status : ","SignUP failed" + e.toString());
                            Toast.makeText(getApplicationContext(),"SignUP failed" + e.toString(),Toast.LENGTH_SHORT).show();

                        }

                    }
                });

            }
            else{

                Log.i("SignUP Status : ","Fill Info.");
                Toast.makeText(getApplicationContext(),"Please Fill Info.",Toast.LENGTH_SHORT).show();

            }
        }
        else{

            ParseUser.logInInBackground(username.getText().toString(), password.getText().toString(), new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {

                    if(e==null){

                        Log.i("Login Status : "," Successful");
                        Toast.makeText(getApplicationContext()," Successful",Toast.LENGTH_SHORT).show();
                        MainPageSwitch();

                    }
                    else{

                        Log.i("Login Status : "," Failed to Log" + e.toString());
                        Toast.makeText(getApplicationContext()," Failed to log",Toast.LENGTH_SHORT).show();

                    }

                }
            });

        }


  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

      changeViewClick = (TextView)findViewById(R.id.changeView);
      changeViewClick.setOnClickListener(this);

      password = (EditText)findViewById(R.id.editPassword);

      ConstraintLayout constraintLayout = (ConstraintLayout)findViewById(R.id.constraintLayoutID);
      ImageView imageView = (ImageView)findViewById(R.id.imageView);

      Toast.makeText(getApplicationContext(), "Click on create", Toast.LENGTH_SHORT).show();

      constraintLayout.setOnClickListener(this);
      imageView.setOnClickListener(this);

      password.setOnKeyListener(this);

      if(ParseUser.getCurrentUser() != null){


      }

    ParseAnalytics.trackAppOpenedInBackground(getIntent());
  }


    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {

        if(keyCode == event.KEYCODE_ENTER && event.getAction() == event.ACTION_DOWN){

            Toast.makeText(getApplicationContext(),"Keycode clicked",Toast.LENGTH_SHORT).show();
            signUP(v);

        }

        return false;
    }
}