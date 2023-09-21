package src.com.java;

import java.util.Random;
public class Room {
    protected Enemy[] enemies;
    protected Chest chest;
    protected String description;
    protected String type;
    protected int enemiesRemaining;
    protected boolean hasChests;
    protected boolean hasRested;
    protected boolean searched;

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
        this.hasRested = false;
        this.searched = false;
    }

    public Room(String description) {
        this.enemiesRemaining = 0;
        this.type = "start";
        this.description = description;
        this.hasChests = false;
        this.searched = false;
        this.hasRested = false;
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


    public boolean checkForChests() {
        return this.hasChests;
    }

    public Chest getChest() {
        return this.chest;
    }

    /**
     * Should be called whenver an action is made within a generic room type.
     * Decides whether the player encounters an enemy.
     * @param plr
     */
    public boolean playerDecideEncounter(Player plr) {
        if(this.enemiesRemaining > 0 && !this.type.equals("boss") && !this.type.equals("start")) {
            Random random = new Random();
            int decider = random.nextInt(100) + 1;
            //25% to encounter enemy
            if(decider <= 25) {
                Main.startEncounter(Main.createRandomSkeleton(), plr);
                return true;
            } else {
                return false;
            }
        } 
        return false;
    }

    public void useRestRoom(Player plr) {
        if(this.type.equals("rest") && !this.hasRested) {
            System.out.println("\nYou take a seat by the campfire and light it.");
            System.out.println("You begin to feel rejuvinated.");
            plr.setHp(plr.getMaxHP());
            plr.setUsedHeals(0);
            System.out.println("Your HP has been fully restored!");
        } else {
            System.out.println("\nYou have already used up the campfire in this room, making you unable to rest here again.");
        }
    }

    public void searchRoom(Player plr) {
        System.out.println("\nYou begin to search the room..");
        if(this.type == "generic") {
            if(playerDecideEncounter(plr)) {
                System.out.println("\nBecause you were attacked, it seems like you'll have to attempt to search the room again.");
                return;
            }
            if(hasChests) {
                System.out.println("\nYou discovered a " + this.chest.getName() + " in the room!");
                this.searched = true;
                plr.gainXp(25.0);
            } else {
                System.out.println("\nThis room appears to have nothing of interest.");
                this.searched = true;
                plr.gainXp(10.0);
            }
        } else if(this.type.equals("rest")){
            System.out.println("\nYou discovered a campfire in the center of the room!\nIt seems like you might be able to rest here.");
            this.searched = true;
            plr.gainXp(25.0);
        }
    }
    
}
