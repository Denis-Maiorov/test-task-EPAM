package model;

import java.util.Date;

public class Pet {
    private String name;
    private double speedMove;
    private double health = 1.0;
    private double hungry = 1.0;
    private long dateSave;
    private long dateDead;
    private boolean isLive = true;
    private long dateBirthDay;

    public long getDateBirthDay() {
        return dateBirthDay;
    }

    public void setDateBirthDay(long dateBirthDay) {
        this.dateBirthDay = dateBirthDay;
    }

    public long getDateDead() {
        return dateDead;
    }

    public void setDateDead(Date dateDead) {
        this.dateDead = dateDead.getTime();
    }

    //проголодаться hunger
    public void play(){
        double hungry = getHungry();
        double health = getHealth();
        if (hungry <= 0){
            hungry = 0;
            if (health < 0){
                //dead
                setIsLive(false);
                setDateDead(new Date());
            }else health -= 0.1;
        }
        hungry -= 0.1;
        setHealth(health);
        setHungry(hungry);
    }

    public void hunger(Pet pet, double negativeValue){
        negativeValue /= 10;
        double hungry = pet.getHungry();
        double health = pet.getHealth();
        if (negativeValue < hungry){
            hungry -= negativeValue;
            negativeValue = 0;
        }else if (negativeValue == hungry){
            hungry = 0;
            negativeValue = 0;
        }else {
            negativeValue -= hungry;
            hungry = 0;
        }
        if (negativeValue < health){
            health -= negativeValue;
        }else {
            health = 0;
            pet.setIsLive(false);
            Date date = new Date();
            pet.setDateDead(date);
        }
        pet.setHealth(health);
        pet.setHungry(hungry);
    }

    public void eating(){
        double hungry = getHungry();
        double health = getHealth();
        if (hungry <= 1){
            hungry += 0.1;
        }
        //чтобы не выходило за рамки
        if (hungry >= 1){
            hungry = 1.0;
            if (health < 1){
                health += 0.1;
            }
        }
        setHungry(hungry);
        setHealth(health);
    }

    public String getName() {
        return name;
    }

    public double getSpeedMove() {
        return speedMove;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getHungry() {
        return hungry;
    }

    public void setHungry(double hungry) {
        this.hungry = hungry;
    }

    public long getDateSave() {
        return dateSave;
    }

    public void setDateSave() {
        Date date = new Date();
        this.dateSave = date.getTime();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpeedMove(double speedMove) {
        this.speedMove = speedMove;
    }

    public void setDateSave(long dateSave) {
        this.dateSave = dateSave;
    }

    public void setDateDead(long dateDead) {
        this.dateDead = dateDead;
    }

    public boolean getIsLive() {
        return isLive;
    }

    public void setIsLive(boolean live) {
        isLive = live;
    }

}
