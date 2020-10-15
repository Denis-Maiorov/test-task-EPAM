package model;

import java.util.*;

public class Model {
    public Pet racoon = new Racoon();
    public Pet lynx = new Lynx();
    public Map<String, Pet> map = new HashMap<String,Pet>();

    public void createPets(){
        this.map.put("Lynx", lynx);
        this.map.put("Racoon", racoon);
    }

    public Pet getPetInMap(String namePet){
        if (namePet.equals("Lynx")){
            return map.get("Lynx");
        }else return map.get("Racoon");
    }

    public void setPetInMap(Pet pet){
        map.put(pet.getName(), pet);
    }
}
