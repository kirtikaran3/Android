package com.kirtidkoplabs.tweetit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class parse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parse);
    }
}
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
        import android.util.Log;

        import com.parse.Parse;
        import com.parse.ParseACL;
        import com.parse.ParseException;
        import com.parse.ParseObject;
        import com.parse.ParseUser;
        import com.parse.SaveCallback;


public class StarterApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        // Add your initialization code here
        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId("6a130ba0c129f90085ef5b0ef5f8c8702622895a")
                .clientKey("05bf1b0b1d244d350418835ae07698a07df806d8")
                .server("http://ec2-35-163-202-99.us-west-2.compute.amazonaws.com:80/parse/")
                .build()
        );

        ParseUser.enableAutomaticUser();

        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);


    }
}