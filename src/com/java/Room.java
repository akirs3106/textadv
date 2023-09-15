package src.com.java;

import java.util.Random;
public class Room {
    protected Enemy[] enemies;
    protected Chest chest;
    protected String description;
    protected String type;
    protected int enemiesRemaining;
    protected boolean hasChests;

    public Room(Enemy[] enemies, Chest chest, String description, String type) {
        this.enemies = enemies;
        this.chest = chest;
        if(this.chest == null) {
            this.hasChests = false;
        } else {
            this.hasChests = true;
        }
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

    public void playerDecideEncounter(Player plr) {
        if(this.enemiesRemaining >= 0 && this.type != "boss") {
            Random random = new Random();
            int decider = random.nextInt(100) + 1;
            if(decider <= 25) {
                Main.startEncounter(Main.createRandomSkeleton(), plr);
            } else {
                return;
            }
        } 
        return;
    }
    
}
