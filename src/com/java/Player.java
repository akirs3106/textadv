package src.com.java;

import java.util.Random;

public class Player {
    private int maxhp;
    private int currenthp;
    private double xp;
    private int level;
    private int baseSpeed;
    private int xpRequiredForLevel;
    protected int activeSpeed;
    private Weapon equippedWeapon;
    private String playerClass;
    protected int availableHeals;
    protected int usedHeals;
    protected int healAmount;

    /**
     * @param playerClass the player's class (Capitalize properly, used cosmetically)
     */
    public Player(String playerClass) {
        this.xp = 0;
        this.level = 0;
        this.playerClass = playerClass;
        this.xpRequiredForLevel = 100;
        this.availableHeals = 2;
        this.usedHeals = 0;

        if(playerClass.toLowerCase().equals("warrior")){
            this.equippedWeapon = new Sword("Rusty Sword", 10, "rusty sword", 20);
            this.baseSpeed = 100;
            this.maxhp = 100;
            this.healAmount = 30;
        } else if (playerClass.toLowerCase().equals("barbarian")) {
            this.equippedWeapon = new Axe("Rusty Axe", 20, "rusty axe", 30);
            this.baseSpeed = 75;
            this.maxhp = 125;
            this.healAmount = 20;
        } else if (playerClass.toLowerCase().equals("rogue")) {
            this.equippedWeapon = new Dagger("Rusty Dagger", 8, "rusty dagger", 0);
            this.baseSpeed = 125;
            this.maxhp = 75;
            this.healAmount = 40;
        }
            calculateActiveSpeed();
            this.currenthp = this.maxhp;
    }


    public int getHp() {
        return this.currenthp;
    }

    public int getSpeed() {
        return this.activeSpeed;
    }

    public Weapon getWeapon() {
        return this.equippedWeapon;
    }

    public void setWeapon(Weapon weapon) {
        this.equippedWeapon = weapon;
    }

    public int getLevel() {
        return this.level;
    }

    public String getPlayerClass() {
        return this.playerClass;
    }

    public void setUsedHeals(int x) {
        this.usedHeals = x;
    }

    public void setHp(int x) {
        this.currenthp = x;
        if(this.currenthp >= this.maxhp) {
            this.currenthp = this.maxhp;
        }
    }

    public int getMaxHP() {
        return this.maxhp;
    }

    /**
     * Prints visual representation of the player's weapon's stats.
     */
    public void inspectWeapon(){
        String str = String.format("\nWeapon: %s \nDamage: %s \nSpeed Reduction: %s\n", this.equippedWeapon.name, this.equippedWeapon.dmg, this.equippedWeapon.speedPenalty);

        System.out.println(str);

    }

    /**
     * Prints visual representation of the player's stats.
     */
    public void viewStats(){
        String str = String.format("\nClass: %s \nHealth: %s / %s \nXP: %s \nLevel: %s \nSpeed: %s \n", this.playerClass, this.currenthp, this.maxhp, this.xp, this.level, this.activeSpeed);

        System.out.println(str);
    }

    
    /**
     * Cosmetic setter for Player.currenthp
     * @param wpn
     */
    public void takeDamage(int dmg) {
        this.currenthp -= dmg;

        System.out.println("You took " + dmg + " damage!");
        if(this.currenthp < 0) {
            this.currenthp = 0;
        }
        System.out.println(String.format("Remaining health: %s / %s", this.currenthp, this.maxhp));
    }
    

    /**
     * Cosmetic setter for Player.xp additionally invokes Player.levelUp() if this.xp is divisible by 100 & level is less than 10
     * @param gainedXp
     */
    public void gainXp(double gainedXp) {
        this.xp += gainedXp;
        System.out.println("You received " + gainedXp + "XP!\n");
        while(this.xp >= xpRequiredForLevel && this.level < 10) {
            levelUp();
        }
    }

    private void levelUp() {
        this.level++;
        this.maxhp += 10;
        this.currenthp = maxhp;
        this.baseSpeed += 5;
        calculateActiveSpeed();
        this.xp -= xpRequiredForLevel;
        this.xpRequiredForLevel += 100;
        this.healAmount = (int)Math.floor((double)(this.healAmount*1.2));

        System.out.println("You levelled up to level " + this.level + "!");
        System.out.println("\nYour max HP has been increased to " + maxhp + "!");
        System.out.println("You have been fully healed!");
        System.out.println("Your base speed has been increased to " + baseSpeed + "!");
        System.out.println("You can now heal yourself for " + this.healAmount + " HP!");



    }

    private void calculateActiveSpeed() {

        this.activeSpeed = this.baseSpeed - this.equippedWeapon.getSpeedPenalty();

    }


    /**
     * Cosmetically invokes Enemy.takeDamage()
     * @param enemy
     * @param plr
     */
    public void attackEnemy(Enemy enemy, Player plr) {

        Random random = new Random();
        int critChance = random.nextInt((10 - 1) + 1) + 1;
        boolean crit = false;

        System.out.println(String.format("\nYou attacked %s with your %s!", enemy.getName(), this.equippedWeapon.getName()));
        if(critChance == 1) {
            crit = true;
            System.out.println("CRITICAL HIT!\n");
        }
        enemy.takeDamage(this.equippedWeapon, plr, crit);
    }

    public boolean heal() {
        if(usedHeals < availableHeals) {
            this.currenthp += this.healAmount;
            if(this.currenthp > this.maxhp) {
                this.currenthp = this.maxhp;
            }
            usedHeals++;
            System.out.println("\nYou healed for " + this.healAmount + "HP!");
            System.out.println("Remaining HP: " + this.currenthp + " / " + this.maxhp);
            System.out.println("You have " + (availableHeals - usedHeals) + " heals remaining!\n");
            return true;
        } else {
            System.out.println("\nYou have no heals remaining!");
            return false;
        }
    }
    
}
