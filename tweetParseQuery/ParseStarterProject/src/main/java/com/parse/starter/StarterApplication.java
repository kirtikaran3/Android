/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;

import android.app.Application;
import android.location.Location;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.util.List;


public class StarterApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();

    // Enable Local Datastore.
    Parse.enableLocalDatastore(this);

    // Add your initialization code here
    Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
            .applicationId("6c8865c26a178869d492e48dacbf156451fb5577")
            .clientKey("d610bc9c1c0343ca6dabe9a263a513bbffe9db6c")
            .server("http://ec2-54-152-18-162.compute-1.amazonaws.com:80/parse/")
            .build()
    );
/*

            //Setting up new ParseObject to cloud

    ParseObject tweet = new ParseObject("Tweet");
      tweet.put("username","Kirti Karan");
      tweet.put("tweet","Hey there");
      tweet.saveInBackground(new SaveCallback() {
          @Override
          public void done(ParseException e) {

              if(e==null){

                  Log.i("Save Status","Successful");

              }
              else{

                  Log.i("Save Status","Failed to save"+e.toString());

              }

          }
      });

        //ParseQuery without any limit

      ParseQuery<ParseObject> query = ParseQuery.getQuery("Tweet");
      query.getInBackground("j3fZQKSBzw", new GetCallback<ParseObject>() {
          @Override
          public void done(ParseObject object, ParseException e) {

              if(e==null && object !=null){

                  object.put("tweet","Updated Tweet");
                  object.saveInBackground();
                  Log.i("Retrieve Status ","Successfully retireved");
                  Log.i("Retrieved UserName ",object.getString("username"));
                  Log.i("Retrieved Tweet ",object.getString("tweet"));

              }
              else{

                  Log.i("Retreive Status","Failed to retrieve data"+e.toString());

              }

          }
      });

            //ParseQuery with query limit

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Tweet");
        query.whereEqualTo("username","Kirti Karan");
        query.setLimit(1);

        query.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                if(e==null){

                    Log.i("Results "," Retrieved " + objects.size()+ " objects");

                    if(objects.size()>0){

                        for(ParseObject object:objects){

                            Log.i("Results ",object.getString("username") + " : "+object.getString("tweet"));

                        }

                    }

                }

            }
        });




                    //SignUP the new user

    ParseUser parseUser = new ParseUser();

      parseUser.setUsername("Kirti_Karan");
      parseUser.setPassword("karankirti");

      parseUser.signUpInBackground(new SignUpCallback() {
          @Override
          public void done(ParseException e) {

              if(e==null){

                  Log.i("SignUP Status: "," Successful");
              }
              else{

                  Log.i("SignUP Status: "," Failed to signup " + e.toString());

              }

          }
      });


*/

    //ParseUser.enableAutomaticUser();

    ParseACL defaultACL = new ParseACL();
    defaultACL.setPublicReadAccess(true);
    defaultACL.setPublicWriteAccess(true);
    ParseACL.setDefaultACL(defaultACL, true);

  }
}





