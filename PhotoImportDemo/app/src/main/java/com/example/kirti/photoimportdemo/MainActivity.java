package com.example.kirti.photoimportdemo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.security.Permission;

public class MainActivity extends AppCompatActivity {

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

                if(grantResults[0]==PackageManager.PERMISSION_GRANTED && grantResults.length>0){

                    getPhoto();
                }

            }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1 && resultCode==RESULT_OK && data !=null){

            Uri imageUri = data.getData();

            try {

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),imageUri);
                ImageView imageView = (ImageView)findViewById(R.id.imageViewID);
                imageView.setImageBitmap(bitmap);


            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}


