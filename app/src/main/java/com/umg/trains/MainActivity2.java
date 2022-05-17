package com.umg.trains;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Service;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class MainActivity2 extends AppCompatActivity {
    Button choosetrain;
    Button wybierzzdjecie;
    Button service;
    Button viewpager;
    Button pliki;
    Button lokalizacja;
    ImageView zdjecieprofilowe;
    ActivityResultLauncher activityResultLauncher;

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;

    public MainActivity2() throws IOException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("hej","hej");
        setContentView(R.layout.activity_welcome);
        choosetrain=findViewById(R.id.button4);
        zdjecieprofilowe=findViewById(R.id.imageView6);
        service=findViewById(R.id.button3);
        pliki=findViewById(R.id.button7);
        pliki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),PlikiActivity.class);
                startActivity(intent);
            }
        });
        viewpager=findViewById(R.id.button5);
        viewpager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),ViewPagerActivity.class);
                startActivity(intent);
            }
        });
        lokalizacja=findViewById(R.id.button10);
        lokalizacja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Location.class);
                startActivity(intent);
            }
        });
        service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(new Intent(getApplicationContext(),LocalService.class));
            }
        });
        choosetrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),ChooseTrain.class);
                startActivity(intent);
            }
        });
        wybierzzdjecie=findViewById(R.id.button6);
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Bundle bundle = result.getData().getExtras();
                    Bitmap bitmap = (Bitmap) bundle.get("data");
                    zdjecieprofilowe.setImageBitmap(bitmap);
                }
            }
        });
        wybierzzdjecie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    activityResultLauncher.launch(intent);
                } else {
                    Toast.makeText(MainActivity2.this, "Nie masz aplikacji dla Aparatu.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        navigationView =findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent=new Intent(getApplicationContext(),ViewPagerActivity.class);
                startActivity(intent);
                return true;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("bye","bye");
    }




}
