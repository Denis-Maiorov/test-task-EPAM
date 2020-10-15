package estimation;

import model.Pet;
import java.io.*;
import java.util.Scanner;

public class HandlerSaveLoad {

    public static void savePet(Pet pet){
        try(FileWriter  writer = new FileWriter(pet.getName() + ".txt", false)) {
            writer.write(pet.getName());
            writer.append('\n');
            writer.write(String.valueOf(pet.getHealth()));
            writer.append('\n');
            writer.write(String.valueOf(pet.getHungry()));
            writer.append('\n');
            writer.write(String.valueOf(pet.getSpeedMove()));
            writer.append('\n');
            writer.write(String.valueOf(pet.getDateDead()));
            writer.append('\n');
            writer.write(String.valueOf(pet.getDateSave()));
            writer.append('\n');
            writer.write(String.valueOf(pet.getIsLive()));
            writer.append('\n');
            writer.write(String.valueOf(pet.getDateBirthDay()));
            writer.append('\n');
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("save " + pet.getName() + " completed");
    }

    public static Pet loadPet(Pet pet){
        File file = new File(pet.getName() + ".txt");
        try (Scanner scanner = new Scanner(file)) {
            pet.setName(scanner.nextLine());
            pet.setHealth(Double.parseDouble(scanner.nextLine()));
            pet.setHungry(Double.parseDouble(scanner.nextLine()));
            pet.setSpeedMove(Double.parseDouble(scanner.nextLine()));
            pet.setDateDead(Long.parseLong(scanner.nextLine()));
            pet.setDateSave(Long.parseLong(scanner.nextLine()));
            pet.setIsLive(Boolean.parseBoolean(scanner.nextLine()));
            pet.setDateBirthDay(Long.parseLong(scanner.nextLine()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("load " + pet.getName() + " completed");
        return pet;
    }
}
