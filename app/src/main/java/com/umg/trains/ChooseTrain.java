package com.umg.trains;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class ChooseTrain extends AppCompatActivity {
    ListView listView;
    String listviewTitle [] = new String[]{
            "Impuls","Koncept","Stadler","Pendolino","ELF","Dart"
    };
    Integer listviewHP [] = new Integer[]{
            190,180,150,130,110,100
    };
    Double listviewArmor [] = new Double[]{
            0.1,0.11,0.13,0.15,0.17,0.2
    };
    Integer listviewPower [] = new Integer[]{
            10,7,14,8,19,20
    };
    Integer listviewDamage [] = new Integer[]{
            20,16,22,24,25
    };
    Double listviewAttackSpeed [] = new Double[]{
            3.0,4.0,2.8,3.7,2.3,2.0
    };
    Integer listviewImages [] = new Integer[]{
        R.drawable.impuls,R.drawable.koncept,R.drawable.stadler,R.drawable.pendolino,R.drawable.elf,R.drawable.dart
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_train);

        ArrayList<HashMap<String,String>> aList= new ArrayList<HashMap<String,String>>();
        for(int i=0;i<5;i++){
            HashMap<String,String> hm=new HashMap<String,String>();
            hm.put("ListTitle",listviewTitle[i]);
            hm.put("ListHP",Integer.toString(listviewHP[i]));
            hm.put("ListArmor",Double.toString(listviewArmor[i]));
            hm.put("ListDamage",Integer.toString(listviewDamage[i]));
            hm.put("ListPower",Integer.toString(listviewPower[i]));
            hm.put("ListAttackSpeed",Double.toString(listviewAttackSpeed[i]));
            hm.put("ListImages",Integer.toString(listviewImages[i]));
            aList.add(hm);
        }

        String[] from ={
            "ListImages","ListTitle","ListHP","ListArmor","ListPower","ListDamage","ListAttackSpeed"
        };
        int[] to={
             R.id.imageView7, R.id.textView22, R.id.textView12, R.id.textView20, R.id.textView18, R.id.textView14, R.id.textView16
        };

        SimpleAdapter sa = new SimpleAdapter(getBaseContext(),aList,R.layout.listtrains,from,to);
        ListView saView= (ListView) findViewById(R.id.listView);
        saView.setAdapter(sa);
        saView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Context context = getApplicationContext();
                TextView text = (TextView) view.findViewById(R.id.textView22);
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text.getText().toString(), duration);
                toast.show();


                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("lista", (Serializable) aList);
                Random r=new Random();
                int bot=r.nextInt(aList.size()-1);
                intent.putExtra("bot",bot);
                intent.putExtra("player",i);
                startActivity(intent);
            }
        });
    }
}