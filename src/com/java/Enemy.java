package src.com.java;

import java.util.Random;


public class Enemy {

    protected String name;
    protected Weapon weapon;
    protected String race;
    protected int maxHealth;
    protected int currentHealth;
    protected int speed;
    protected double xpValue;
    protected String type;
    protected boolean hit;
    protected boolean hitPlayer;
    protected int dodgeChance;


    public Enemy(String name, Weapon weapon, String race, int maxHealth, int speed, double xpValue, String type, Player plr) {
        this.name = name;
        this.weapon = weapon;
        this.race = race;
        this.maxHealth = maxHealth;
        this.currentHealth = this.maxHealth;
        if(speed - this.weapon.getSpeedPenalty() < 0) {
            this.speed = 0;
        } else {
            this.speed = speed - this.weapon.getSpeedPenalty();
        }
        this.xpValue = xpValue;
        this.type = type;
        this.hit = false;
        calculateDodgeChance(plr);
    }

    public Weapon getWeapon() {
        return this.weapon;
    }
    public String getName() {
        return this.name;
    }

    public int getHp() {
        return this.currentHealth;
    }

    public int getSpeed() {
        return this.speed;
    }

    public int getDodgeChance() {
        return this.dodgeChance;
    }

    private void calculateDodgeChance(Player plr) {
        if (plr.getSpeed() >= this.speed) {
            this.dodgeChance = 0;
        } else {
            int difference = this.speed-plr.getSpeed();
            this.dodgeChance = (int)(difference/2);
            if(this.dodgeChance > 80) {
                this.dodgeChance = 80;
            }
        }
    }

    /**
     * Enemy damages the player.
     * @param plr
     */
    public void attackPlayer(Player plr) {
        Typer.typeStringln(String.format("%s attacked you with its %s!\n", this.name, this.weapon.getName()));

        plr.takeDamage(this.weapon.getDmg());
        this.hitPlayer = true;
    }

    /**
     * Enemy attempts to damage the player, taking into account the player's dodge chance.
     * @param plr
     * @param dodgeChance
     */
    public void attackPlayer(Player plr, int dodgeChance) {

        Typer.typeStringln(String.format("%s attacks you with its %s!\n", this.name, this.weapon.getName()));
        Random random = new Random();
        int dodgeDecider = random.nextInt(100) + 1;
        Main.wait(500);
        if(dodgeDecider <= dodgeChance) {
            Typer.typeStringln(String.format("You jumped out of the way of %s's attack!\n", this.name));
            return;
        } else {
            Typer.typeStringln(String.format("You were hit by %s's %s!\n", this.name, this.weapon.getName()));
            this.hitPlayer = true;
            plr.takeDamage(this.weapon.getDmg());
        }
    }

    public void inspect() {
        if(this.hit) {
            Typer.typeStringsNoSpace(new String[] {
                String.format("Name: %s", this.name),
                String.format("Health: %s / %s", this.currentHealth, this.maxHealth),
                "Speed: ???",
                String.format("Race: %s", this.race)
            }, 100);
        } else {
            Typer.typeStringsNoSpace(new String[] {
                String.format("Name: %s", this.name),
                "Health: ??? / ???",
                "Speed: ???",
                String.format("Race: %s", this.race)
            }, 100);
        }
        if(this.hitPlayer) {
            Typer.typeStringsNoSpace(new String[] {
                String.format("\nWeapon: %s", this.weapon.getName()),
                String.format("Damage: %s", this.weapon.getDmg()),
                "Speed Reduction: ???"
            }, 100);
        } else {
            Typer.typeStringsNoSpace(new String[] {
                String.format("\nWeapon: %s", this.weapon.getName()),
                "Damage: ???",
                "Speed Reduction: ???"
            }, 100);
        }
    }

    /**
     * Cosmetic setter for Enemy.currentHealth
     * @param wpn
     * @param plr
     */
    public void takeDamage(Weapon wpn, Player plr, boolean crit) {

        if(crit) {
            this.currentHealth -= wpn.getDmg()*2;
            Typer.typeStringln(String.format("%s took %s damage!", this.name, wpn.getDmg()*2));
        } else {
            this.currentHealth -= wpn.getDmg();
            Typer.typeStringln(String.format("%s took %s damage!", this.name, wpn.getDmg()));
        }
        this.hit = true;
        

        if(this.currentHealth > 0) {
            Typer.typeStringln(String.format("%s's remaining HP: %s / %s\n", this.name, this.currentHealth, this.maxHealth));
        } else {
            Typer.typeStringln(String.format("%s has been defeated!", this.name));
            plr.gainXp(this.xpValue);
        }
        
    }
    
}
