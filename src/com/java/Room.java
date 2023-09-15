package src.com.java;

import java.util.Random;
public class Room {
    Enemy[] enemies;
    Chest chest;
    String description;
    String type;
    int enemiesRemaining;

    public Room(Enemy[] enemies, Chest chest, String description, String type) {
        this.enemies = enemies;
        this.chest = chest;
        this.description = description;
        this.type = type;
        this.enemiesRemaining = enemies.length;
    }

    public Room(Enemy[] enemies, Chest chest, String description) {
        this.enemies = enemies;
        this.chest = chest;
        this.description = description;
        
        String[] roomTypes = {"generic" , "rest"};
        Random random = new Random();
        int decider = random.nextInt(100) + 1;

        if(decider > 70) {
            this.type = roomTypes[1];
        } else {
            this.type = roomTypes[0];
        }

    }
    
}
