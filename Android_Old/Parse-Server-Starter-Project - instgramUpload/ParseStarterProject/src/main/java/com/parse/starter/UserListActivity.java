package com.parse.starter;

import android.*;
import android.app.DownloadManager;
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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

public class UserListActivity extends AppCompatActivity {


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        //menuInflater.inflate(R.menu.share_menu,menu);
        menuInflater.inflate(R.menu.share_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    public void getPhoto() {

        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, 1);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(grantResults.length>0 && grantResults[0] == getPackageManager().PERMISSION_GRANTED){

            getPhoto();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.share){

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if(checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){

                    requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},1);
                }
                else{

                    getPhoto();
                }
            }
            else{

                getPhoto();
            }


        }else if(item.getItemId()==R.id.Logout){

            ParseUser.logOut();
            Intent i = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {

            Uri selectedImage = data.getData();


            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);

                ByteArrayOutputStream stream =  new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
                byte[] byteArray = stream.toByteArray();
                ParseFile file = new ParseFile("image.png",byteArray);
                ParseObject object =  new ParseObject("Image");
                object.put("image",file);
                object.put("username",ParseUser.getCurrentUser().getUsername());

                object.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {

                        if(e==null){

                            Toast.makeText(UserListActivity.this, "Successfully Uploaded", Toast.LENGTH_SHORT).show();
                        }
                        else {

                            Toast.makeText(UserListActivity.this, "Failed to Upload", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

                //Log.i("Status:" ,"Uploading files");
            }

            catch (IOException e) {
                e.printStackTrace();
            }


        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        setTitle("User Feed");

        final ListView userlistview =  (ListView) findViewById(R.id.listview);
        final ArrayList<String> usernames =  new ArrayList<String>();

        final ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,usernames);

        userlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(getApplicationContext(),UserFeedActivity.class);
                i.putExtra("username",usernames.get(position));
                startActivity(i);
            }
        });



        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereNotEqualTo("usernames",ParseUser.getCurrentUser().getUsername());
        query.addAscendingOrder("usernames");
        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {

                if(e==null){

                    if(objects.size()>0){

                        for(ParseUser user : objects){

                            usernames.add(user.getUsername());
                        }
                        userlistview.setAdapter(arrayAdapter);

                    }

                }
                else{

                    e.printStackTrace();

                }
            }
        });
    }
}
