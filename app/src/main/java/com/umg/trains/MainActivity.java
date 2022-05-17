package com.umg.trains;

import static com.umg.trains.R.color.purple_500;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    boolean czygraczwyszedl;
    Button shootingbutton;
    Button restart;
    Button start;
    ProgressBar hp1;
    ProgressBar hp2;
    ProgressBar reload;
    TextView wygrales;
    ImageView train1;
    ImageView train2;
    TextView hpvalue1;
    TextView hpvalue2;

    private DBHandler dbHandler;

    static Integer player;
    static Integer bot;
    static ArrayList<HashMap<String,String>> aList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        aList= (ArrayList<HashMap<String, String>>) intent.getSerializableExtra("lista");
        player=intent.getIntExtra("player",0);
        bot=intent.getIntExtra("bot",1);
        HashMap<String,String> hmplayer=new HashMap<String,String>();
        czygraczwyszedl=false;
        setContentView(R.layout.activity_main);
        train1=findViewById(R.id.imageView);
        War();
    }

    public void War(){
        restart=findViewById(R.id.button2);
        wygrales=findViewById(R.id.textView2);
        //Battle
        Battle battle=new Battle(aList,player,bot);
        hp1=findViewById(R.id.progressBar3);
        hp2=findViewById(R.id.progressBar2);
        train1=findViewById(R.id.imageView);
        train2=findViewById(R.id.imageView2);
        train1.setImageResource(Battle.getT1().getImage());
        train2.setImageResource(Battle.getT2().getImage());
        hpvalue1=findViewById(R.id.textView10);
        hpvalue2=findViewById(R.id.textView8);
        hp1.setMax(Battle.getT1().getHp());
        hp2.setMax(Battle.getT2().getHp());
        hp1.setProgress(Battle.getT1().getHp());
        hp2.setProgress(Battle.getT2().getHp());
        shootingbutton=findViewById(R.id.button);
        hp1.setVisibility(View.VISIBLE);
        hp2.setVisibility(View.VISIBLE);
        hp1.setProgressTintList(ColorStateList.valueOf(Color.BLUE));
        hp2.setProgressTintList(ColorStateList.valueOf(Color.BLUE));
        reload=findViewById(R.id.progressBar);
        reload.setVisibility(View.VISIBLE);
        reload.setProgress(100);
        shootingbutton.setVisibility(View.VISIBLE);
        train1.setVisibility(View.VISIBLE);
        train2.setVisibility(View.VISIBLE);
        hpvalue1.setText(String.valueOf(Battle.getT1().getHp()));
        hpvalue2.setText(String.valueOf(Battle.getT2().getHp()));
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                AI();
            }
        }, 2000);
       shootingbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Battle.Shoot();
                if(!Wyniki()){
                    hp2.setProgress(Battle.getT2().getHp());
                    hpvalue2.setText(String.valueOf(Battle.getT2().getHp()));
                    hp2.setProgressTintList(ColorStateList.valueOf(Color.RED));
                    shootingbutton.setEnabled(false);
                    reload.setProgress(0);
                    Reload();
                }
            }
        });
        //Restart
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wygrales.setVisibility(View.INVISIBLE);
                restart.setVisibility(View.INVISIBLE);
                Intent intent=new Intent(getApplicationContext(),ChooseTrain.class);
                startActivity(intent);
            }
        });
    }
    public void AI(){
        Random r=new Random();
        int wylosowaneopoznienie=r.nextInt(2000);
        Handler handler = new Handler();
        try {
            Thread.sleep(wylosowaneopoznienie);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Battle.BotShoot();
        if(!Wyniki()){
            hp1.setProgress(Battle.getT1().getHp());
            hpvalue1.setText(String.valueOf(Battle.getT1().getHp()));
            hp1.setProgressTintList(ColorStateList.valueOf(Color.RED));
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hp1.setProgressTintList(ColorStateList.valueOf(Color.BLUE));
                }
            }, Battle.getDelayAttackSpeed(Battle.getT2())/2);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    AI();
                }
            }, Battle.getDelayAttackSpeed(Battle.getT2()));
        }

    }
    public boolean Wyniki(){
        if(Battle.getT2().getHp()==0) {
            hp2.setProgress(Battle.getT2().getHp());
            hpvalue2.setText(String.valueOf(Battle.getT2().getHp()));
            hp2.setProgressTintList(ColorStateList.valueOf(Color.BLUE));
            hp1.setProgressTintList(ColorStateList.valueOf(Color.BLUE));
            shootingbutton.setVisibility(View.INVISIBLE);
            wygrales.setText("Wygrałeś!");
            wygrales.setTextColor(ColorStateList.valueOf(Color.GREEN));
            wygrales.setVisibility(View.VISIBLE);
            restart.setVisibility(View.VISIBLE);

            dbHandler = new DBHandler(MainActivity.this);
            dbHandler.addNewBattle(String.valueOf(Battle.getT1().getHp()),"Player");
            return true;
        }
        else if(Battle.getT1().getHp()==0){
            //Przegrana
            hp1.setProgress(Battle.getT1().getHp());
            hpvalue1.setText(String.valueOf(Battle.getT1().getHp()));
            hp2.setProgressTintList(ColorStateList.valueOf(Color.BLUE));
            hp1.setProgressTintList(ColorStateList.valueOf(Color.BLUE));
            shootingbutton.setVisibility(View.INVISIBLE);
            wygrales.setText("Przegrales.");
            wygrales.setTextColor(ColorStateList.valueOf(Color.RED));
            wygrales.setVisibility(View.VISIBLE);
            restart.setVisibility(View.VISIBLE);

            dbHandler = new DBHandler(MainActivity.this);
            dbHandler.addNewBattle(String.valueOf(Battle.getT2().getHp()),"Bot");
            return true;
        }
        return false;
    }
    public void Reload(){
        if(reload.getProgress()==100){
            shootingbutton.setEnabled(true);
        }
        else{
            if(reload.getProgress()==50){
                hp2.setProgressTintList(ColorStateList.valueOf(Color.BLUE));
            }
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    reload.setProgress(reload.getProgress()+25);
                    Reload();
                }
            }, Battle.getDelayAttackSpeed(Battle.getT1())/4);
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        czygraczwyszedl=true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(czygraczwyszedl){
            Intent intent=new Intent(this,MainActivity2.class);
            startActivity(intent);
        }
    }
}