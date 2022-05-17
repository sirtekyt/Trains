package com.umg.trains;

public class Data {

    public static int train=0;
    public static int impuls=R.drawable.impuls;
    public static int impuls_hp=190;
    public static double impuls_armor=0.1;
    public static int impuls_power=10;
    public static int impuls_damage=20;
    public static double impuls_attackspeed=3;
    public static int koncept=R.drawable.koncept;

    public static int koncept_hp=100;
    public static double koncept_armor=0.2;
    public static int koncept_power=20;
    public static int koncept_damage=25;
    public static double koncept_attackspeed=2;

    public static int getTrain() {
        return train;
    }

    public static void setTrain(int train) {
        Data.train = train;
    }

    public static int whichtrain(){
        if(train==0){
            return impuls;
        }
        else if(train==1){
            return koncept;
        }
        return impuls;
    }
    public static int getHP(){
        if(train==0){
            return impuls_hp;
        }
        else if(train==1){
            return koncept_hp;
        }
        return impuls_hp;
    }
    public static double getArmor(){
        if(train==0){
            return impuls_armor;
        }
        else if(train==1){
            return koncept_armor;
        }
        return impuls_armor;
    }
    public static int getPower(){
        if(train==0){
            return impuls_power;
        }
        else if(train==1){
            return koncept_power;
        }
        return impuls_power;
    }
    public static int getDamage(){
        if(train==0){
            return impuls_damage;
        }
        else if(train==1){
            return koncept_damage;
        }
        return impuls_damage;
    }
    public static double getAttackspeed(){
        if(train==0){
            return impuls_attackspeed;
        }
        else if(train==1){
            return koncept_attackspeed;
        }
        return impuls_attackspeed;
    }
}
