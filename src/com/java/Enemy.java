package src.com.java;

import java.util.Random;


public class Enemy {

    protected String name;
    protected Weapon weapon;
    protected String race;
    protected int maxHealth;
    protected int currentHealth;
    protected int baseSpeed;
    protected int activeSpeed;
    protected double xpValue;
    protected String type;
    protected boolean hit;
    protected boolean hitPlayer;
    protected int dodgeChance;
    protected double defense;
    protected double defenseReduction;
    protected boolean stunned;
    protected int unstunAttempts;


    public Enemy(String name, Weapon weapon, String race, int maxHealth, int baseSpeed, double xpValue, String type, Player plr) {
        this.name = name;
        this.weapon = weapon;
        this.race = race;
        this.maxHealth = maxHealth;
        this.currentHealth = this.maxHealth;
        this.baseSpeed = baseSpeed;
        this.xpValue = xpValue;
        this.type = type;
        this.hit = false;
        this.defense = 1;
        this.defenseReduction = 0;
        calculateDodgeChance(plr);
        calculateActiveSpeed();
        this.stunned = false;
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
        return this.activeSpeed;
    }

    public int getBaseSpeed() {
        return this.baseSpeed;
    }

    public int getDodgeChance() {
        return this.dodgeChance;
    }

    public String getType() {
        return this.type;
    }

    public void setBaseSpeed(int x) {
        this.baseSpeed = x;
        calculateActiveSpeed();
    }

    public void reduceActiveSpeed(int x) {
        this.activeSpeed -= x;
    }

    public void setDefense(double x) {
        this.defense = x;
    }

    public void reduceDefense(double x) {
        this.defenseReduction = x;
        this.defense -= this.defenseReduction;
        if(this.defense < 0.25) {
            this.defense = 0.25;
        }
    }

    public void resetDefenseReduction() {
        this.defenseReduction = 0;
        this.defense = 1;
    }

    public double getDefense() {
        return this.defense;
    }

    public void setStunned(boolean x) {
        this.stunned = x;
    }

    public boolean getStunned() {
        return this.stunned;
    }

    public boolean attemptUnstun() {
        this.unstunAttempts += 1;
        Random random = new Random();
        int unstunDecider = random.nextInt(100) + 1;
        if(unstunDecider <= (25*unstunAttempts)) {
            this.stunned = false;
            Typer.typeStringln(String.format("%s recovered from being stunned!", this.name));
            this.unstunAttempts = 0;
            return true;
        } else {
            Typer.typeStringln(String.format("%s is still stunned!", this.name));
            return false;
        }

    }

    public void calculateDodgeChance(Player plr) {
        if (plr.getSpeed() >= this.activeSpeed) {
            this.dodgeChance = 0;
        } else {
            int difference = this.activeSpeed-plr.getSpeed();
            this.dodgeChance = (int)(difference/2);
            if(this.dodgeChance > 80) {
                this.dodgeChance = 80;
            }
        }
    }

    public void calculateActiveSpeed() {
        if(baseSpeed - this.weapon.getSpeedPenalty() < 0) {
            this.activeSpeed = 0;
        } else {
            this.activeSpeed = baseSpeed - this.weapon.getSpeedPenalty();
        }
    }

    /**
     * Enemy damages the player.
     * @param plr
     */
    public void attackPlayer(Player plr) {
        Typer.typeStringln(String.format("%s attacked you with its %s!\n", this.name, this.weapon.getName()));

        if(plr.getRetaliation()) {
            plr.setHitInRetaliation(this);
        }

        if(plr.getWeapon().getRiposte()) {
            if(plr.getWeapon().getRiposte()){
                return;
            }
        }
        
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
        if(plr.getWeapon().getRiposte()) {
            if(plr.getWeapon().riposte()) {
                return;
            }
        }
        if(dodgeDecider <= dodgeChance) {
            Typer.typeStringln(String.format("You jumped out of the way of %s's attack!\n", this.name));
            return;
        } else {
            Typer.typeStringln(String.format("You were hit by %s's %s!\n", this.name, this.weapon.getName()));
            if(plr.getHideActive()) {
                plr.setHideActive(false, this);
                Typer.typeStringln("You are no longer hidden!");
                plr.calculateDodgeChance(this);
            }
            this.hitPlayer = true;
            if(plr.getRetaliation()) {
                plr.setHitInRetaliation(this);
            }
            plr.takeDamage(this.weapon.getDmg());
        }
    }

    public void inspect(Player plr) {
        if(plr.getKeenEyedActive()) {
            Typer.typeStringsNoSpace(new String[] {
                String.format("Name: %s", this.name),
                String.format("Health: %s / %s", this.currentHealth, this.maxHealth),
                String.format("Speed: %s", this.baseSpeed),
                String.format("Race: %s", this.race),
                String.format("\nWeapon: %s", this.weapon.getName()),
                String.format("Damage: %s", this.weapon.getDmg()),
                String.format("Speed Reduction: %s", this.weapon.getSpeedPenalty())
            }, 100);
            return;
        }
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
            plr.setKeenEyedActive(false);
            plr.setHideActive(false, this);
            plr.setBattlecry(false);
            plr.setAdrenalineRushActive(false);
            plr.setRetaliation(false);
            plr.setTurnsToSkip(0);
            plr.resetAbilityActiveLength(this);
            plr.gainXp(this.xpValue);
        }
        
    }

    public void takeRawDamage(int dmg, Player plr) {
        this.currentHealth -= dmg;

        Typer.typeStringln(String.format("%s took %s damage!", this.name, dmg));

        if(this.currentHealth > 0) {
            Typer.typeStringln(String.format("%s's remaining HP: %s / %s\n", this.name, this.currentHealth, this.maxHealth));
        } else {
            Typer.typeStringln(String.format("%s has been defeated!", this.name));
            plr.setKeenEyedActive(false);
            plr.setHideActive(false, this);
            plr.setBattlecry(false);
            plr.setAdrenalineRushActive(false);
            plr.setRetaliation(false);
            plr.setTurnsToSkip(0);
            plr.resetAbilityActiveLength(this);
            plr.gainXp(this.xpValue);
        }
    }

    public void takeRawDamage(int dmg, Player plr, boolean crit) {
        if(crit) {
            dmg *= 2;
            Typer.typeStringln("CRITICAL HIT!");
        }
        this.currentHealth -= dmg;

        Typer.typeStringln(String.format("%s took %s damage!", this.name, dmg));

        if(this.currentHealth > 0) {
            Typer.typeStringln(String.format("%s's remaining HP: %s / %s\n", this.name, this.currentHealth, this.maxHealth));
        } else {
            Typer.typeStringln(String.format("%s has been defeated!", this.name));
            plr.setKeenEyedActive(false);
            plr.setHideActive(false, this);
            plr.setBattlecry(false);
            plr.setAdrenalineRushActive(false);
            plr.setRetaliation(false);
            plr.setTurnsToSkip(0);
            plr.resetAbilityActiveLength(this);
            plr.gainXp(this.xpValue);
        }
    }
    
}
