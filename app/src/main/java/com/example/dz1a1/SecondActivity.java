package com.example.dz1a1;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;


public class SecondActivity extends AppCompatActivity {
    public static final String NAME = "NAME";
    public static final String AGE = "AGE";
    EditText username,password;
    private  String name;
    private TextView photo;
    private ImageView image;
    private int age;
    private final int GALEREA = 12;
    private final int CAMERA = 13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        username = findViewById(R.id.name);
        password = findViewById(R.id.password);
        setinitView();
        imageClik();
        photoClik();


        Intent i = getIntent();
        name = i.getStringExtra(NAME);
        age = i.getIntExtra(AGE,0);
        username.setText("" + name);
        password.setText(""+ age);

    }


    private void photoClik() {
        photo.setOnClickListener(v ->{
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent,CAMERA);

        });
    }

    private void imageClik() {
        image.setOnClickListener(v ->{
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent,GALEREA);
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



        if(requestCode == GALEREA && resultCode == RESULT_OK && data != null ){
            Glide.with(this).load(data.getData().toString()).circleCrop().into(image);

        }
        if(requestCode == CAMERA && resultCode == RESULT_OK && data != null ){
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            image.setImageBitmap(imageBitmap);
            Glide.with(image).load(imageBitmap).circleCrop().into(image);
        }
    }


    private void setinitView() {
        image = findViewById(R.id.image);
        photo = findViewById(R.id.edit_photo);
    }


}