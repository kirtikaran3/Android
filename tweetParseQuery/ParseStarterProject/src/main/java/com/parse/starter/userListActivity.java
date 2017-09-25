package com.parse.starter;

import android.*;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class userListActivity extends AppCompatActivity {

    public void getPhoto(){

        Log.i("Photo Status: "," Getting Photo");
        Toast.makeText(getApplicationContext(),"Getting Photo",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,1);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        Log.i("Permission Status: "," Already Have Permission");
        Toast.makeText(getApplicationContext(),"Already Have",Toast.LENGTH_SHORT).show();
        if(requestCode==1 ){

            if(grantResults[0]== PackageManager.PERMISSION_GRANTED && grantResults.length>0){

                getPhoto();
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.share_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.ShareID){

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){

                    Log.i("Permission Status: ","Waiting for Permission");
                    Toast.makeText(getApplicationContext(),"waiting for permission",Toast.LENGTH_SHORT).show();
                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
                }
                else{

                    Log.i("Permission Status: "," Got Permission");
                    Toast.makeText(getApplicationContext(),"Got Permission",Toast.LENGTH_SHORT).show();
                    getPhoto();

                }
            } else {

                getPhoto();
            }

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1 && resultCode==RESULT_OK && data !=null){

            Uri imageUri = data.getData();

            try {

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),imageUri);

                Log.i("Photo: ","Photo Received");
                Toast.makeText(getApplicationContext(),"Photo Received",Toast.LENGTH_SHORT).show();

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,50,byteArrayOutputStream);

                //Don't use .PNG as it is lossless compression and will not compress image,instead use .JPEG

                byte[] byteArray = byteArrayOutputStream.toByteArray();

                ParseFile parseFile = new ParseFile("image.png",byteArray);

                ParseObject parseObject = new ParseObject("Images");

                parseObject.put("image",parseFile);

                parseObject.put("username",ParseUser.getCurrentUser().getUsername());

                parseObject.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {

                        if(e==null){

                            Log.i("Photo Status: ","Uploaded Successfully");
                            Toast.makeText(getApplicationContext(),"Uploaded Successfully",Toast.LENGTH_SHORT).show();

                        }
                        else{

                            Log.i("Photo Status: ","Failed to Upload");
                            Toast.makeText(getApplicationContext(),"Failed to Upload",Toast.LENGTH_SHORT).show();

                        }

                    }
                });


            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        final ListView userListView = (ListView) findViewById(R.id.userListViewID);

        final ArrayList<String> usernames = new ArrayList<String>();

        final ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,usernames);

        ParseQuery<ParseUser> parseUserQuery = ParseUser.getQuery();

        parseUserQuery.whereNotEqualTo("username",ParseUser.getCurrentUser().getUsername());

        parseUserQuery.addAscendingOrder("username");

        parseUserQuery.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {

                if(e==null){

                    if(objects.size()>0){

                        for(ParseUser user : objects){

                            usernames.add(user.getUsername());

                        }
                        userListView.setAdapter(arrayAdapter);
                        Log.i("UserNames Status: ","UserNames fetched");
                        Toast.makeText(getApplicationContext(),"UserNames fetched",Toast.LENGTH_SHORT).show();

                    }

                }

                else{

                    Log.i("UserNames Status: ","Failed to fetch" + e.toString());
                    Toast.makeText(getApplicationContext(),"Failed to fetch",Toast.LENGTH_SHORT).show();

                }

            }
        });

    }
}
