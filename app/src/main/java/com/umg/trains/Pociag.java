package com.umg.trains;

public class Pociag implements TrainInterface{
    private int hp;
    private double armor;
    private int power;
    private int damage;
    private double Attackspeed;
    private int image;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }



    public double getAttackspeed() {
        return Attackspeed;
    }

    public void setAttackspeed(double Attackspeed) {
        this.Attackspeed = Attackspeed;
    }


    public Pociag(int hp,double armor,int power,int damage,double Attackspeed){
        this.hp=hp;
        this.armor=armor;
        this.power=power;
        this.damage=damage;
        this.Attackspeed=Attackspeed;
    }
    public Pociag(String hp,String armor,String power,String damage,String Attackspeed,String image){
        this.hp=Integer.parseInt(hp);
        this.armor=Double.parseDouble(armor);
        this.power=Integer.parseInt(power);
        this.damage=Integer.parseInt(damage);
        this.Attackspeed=Double.parseDouble(Attackspeed);
        this.image=Integer.parseInt(image);
    }


    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public double getArmor() {
        return armor;
    }

    public void setArmor(double armor) {
        this.armor = armor;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }


    @Override
    public double GetDefence() {
        return getArmor()*getHp();
    }

    @Override
    public double GetOffence() {
        return getDamage()*getAttackspeed();
    }

}
