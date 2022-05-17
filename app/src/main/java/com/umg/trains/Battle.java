package com.umg.trains;

import android.util.Log;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Battle {

    private static Pociag T1;
    private static Pociag T2;

    public Battle(){
        Pociag T1;
        Pociag T2;
        T1=new Pociag(Data.getHP(),Data.getArmor(),Data.getPower(),Data.getDamage(),Data.getAttackspeed());
        Random random=new Random();
        int losuj=random.nextInt(2);
        Data.setTrain(losuj);
        T2=new Pociag(Data.getHP(),Data.getArmor(),Data.getPower(),Data.getDamage(),Data.getAttackspeed());
        this.T1=T1;
        this.T2=T2;
    }
    public Battle(List<HashMap<String,String>> aList,Integer player,Integer bot){
        HashMap<String,String> hmplayer=new HashMap<String,String>();
        HashMap<String,String> hmbot=new HashMap<String,String>();
        hmplayer= aList.get(player);
        hmbot=aList.get(bot);
        T1=new Pociag(hmplayer.get("ListHP"),hmplayer.get("ListArmor"),hmplayer.get("ListPower"),hmplayer.get("ListDamage"),hmplayer.get("ListAttackSpeed"),hmplayer.get("ListImages"));
        T2=new Pociag(hmbot.get("ListHP"),hmbot.get("ListArmor"),hmbot.get("ListPower"),hmbot.get("ListDamage"),hmbot.get("ListAttackSpeed"),hmbot.get("ListImages"));
        String t1hp=String.valueOf(T1.getHp());
    }
    public static void Shoot(){
        int howmuchdmg=T1.getDamage();
        double armor=T2.getArmor();
        howmuchdmg-=howmuchdmg*armor;
        if(howmuchdmg>T2.getHp()){
            T2.setHp(0);
        }
        else{
            T2.setHp(T2.getHp()-howmuchdmg);
        }
    }
    public static void BotShoot(){
        int howmuchdmg=T2.getDamage();
        double armor=T1.getArmor();
        howmuchdmg-=howmuchdmg*armor;
        if(howmuchdmg>T1.getHp()){
            T1.setHp(0);
        }
        else{
            T1.setHp(T1.getHp()-howmuchdmg);
        }
    }
    public static long getDelayAttackSpeed(Pociag p1){
       return (long)p1.getAttackspeed()*1000;
    }
    public boolean IsShootingFirst(){
        return false;
    }
    public static Pociag getT1() {
        return T1;
    }

    public static void setT1(Pociag t1) {
        T1 = t1;
    }

    public static Pociag getT2() {
        return T2;
    }

    public static void setT2(Pociag t2) {
        T2 = t2;
    }


}
