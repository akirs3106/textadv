package src.com.java;

import java.util.Random;

import src.com.java.Axes.*;
import src.com.java.Daggers.*;
import src.com.java.Swords.*;

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
    protected int baseHealAmount;
    protected double damageReduction;
    protected boolean defensiveStance;
    protected int critChanceCap;
    protected double damageMultiplier;
    protected boolean riposte;
    protected int dodgeChance;
    protected int turnsToSkip;

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
        this.damageReduction = 0;
        this.critChanceCap = 10;
        this.damageMultiplier = 1;
        this.riposte = false;
        this.dodgeChance = 0;
        this.turnsToSkip = 0;

        if(playerClass.toLowerCase().equals("warrior")){
            this.equippedWeapon = new Sword("Rusty Sword", 10, "rusty sword", 20, 0);
            this.baseSpeed = 100;
            this.maxhp = 100;
            this.healAmount = 30;
        } else if (playerClass.toLowerCase().equals("barbarian")) {
            this.equippedWeapon = new Axe("Rusty Axe", 20, "rusty axe", 30, 0);
            this.baseSpeed = 75;
            this.maxhp = 125;
            this.healAmount = 20;
        } else if (playerClass.toLowerCase().equals("rogue")) {
            this.equippedWeapon = new Dagger("Rusty Dagger", 8, "rusty dagger", 0, 0);
            this.baseSpeed = 125;
            this.maxhp = 75;
            this.healAmount = 40;
        }
            calculateActiveSpeed();
            this.currenthp = this.maxhp;
            this.baseHealAmount = this.healAmount;
    }




    public int getHp() {
        return this.currenthp;
    }

    public int getUsedHeals() {
        return this.usedHeals;
    }

    public int getSpeed() {
        return this.activeSpeed;
    }

    public Weapon getWeapon() {
        return this.equippedWeapon;
    }

    public void setWeapon(Weapon x) {
        this.equippedWeapon = x;
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

    public void setDamageReduction(double x) {
        this.damageReduction += x;
        if(this.damageReduction > 1) {
            this.damageReduction = 1;
        }
    }

    public double getDamageReduction() {
        return this.damageReduction;
    }

    public void setDefensiveStance(boolean x) {
        this.defensiveStance = x;
        if(defensiveStance) {
            setDamageReduction(0.75);
        }
    }

    public boolean getDefensiveStance() {
        return this.defensiveStance;
    }

    public void setCritChanceCap(int x) {
        this.critChanceCap = x;
    }

    public void setDamageMultiplier(double x) {
        this.damageMultiplier = x;
        this.equippedWeapon.setDamage((int)(this.equippedWeapon.getDmg()*x));
    }

    public void setRiposte(boolean x) {
        this.riposte = x;
    }

    public boolean getRiposte() {
        return this.riposte;
    }

    private void resetWeaponDamage() {
        this.damageMultiplier = 1;
        this.equippedWeapon.setDamage(this.equippedWeapon.getInitialDamage());
    }

    public int getDodgeChance() {
        return this.dodgeChance;
    }

    public int getTurnsToSkip() {
        return this.turnsToSkip;
    }

    public void setTurnsToSkip(int x) {
        this.turnsToSkip = x;
    }

    /**
     * Prints visual representation of the player's weapon's stats.
     */
    public void inspectWeapon(){
        String str = String.format("\nWeapon: %s \nDamage: %s \nSpeed Reduction: %s\n", this.equippedWeapon.name, this.equippedWeapon.dmg, this.equippedWeapon.speedPenalty);

        Typer.typeString(str);

    }

    /**
     * Prints visual representation of the player's stats.
     */
    public void viewStats(){
        String str = String.format("\nClass: %s \nHealth: %s / %s \nXP: %s / %s \nLevel: %s \nSpeed: %s \nRemaining Heals: %s \nHeal Amount: %s \nRoom: %s\n", this.playerClass, this.currenthp, this.maxhp, this.xp, this.xpRequiredForLevel, this.level, this.activeSpeed, (this.availableHeals - this.usedHeals), this.healAmount, Room.roomNumber);

        Typer.typeString(str);
    }

    
    /**
     * Cosmetic setter for Player.currenthp
     * @param wpn
     */
    public void takeDamage(int dmg) {
        dmg = ((int)((1-this.damageReduction) * dmg));
        this.defensiveStance = false;
        this.damageReduction -= 0.75;
        this.currenthp -= dmg;

        Typer.typeStringln("You took " + dmg + " damage!");
        if(this.currenthp < 0) {
            this.currenthp = 0;
        }
        Typer.typeStringln(String.format("Remaining health: %s / %s", this.currenthp, this.maxhp));
    }
    

    /**
     * Cosmetic setter for Player.xp additionally invokes Player.levelUp() if this.xp is divisible by 100 & level is less than 10
     * @param gainedXp
     */
    public void gainXp(double gainedXp) {
        this.xp += gainedXp;
        Typer.typeStringln("You received " + gainedXp + "XP!\n");
        while(this.xp >= xpRequiredForLevel && this.level < 10) {
            levelUp();
        }
    }

    private void levelUp() {
        this.level += 1;
        this.maxhp += 10;
        this.currenthp = maxhp;
        this.baseSpeed += 5;
        calculateActiveSpeed();
        this.xp -= xpRequiredForLevel;
        this.xpRequiredForLevel += 100;
        this.healAmount += (int)Math.floor((double)(this.baseHealAmount*.2));
        this.usedHeals = 0;
        if(this.level % 3 == 0) {
            this.availableHeals += 1;
            Typer.typeStrings(new String[] {
            "You levelled up to level " + this.level + "!",
            "\nYour max HP has been increased to " + maxhp + "!",
            "You have been fully healed!",
            "Your base speed has been increased to " + baseSpeed + "!",
            "You can now heal yourself for " + this.healAmount + " HP!",
            "Your available heals has increased to " + this.availableHeals + "!",
            "You have regained all of your heals!"
            }, 500);
        } else {
            Typer.typeStrings(new String[] {
            "You levelled up to level " + this.level + "!",
            "\nYour max HP has been increased to " + maxhp + "!",
            "You have been fully healed!",
            "Your base speed has been increased to " + baseSpeed + "!",
            "You can now heal yourself for " + this.healAmount + " HP!",
            "You have regained all of your heals!"
            }, 500);
        }


        

    }

    private void calculateActiveSpeed() {

        this.activeSpeed = this.baseSpeed - this.equippedWeapon.getSpeedPenalty();

    }

    public void calculateDodgeChance(Enemy enemy) {
        int result = 0;
        if(enemy.getSpeed() >= this.activeSpeed) {
            result = 0;
        } else {
            int difference = this.activeSpeed-enemy.getSpeed();
            result = (int)(difference/2);
            if(result > 80) {
                result = 80;
            }
        }
        this.dodgeChance = result;
    }


    /**
     * Cosmetically invokes Enemy.takeDamage()
     * @param enemy
     * @param plr
     */
    public void attackEnemy(Enemy enemy, Player plr) {

        Random random = new Random();
        int critChance = random.nextInt(this.critChanceCap) + 1;
        boolean crit = false;

        Typer.typeStringln(String.format("\nYou attacked %s with your %s!", enemy.getName(), this.equippedWeapon.getName()));
        if(critChance == 1) {
            crit = true;
            Typer.typeStringln("CRITICAL HIT!\n");
        }
        enemy.takeDamage(this.equippedWeapon, plr, crit);
        resetWeaponDamage();
    }

    public void attackEnemy(Enemy enemy, Player plr, int enemyDodgeChance) {
        

        Random random = new Random();

        Typer.typeStringln(String.format("You attack %s with your %s!", enemy.getName(), this.equippedWeapon.getName()));

        int dodgeDecider = random.nextInt(100) + 1;
        Main.wait(500);
        if(dodgeDecider <= enemyDodgeChance) {
            Typer.typeStringln(String.format("%s jumped out of the way of your attack!", enemy.getName()));
            return;
        } else {
            int critChance = random.nextInt(this.critChanceCap) + 1;
            boolean crit = false;
            if(critChance == 1) {
                crit = true;
                Typer.typeStringln("CRITICAL HIT!\n");
            }
            enemy.takeDamage(this.equippedWeapon, plr, crit);
            resetWeaponDamage();
        }
    }

    public void attackEnemy(Enemy enemy, Player plr, int enemyDodgeChance, int dmg) {
        

        Random random = new Random();

        Typer.typeStringln(String.format("You attack %s with your %s!", enemy.getName(), this.equippedWeapon.getName()));

        int dodgeDecider = random.nextInt(100) + 1;
        Main.wait(500);
        if(dodgeDecider <= enemyDodgeChance) {
            Typer.typeStringln(String.format("%s jumped out of the way of your attack!", enemy.getName()));
            return;
        } else {
            enemy.takeRawDamage(dmg, plr);
            resetWeaponDamage();
        }
    }

    public boolean attackEnemyAbility(Enemy enemy, Player plr, int enemyDodgeChance) {
        

        Random random = new Random();

        int dodgeDecider = random.nextInt(100) + 1;
        Main.wait(500);
        if(dodgeDecider <= enemyDodgeChance) {
            Typer.typeStringln(String.format("%s jumped out of the way of your attack!", enemy.getName()));
            return false;
        } else {
            int critChance = random.nextInt(this.critChanceCap) + 1;
            boolean crit = false;
            if(critChance == 1) {
                crit = true;
                Typer.typeStringln("CRITICAL HIT!\n");
            }
            enemy.takeDamage(this.equippedWeapon, plr, crit);
            resetWeaponDamage();
            return true;
        }
    }

    public boolean attackEnemyAbility(Enemy enemy, Player plr, int enemyDodgeChance, int dmg) {
        

        Random random = new Random();

        int dodgeDecider = random.nextInt(100) + 1;
        Main.wait(500);
        if(dodgeDecider <= enemyDodgeChance) {
            Typer.typeStringln(String.format("%s jumped out of the way of your attack!", enemy.getName()));
            return false;
        } else {
            int critChance = random.nextInt(this.critChanceCap) + 1;
            boolean crit = false;
            if(critChance == 1) {
                crit = true;
                Typer.typeStringln("CRITICAL HIT!\n");
            }
            enemy.takeRawDamage(dmg, plr, crit);
            resetWeaponDamage();
            return true;
        }
    }

    public boolean heal() {
        if(usedHeals < availableHeals) {
            this.currenthp += this.healAmount;
            if(this.currenthp > this.maxhp) {
                this.currenthp = this.maxhp;
            }
            usedHeals++;
            Typer.typeStrings(new String[] {
                "\nYou healed for " + this.healAmount + "HP!",
                "Remaining HP: " + this.currenthp + " / " + this.maxhp,
                "You have " + (availableHeals - usedHeals) + " heals remaining!\n"
            }, 500);
            return true;
        } else {
            Typer.typeStringln("\nYou have no heals remaining!");
            return false;
        }
    }
    
}
