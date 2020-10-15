package controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Model;
import java.io.*;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Scanner;

import static estimation.HandlerSaveLoad.*;
import static estimation.Tools.*;
import static estimation.DateLogic.*;

public class Controller implements Initializable {
    private static int crashEgg = 0;
    public Model pet = new Model();
    public Button buttonBirth;
    public Button button222;
    public Label labelFx;
    public Label label2Fx;
    public Label labelNameFx;
    public ProgressBar progressBarHealth;
    public ProgressBar progressBarHungry;
    public MenuItem racoonIdChange;
    public MenuItem lynxIdChange;
    public ImageView image;
    public Image imageRacoon;
    public Image imageLynx;
    public Image imageEgg;
    public String countPet = "Racoon";


    public void saveAndExit(){
        initializeButtonSave();
        System.exit(0);
    }

    public void buttonBirth(){
        crashEgg++;
        if (crashEgg == 5){
            changeToRacoon();
            crashEgg = 0;
            buttonBirth.setDisable(true);
        }
    }

    public void createNewRacoon(){
        long l = resultDeadDate(pet.getPetInMap("Racoon").getDateDead());
        //если больше 1 минуты, то создаст нового
        if (l >= 1){
            buttonBirth.setDisable(false);
            labelNameFx.setText("Racoon");
            image.setImage(imageEgg);
            countPet = "Racoon";
            progressBarHungry.setProgress(1.0);
            progressBarHealth.setProgress(1.0);
            synchronizationData(progressBarHealth.getProgress(),progressBarHungry.getProgress());
            pet.getPetInMap(countPet).setIsLive(true);
            pet.getPetInMap(countPet).setHungry(1.0);
            pet.getPetInMap(countPet).setHealth(1.0);
            Date date = new Date();
            pet.getPetInMap(countPet).setDateBirthDay(date.getTime());
            initializeButtonSave();
        }
    }
    public void createNewLynx(){
        long l = resultDeadDate(pet.getPetInMap("Lynx").getDateDead());
        if (l >= 1){
            buttonBirth.setDisable(false);
            image.setImage(imageEgg);
            countPet = "Lynx";
            labelNameFx.setText(pet.getPetInMap(countPet).getName());
            progressBarHungry.setProgress(1.0);
            progressBarHealth.setProgress(1.0);
            synchronizationData(progressBarHealth.getProgress(),progressBarHungry.getProgress());
            pet.getPetInMap(countPet).setIsLive(true);
            pet.getPetInMap(countPet).setHungry(1.0);
            pet.getPetInMap(countPet).setHealth(1.0);
            Date date = new Date();
            pet.getPetInMap(countPet).setDateBirthDay(date.getTime());
            initializeButtonSave();
        }
    }

    public void changeToRacoon(){
        initializeButtonSave();
        labelNameFx.setText(pet.getPetInMap("Racoon").getName());
        countPet = "Racoon";
        image.setImage(imageRacoon);
        image.setFitHeight(image.getFitHeight() + resultBirthDate(pet.getPetInMap(countPet).getDateBirthDay()));
        image.setFitWidth(image.getFitWidth() + resultBirthDate(pet.getPetInMap(countPet).getDateBirthDay()));
        initializeButtonLoad();
    }

    public void changeToLynx(){
        initializeButtonSave();
        labelNameFx.setText(pet.getPetInMap("Lynx").getName());
        countPet = "Lynx";
        image.setImage(imageLynx);
        initializeButtonLoad();
        synchronizationLoadDate((double) resultDate(pet.getPetInMap(countPet).getDateSave()));
    }
        //обработка кнопки сохранить, так же используется при смене питомца
    public void initializeButtonSave(){
        pet.getPetInMap(countPet).setHungry(progressBarHungry.getProgress());//нужно для рождения
        pet.getPetInMap(countPet).setHealth(progressBarHealth.getProgress());//
        pet.getPetInMap(countPet).setDateSave();
        savePet(pet.getPetInMap(countPet));
    }
        //загрузка по кнопке, загрузка реализована при запуске, используется при смене питомцев
    public void initializeButtonLoad(){
        pet.setPetInMap(loadPet(pet.getPetInMap(countPet)));
        synchronizationData(pet.getPetInMap(countPet).getHealth(), pet.getPetInMap(countPet).getHungry());
    }
        //кормим питомца нажатием кнопки
    public void initializeButtonEating(){
        if (pet.getPetInMap(countPet).getIsLive()){
            pet.getPetInMap(countPet).eating();
            synchronizationData(pet.getPetInMap(countPet).getHealth(), pet.getPetInMap(countPet).getHungry());
        }
    }
        //иммитация голода нажатием кнопки(Play)
    public void initializeButtonPlay(){
        if (pet.getPetInMap(countPet).getIsLive()){
            pet.getPetInMap(countPet).play();
            synchronizationData(pet.getPetInMap(countPet).getHealth(), pet.getPetInMap(countPet).getHungry());
        }
    }

    public void synchronizationLoadDate(double negativeValue){
        pet.getPetInMap(countPet).hunger(pet.getPetInMap(countPet), negativeValue);
        synchronizationData(pet.getPetInMap(countPet).getHealth(), pet.getPetInMap(countPet).getHungry());
    }
        //раскидывает данные по барам, текстовым полям и в экземпляр
    public void synchronizationData(double health, double hungry){
        progressBarHealth.setProgress(areaProgressBar(round(health, 1)));
        progressBarHungry.setProgress(areaProgressBar(round(hungry, 1)));
        labelFx.setText(String.valueOf(progressBarHealth.getProgress()));
        label2Fx.setText(String.valueOf(progressBarHungry.getProgress()));
    }
        //двигаем питомца WASD
    public void handleOnKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.W){
            image.setLayoutY(image.getLayoutY() - pet.getPetInMap(countPet).getSpeedMove());
        }
        if (event.getCode() == KeyCode.S){
            image.setLayoutY(image.getLayoutY() + pet.getPetInMap(countPet).getSpeedMove());
        }
        if (event.getCode() == KeyCode.A){
            image.setLayoutX(image.getLayoutX() - pet.getPetInMap(countPet).getSpeedMove());
        }
        if (event.getCode() == KeyCode.D){
            image.setLayoutX(image.getLayoutX() + pet.getPetInMap(countPet).getSpeedMove());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pet.createPets();
        File file = new File(countPet + ".txt");
        try (Scanner scanner = new Scanner(file)){
            pet.getPetInMap(countPet).setName(scanner.nextLine());
            pet.getPetInMap(countPet).setHealth(Double.parseDouble(scanner.nextLine()));
            pet.getPetInMap(countPet).setHungry(Double.parseDouble(scanner.nextLine()));
            pet.getPetInMap(countPet).setSpeedMove(Double.parseDouble(scanner.nextLine()));
            pet.getPetInMap(countPet).setDateDead(Long.parseLong(scanner.nextLine()));
            pet.getPetInMap(countPet).setDateSave(Long.parseLong(scanner.nextLine()));
            pet.getPetInMap(countPet).setIsLive(Boolean.parseBoolean(scanner.nextLine()));
            pet.getPetInMap(countPet).setDateBirthDay(Long.parseLong(scanner.nextLine()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
            //высчитываем время отсутствия
        synchronizationLoadDate((double) resultDate(pet.getPetInMap(countPet).getDateSave()));
    }
}