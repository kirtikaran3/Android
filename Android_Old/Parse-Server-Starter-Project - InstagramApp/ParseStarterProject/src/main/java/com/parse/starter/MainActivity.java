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
import android.widget.Button;
import android.widget.EditText;
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

import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView changeSign;
    Boolean signStatus=true;

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
            }
        }
    }


    public void signupButton(View view){

        EditText username =  (EditText)findViewById(R.id.editusername);
        EditText password =  (EditText)findViewById(R.id.editpassword);

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

      changeSign  = (TextView) findViewById(R.id.textViewlogIn);
        changeSign.setOnClickListener(this);

    ParseAnalytics.trackAppOpenedInBackground(getIntent());

  }

}




